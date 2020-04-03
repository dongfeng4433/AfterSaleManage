package com.joindoo.jdwechat.controller;

import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.beans.TDataEnterpriseGoodsModel;
import com.joindoo.jdwechat.cache.SessionModel;
import com.joindoo.jdwechat.cache.UserCacheModel;
import com.joindoo.jdwechat.daos.TDataEnterpriseGoodsDao;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.model.BaseResultModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/OrderManage")
public class OrderManageController extends BaseController{
    @RequestMapping("/page")
    public ModelAndView page(HttpServletRequest servletRequest) {
        ModelAndView modelAndView = new ModelAndView();
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            modelAndView.setViewName("account/login");
        }else{
            modelAndView.setViewName("order/page");
            modelAndView.setStatus(HttpStatus.OK);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/getCurrentUser",method = RequestMethod.POST)
    public ResponseEntity<BaseResultModel> getCurrentUser(HttpServletRequest request) {
        BaseResultModel baseResultModel = new BaseResultModel();

        SessionModel sessionModel=this.getSessionModel(request);
        DataService dataService = this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);
        UserCacheModel cacheModel=new UserCacheModel();
        cacheModel.setUserId(sessionModel.getUserId());
        baseResultModel.setTag(cacheModel);
        baseResultModel.setSuccess(true);
        dataService.disposeInCurrentThread();
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }
}
