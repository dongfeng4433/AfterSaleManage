package com.joindoo.jdwechat.controller;

import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.cache.SessionModel;
import com.joindoo.jdwechat.beans.*;
import com.joindoo.jdwechat.daos.*;
import com.joindoo.jdwechat.codeGen.*;
import com.joindoo.jdwechat.data.DBHelper;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.entity.config.SysProperties;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.ResultListModel;
import com.joindoo.jdwechat.model.query.*;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("TDataEnterpriseOrder")
public class TDataEnterpriseOrderController  extends BaseController{
	 @Autowired
    DruidConfig druidConfig;

    @Autowired
    SysProperties sysProperties;

	@RequestMapping("/page")
	public ModelAndView page(HttpServletRequest servletRequest) {
		ModelAndView modelAndView = new ModelAndView();
		SessionModel sessionModel = ChkLogin(servletRequest);
		if (sessionModel.isLogin() == false){
			modelAndView.setViewName("account/login");
		}else{
			modelAndView.setViewName("order/index");
			modelAndView.setStatus(HttpStatus.OK);
		}
		return modelAndView;
	}
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public ResponseEntity<ResultListModel> search(HttpServletRequest request,PagingOptions pagingOptions, TDataEnterpriseOrderQueryModel queryModel) {
		ResultListModel resultListModel = new ResultListModel();
        SessionModel sessionModel=this.getSessionModel(request);
        DataService dataService = this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);

