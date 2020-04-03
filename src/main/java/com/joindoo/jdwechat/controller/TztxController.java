package com.joindoo.jdwechat.controller;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.WeChatFields;
import com.joindoo.jdwechat.beans.BaseModel;
import com.joindoo.jdwechat.beans.TTxTztxjl2RyModel;
import com.joindoo.jdwechat.cache.SessionModel;
import com.joindoo.jdwechat.codeGen.TTxTztxjlDtoModel;
import com.joindoo.jdwechat.common.WeChatEnums;
import com.joindoo.jdwechat.daos.TTxTztxjl2RyDao;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.entity.config.SysProperties;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.ResultListModel;
import com.joindoo.jdwechat.model.query.BaseResultQueryModel;
import com.joindoo.jdwechat.model.query.PagingOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Tztx")
public class  TztxController extends BaseController{
    @Autowired
    SysProperties sysProperties;
    @Autowired
    DruidConfig druidConfig;

    @RequestMapping("/ToMyMsg")
    public ModelAndView ToMyMsg(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            modelAndView.setViewName("account/login");
        }else{
            modelAndView.setViewName("message/index");
            modelAndView.setStatus(HttpStatus.OK);
        }
        return modelAndView;
    }

    @RequestMapping("/queryMsg")
    public ResponseEntity<ResultListModel> queryMsg(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, PagingOptions pagingOptions, BaseResultQueryModel queryModel)throws Exception {
        ResultListModel resultListModel = new ResultListModel();
        SessionModel sessionModel= ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            resultListModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
//            httpServletResponse.sendRedirect(WeChatFields.LOGIN_URL);
            resultListModel.setSuccess(false);
        }else{
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            if (pagingOptions.getPageSize() == 0) pagingOptions.setPageSize(20);
            pagingOptions.setNeedTotal(true);
            queryModel.setRy_xh(sessionModel.getUserId());
            List<TTxTztxjlDtoModel> dtoModelList = dataService.selectT_TX_TZTXJL(pagingOptions, queryModel);
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


    @RequestMapping("/UptMsgState")
    public ResponseEntity<BaseResultModel> UptMsgState(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, TTxTztxjl2RyModel data) throws Exception {
        DataService dataService = this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);
        BaseResultModel baseResultModel = new BaseResultModel();
        String loginId="";
        SessionModel sessionModel= ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            baseResultModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
//            httpServletResponse.sendRedirect(WeChatFields.LOGIN_URL);
            baseResultModel.setSuccess(false);
        }else{
            loginId=sessionModel.getUserId();
            if (Utility.isNullOrEmpty(data.gettztxjl_xh()) || Utility.isNullOrEmpty(data.getry_xh())) {
                baseResultModel.setMsg("参数有误");
                baseResultModel.setSuccess(false);
                return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
            }

            TTxTztxjl2RyDao dao=new TTxTztxjl2RyDao(dataContext);
            String sql_where = Utility.String_Format(" TZTXJL_XH='{0}' AND RY_XH='{1}' ", data.gettztxjl_xh(),data.getry_xh());
            List<BaseModel> baseModels = dao.findAll(sql_where);
            TTxTztxjl2RyModel model=null;
            if(baseModels.size()>0)
                model=(TTxTztxjl2RyModel)baseModels.get(0);
            try{
                if(model!=null){
                    model.setclzt_dm(WeChatEnums._已处理.getIndex());
                    model.setcl_sj(new Date());
                    model.setxg_sj(new Date());
                    model.setxgry_xh(loginId);
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
}
