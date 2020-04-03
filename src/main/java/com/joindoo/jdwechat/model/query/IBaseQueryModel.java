package com.joindoo.jdwechat.model.query;

import com.joindoo.jdwechat.model.sys.ScriptItemModel;

/**
 * Created by zhuqiang on 2018/5/15.
 */
public interface IBaseQueryModel {
    void fillParams(ScriptItemModel scriptItemModel);
}
