package com.joindoo.jdwechat.common;

public enum WeChatEnums {

    //region
    // 密钥
    _AK("AK", "C8F2714592694F29858BD8D27E5EF02B"), _SK("SK", "CDA08CB4EC6142FFADB6C515592E3F9F"),
   //1企业2个体
   _企业("企业", "1"), _个体("个体", "2"),
    //endregion

    //region 通知提醒发送方式
    _短信("短信", "1"), _邮件("邮件", "2"), _短信邮件("短信邮件", "3"), _仅推送("仅推送", "4"),
    //endregion

    //region 01 待办 02发起
    _待办("待办", "01"),
    _发起("发起", "02"),
    _历史记录("历史记录", "03"),
    //endregion

    //region 处理状态
    _未处理("未处理", "0"),
    _已处理("已处理", "1"),
    //endregion

    //region 短信发送类型
    _艾特("艾特", "1"),
    _自动发送("自动发送", "2"),
    //endregion

    //region 角色
    _部门管理员("部门管理员", "1"),
    _部门普通用户("部门普通用户", "2"),
    _超级管理员("超级管理员", "3"),

    //endregion

    //region 系统模块
    _菜单用户管理("用户管理", "1"),
    _菜单历史记录("历史记录", "2"),
    //endregion

    //region 信息录入是否有回复信息
    _已回复("已回复", "1");
    //endregion

    private String name;
    private String index;

    private WeChatEnums(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
