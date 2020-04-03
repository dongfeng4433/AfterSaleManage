package com.joindoo.jdwechat.model.query;

/**
 * Created by zhuqi on 2018/1/2.
 */
public class PagingOptions {
    private int start;
    private int limit;
    private int page;//页码
    private int pageSize;//每页条数
    private int total;
    private boolean isNeedTotal;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

   public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isNeedTotal() {
        return isNeedTotal;
    }

    public void setNeedTotal(boolean needTotal) {
        isNeedTotal = needTotal;
    }

    public void fillPagingOptions(PagingOptions options){
        this.start=options.start;
        this.limit=options.limit;
        this.page=options.page;
        this.pageSize=options.pageSize;
    }
}
