package com.joindoo.jdwechat.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TwtInterceptor implements HandlerInterceptor {
    protected final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    protected final Gson gson = new GsonBuilder().setDateFormat("yyyyMMddHHmmss").create();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (request.getMethod().equals("OPTIONS")){
            return true;
        }
        String authHeader = request.getHeader("Authorization");
        try {
            //判断是否符合标准
            JwtUtil.checkToken(authHeader);
            String refreshToken = JwtUtil.getRefreshToken(authHeader);
            httpServletResponse.setHeader("Authorization",refreshToken);
            return true;

        } catch (Exception e) {
            httpServletResponse.sendError(201,"请重新登录");
            return false;
//            throw new ServletException(e.getMessage());
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
