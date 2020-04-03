package com.joindoo.jdwechat.entity;

import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.beans.TYwXxlrModel;
import com.joindoo.jdwechat.common.WeChatEnums;

import java.util.Date;

public class NoticeModel  {
    protected String title;
    protected String msg;
    protected String originalId;//相关记录序号
    protected Character sendType;//发送方式
    protected String fqry;//发起人员
    protected String lrry;//录入人员




    //添加信息录入提醒
    public void copyWithSaveXxlr(TYwXxlrModel data,String loginId) {
        this.setTitle("请求协助:");
        String msg="";
        if(!Utility.isNullOrEmpty(data.getry_name()))
            msg+=" 姓名:"+data.getry_name();
        if(!Utility.isNullOrEmpty(data.getry_zj_hm()))
            msg+=" 证件号码:"+data.getry_zj_hm();
        if(!Utility.isNullOrEmpty(data.getsqnr()))
            msg+=" 内容:"+data.getsqnr();
        if(data.gethf_sj()!=null)
            msg+=" 限定回复时间:"+Utility.formatDateTime(data.gethf_sj(),false);
        this.setMsg(msg);
        this.setOriginalId(data.getxxlr_xh());
        this.setSendType(WeChatEnums._仅推送.getIndex().charAt(0));//只做弹出框提示 不发短信邮件
        this.setFqry(data.getlrry_xh());
        this.setLrry(loginId);
    }
    //回复
    public void copyWithReplyXxlr(TYwXxlrModel data,String loginId,String userName) {
        this.setTitle(userName+"回复:");
        this.setMsg(data.getsqnr());
        this.setOriginalId(data.getxxlr_xh());
        this.setSendType(WeChatEnums._仅推送.getIndex().charAt(0));//只做弹出框提示 不发短信邮件
        this.setFqry(data.getlrry_xh());
        this.setLrry(loginId);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public Character getSendType() {
        return sendType;
    }

    public void setSendType(Character sendType) {
        this.sendType = sendType;
    }

    public String getFqry() {
        return fqry;
    }

    public void setFqry(String fqry) {
        this.fqry = fqry;
    }

    public String getLrry() {
        return lrry;
    }

    public void setLrry(String lrry) {
        this.lrry = lrry;
    }
}
