package com.joindoo.jdwechat.controller;

import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.cache.SessionModel;
import com.joindoo.jdwechat.beans.*;
import com.joindoo.jdwechat.daos.*;
import com.joindoo.jdwechat.codeGen.*;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.entity.config.SysProperties;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.ResultListModel;
import com.joindoo.jdwechat.model.query.*;
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
@RequestMapping("TXtQxBm")
public class TXtQxBmController  extends BaseController{
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
			modelAndView.setViewName("bm/index");
			modelAndView.setStatus(HttpStatus.OK);
		}
		return modelAndView;
	}
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public ResponseEntity<ResultListModel> search(HttpServletRequest request,PagingOptions pagingOptions, TXtQxBmQueryModel queryModel) {
		ResultListModel resultListModel = new ResultListModel();
        SessionModel sessionModel=this.getSessionModel(request);
        DataService dataService = this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);

		if(pagingOptions.getPageSize()==0)pagingOptions.setPageSize(20);
		pagingOptions.setNeedTotal(true);
		Collection<TXtQxBmDtoModel> dtoModelList=dataService.SelectT_XT_QX_BM(pagingOptions,queryModel,null);
		int start = pagingOptions.getStart();
		resultListModel.setRows(dtoModelList);
        resultListModel.setStart(start);
        resultListModel.setResultCount(dtoModelList.size());
        resultListModel.setTotal(pagingOptions.getTotal());
		dataContext.release();
        dataService.disposeInCurrentThread();
        return new ResponseEntity<>(resultListModel, HttpStatus.OK);
    }
	@RequestMapping(value = "/saveBm",method = RequestMethod.POST)
	public ResponseEntity<BaseResultModel> saveBm(TXtQxBmDtoModel dtoModel, HttpServletRequest request) {
		BaseResultModel baseResultModel = new BaseResultModel();
		SessionModel sessionModel=this.getSessionModel(request);
		DataService dataService = this.getDataService();
		DataContext dataContext = dataService.getDataContext(druidConfig);
		TXtQxBmDao dao = new TXtQxBmDao(dataContext);
		TXtQxBmModel model=new TXtQxBmModel();
		model.setbm_dm(dtoModel.getbm_dm());
		boolean exist = dao.exist(model);
		try {
			if(exist){
				model=dao.find(model);
				model.doUpdate();
				model.setmc(dtoModel.getmc());
				model.setms(dtoModel.getms());
				model.setwz_px(dtoModel.getwz_px());
				model.setlr_sj(dtoModel.getlr_sj());
				model.setxg_sj(dtoModel.getxg_sj());
				model.setlrry_xh(dtoModel.getlrry_xh());
				model.setxgry_xh(dtoModel.getxgry_xh());
				model.setsfyx_bj(dtoModel.getsfyx_bj());
				model.setyyz_xh(dtoModel.getyyz_xh());
				dao.updateOnSubmit(model);
			}else{
				BeanUtils.copyProperties(dtoModel,model);
				model.setsfyx_bj(1);
				model.setbm_dm(Utility.createUniqueId());
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
	public ResponseEntity<BaseResultModel> remove(HttpServletRequest request, TXtQxBmModel model) {
		BaseResultModel baseResultModel = new BaseResultModel();
		if(Utility.isNullOrEmpty(model.getbm_dm())){
			baseResultModel.setSuccess(false);
			baseResultModel.setMsg("参数有误");
			return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
		}
			
		SessionModel sessionModel=this.getSessionModel(request);
		DataService dataService = this.getDataService();
		DataContext dataContext = dataService.getDataContext(druidConfig);
		TXtQxBmDao dao = new TXtQxBmDao(dataContext);
		TXtQxBmModel model1= dao.find(model);
		try {
			if(model1!=null){
				model1.doUpdate();
				model1.setsfyx_bj(0);
				model.setxg_sj(new Date());
				model1.setxgry_xh(sessionModel.getUserId());
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
	

}

