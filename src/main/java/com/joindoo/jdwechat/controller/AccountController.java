package com.joindoo.jdwechat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.beans.BaseModel;
import com.joindoo.jdwechat.beans.TSysUserinfoModel;
import com.joindoo.jdwechat.beans.TXtAqZhxxModel;
import com.joindoo.jdwechat.beans.TXtWxyhModel;
import com.joindoo.jdwechat.cache.SessionModel;
import com.joindoo.jdwechat.cache.UserCacheModel;
import com.joindoo.jdwechat.codeGen.TXtAqZhxxDtoModel;
import com.joindoo.jdwechat.daos.TSysUserinfoDao;
import com.joindoo.jdwechat.daos.TXtAqZhxxDao;
import com.joindoo.jdwechat.daos.TXtWxyhDao;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.web.WxLogOnModel;
import com.joindoo.jdwechat.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuqiang on 2018/3/31.
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {
    private static int att_times = 5;
    @Autowired
    DruidConfig druidConfig;

    @RequestMapping("/")
    public ModelAndView index() {
        return redirectTo("/account/login");
    }


    @RequestMapping(value = "login")
    public ResponseEntity<BaseResultModel> login(HttpServletRequest request, WxLogOnModel model) {
        BaseResultModel baseResultModel = new BaseResultModel();
        if (Utility.isNullOrEmpty(model.getCode())) {
            baseResultModel.setSuccess(false);
            baseResultModel.setMsg("参数有误");
        } else {
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + SystemSetting.SYS_WECHAT_APPID + "&secret=" +
                    SystemSetting.SYS_WECHAT_APPSECRET + "&js_code=" + model.getCode() + "&grant_type=authorization_code";
            //调用get方法发起get请求，并把返回值赋值给returnvalue
            String returnvalue = HttpUtils.httpGet(url, null);
            logger.info(url);//打印发起请求的url
            logger.info(returnvalue);//打印调用GET方法返回值
            if (returnvalue.contains("40029")) {
                baseResultModel.setSuccess(false);
                baseResultModel.setMsg("code无效");
            } else {
                //定义一个json对象。
                JSONObject convertvalue = new JSONObject();
                //将得到的字符串转换为json
                convertvalue = (JSONObject) JSON.parse(returnvalue);

//            System.out.println("return openid is ："+(String)convertvalue.get("openid")); //打印得到的openid
//            System.out.println("return sessionkey is ："+(String)convertvalue.get("session_key"));//打印得到的sessionkey，
                //把openid和sessionkey分别赋值给openid和sessionkey
                String openid = (String) convertvalue.get("openid");
                String sessionkey = (String) convertvalue.get("session_key");//定义两个变量存储得到的openid和session_key.
                TXtWxyhModel tXtWxyhModel = new TXtWxyhModel();
                tXtWxyhModel.setopenid(openid);
                tXtWxyhModel.setsession_key(sessionkey);
                if(!Utility.isNullOrEmpty(model.getEncryptedData())){
//                    logger.info(model.getEncryptedData());
//                    logger.info(model.getIv());
                    try {
//                        AesCbcUtil.decrypt(model.getEncryptedData(),sessionkey,model.getIv(),"UTF-8");
//                        logger.info("=============");
                        JSONObject result = AesCbcUtil.getUserInfo(model.getEncryptedData(), sessionkey, model.getIv());
                        if (null != result) {
//                            logger.info(result.toJSONString());
                            tXtWxyhModel.setis_valid(1);
                            tXtWxyhModel.setnickname(result.get("nickName").toString());
                            tXtWxyhModel.setgender(result.get("gender").toString());
                            tXtWxyhModel.setprovince(result.get("province").toString());
                            tXtWxyhModel.setcity(result.get("city").toString());
                            tXtWxyhModel.setcountry(result.get("country").toString());
                            tXtWxyhModel.setavatarurl(result.get("avatarUrl").toString());
                            tXtWxyhModel.setlanguage(result.get("language").toString());
                            tXtWxyhModel.setcreate_time(new Date());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error(e.getMessage());
                    }
                    if(!SystemSetting.JD_ServerCache.isExistWxyh(tXtWxyhModel)){
                        //不存在缓存中，持久化
                        tXtWxyhModel.setid(Utility.createUniqueId());
                        SystemSetting.JD_ServerCache.updateWxyhCache(tXtWxyhModel);
                        //持久化
                        DataService dataService=this.getDataService();
                        DataContext dataContext=dataService.getDataContext(druidConfig);
                        TXtWxyhDao dao=new TXtWxyhDao(dataContext);
                        try {
                            //去除表情符号
                            tXtWxyhModel.setnickname(EmojiFilter.filterEmoji(tXtWxyhModel.getnickname()));
                            dao.insertOnSubmit(tXtWxyhModel);
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.error(e.getMessage()+"\n"+tXtWxyhModel.getopenid()+","+tXtWxyhModel.getnickname());
                        }finally {
                            dataContext.release();
                            dataService.disposeInCurrentThread();
                        }
                    }else{
                        tXtWxyhModel= SystemSetting.JD_ServerCache.getWxyhCacheModel(tXtWxyhModel.getopenid());
                    }
                }


                baseResultModel.setTag(tXtWxyhModel);
                baseResultModel.setSuccess(true);
            }

        }
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }

    @RequestMapping(value = "getAccount", method = RequestMethod.GET)
    public ResponseEntity<BaseResultModel> getAccount(HttpServletRequest servletRequest){
        BaseResultModel baseResultModel = new BaseResultModel();
        SessionModel sessionModel = ChkLogin(servletRequest);
        if(null!=sessionModel){
            UserCacheModel cacheModel= SystemSetting.JD_ServerCache.getZhxxCacheModelById(sessionModel.getUserId());
            if(null!=cacheModel){
                cacheModel.setSsoTime(new Date());
                baseResultModel.setSuccess(true);
                baseResultModel.setTag(cacheModel);
                SystemSetting.JD_ServerCache.updateZhxxCacheModel(cacheModel);
                logger.info("getAccount:"+cacheModel.getUserId());
            }
        }
        return new ResponseEntity<BaseResultModel>(baseResultModel,HttpStatus.OK);
    }
    @RequestMapping(value = "checkSsoAccount", method = RequestMethod.GET)
    public ResponseEntity<BaseResultModel> checkSsoAccount(HttpServletRequest servletRequest,UserCacheModel userCacheModel){
        BaseResultModel baseResultModel = new BaseResultModel();
        logger.info("checkSsoAccount:"+userCacheModel.getUserId());
        if(null!=userCacheModel&&!Utility.isNullOrEmpty(userCacheModel.getUserId())){
            UserCacheModel cacheModel= SystemSetting.JD_ServerCache.getZhxxCacheModelById(userCacheModel.getUserId());
            if(null!=cacheModel){
                if(cacheModel.getSsoTime()!=null&&new Date().getTime()- cacheModel.getSsoTime().getTime()<60*1000){
                    baseResultModel.setSuccess(true);
                    DataService dataService=this.getDataService();
                    DataContext dataContext=dataService.getDataContext(druidConfig);
                    TXtAqZhxxDao dao = new TXtAqZhxxDao(dataContext);
                    List<BaseModel> all = dao.findAll("RY_XH=? and sfyx_bj=1", cacheModel.getUserId());
                    if(all.size()>0){
                        TXtAqZhxxModel zhxxModel = (TXtAqZhxxModel) all.get(0);
                        TXtAqZhxxDtoModel dtoModel=new TXtAqZhxxDtoModel();
                        dtoModel.setaspnet_xh(zhxxModel.getaspnet_xh());
                        baseResultModel.setTag(dtoModel);
                        logger.info("checkSsoAccount success");
                    }
                    dataContext.release();
                    dataService.disposeInCurrentThread();
                }
            }
        }
        return new ResponseEntity<BaseResultModel>(baseResultModel,HttpStatus.OK);
    }
    @RequestMapping(value = "loginIn", method = RequestMethod.POST)
    public ResponseEntity<BaseResultModel> loginIn(String valicode, TSysUserinfoModel tSysUserinfoModel, HttpServletRequest servletRequest, HttpServletResponse httpServletResponse) {
        BaseResultModel baseResultModel = new BaseResultModel();
        DataService dataService = this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);
        UserCacheModel cacheModel = SystemSetting.JD_ServerCache.getUserCacheModel(tSysUserinfoModel.getusername());
        String salt = null;
        String userId = null;
        //加密后的密文
        String md5Pwd = null;
        if (cacheModel != null) {
            salt = cacheModel.getSalt();
            userId = cacheModel.getUserId();
            md5Pwd = cacheModel.getPassword();
        } else {
            TSysUserinfoDao dao = new TSysUserinfoDao(dataContext);
            List<BaseModel> all = dao.findAll("username=? and is_valid=1", tSysUserinfoModel.getusername());
            if (ObjectUtils.isEmpty(all) || all.size() == 0) {
                baseResultModel.setSuccess(false);
                baseResultModel.setMsg("用户名或密码有误");
                return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
            }
            TSysUserinfoModel tSysUserinfoModel1 = (TSysUserinfoModel) all.get(0);
            salt = tSysUserinfoModel1.getsalt();
            userId = tSysUserinfoModel1.getid();
            md5Pwd = tSysUserinfoModel1.getpassword();
            SystemSetting.JD_ServerCache.updateUserCacheModel(tSysUserinfoModel1);
        }
        String getpassword = tSysUserinfoModel.getpassword();//输入的密码
        if (checkPwd(getpassword, md5Pwd, salt)) {
            //登陆成功
            baseResultModel.setSuccess(true);
            SessionModel sessionModel = new SessionModel();
            sessionModel.setUserId(userId);
            sessionModel.setLogin(true);
            sessionModel.setUserName(tSysUserinfoModel.getusername());
            sessionModel.setLogin(true);
            //session登录－－start
            servletRequest.getSession().setAttribute("SessionModel", gson.toJson(sessionModel));
            //token登录-start
            String token = JwtUtil.getToken(tSysUserinfoModel.getusername(), sessionModel);
            HashMap<Object, Object> tokeMap = new HashMap<>();
            tokeMap.put("Authorization", token);
            tokeMap.put("username", tSysUserinfoModel.getusername());

            baseResultModel.setMsg("登录成功");
            baseResultModel.setTag(tokeMap);
            //token登录-end
            dataContext.release();
            dataService.disposeInCurrentThread();
            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
        } else {
            baseResultModel.setMsg("用户名或密码有误");
            baseResultModel.setSuccess(false);
            if (cacheModel.getAttempts_times() == att_times) {
                baseResultModel.setMsg("账户被锁定,请联系维护人员");
            } else {
                baseResultModel.setMsg("用户名或密码有误，还有" + att_times-- + "次锁定！");
            }
        }
        dataContext.release();
        dataService.disposeInCurrentThread();
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }


    /**
     * 生成验证码
     *
     * @param response
     * @param session
     * @throws Exception
     */
    @RequestMapping("/valicode")
    public void valicode(HttpServletResponse response, HttpServletRequest httpServletRequest, HttpSession session) throws Exception {
        //利用图片工具生成图片
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = VerifyUtil.createImage();
        //将验证码存入Session
        session.setAttribute("valicode", objs[0]);

        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];

        response.setContentType("image/png");

        //验证码token－－－start
        String valicodepublickey = Utility.createUniqueId();
        String valicodePublicKeyToken = JwtUtil.getValicodePublicKeyToken(valicodepublickey, objs[0].toString());
        response.setHeader("valicodepublickeyToken", valicodePublicKeyToken);
        //验证码token－－－end

        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

    @RequestMapping("/error")
    public ModelAndView error() {
        return new ModelAndView("/account/error");
    }

    /**
     * 退出就是往缓存里放纪录（这些纪录会有定时器清）
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public ResponseEntity<BaseResultModel> logout(HttpServletRequest request) {
        BaseResultModel baseResultModel = new BaseResultModel();
        baseResultModel.setSuccess(true);
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }


    /**
     * 更新微信群、街道、社区
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "wxyh",method = RequestMethod.POST)
    public ResponseEntity<BaseResultModel> wxyh(HttpServletRequest request, TXtWxyhModel model) {
        BaseResultModel baseResultModel = new BaseResultModel();
        baseResultModel.setSuccess(true);
        if(!Utility.isNullOrEmpty(model.getopenid())&&SystemSetting.JD_ServerCache.isExistWxyh(model)){
            //todo 更新并持久化微信用户信息 只要缓存中昵称是第一次有，则持久化
            TXtWxyhModel xtWxyhModel=SystemSetting.JD_ServerCache.getWxyhCacheModel(model.getopenid());
            if(Utility.isNullOrEmpty(xtWxyhModel.getwxq_mc())&&!Utility.isNullOrEmpty(model.getwxq_mc()) ){
                //需要持久化 先找到库中的
                model.setcreate_time(new Date());
                DataService dataService=this.getDataService();
                DataContext dataContext=dataService.getDataContext(druidConfig);
                TXtWxyhDao dao=new TXtWxyhDao(dataContext);
                TXtWxyhModel model_db=dao.find(xtWxyhModel);
                try {
                    if(null!=model_db){
                        model_db.doUpdate();
                        model_db.setwxq_mc(model.getwxq_mc());
                        model_db.setjd_mc(model.getjd_mc());
                        model_db.setsq_mc(model.getsq_mc());
                        if(!Utility.isNullOrEmpty(model.getplatform()))
                            model_db.setplatform(model.getplatform());
                        if(!Utility.isNullOrEmpty(model.getpixelratio()))
                            model_db.setpixelratio(model.getpixelratio());
                        dao.updateOnSubmit(model_db);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage()+"\n"+model.getopenid()+","+model.getnickname());
                }finally {
                    dataContext.release();
                    dataService.disposeInCurrentThread();
                }

            }
        }
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }
}
