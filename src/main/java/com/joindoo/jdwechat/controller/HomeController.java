package com.joindoo.jdwechat.controller;

import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.WeChatFields;
import com.joindoo.jdwechat.beans.*;
import com.joindoo.jdwechat.cache.SessionModel;
import com.joindoo.jdwechat.codeGen.TYwXxlrDtoModel;
import com.joindoo.jdwechat.codeGen.beans.TXtGyFjxxDtoModel;
import com.joindoo.jdwechat.common.WeChatEnums;
import com.joindoo.jdwechat.daos.*;
import com.joindoo.jdwechat.data.DBHelper;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.entity.config.SysProperties;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.ResultListModel;
import com.joindoo.jdwechat.model.query.BaseQueryModel;
import com.joindoo.jdwechat.model.query.BaseResultQueryModel;
import com.joindoo.jdwechat.model.query.PagingOptions;
import com.joindoo.jdwechat.model.query.TXtGyFjxxQueryModel;
import com.joindoo.jdwechat.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * Created by zhuqiang on 2018/3/31.
 */
@Controller
@RequestMapping("/Home")
public class HomeController extends BaseController {
    @Autowired
    SysProperties sysProperties;
    @Autowired
    DruidConfig druidConfig;

    @RequestMapping("/preview")
    public void pdfStreamHandler(String fileName,HttpServletRequest servletRequest,HttpServletResponse response) {
        String opath =  Utility.Path_Combine(sysProperties.getDocs(), "MainStorage" + File.separator + "DocumentFile" + File.separator);
        File file = new File(opath+fileName);
        if (file.exists()){
            byte[] data = null;
            try {
                FileInputStream input = new FileInputStream(file);
                data = new byte[input.available()];
                input.read(data);
                response.getOutputStream().write(data);
                input.close();
            } catch (Exception e) {
                logger.error("pdf文件处理异常：" + e.getMessage());
            }
        }
    }
    //region 页面跳转
    @RequestMapping("/Index")
    public ModelAndView Index(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, Model model) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            modelAndView.setViewName("account/login");
        }else{
            modelAndView.setViewName("home/index");
            modelAndView.setStatus(HttpStatus.OK);
            model.addAttribute("sessionModel", sessionModel);
        }
        return modelAndView;
    }
    //页面跳转-历史事项
    @RequestMapping("/xxlrHistory")
    public ModelAndView xxlrHistory(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("history/index");
        modelAndView.setStatus(HttpStatus.OK);
//        SessionModel sessionModel = ChkLogin(servletRequest);
//        if (sessionModel.isLogin() == false){
//            modelAndView.setViewName("account/login");
//        }
        return modelAndView;
    }

    @RequestMapping("/Licence")
    public ModelAndView Licence(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("licence/index");
        modelAndView.setStatus(HttpStatus.OK);
//        SessionModel sessionModel = ChkLogin(servletRequest);
//        if (sessionModel.isLogin() == false){
//            modelAndView.setViewName("account/login");
//        }
        return modelAndView;
    }
    @RequestMapping("/Taxpayer")
    public ModelAndView Taxpayer(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("taxpayer/index");
        modelAndView.setStatus(HttpStatus.OK);
//        SessionModel sessionModel = ChkLogin(servletRequest);
//        if (sessionModel.isLogin() == false){
//            modelAndView.setViewName("account/login");
//        }
        return modelAndView;
    }
    //endregion


    //在线预览附件信息
    @RequestMapping("/queryFileInfo")
    public ResponseEntity<BaseResultModel> queryFileInfo(HttpServletRequest servletRequest, TXtGyFjxxQueryModel queryModel) {
        BaseResultModel baseResultModel = new BaseResultModel();
        SessionModel sessionModel = ChkLogin(servletRequest);
        if(Utility.isNullOrEmpty(queryModel.getfj_xh())){
            baseResultModel.setSuccess(false);
            baseResultModel.setMsg("参数有误");
            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
        }
        if (sessionModel.isLogin() == false){
            baseResultModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
            baseResultModel.setSuccess(false);
        }else{
            DataService dataService=this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            TXtGyFjxxDao fjxxDao = new TXtGyFjxxDao(dataContext);
            List<BaseModel> list=fjxxDao.findAll("fj_xh=?",queryModel.getfj_xh()) ;
           if(list.size()>0){
               baseResultModel.setTag(list.get(0));
           }
            baseResultModel.setSuccess(true);
        }
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }

    //查询信息录入待办
    @RequestMapping("/queryXxlr_Db")
    public ResponseEntity<ResultListModel> queryXxlr_Db(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, PagingOptions pagingOptions, BaseResultQueryModel queryModel) throws Exception {
        SessionModel sessionModel = ChkLogin(servletRequest);
        ResultListModel resultListModel=new ResultListModel();
        if (sessionModel.isLogin() == false){
            resultListModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
//            httpServletResponse.sendRedirect(WeChatFields.LOGIN_URL);
            resultListModel.setSuccess(false);
        }else{
            queryModel.setlrry_xh(sessionModel.getUserId());
            queryModel.setLr_type(WeChatEnums._待办.getIndex());
            queryModel.setsfyx_bj(1);
            resultListModel= getResultList(resultListModel,pagingOptions, queryModel);
            resultListModel.setSuccess(true);
        }
        return new ResponseEntity<>(resultListModel, HttpStatus.OK);
    }

    //查询信息录入由当前登录人发起
    @RequestMapping("/queryXxlr_Fq")
    public ResponseEntity<ResultListModel> queryXxlr_Jb(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, PagingOptions pagingOptions, BaseResultQueryModel queryModel) throws Exception {
        SessionModel sessionModel = ChkLogin(servletRequest);
        ResultListModel resultListModel=new ResultListModel();
        if (sessionModel.isLogin() == false){
            resultListModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
//            httpServletResponse.sendRedirect(WeChatFields.LOGIN_URL);
            resultListModel.setSuccess(false);
        }else{
            queryModel.setlrry_xh(sessionModel.getUserId());
            queryModel.setLr_type(WeChatEnums._发起.getIndex());
            queryModel.setsfyx_bj(1);
            resultListModel = getResultList(resultListModel,pagingOptions, queryModel);
            resultListModel.setSuccess(true);
        }
        return new ResponseEntity<>(resultListModel, HttpStatus.OK);
    }

    //查询历史数据
    @RequestMapping("/queryXxlrHistory")
    public ResponseEntity<ResultListModel> queryXxlrHistory(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, PagingOptions pagingOptions, BaseResultQueryModel queryModel) throws Exception {
        SessionModel sessionModel = ChkLogin(servletRequest);
        ResultListModel resultListModel=new ResultListModel();
        if (sessionModel.isLogin() == false){
            resultListModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
//            httpServletResponse.sendRedirect(WeChatFields.LOGIN_URL);
            resultListModel.setSuccess(false);
        }else{
            queryModel.setLr_type(WeChatEnums._历史记录.getIndex());
            queryModel.setsfyx_bj(1);
            resultListModel = getResultList(resultListModel,pagingOptions, queryModel);
            resultListModel.setSuccess(true);
        }
        return new ResponseEntity<>(resultListModel, HttpStatus.OK);
    }

    protected ResultListModel getResultList(ResultListModel resultListModel,PagingOptions pagingOptions, BaseResultQueryModel queryModel) {
        DataService dataService = this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);
        if (pagingOptions.getPageSize() == 0) pagingOptions.setPageSize(20);
        pagingOptions.setNeedTotal(true);
        List<TYwXxlrDtoModel> dtoModelList = dataService.selectT_YW_XXLR(pagingOptions, queryModel);
        if(dtoModelList.size()>0){
            BaseResultQueryModel childQueryModel = new BaseResultQueryModel();
            childQueryModel.setsfyx_bj(1);
            for (TYwXxlrDtoModel model : dtoModelList) {
                childQueryModel.setsj_xxlr_xh(model.getxxlr_xh());
                List<TYwXxlrDtoModel> childList = dataService.selectT_YW_XXLR(new PagingOptions(), childQueryModel);
//                model.setChildList(childList);
                if(childList.size()>0)
                    model.setzt_dm(WeChatEnums._已回复.getIndex());
            }
        }

        int start = pagingOptions.getStart();
        resultListModel.setRows(dtoModelList);
        resultListModel.setStart(start);
        resultListModel.setResultCount(dtoModelList.size());
        resultListModel.setTotal(pagingOptions.getTotal());
        dataContext.release();
        dataService.disposeInCurrentThread();
        return resultListModel;
    }

    //查询信息回复的列表(加检索条件)
    @RequestMapping("/queryChildXxlr")
    public ResponseEntity<ResultListModel> queryChildXxlr(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, PagingOptions pagingOptions, BaseResultQueryModel queryModel) throws Exception {
        ResultListModel resultListModel = new ResultListModel();
        SessionModel sessionModel = ChkLogin(servletRequest);

        if (sessionModel.isLogin() == false){
            resultListModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
//            httpServletResponse.sendRedirect(WeChatFields.LOGIN_URL);
            resultListModel.setSuccess(false);
        }else{
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            if (pagingOptions.getPageSize() == 0) pagingOptions.setPageSize(20);
            pagingOptions.setNeedTotal(true);
            queryModel.setsfyx_bj(1);
            List<TYwXxlrDtoModel> dtoModelList= dataService.selectT_YW_XXLR(new PagingOptions(), queryModel);
            //查找回复对应的附件
            TXtGyFjxxQueryModel fjxxQueryModel = new TXtGyFjxxQueryModel();
            for (TYwXxlrDtoModel cld : dtoModelList) {
                if (!Utility.isNullOrEmpty(cld.getfj_xh())) {
                    fjxxQueryModel.setsfyx_bj(1);
                    fjxxQueryModel.setzfj_xh(cld.getfj_xh());
                    List<TXtGyFjxxDtoModel> fjxxList = dataService.selectT_XT_GY_FJXX(new PagingOptions(), fjxxQueryModel);
                    cld.setFjxxList(fjxxList);
                }
            }
            int start = pagingOptions.getStart();
            resultListModel.setRows(dtoModelList);
            resultListModel.setSuccess(true);
            resultListModel.setStart(start);
            resultListModel.setResultCount(dtoModelList.size());
            resultListModel.setTotal(pagingOptions.getTotal());
            dataContext.release();
            dataService.disposeInCurrentThread();

        }


        return new ResponseEntity<>(resultListModel, HttpStatus.OK);
    }



    //添加
    @Transactional
    @RequestMapping("/saveXxlr")
    public ResponseEntity<BaseResultModel> saveXxlr(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, TYwXxlrModel model, String ryxhs,String hf_sj_string) throws Exception {
        BaseResultModel baseResultModel = new BaseResultModel();
        String loginId = "";
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            baseResultModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
//            httpServletResponse.sendRedirect(WeChatFields.LOGIN_URL);
            baseResultModel.setSuccess(false);
        }else{
            loginId = sessionModel.getUserId();
            DataService dataService=this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            BaseServiceImpl baseServiceImpl=new BaseServiceImpl();
            String result= baseServiceImpl.saveXxlr(dataService,dataContext,loginId,model,ryxhs,hf_sj_string);
            if(!Utility.isNullOrEmpty(result)){
                baseResultModel.setMsg(result);
                baseResultModel.setSuccess(false);
                return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
            }
            baseResultModel.setSuccess(true);
        }
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }

    //撤销
    @Transactional
    @RequestMapping("/delXxlr")
    public ResponseEntity<BaseResultModel> delXxlr(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse,TYwXxlrModel model) throws Exception {
        BaseResultModel baseResultModel = new BaseResultModel();
        if(Utility.isNullOrEmpty(model.getxxlr_xh())){
            baseResultModel.setSuccess(false);
            baseResultModel.setMsg("参数有误");
            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
        }
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            baseResultModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
//            httpServletResponse.sendRedirect(WeChatFields.LOGIN_URL);
            baseResultModel.setSuccess(false);
        }else{
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            BaseResultQueryModel queryModel=new BaseResultQueryModel();
            queryModel.setsj_xxlr_xh(model.getxxlr_xh());
            queryModel.setsfyx_bj(1);
            List<TYwXxlrDtoModel> childList = dataService.selectT_YW_XXLR(new PagingOptions(), queryModel);
            if(childList.size()>0){
                baseResultModel.setSuccess(false);
                baseResultModel.setMsg("该记录已有回复，不可撤销");
                return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
            }

            TYwXxlrDao dao = new TYwXxlrDao(dataContext);
            try {
                dao.delete(model);

                String sql2="delete from "+TYwXxlr2RyModel.TABLE_NAME+" where xxlr_xh="+Utility.getSqlFielStatement("string",model.getxxlr_xh())+" ";
                DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql2);

                String sql3="delete from "+TTxTztxjl2RyModel.TABLE_NAME+" where tztxjl_xh in (select tztxjl_xh from t_tx_tztxjl where xgjl_zj_xh="+Utility.getSqlFielStatement("string",model.getxxlr_xh())+")";
                DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql3);

                String sql4="delete from "+TTxTztxjlModel.TABLE_NAME+" where xgjl_zj_xh="+Utility.getSqlFielStatement("string",model.getxxlr_xh())+" ";
                DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql4);

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

    //回复
    @RequestMapping("/replyXxlr")
    public ResponseEntity<BaseResultModel> replyXxlr(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, TYwXxlrModel model, String replyRyXh) throws Exception {
        BaseResultModel baseResultModel = new BaseResultModel();
        if (Utility.isNullOrEmpty(model.getsj_xxlr_xh()) || Utility.isNullOrEmpty(replyRyXh)) {
            baseResultModel.setMsg("参数有误");
            baseResultModel.setSuccess(false);
            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
        }
        String loginId = "", loginMc = "";
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            baseResultModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
//            httpServletResponse.sendRedirect(WeChatFields.LOGIN_URL);
            baseResultModel.setSuccess(false);
        }else{
            loginId = sessionModel.getUserId();
            loginMc = sessionModel.getUserMc();
            DataService dataService=this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            BaseServiceImpl baseServiceImpl=new BaseServiceImpl();
            String result= baseServiceImpl.replyXxlr(dataService,dataContext,model,loginId,loginMc,replyRyXh);
            if(!Utility.isNullOrEmpty(result)){
                baseResultModel.setMsg(result);
                baseResultModel.setSuccess(false);
                return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
            }
            baseResultModel.setSuccess(true);
        }
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }


    @RequestMapping(value = "testPath", method = RequestMethod.GET)
    public String testPath() {
        String dt = Utility.formatDateTime(new Date(), true);
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        String path = Utility.Path_Combine(sysProperties.getDocs(), dt + File.separator + hour + File.separator);
        if (!Utility.File_Exists(path))
            Utility.File_Mkdirs(path);
        return null;
    }


}
