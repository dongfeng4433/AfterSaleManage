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
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.BeanUtils;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.util.ObjectUtils;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("TDataEnterprise")
public class TDataEnterpriseController  extends BaseController{
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
			modelAndView.setViewName("enterprise/index");
			modelAndView.setStatus(HttpStatus.OK);
		}
		return modelAndView;
	}
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public ResponseEntity<ResultListModel> search(HttpServletRequest request,PagingOptions pagingOptions, TDataEnterpriseQueryModel queryModel) {
		ResultListModel resultListModel = new ResultListModel();
        SessionModel sessionModel=this.getSessionModel(request);
        DataService dataService = this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);

		if(pagingOptions.getPageSize()==0)pagingOptions.setPageSize(20);
		pagingOptions.setNeedTotal(true);
		Collection<TDataEnterpriseDtoModel> dtoModelList=dataService.SelectT_DATA_ENTERPRISE(pagingOptions,queryModel,null);
		int start = pagingOptions.getStart();
		resultListModel.setRows(dtoModelList);
        resultListModel.setStart(start);
        resultListModel.setResultCount(dtoModelList.size());
        resultListModel.setTotal(pagingOptions.getTotal());
		dataContext.release();
        dataService.disposeInCurrentThread();
        return new ResponseEntity<>(resultListModel, HttpStatus.OK);
    }
	@RequestMapping(value = "/getAdmin4Crop",method = RequestMethod.POST)
	public ResponseEntity<ResultListModel> getAdmin4Crop(HttpServletRequest request,TDataEnterpriseQueryModel queryModel) {
		ResultListModel resultListModel = new ResultListModel();
		SessionModel sessionModel=this.getSessionModel(request);
		if(Utility.isNullOrEmpty(queryModel.getenterprise_id())){
			resultListModel.setSuccess(false);
			resultListModel.setMsg("参数有误");
			return new ResponseEntity<>(resultListModel, HttpStatus.OK);
		}
		DataService dataService = this.getDataService();
		DataContext dataContext = dataService.getDataContext(druidConfig);
		TXtAqZhxxDao zhxxDao=new TXtAqZhxxDao(dataContext);
		String sql="select RY_XH,MC,YHM,SJ_HM from t_xt_aq_zhxx where ry_xh in (select user_id from t_data_enterprise_2_user where enterprise_id =? and is_valid=1)";
		List<BaseModel> zhxxModels= zhxxDao.queryAll(sql,queryModel.getenterprise_id());
		List<TXtAqZhxxDtoModel> zhxxDtoModelList=new ArrayList<>();
		if(zhxxModels.size()>0){
			for(BaseModel model:zhxxModels){
				TXtAqZhxxModel zhxxModel=(TXtAqZhxxModel) model;
				TXtAqZhxxDtoModel dtoModel=new TXtAqZhxxDtoModel();
				BeanUtils.copyProperties(zhxxModel,dtoModel);
				zhxxDtoModelList.add(dtoModel);
			}
		}

//		TDataEnterprise2UserDao userDao=new TDataEnterprise2UserDao(dataContext);
//		List<BaseModel> resultList=userDao.findAll("enterprise_id=? and is_valid=1",queryModel.getenterprise_id());
//		List<TDataEnterprise2UserDtoModel> dtoModelList=new ArrayList<>();
//		if(resultList.size()>0){
//			for(BaseModel model:resultList){
//				TDataEnterprise2UserModel userModel=(TDataEnterprise2UserModel)model;
//				TDataEnterprise2UserDtoModel dtoModel=new TDataEnterprise2UserDtoModel();
//				BeanUtils.copyProperties(userModel,dtoModel);
//				dtoModelList.add(dtoModel);
//			}
//		}
		resultListModel.setRows(zhxxDtoModelList);
		dataContext.release();
		dataService.disposeInCurrentThread();
		return new ResponseEntity<>(resultListModel, HttpStatus.OK);
	}
	@RequestMapping(value = "/saveEnterprise",method = RequestMethod.POST)
	public ResponseEntity<BaseResultModel> saveEnterprise(TDataEnterpriseDtoModel dtoModel, HttpServletRequest request) {
		BaseResultModel baseResultModel = new BaseResultModel();
		SessionModel sessionModel=this.getSessionModel(request);
		DataService dataService = this.getDataService();
		DataContext dataContext = dataService.getDataContext(druidConfig);
		TDataEnterpriseDao dao = new TDataEnterpriseDao(dataContext);
		TDataEnterpriseModel model=new TDataEnterpriseModel();
		model.setenterprise_id(dtoModel.getenterprise_id());
		boolean exist = dao.exist(model);
		try {
			if(exist){
				model=dao.find(model);
				model.doUpdate();
				model.setname(dtoModel.getname());
				model.setshort_name(dtoModel.getshort_name());
				model.setaddress(dtoModel.getaddress());
				model.setdescription(dtoModel.getdescription());
				model.settelephone_number(dtoModel.gettelephone_number());
				model.setlast_edit_time(new Date());
				model.setcreation_user_id(sessionModel.getUserId());
				model.setlast_edit_user_id(sessionModel.getUserId());
				model.setdd_corp_id(dtoModel.getdd_corp_id());
				dao.updateOnSubmit(model);
			}else{
				BeanUtils.copyProperties(dtoModel,model);
				model.setis_valid(1);
				model.setenterprise_id(Utility.createUniqueId());
				model.setcreate_time(new Date());
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
	public ResponseEntity<BaseResultModel> remove(HttpServletRequest request, TDataEnterpriseModel model) {
		BaseResultModel baseResultModel = new BaseResultModel();
		if(Utility.isNullOrEmpty(model.getenterprise_id())){
			baseResultModel.setSuccess(false);
			baseResultModel.setMsg("参数有误");
			return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
		}
			
		SessionModel sessionModel=this.getSessionModel(request);
		DataService dataService = this.getDataService();
		DataContext dataContext = dataService.getDataContext(druidConfig);
		TDataEnterpriseDao dao = new TDataEnterpriseDao(dataContext);
		TDataEnterpriseModel model1= dao.find(model);
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

	@RequestMapping(value = "/setCropAdmins",method = RequestMethod.POST)
	public ResponseEntity<BaseResultModel> setCropAdmins(HttpServletRequest request, TDataEnterprise2UserQueryModel queryModel) {
		BaseResultModel baseResultModel = new BaseResultModel();
		if(Utility.isNullOrEmpty(queryModel.getenterprise_id())||Utility.isNullOrEmpty(queryModel.getUser_ids())){
			baseResultModel.setSuccess(false);
			baseResultModel.setMsg("参数有误");
			return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
		}

		SessionModel sessionModel=this.getSessionModel(request);
		DataService dataService = this.getDataService();
		DataContext dataContext = dataService.getDataContext(druidConfig);
		TXtAqZhxxDao zhxxDao=new TXtAqZhxxDao(dataContext);
		String ids=Utility.formatIds(queryModel.getUser_ids().split(","),true,",");
		List<BaseModel> baseModels=zhxxDao.findAll("ry_xh in ("+ ids+")");
		if(baseModels.size()==0){
			baseResultModel.setSuccess(false);
			baseResultModel.setMsg("参数有误");
		}else {
			TDataEnterpriseModel model=new TDataEnterpriseModel();
			model.setenterprise_id(queryModel.getenterprise_id());
			TDataEnterpriseDao dao = new TDataEnterpriseDao(dataContext);
			TDataEnterpriseModel model1= dao.find(model);
			try {
				if(model1!=null){
					TDataEnterprise2UserDao userDao=new TDataEnterprise2UserDao(dataContext);
					List<BaseModel> userBaseModels= userDao.findAll("enterprise_id=? and is_valid=1",model1.getenterprise_id());
					HashMap<String,String> userHashMap=new HashMap<>();
					HashSet<String> hashSet=new HashSet<>();
					HashSet<String> oldAdmainHashSet=new HashSet<>();//原始管理员id
					if(userBaseModels.size()>0){
						for(BaseModel baseModel:userBaseModels){
							TDataEnterprise2UserModel o=(TDataEnterprise2UserModel)baseModel;
							userHashMap.put(o.getuser_id(),o.getuser_type_code());
							if(o.getuser_type_code().equals("0"))
								oldAdmainHashSet.add(o.getuser_id());
						}
					}
					List<String> insertList=new ArrayList<>();
					List<String> updateList=new ArrayList<>();
					for(BaseModel baseModel:baseModels){
						TXtAqZhxxModel zhxxModel=(TXtAqZhxxModel) baseModel;
						if(userHashMap.containsKey(zhxxModel.getry_xh())){
							//修改
							if(!userHashMap.get(zhxxModel.getry_xh()).equals("0")){
								String sql_update="update "+TDataEnterprise2UserModel.TABLE_NAME+" set user_type_code=0,last_edit_time='"+
										Utility.formatDateTime(new Date(),false)+"' where enterprise_id='"+
										model1.getenterprise_id()+"' and user_id='"+zhxxModel.getry_xh()+"'";
								updateList.add(sql_update);
								hashSet.add(zhxxModel.getry_xh());
							}
						}else{//新增
							TDataEnterprise2UserModel userModel=new TDataEnterprise2UserModel();
							userModel.setuser_id(zhxxModel.getry_xh());
							userModel.setenterprise_id(model1.getenterprise_id());
							userModel.setis_valid(1);
							userModel.setcreate_time(new Date());
							userModel.setuser_type_code("0");
							userModel.setcurrent_status("0");
							userModel.setcreation_user_id(sessionModel.getUserId());
							insertList.add(userModel.getInsert());
							hashSet.add(zhxxModel.getry_xh());
						}
					}
					for (String id :oldAdmainHashSet){
						if(!hashSet.contains(id)){
							String sql_update="update "+TDataEnterprise2UserModel.TABLE_NAME+" set user_type_code=1,last_edit_time='"+
									Utility.formatDateTime(new Date(),false)+"' where enterprise_id='"+
									model1.getenterprise_id()+"' and user_id='"+id+"'";
							updateList.add(sql_update);
						}
					}
					DBHelper.executeNonQuery(DataContext.getCurrentConnection(),insertList);
					DBHelper.executeNonQuery(DataContext.getCurrentConnection(),updateList);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
			baseResultModel.setSuccess(true);
		}

		dataContext.release();
		dataService.disposeInCurrentThread();
		return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
	}
}

