package com.joindoo.jdwechat.model.query;

import com.joindoo.jdwechat.data.IDataParamsHandler;
import com.joindoo.jdwechat.model.sys.ScriptItemModel;

import java.util.Date;

/**
 * Created by zhuqiang on 2018/4/3.
 */
public class BaseQueryModel implements IBaseQueryModel{
    public IDataParamsHandler dataParamsHandler;
    private String jd_token;
    private int limit;
    private Date startTime;
    private Date endTime;

    public String getJd_token() {
        return jd_token;
    }

    public void setJd_token(String jd_token) {
        this.jd_token = jd_token;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public void fillParams(ScriptItemModel scriptItemModel) {
        if(null!=dataParamsHandler){
            dataParamsHandler.resolveParams(scriptItemModel);
        }
    }
}
