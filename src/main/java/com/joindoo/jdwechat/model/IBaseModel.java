package com.joindoo.jdwechat.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhuqiang on 2018/4/26.
 */
public interface IBaseModel {
    void fillModelField(ResultSet resultSet, String fileName) throws SQLException;
}
