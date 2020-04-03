package com.joindoo.jdwechat.model.web;

/**
 * Created by zhuqiang on 2018/4/3.
 */
public class ConnectServerModel {
    private String ltEle;
    private String cookie;
    private String action;//登录页面表单提交时的地址

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getLtEle() {
        return ltEle;
    }

    public void setLtEle(String ltEle) {
        this.ltEle = ltEle;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
