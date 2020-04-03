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

    public void fillModel(){

    }
}

