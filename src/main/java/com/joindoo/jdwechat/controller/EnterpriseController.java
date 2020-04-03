package com.joindoo.jdwechat.controller;

import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.beans.*;
import com.joindoo.jdwechat.cache.SessionModel;
import com.joindoo.jdwechat.codeGen.TDataEnterpriseDtoModel;
import com.joindoo.jdwechat.codeGen.TXtQxRy2BmDtoModel;
import com.joindoo.jdwechat.codeGen.beans.TXtAqZhxxDtoModel;
import com.joindoo.jdwechat.codeGen.beans.TXtQxRy2JsDtoModel;
import com.joindoo.jdwechat.common.WeChatEnums;
import com.joindoo.jdwechat.daos.*;
import com.joindoo.jdwechat.data.DBHelper;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.entity.config.SysProperties;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.ResultListModel;
import com.joindoo.jdwechat.model.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Enterprise")
public class EnterpriseController  extends BaseController {
    @Autowired
    SysProperties sysProperties;
    @Autowired
    DruidConfig druidConfig;

    @RequestMapping("/EnterpriseManage")
    public ModelAndView EnterpriseManage(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse) throws Exception {
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
    private boolean isHaveAuth(DataService dataService,String userId){
        boolean result=true;
        //查询当前登录用户的类型 只有平台账号才能查询企业列表
        TXtAqZhxxQueryModel zhxxQueryModel=new TXtAqZhxxQueryModel();
        zhxxQueryModel.setry_xh(userId);
        zhxxQueryModel.setsfyx_bj(1);
        List<TXtAqZhxxDtoModel>  zhxxDtoModels= dataService.selectT_XT_AQ_ZHXX(new PagingOptions(), zhxxQueryModel,null);
        if(zhxxDtoModels.size()>0){
            TXtAqZhxxDtoModel zhxxDtoModel=zhxxDtoModels.get(0);
            if(!"0".equals(zhxxDtoModel.getyhlx_dm())){
               result=false;
            }
        }
        return result;
    }
    @RequestMapping("/SearchEnterprise")
    public ResponseEntity<ResultListModel> SearchEnterprise(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, PagingOptions pagingOptions, TDataEnterpriseQueryModel queryModel) throws Exception {
        ResultListModel resultListModel = new ResultListModel();
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            resultListModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
            resultListModel.setSuccess(false);
        }else{
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            if (pagingOptions.getPageSize() == 0) pagingOptions.setPageSize(20);
            pagingOptions.setNeedTotal(true);
            queryModel.setis_valid(1);
            if(!isHaveAuth(dataService,sessionModel.getUserId())){
                resultListModel.setSuccess(false);
                resultListModel.setMsg("无权限获取");
                return new ResponseEntity<>(resultListModel, HttpStatus.OK);
            }

            List<TDataEnterpriseDtoModel>  dtoModelList= dataService.selectT_DATA_ENTERPRISE(pagingOptions, queryModel,null);
            int start = pagingOptions.getStart();
            resultListModel.setRows(dtoModelList);
            resultListModel.setStart(start);
            resultListModel.setSuccess(true);
            resultListModel.setResultCount(dtoModelList.size());
            resultListModel.setTotal(pagingOptions.getTotal());
            dataContext.release();
            dataService.disposeInCurrentThread();
        }
        return new ResponseEntity<>(resultListModel, HttpStatus.OK);
    }

    @Transactional
    @RequestMapping("/SaveEnterprise")
    public ResponseEntity<BaseResultModel> SaveEnterprise(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, TDataEnterpriseModel model) throws Exception {
        BaseResultModel baseResultModel = new BaseResultModel();
        String loginId = "";
        SessionModel sessionModel = ChkLogin(servletRequest);
        Date now=new Date();
        if (sessionModel.isLogin() == false){
            baseResultModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
            baseResultModel.setSuccess(false);
        }else{
            loginId = sessionModel.getUserId();
            boolean isAdd=false;
            if (Utility.isNullOrEmpty(model.getenterprise_id())) {
                isAdd=true;
                model.setenterprise_id(Utility.createUniqueId());
                model.setcreation_user_id(loginId);
                model.setcreate_time(now);
            }

            if(isAdd){
                if(Utility.isNullOrEmpty(model.getname())||Utility.isNullOrEmpty(model.gettelephone_number())){
                    baseResultModel.setSuccess(false);
                    baseResultModel.setMsg("请输入名称和联系电话");
                    return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
                }
            }
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            TDataEnterpriseDao dao = new TDataEnterpriseDao(dataContext);

            if(!isHaveAuth(dataService,sessionModel.getUserId())){
                baseResultModel.setSuccess(false);
                baseResultModel.setMsg("无权限操作");
                return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
            }

            try {
                List<BaseModel> old_data=dao.findAll("name=? and is_valid=1",model.getname());
                if(isAdd&old_data.size()>0){
                    baseResultModel.setSuccess(false);
                    baseResultModel.setMsg("名称已存在");
                    return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
                }
                if(!isAdd&old_data.size()>0){
                    TDataEnterpriseModel data=(TDataEnterpriseModel)old_data.get(0);
                    if(!data.getenterprise_id().equals(model.getenterprise_id())){
                        baseResultModel.setSuccess(false);
                        baseResultModel.setMsg("名称已存在");
                        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
                    }
                }
                model.setlast_edit_time(now);
                model.setlast_edit_user_id(loginId);
                model.setis_valid(1);
                if (isAdd) {
                    dao.insertOnSubmit(model);
                }else{
                    dao.updateOnSubmit(model);
                }
                baseResultModel.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                baseResultModel.setSuccess(false);
            } finally {
                dataContext.release();
                dataService.disposeInCurrentThread();
            }
        }
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }

    @Transactional
    @RequestMapping("/deleteEnterprise")
    public ResponseEntity<BaseResultModel> deleteEnterprise(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse,TDataEnterpriseModel model) throws Exception {
        BaseResultModel baseResultModel = new BaseResultModel();
        String loginId = "";
        SessionModel sessionModel = ChkLogin(servletRequest);
        Date now=new Date();
        if (sessionModel.isLogin() == false){
            baseResultModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
            baseResultModel.setSuccess(false);
        }else{
            loginId = sessionModel.getUserId();

            if(Utility.isNullOrEmpty(model.getenterprise_id())){
                baseResultModel.setSuccess(false);
                baseResultModel.setMsg("参数有误");
                return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
            }
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            TDataEnterpriseDao dao = new TDataEnterpriseDao(dataContext);

            try {
                List<BaseModel> old_data=dao.findAll("enterprise_id=?",model.getenterprise_id());
                if(old_data.size()>0){
                    TDataEnterpriseModel data=(TDataEnterpriseModel)old_data.get(0);
                    data.doUpdate();
                    data.setlast_edit_time(now);
                    data.setlast_edit_user_id(loginId);
                    data.setis_valid(0);
                    dao.updateOnSubmit(data);
                }else{
                    baseResultModel.setSuccess(false);
                    baseResultModel.setMsg("企业不存在");
                    return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
                }
                baseResultModel.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                baseResultModel.setSuccess(false);
            } finally {
                dataContext.release();
                dataService.disposeInCurrentThread();
            }
        }
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }

}
