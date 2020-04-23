package com.joindoo.jdwechat.cache;

import java.util.Date;

/**
 * Created by zhuqi on 2017/12/28.
 */
public class UserCacheModel {
    private String userId;//人员代码  人员序号
    private String userName;//账户
    private String name;//姓名
    private String groupId;
    private String password;
    private String salt;
    private Integer attempts_times ;//尝试的次数
    private Date ssoTime;//单点登录请求时间
    private String accountType;
    private String enterprise_id;//归属企业id
    private String applet_jd_token;//小程序登录token
    private String applet_user_id;
    private String applet_user_name;
    private String applet_user_mobile;


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getAttempts_times() {
        return attempts_times;
    }

    public void setAttempts_times(Integer attempts_times) {
        this.attempts_times = attempts_times;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSsoTime() {
        return ssoTime;
    }

    public void setSsoTime(Date ssoTime) {
        this.ssoTime = ssoTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public String getApplet_jd_token() {
        return applet_jd_token;
    }

    public void setApplet_jd_token(String applet_jd_token) {
        this.applet_jd_token = applet_jd_token;
    }

    public String getApplet_user_id() {
        return applet_user_id;
    }

    public void setApplet_user_id(String applet_user_id) {
        this.applet_user_id = applet_user_id;
    }

    public String getApplet_user_name() {
        return applet_user_name;
    }

    public void setApplet_user_name(String applet_user_name) {
        this.applet_user_name = applet_user_name;
    }

    public String getApplet_user_mobile() {
        return applet_user_mobile;
    }

    public void setApplet_user_mobile(String applet_user_mobile) {
        this.applet_user_mobile = applet_user_mobile;
    }

    public void fillModel(){

    }
}