		//归属企业查询
		fillQueryModel4Crop(dataContext,queryModel,sessionModel);
		queryModel.setenterprise_id(queryModel.getEnterprise_id());
		if(pagingOptions.getPageSize()==0)pagingOptions.setPageSize(20);
		pagingOptions.setNeedTotal(true);
		Collection<TDataEnterpriseOrderDtoModel> dtoModelList=dataService.SelectT_DATA_ENTERPRISE_ORDER(pagingOptions,queryModel,null);
		int start = pagingOptions.getStart();
		resultListModel.setRows(dtoModelList);
        resultListModel.setStart(start);
        resultListModel.setResultCount(dtoModelList.size());
        resultListModel.setTotal(pagingOptions.getTotal());
		dataContext.release();
        dataService.disposeInCurrentThread();
        return new ResponseEntity<>(resultListModel, HttpStatus.OK);
    }
	@RequestMapping(value = "/saveOrder",method = RequestMethod.POST)
	public ResponseEntity<BaseResultModel> saveOrder(TDataEnterpriseOrderDtoModel dtoModel, HttpServletRequest request) {
		BaseResultModel baseResultModel = new BaseResultModel();
		SessionModel sessionModel=this.getSessionModel(request);
		DataService dataService = this.getDataService();
		DataContext dataContext = dataService.getDataContext(druidConfig);
		TDataEnterpriseOrderDao dao = new TDataEnterpriseOrderDao(dataContext);

		String enterprise_id=getEnterpriseId2User(dataContext,sessionModel);
		dtoModel.setenterprise_id(enterprise_id);
		TDataEnterpriseOrderModel model=new TDataEnterpriseOrderModel();
		model.setorder_id(dtoModel.getorder_id());
		boolean exist = dao.exist(model);
		try {
			if(exist){
				model=dao.find(model);
				model.doUpdate();
				model.setorder_no(dtoModel.getorder_no());
				model.setenterprise_id(dtoModel.getenterprise_id());
				model.setname(dtoModel.getname());
				model.setcustomer_id(dtoModel.getcustomer_id());
				model.setaddress(dtoModel.getaddress());
				model.setdescription(dtoModel.getdescription());
				model.setlast_edit_time(new Date());
				model.setlast_edit_user_id(sessionModel.getUserId());
				model.setstart_time(dtoModel.getstart_time());
				model.setcomplete_time(dtoModel.getcomplete_time());
				model.setplan_complete_time(dtoModel.getplan_complete_time());
				model.setwork_user_id(dtoModel.getwork_user_id());
				model.setorder_status(dtoModel.getorder_status());
				model.setgoods_amount(dtoModel.getgoods_amount());
				dao.updateOnSubmit(model);
			}else{
				BeanUtils.copyProperties(dtoModel,model);
				model.setis_valid(1);
				model.setorder_id(Utility.createUniqueId());
				model.setcreate_time(new Date());
				model.setlast_edit_time(new Date());
				model.setcreation_user_id(sessionModel.getUserId());
				model.setlast_edit_user_id(sessionModel.getUserId());
				dao.insertOnSubmit(model);
			}
			baseResultModel.setSuccess(true);
		}catch (Exception err){
			logger.error(err.getMessage());
			baseResultModel.setSuccess(false);
			baseResultModel.setMsg(err.getMessage());
		}

		dataService.disposeInCurrentThread();
		return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
	}
	@RequestMapping(value = "/remove",method = RequestMethod.POST)
	public ResponseEntity<BaseResultModel> remove(HttpServletRequest request, TDataEnterpriseOrderModel model) {
		BaseResultModel baseResultModel = new BaseResultModel();
		if(Utility.isNullOrEmpty(model.getorder_id())){
			baseResultModel.setSuccess(false);
			baseResultModel.setMsg("参数有误");
			return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
		}
			
		SessionModel sessionModel=this.getSessionModel(request);
		DataService dataService = this.getDataService();
		DataContext dataContext = dataService.getDataContext(druidConfig);
		TDataEnterpriseOrderDao dao = new TDataEnterpriseOrderDao(dataContext);
		TDataEnterpriseOrderModel model1= dao.find(model);
		try {
			if(model1!=null){
				model1.doUpdate();
				model1.setis_valid(0);
				model.setlast_edit_time(new Date());
				model1.setlast_edit_user_id(sessionModel.getUserId());
				dao.updateOnSubmit(model1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		baseResultModel.setSuccess(true);
		dataService.disposeInCurrentThread();
		return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/saveOrderDetails",method = RequestMethod.POST)
	public ResponseEntity<BaseResultModel> saveOrderDetails(TDataEnterpriseOrderDtoModel dtoModel, HttpServletRequest request) {
		BaseResultModel baseResultModel = new BaseResultModel();
		SessionModel sessionModel=this.getSessionModel(request);
		DataService dataService = this.getDataService();
		DataContext dataContext = dataService.getDataContext(druidConfig);
		TDataEnterpriseOrderDao dao = new TDataEnterpriseOrderDao(dataContext);

		String enterprise_id=getEnterpriseId2User(dataContext,sessionModel);
		dtoModel.setenterprise_id(enterprise_id);

		TDataEnterpriseOrderModel model=new TDataEnterpriseOrderModel();
		model.setorder_id(dtoModel.getorder_id());
		boolean exist = dao.exist(model);
		try {
			if(exist){
				model=dao.find(model);
				List<String> ids=new ArrayList<>();
				List<TDataEnterpriseOrder2GoodsModel> insertList=new ArrayList<>();
				for(TDataEnterpriseOrder2GoodsDtoModel goodsDtoModel:dtoModel.details){
					ids.add(goodsDtoModel.getorder_details_id());
					TDataEnterpriseOrder2GoodsModel order2GoodsModel=new TDataEnterpriseOrder2GoodsModel();
					order2GoodsModel.setorder_details_id(goodsDtoModel.getorder_details_id());
					order2GoodsModel.setorder_id(model.getorder_id());
					order2GoodsModel.setis_valid(1);
					order2GoodsModel.setcreate_time(new Date());
					order2GoodsModel.setcreation_user_id(sessionModel.getUserId());
					order2GoodsModel.setquantity(goodsDtoModel.getquantity());
					order2GoodsModel.setunit_name(goodsDtoModel.getunit_name());
					order2GoodsModel.setgoods_id(goodsDtoModel.getgoods_id());
					order2GoodsModel.setwarehouse_id(goodsDtoModel.getwarehouse_id());
					insertList.add(order2GoodsModel);
				}
				String[] idsArr=new String[ids.size()];
				ids.toArray(idsArr);
				String sql=Utility.String_Format("delete from "+TDataEnterpriseOrder2GoodsModel.TABLE_NAME+" where order_id = ?");
				DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql,model.getorder_id());
				TDataEnterpriseOrder2GoodsDao order2GoodsDao=new TDataEnterpriseOrder2GoodsDao(dataContext);
				for(TDataEnterpriseOrder2GoodsModel o :insertList){
					order2GoodsDao.insertOnSubmit(o);
				}

			}
			baseResultModel.setSuccess(true);
		}catch (Exception err){
			logger.error(err.getMessage());
			baseResultModel.setSuccess(false);
			baseResultModel.setMsg(err.getMessage());
		}

		dataService.disposeInCurrentThread();
		return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
	}

    @RequestMapping(value = "/searchOrderDetails",method = RequestMethod.POST)
    public ResponseEntity<ResultListModel> searchOrderDetails(HttpServletRequest request,PagingOptions pagingOptions, TDataEnterpriseOrder2GoodsQueryModel queryModel) {
        ResultListModel resultListModel = new ResultListModel();
        SessionModel sessionModel=this.getSessionModel(request);
        DataService dataService = this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);
		if(Utility.isNullOrEmpty(queryModel.getorder_id())){
			resultListModel.setSuccess(false);
			resultListModel.setMsg("参数有误");
			return new ResponseEntity<>(resultListModel, HttpStatus.OK);
		}
		logger.info(queryModel.getorder_id());
        //归属企业查询
//        fillQueryModel4Crop(dataContext,queryModel,sessionModel);
        if(pagingOptions.getPageSize()==0)pagingOptions.setPageSize(20);
        pagingOptions.setNeedTotal(true);
        Collection<TDataEnterpriseOrder2GoodsDtoModel> dtoModelList=dataService.SelectT_DATA_ENTERPRISE_ORDER_2_GOODS(pagingOptions,queryModel,null);
        int start = pagingOptions.getStart();
        resultListModel.setRows(dtoModelList);
        resultListModel.setStart(start);
        resultListModel.setResultCount(dtoModelList.size());
        resultListModel.setTotal(pagingOptions.getTotal());
        dataContext.release();
        dataService.disposeInCurrentThread();
        return new ResponseEntity<>(resultListModel, HttpStatus.OK);
    }
}

