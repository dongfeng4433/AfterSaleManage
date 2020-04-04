package com.joindoo.jdwechat.controller;

import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.WeChatFields;
import com.joindoo.jdwechat.beans.*;
import com.joindoo.jdwechat.cache.SessionModel;
import com.joindoo.jdwechat.cache.UserCacheModel;
import com.joindoo.jdwechat.codeGen.TXtQxBmDtoModel;
import com.joindoo.jdwechat.codeGen.TXtQxJsDtoModel;
import com.joindoo.jdwechat.codeGen.TXtQxRy2BmDtoModel;
import com.joindoo.jdwechat.codeGen.beans.TXtAqZhxxDtoModel;
import com.joindoo.jdwechat.codeGen.beans.TXtQxRy2JsDtoModel;
import com.joindoo.jdwechat.codeGen.beans.TXtQxXtmkDtoModel;
import com.joindoo.jdwechat.common.WeChatEnums;
import com.joindoo.jdwechat.daos.*;
import com.joindoo.jdwechat.data.DBHelper;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.NoticeModel;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.entity.config.SysProperties;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.ResultListModel;
import com.joindoo.jdwechat.model.query.*;
import com.joindoo.jdwechat.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/Zhxx")
public class ZhxxController extends BaseController {
    @Autowired
    SysProperties sysProperties;
    @Autowired
    DruidConfig druidConfig;

