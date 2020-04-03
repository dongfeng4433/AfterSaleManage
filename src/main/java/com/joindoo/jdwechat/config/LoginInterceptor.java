package com.joindoo.jdwechat.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joindoo.jdwechat.cache.SessionModel;
import com.joindoo.jdwechat.utils.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    protected final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    protected final Gson gson = new GsonBuilder().setDateFormat("yyyyMMddHHmmss").create();

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean flag=true;
//        //TODO  未知
        String ip= IpUtil.getIpAddr(httpServletRequest);
        logger.info("url:"+httpServletRequest.getRequestURI()+",ip:"+ip);
        Object sessionModel1 = httpServletRequest.getSession().getAttribute("SessionModel");
        if (null==sessionModel1){
            httpServletResponse.sendRedirect("/account/login");
            flag=false;
        }else {
            SessionModel sessionModel= gson.fromJson(sessionModel1.toString(), SessionModel.class);
            if (!ObjectUtils.isEmpty(sessionModel)&&!StringUtils.isEmpty(sessionModel.getUserId())){
                flag=true;
            }else {
                httpServletResponse.sendRedirect("/account/login");
                flag=false;
            }

        }

//
//        Object userId = httpServletRequest.getSession().getAttribute("userId");
//        //
//        if (null==userId){
//            httpServletResponse.sendRedirect("/account/toLogin");
//            flag=false;
//        }else {
//            flag=true;
//        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
