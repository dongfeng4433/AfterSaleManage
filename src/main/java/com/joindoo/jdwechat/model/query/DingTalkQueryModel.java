package com.joindoo.jdwechat.model.query;

public class DingTalkQueryModel extends BaseQueryModel {
    private String authCode;
    private String cropId;


    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getCropId() {
        return cropId;
    }

    public void setCropId(String cropId) {
        this.cropId = cropId;
    }
}