    //region 页面跳转
    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account/login");
        return modelAndView;
    }

    @RequestMapping("/zhxxManage")
    public ModelAndView zhxxManage(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            modelAndView.setViewName("account/login");
        }else{
            modelAndView.setViewName("users/index");
            modelAndView.setStatus(HttpStatus.OK);
        }
        return modelAndView;
    }


    @RequestMapping("/ToUpdatePassword")
    public ModelAndView ToUpdatePassword(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            modelAndView.setViewName("account/login");
            modelAndView.setStatus(HttpStatus.UNAUTHORIZED);

        }else{
            modelAndView.setViewName("pwd/index");
            modelAndView.setStatus(HttpStatus.OK);
        }
        return modelAndView;
    }

    //endregion

    @RequestMapping("/logout")
    public ResponseEntity<BaseResultModel> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("SessionModel");
        BaseResultModel baseResultModel = new BaseResultModel();
        baseResultModel.setSuccess(true);
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }

    @RequestMapping("/loginIn")
    public ResponseEntity<BaseResultModel> loginIn(String valicode, TSysUserinfoModel tSysUserinfoModel, HttpServletRequest servletRequest, HttpServletResponse httpServletResponse) {
        BaseResultModel baseResultModel = new BaseResultModel();
        DataService dataService = this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);
        UserCacheModel cacheModel = SystemSetting.JD_ServerCache.getUserCacheModel(tSysUserinfoModel.getusername());
        String salt = null;
        String userId = null;
        //加密后的密文
        String md5Pwd = null;
        String mc = null;
        if (cacheModel != null) {
            salt = cacheModel.getSalt();
            userId = cacheModel.getUserId();
            md5Pwd = cacheModel.getPassword();
            mc=cacheModel.getName();
        } else {
            TXtAqZhxxDao dao = new TXtAqZhxxDao(dataContext);
            List<BaseModel> all = dao.findAll("yhm=? and sfyx_bj=1", tSysUserinfoModel.getusername());
            if (ObjectUtils.isEmpty(all) || all.size() == 0) {
                baseResultModel.setSuccess(false);
                baseResultModel.setMsg("用户名或密码有误");
                return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
            }
            TXtAqZhxxModel zhxxModel = (TXtAqZhxxModel) all.get(0);
            userId = zhxxModel.getry_xh();
            md5Pwd = zhxxModel.getmm();
            mc = zhxxModel.getmc();
            SystemSetting.JD_ServerCache.updateZhxxCacheModel(zhxxModel);
        }
        String getpassword = tSysUserinfoModel.getpassword();//输入的密码
        if (chkPassword(getpassword, md5Pwd)) {
            //登陆成功
            baseResultModel.setSuccess(true);
            SessionModel sessionModel = new SessionModel();
            sessionModel.setUserId(userId);
            sessionModel.setLogin(true);
            sessionModel.setUserName(tSysUserinfoModel.getusername());
            sessionModel.setLogin(true);
            sessionModel.setUserMc(mc);
            servletRequest.getSession().setAttribute("SessionModel",gson.toJson(sessionModel));
            //token登录-start
            String token = JwtUtil.getToken(tSysUserinfoModel.getusername(), sessionModel);
            HashMap<Object, Object> tokeMap = new HashMap<>();
            tokeMap.put("Authorization", token);
            tokeMap.put("userid", userId);
            tokeMap.put("username", tSysUserinfoModel.getusername());
            tokeMap.put("usermc", mc);

            TXtQxRy2XtmkDao ry2XtmkDao=new TXtQxRy2XtmkDao();
            BaseResultQueryModel queryModel=new BaseResultQueryModel();
            queryModel.setRy_xh(userId);

            List<TXtQxXtmkDtoModel> dtoModelList = dataService.selectT_XT_QX_XTMK(new PagingOptions(), queryModel,WeChatFields.Script_SelectT_XT_QX_XTMK_BY_RY);
            tokeMap.put("xtmks", dtoModelList);

            baseResultModel.setMsg("登录成功");
            baseResultModel.setTag(tokeMap);
            //token登录-end
            dataContext.release();
            dataService.disposeInCurrentThread();
            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
        } else {
            baseResultModel.setMsg("用户名或密码有误");
            baseResultModel.setSuccess(false);
        }
        dataContext.release();
        dataService.disposeInCurrentThread();
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }

    //根据权限查询账号信息
    @RequestMapping("/searchZhxxByQx")
    public ResponseEntity<ResultListModel> searchZhxxByQx(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, PagingOptions pagingOptions, TXtAqZhxxQueryModel queryModel) throws Exception {
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
            queryModel.setsfyx_bj(1);
            //查询当前登录用户的角色
            TXtQxRy2JsQueryModel ry2JsQueryModel=new TXtQxRy2JsQueryModel();
            ry2JsQueryModel.setry_xh(sessionModel.getUserId());
            List<TXtQxRy2JsDtoModel> Ry2JsList = dataService.selectT_XT_QX_RY_2_JS(pagingOptions, ry2JsQueryModel,null);
            if(Ry2JsList.size()>0){
                TXtQxRy2JsDtoModel ry2JsDtoModel=Ry2JsList.get(0);
                if(ry2JsDtoModel!=null&&ry2JsDtoModel.getjs_dm().equals(WeChatEnums._部门普通用户.getIndex())){
                   queryModel.setry_xh(sessionModel.getUserId());
                }else if(ry2JsDtoModel!=null&&ry2JsDtoModel.getjs_dm().equals(WeChatEnums._部门管理员.getIndex())){
                    //查询所在部门
                    TXtQxRy2BmQueryModel ry2BmQueryModel=new TXtQxRy2BmQueryModel();
                    ry2BmQueryModel.setry_xh(sessionModel.getUserId());
                    List<TXtQxRy2BmDtoModel> Ry2BmList = dataService.selectT_XT_QX_RY_2_BM(pagingOptions, ry2BmQueryModel,null);
                    if(Ry2BmList.size()>0){
                        TXtQxRy2BmDtoModel ry2BmDtoModel=Ry2BmList.get(0);
                        if(ry2BmDtoModel!=null){
                            queryModel.setyyz_xh(ry2BmDtoModel.getbm_dm());
                        }
                    }
                }
            }
            //归属企业查询
            fillQueryModel4Crop(dataContext,queryModel,sessionModel);
            List<TXtAqZhxxDtoModel>  dtoModelList= dataService.selectT_XT_AQ_ZHXX(pagingOptions, queryModel,null);
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

    //查询所有账号信息
    @RequestMapping("/searchZhxx")
    public ResponseEntity<ResultListModel> searchZhxx(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, PagingOptions pagingOptions, TXtAqZhxxQueryModel queryModel) throws Exception {
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
            List<TXtAqZhxxDtoModel> dtoModelList = dataService.selectT_XT_AQ_ZHXX(pagingOptions, queryModel,null);
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

    //查询人员权限
    @RequestMapping("/queryRyQx")
    public ResponseEntity<ResultListModel> queryRyQx(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, PagingOptions pagingOptions, BaseResultQueryModel queryModel) throws Exception {
        ResultListModel resultListModel = new ResultListModel();
        if(Utility.isNullOrEmpty(queryModel.getRy_xh())){
            resultListModel.setSuccess(false);
            resultListModel.setMsg("参数有误");
            return new ResponseEntity<>(resultListModel, HttpStatus.OK);
        }
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            resultListModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
//            httpServletResponse.sendRedirect(WeChatFields.LOGIN_URL);
            resultListModel.setSuccess(false);
        }else{
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            List<TXtQxXtmkDtoModel> dtoModelList = dataService.selectT_XT_QX_XTMK(pagingOptions, queryModel,WeChatFields.Script_SelectT_XT_QX_XTMK_BY_RY);
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

    //查询当前账号权限
    @RequestMapping("/queryCurrentRyQx")
    public ResponseEntity<ResultListModel> queryCurrentRyQx(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, PagingOptions pagingOptions, BaseResultQueryModel queryModel) throws Exception {
        ResultListModel resultListModel = new ResultListModel();
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (!sessionModel.isLogin()){
            resultListModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
            resultListModel.setSuccess(false);
        }else{
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            queryModel.setRy_xh(sessionModel.getUserId());
            List<TXtQxXtmkDtoModel> dtoModelList = dataService.selectT_XT_QX_XTMK(pagingOptions, queryModel,WeChatFields.Script_SelectT_XT_QX_XTMK_BY_RY);
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
    //查询系统模块
    @RequestMapping("/searchXtmk")
    public ResponseEntity<ResultListModel> searchXtmk(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, PagingOptions pagingOptions, BaseResultQueryModel queryModel) throws Exception {
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

            List<TXtQxXtmkDtoModel> dtoModelList = dataService.selectT_XT_QX_XTMK(pagingOptions, queryModel,WeChatFields.Script_SelectT_XT_QX_XTMK);
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


    //添加或修改用户信息
    @Transactional
    @RequestMapping("/saveZhxx")
    public ResponseEntity<BaseResultModel> saveZhxx(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse,TXtAqZhxxModel model,String bm_dm,String js_dm) throws Exception {
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
            if (Utility.isNullOrEmpty(model.getry_xh())) {
                isAdd=true;
                model.setry_xh(Utility.createUniqueId());
                String md5String = Utility.getMD5String(model.getmm());
                model.setmm(md5String);
                model.setlrry_xh(loginId);
                model.setlr_sj(now);
            }

            if(isAdd){
                if(Utility.isNullOrEmpty(model.getyhm())||Utility.isNullOrEmpty(model.getmm())){
                    baseResultModel.setSuccess(false);
                    baseResultModel.setMsg("请输入用户名/密码");
                    return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
                }
            }
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            TXtAqZhxxDao dao = new TXtAqZhxxDao(dataContext);

            try {
                List<BaseModel> old_data=dao.findAll("yhm=?",model.getyhm());
                if(isAdd&old_data.size()>0){
                    baseResultModel.setSuccess(false);
                    baseResultModel.setMsg("用户名已存在");
                    return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
                }
                if(!isAdd&old_data.size()>0){
                    TXtAqZhxxModel data=(TXtAqZhxxModel)old_data.get(0);
                    if(!data.getry_xh().equals(model.getry_xh())){
                        baseResultModel.setSuccess(false);
                        baseResultModel.setMsg("用户名已存在");
                        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
                    }
                }
                model.setxg_sj(now);
                model.setxgry_xh(loginId);
                model.setsfyx_bj(1);
                if (isAdd) {
                    dao.insertOnSubmit(model);

                    //处理部门关系表
                    if(!Utility.isNullOrEmpty(bm_dm)){
                        String sql1="delete from "+TXtQxRy2BmModel.TABLE_NAME+" where ry_xh="+Utility.getSqlFielStatement("string",model.getry_xh())+" ";
                        DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql1);
                        TXtQxRy2BmDao ry2BmDao=new TXtQxRy2BmDao();
                        TXtQxRy2BmModel ry2BmModel=new TXtQxRy2BmModel();
                        ry2BmModel.setry_xh(model.getry_xh());
                        ry2BmModel.setbm_dm(bm_dm);
                        ry2BmModel.setlr_sj(now);
                        ry2BmModel.setlrry_xh(loginId);
                        ry2BmModel.setxg_sj(now);
                        ry2BmModel.setxgry_xh(loginId);
                        ry2BmModel.setsfyx_bj(1);
                        ry2BmDao.insertOnSubmit(ry2BmModel);
                    }

                    if(!Utility.isNullOrEmpty(js_dm)){
                        //处理角色关系表
                        String sql2="delete from "+TXtQxRy2JsModel.TABLE_NAME+" where ry_xh="+Utility.getSqlFielStatement("string",model.getry_xh())+" ";
                        DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql2);
                        TXtQxRy2JsDao ry2JsDao=new TXtQxRy2JsDao();
                        TXtQxRy2JsModel ry2JsModel=new TXtQxRy2JsModel();
                        ry2JsModel.setry_xh(model.getry_xh());
                        ry2JsModel.setjs_dm(js_dm);
                        ry2JsModel.setlr_sj(now);
                        ry2JsModel.setlrry_xh(loginId);
                        ry2JsModel.setxg_sj(now);
                        ry2JsModel.setxgry_xh(loginId);
                        ry2JsModel.setsfyx_bj(1);
                        ry2JsDao.insertOnSubmit(ry2JsModel);

                        if(js_dm.equals(WeChatEnums._部门管理员.getIndex())){
                            TXtQxRy2XtmkDao ry2XtmkDao=new TXtQxRy2XtmkDao();
                            List<BaseModel> list=ry2XtmkDao.findAll("ry_xh=? and xtmk_dm=?",model.getry_xh(),WeChatEnums._菜单用户管理.getIndex());
                            if(list.size()==0){
                                TXtQxRy2XtmkModel ry2XtmkModel=new TXtQxRy2XtmkModel();
                                ry2XtmkModel.setry_xh(model.getry_xh());
                                ry2XtmkModel.setxtmk_dm(WeChatEnums._菜单用户管理.getIndex());
                                ry2XtmkModel.setlr_sj(now);
                                ry2XtmkModel.setlrry_xh(loginId);
                                ry2XtmkModel.setxg_sj(now);
                                ry2XtmkModel.setxgry_xh(loginId);
                                ry2XtmkModel.setsfyx_bj(1);
                                ry2XtmkDao.insertOnSubmit(ry2XtmkModel);
                            }
                        }
                    }
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
    @RequestMapping("/deleteUser")
    public ResponseEntity<BaseResultModel> deleteUser(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse,TXtAqZhxxModel model) throws Exception {
        BaseResultModel baseResultModel = new BaseResultModel();
        String loginId = "";
        SessionModel sessionModel = ChkLogin(servletRequest);
        Date now=new Date();
        if (sessionModel.isLogin() == false){
            baseResultModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
            baseResultModel.setSuccess(false);
        }else{
            loginId = sessionModel.getUserId();

            if(Utility.isNullOrEmpty(model.getry_xh())){
                baseResultModel.setSuccess(false);
                baseResultModel.setMsg("参数有误");
                return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
            }
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            TXtAqZhxxDao dao = new TXtAqZhxxDao(dataContext);

            try {
                List<BaseModel> old_data=dao.findAll("ry_xh=?",model.getry_xh());
                if(old_data.size()>0){
                    TXtAqZhxxModel data=(TXtAqZhxxModel)old_data.get(0);
                    data.doUpdate();
                    data.setxg_sj(now);
                    model.setxgry_xh(loginId);
                    model.setsfyx_bj(0);
                    dao.updateOnSubmit(model);
                }else{
                    baseResultModel.setSuccess(false);
                    baseResultModel.setMsg("用户名不存在");
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

    //修改密码
    @Transactional
    @RequestMapping("/updatePwd")
    public ResponseEntity<BaseResultModel> updatePwd(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse,TXtAqZhxxModel model,String oldMm) throws Exception {
        BaseResultModel baseResultModel = new BaseResultModel();
        if(Utility.isNullOrEmpty(model.getry_xh())||Utility.isNullOrEmpty(oldMm)||Utility.isNullOrEmpty(model.getmm())){
            baseResultModel.setSuccess(false);
            baseResultModel.setMsg("参数有误");
            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
        }
        String loginId = "";
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            baseResultModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
//            httpServletResponse.sendRedirect(WeChatFields.LOGIN_URL);
            baseResultModel.setSuccess(false);
        }else{
            loginId = sessionModel.getUserId();
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            TXtAqZhxxDao dao = new TXtAqZhxxDao(dataContext);
            List<BaseModel> all = dao.findAll("ry_xh=?",model.getry_xh());
            if (ObjectUtils.isEmpty(all) || all.size() == 0) {
                baseResultModel.setSuccess(false);
                baseResultModel.setMsg("用户名或密码有误");
                return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
            }
            TXtAqZhxxModel tSysUserinfoModel1 = (TXtAqZhxxModel) all.get(0);
            if (!chkPassword(oldMm, tSysUserinfoModel1.getmm())) {
                baseResultModel.setSuccess(false);
                baseResultModel.setMsg("原密码输入不正确");
                return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
            }
            try {
                model.setmm(Utility.getMD5String(model.getmm()));
                model.setxg_sj(new Date());
                model.setxgry_xh(loginId);
                model.setsfyx_bj(1);
                dao.updateOnSubmit(model);

                //更新缓存
                UserCacheModel cacheModel = SystemSetting.JD_ServerCache.getUserCacheModel(tSysUserinfoModel1.getyhm());
                cacheModel.setPassword(model.getmm());
                SystemSetting.JD_ServerCache.updateUserCacheModel(cacheModel);
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


    //设置人员2菜单
    @RequestMapping("/setRy2Xtmk")
    public ResponseEntity<BaseResultModel> setRy2Xtmk(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse,TXtQxRy2XtmkModel model,String xtmk_dms) throws Exception {
        BaseResultModel baseResultModel = new BaseResultModel();
        if(Utility.isNullOrEmpty(model.getry_xh())||Utility.isNullOrEmpty(xtmk_dms)){
            baseResultModel.setSuccess(false);
            baseResultModel.setMsg("参数有误");
            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
        }
        String loginId = "";
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            baseResultModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
//            httpServletResponse.sendRedirect(WeChatFields.LOGIN_URL);
            baseResultModel.setSuccess(false);
        }else{
            loginId = sessionModel.getUserId();
            String sql="delete from "+TXtQxRy2XtmkModel.TABLE_NAME+" where ry_xh ='"+model.getry_xh()+"'";
            DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql);
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            String[] xtmk_dmArray=Utility.getSeparatedItems(xtmk_dms,',');
            TXtQxRy2XtmkDao dao = new TXtQxRy2XtmkDao(dataContext);
            Date now=new Date();
            TXtQxRy2XtmkModel d=null;
            try {
                for(String js_dm:xtmk_dmArray){
                    d=new TXtQxRy2XtmkModel();
                    d.setry_xh(model.getry_xh());
                    d.setxtmk_dm(js_dm);
                    d.setlrry_xh(loginId);
                    d.setlr_sj(now);
                    d.setxg_sj(now);
                    d.setxgry_xh(loginId);
                    d.setsfyx_bj(1);
                    dao.insertOnSubmit(d);
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


    //查询部门
    @RequestMapping("/searchBm")
    public ResponseEntity<ResultListModel> searchBm(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, PagingOptions pagingOptions, TXtQxBmQueryModel queryModel) throws Exception {
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
            queryModel.setsfyx_bj(1);
            List<TXtQxBmDtoModel> dtoModelList = dataService.selectT_XT_QX_BM(pagingOptions, queryModel,null);
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

    //查询角色
    @RequestMapping("/searchJs")
    public ResponseEntity<ResultListModel> searchJs(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, PagingOptions pagingOptions, TXtQxJsQueryModel queryModel) throws Exception {
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
            queryModel.setsfyx_bj(1);
            List<TXtQxJsDtoModel> dtoModelList = dataService.selectT_XT_QX_JS(pagingOptions, queryModel,null);
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

    //添加部门信息
    @Transactional
    @RequestMapping("/saveBmxx")
    public ResponseEntity<BaseResultModel> saveBmxx(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse,TXtQxBmModel model) throws Exception {
        BaseResultModel baseResultModel = new BaseResultModel();
        String loginId = "";
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            baseResultModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
//            httpServletResponse.sendRedirect(WeChatFields.LOGIN_URL);
            baseResultModel.setSuccess(false);
        }else{
            loginId = sessionModel.getUserId();
            if(Utility.isNullOrEmpty(model.getmc())){
                baseResultModel.setSuccess(false);
                baseResultModel.setMsg("请输入名称");
                return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
            }
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            TXtQxBmDao dao = new TXtQxBmDao(dataContext);
            Date now=new Date();
            try {
                List<BaseModel> old_data=dao.findAll("mc=?",model.getmc());
                if(old_data.size()>0){
                    baseResultModel.setSuccess(false);
                    baseResultModel.setMsg("该部门已存在");
                    return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
                }

                model.setbm_dm(Utility.createUniqueId());
                model.setlr_sj(now);
                model.setlrry_xh(loginId);
                model.setxg_sj(now);
                model.setxgry_xh(loginId);
                model.setsfyx_bj(1);
                dao.insertOnSubmit(model);
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

    //添加角色信息
    @Transactional
    @RequestMapping("/saveJsxx")
    public ResponseEntity<BaseResultModel> saveJsxx(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse,TXtQxJsModel model) throws Exception {
        BaseResultModel baseResultModel = new BaseResultModel();
        String loginId = "";
        SessionModel sessionModel = ChkLogin(servletRequest);
        if (sessionModel.isLogin() == false){
            baseResultModel.setLoginStatus(HttpStatus.UNAUTHORIZED.value());
            baseResultModel.setSuccess(false);
        }else{
            loginId = sessionModel.getUserId();
            if(Utility.isNullOrEmpty(model.getmc())){
                baseResultModel.setSuccess(false);
                baseResultModel.setMsg("请输入名称");
                return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
            }
            DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
            TXtQxJsDao dao = new TXtQxJsDao(dataContext);
            Date now=new Date();
            try {
                List<BaseModel> old_data=dao.findAll("mc=?",model.getmc());
                if(old_data.size()>0){
                    baseResultModel.setSuccess(false);
                    baseResultModel.setMsg("该角色已存在");
                    return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
                }

                model.setjs_dm(Utility.createUniqueId());
                model.setlr_sj(now);
                model.setlrry_xh(loginId);
                model.setxg_sj(now);
                model.setxgry_xh(loginId);
                model.setsfyx_bj(1);
                dao.insertOnSubmit(model);
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


//    //设置人员2角色
//    @RequestMapping("/setRy2Js")
//    public ResponseEntity<BaseResultModel> setRy2Js(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse,TXtQxRy2JsModel model,String js_dms) throws Exception {
//        BaseResultModel baseResultModel = new BaseResultModel();
//        if(Utility.isNullOrEmpty(model.getry_xh())||Utility.isNullOrEmpty(js_dms)){
//            baseResultModel.setSuccess(false);
//            baseResultModel.setMsg("参数有误");
//            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
//        }
//        String loginId = "";
//        SessionModel sessionModel = ChkLogin(servletRequest);
//        if (sessionModel.isLogin() == false)
//            httpServletResponse.sendRedirect(WeChatFields.LOGIN_URL);
//        else
//            loginId = sessionModel.getUserId();
//
//        DataService dataService = this.getDataService();
//        DataContext dataContext = dataService.getDataContext(druidConfig);
//        String[] js_dmArray=Utility.getSeparatedItems(js_dms,',');
//        TXtAqZhxxDao dao = new TXtAqZhxxDao(dataContext);
//        Date now=new Date();
//        TXtQxRy2JsModel d=null;
//
//        try {
//            for(String js_dm:js_dmArray){
//                d=new TXtQxRy2JsModel();
//                d.setry_xh(model.getry_xh());
//                d.setjs_dm(js_dm);
//                d.setlrry_xh(loginId);
//                d.setlr_sj(now);
//                d.setxg_sj(now);
//                d.setxgry_xh(loginId);
//                d.setsfyx_bj(1);
//                dao.insertOnSubmit(d);
//            }
//            baseResultModel.setSuccess(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error(e.getMessage());
//            baseResultModel.setSuccess(false);
//        } finally {
//            dataContext.release();
//            dataService.disposeInCurrentThread();
//        }
//        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
//    }



//    //设置角色2菜单
//    @RequestMapping("/setJs2Xtmk")
//    public ResponseEntity<BaseResultModel> setJs2Xtmk(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse,TXtQxXtjs2XtmkModel model,String xtmk_dms)throws Exception {
//        BaseResultModel baseResultModel = new BaseResultModel();
//        if(Utility.isNullOrEmpty(model.getjs_dm())||Utility.isNullOrEmpty(model.getxtmk_dm())){
//            baseResultModel.setSuccess(false);
//            baseResultModel.setMsg("参数有误");
//            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
//        }
//        String loginId = "";
//        SessionModel sessionModel = ChkLogin(servletRequest);
//        if (sessionModel.isLogin() == false)
//            httpServletResponse.sendRedirect(WeChatFields.LOGIN_URL);
//        else
//            loginId = sessionModel.getUserId();
//
//        DataService dataService = this.getDataService();
//        DataContext dataContext = dataService.getDataContext(druidConfig);
//        String[] xtmk_dmArray=Utility.getSeparatedItems(xtmk_dms,',');
//        TXtAqZhxxDao dao = new TXtAqZhxxDao(dataContext);
//        Date now=new Date();
//        TXtQxXtjs2XtmkModel d=null;
//        try {
//            for(String js_dm:xtmk_dmArray){
//                d=new TXtQxXtjs2XtmkModel();
//                d.setjs_dm(model.getjs_dm());
//                d.setxtmk_dm(js_dm);
//                d.setlrry_xh(loginId);
//                d.setlr_sj(now);
//                d.setxg_sj(now);
//                d.setxgry_xh(loginId);
//                d.setsfyx_bj(1);
//                dao.insertOnSubmit(d);
//            }
//            baseResultModel.setSuccess(true);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error(e.getMessage());
//            baseResultModel.setSuccess(false);
//        } finally {
//            dataContext.release();
//            dataService.disposeInCurrentThread();
//        }
//        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
//    }


}
