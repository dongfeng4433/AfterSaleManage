package com.joindoo.jdwechat.model.web;

/**
 * Created by zhuqiang on 2018/4/3.
 */
public class WebfetchPageModel {
    private int pageIndex;
    private int pageSize;
    private String sortOrder;
    private boolean isSecondRequest;
    private int firstPageIndex;
    private int firstPageSize;
    private int firstPageTotal;
    private String startDate;
    private String endDate;
    private boolean reset;//是否重置抓取 及处理自动表重新插入外，预约表也重新插入

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isSecondRequest() {
        return isSecondRequest;
    }

    public void setSecondRequest(boolean secondRequest) {
        isSecondRequest = secondRequest;
    }

    public int getFirstPageIndex() {
        return firstPageIndex;
    }

    public void setFirstPageIndex(int firstPageIndex) {
        this.firstPageIndex = firstPageIndex;
    }

    public int getFirstPageSize() {
        return firstPageSize;
    }

    public void setFirstPageSize(int firstPageSize) {
        this.firstPageSize = firstPageSize;
    }

    public int getFirstPageTotal() {
        return firstPageTotal;
    }

    public void setFirstPageTotal(int firstPageTotal) {
        this.firstPageTotal = firstPageTotal;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public boolean isReset() {
        return reset;
    }
}
