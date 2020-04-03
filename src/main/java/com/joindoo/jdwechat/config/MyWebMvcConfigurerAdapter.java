package com.joindoo.jdwechat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TwtInterceptor()).
                addPathPatterns("/Sqbl/search","/FileService/downloadFile");

        registry.addInterceptor(new LoginInterceptor()).
                addPathPatterns("/Zhxx/**","/Enterprise/**","/TDataCustomer/**","/OrderManage/**").
                excludePathPatterns("/Zhxx/login","/Zhxx/loginIn");
        super.addInterceptors(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        ,
        registry.addMapping("/**")
                .allowedOrigins("*")
                .exposedHeaders("valicodepublickeyToken","Authorization")
//                .allowedOrigins("http://localhost:6334")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }
}
