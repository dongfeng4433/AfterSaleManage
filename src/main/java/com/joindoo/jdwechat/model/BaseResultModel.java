package com.joindoo.jdwechat.model;

import org.springframework.http.HttpStatus;

/**
 * Created by zhuqi on 2017/12/28.
 */
public class BaseResultModel {
    private boolean success = true;
    private String msg;
    private String result;
    private Object tag;
    private String url;
    private int loginStatus=HttpStatus.OK.value();

    public BaseResultModel(){

    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }
}
