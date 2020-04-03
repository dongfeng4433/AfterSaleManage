package com.joindoo.jdwechat.cache;

import com.joindoo.jdwechat.codeGen.beans.TXtQxRy2BmDtoModel;
import com.joindoo.jdwechat.codeGen.beans.TXtQxRy2JsDtoModel;

/**
 * Created by zhuqi on 2017/12/28.
 */
public class SessionModel {
    private String userId;
    private String userName;
    private String groupId;
    private boolean isLogin=true;
    private String userMc;

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

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getUserMc() {
        return userMc;
    }

    public void setUserMc(String userMc) {
        this.userMc = userMc;
    }

}
