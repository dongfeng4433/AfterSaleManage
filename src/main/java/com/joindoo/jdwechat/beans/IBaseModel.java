package com.joindoo.jdwechat.beans;

import java.sql.ResultSet;

/**
 * Created by zhuqiang1 on 2016/5/27.
 */
public interface IBaseModel {
    String getExist();
    String getSelect();
    String getSelectByCondition(String condition);
    String getInsert();
    String getDelete();
    String getUpdate();
    void initAsInsert();
    void fillModel(ResultSet resultSet);
}
