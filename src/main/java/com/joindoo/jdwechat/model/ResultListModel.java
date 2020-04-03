package com.joindoo.jdwechat.model;

import org.springframework.http.HttpStatus;

import java.util.Collection;

/**
 * Created by zhuqiang on 2018/3/8.
 */
public class ResultListModel<T> extends BaseResultModel {
    private int start;
    private int resultCount;
    private int total;
    private int loginStatus=HttpStatus.OK.value();
    /**
     * 服务端返回它的日期标签
     */
    private Long ticks;
    protected Collection<T> rows;

    public ResultListModel(){
        this.start=0;
        this.resultCount=0;
        this.total=0;
        this.setSuccess(true);
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Long getTicks() {
        return ticks;
    }

    public void setTicks(Long ticks) {
        this.ticks = ticks;
    }

    public Collection<T> getRows() {
        return rows;
    }

    public void setRows(Collection<T> rows) {
        this.rows = rows;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }
}
