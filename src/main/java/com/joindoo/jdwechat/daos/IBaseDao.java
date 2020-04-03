package com.joindoo.jdwechat.daos;


import com.joindoo.jdwechat.beans.BaseModel;

import java.util.List;

/**
 * Created by zhuqiang1 on 2016/5/27.
 */
public interface IBaseDao {
    boolean exist(BaseModel model);
    void insert(BaseModel model);
    void delete(BaseModel model);
    void update(BaseModel model);
    BaseModel find(BaseModel model);
    List<BaseModel> findAll(String condition);
    List<BaseModel> queryAll(String sql,Object... objects);
}
