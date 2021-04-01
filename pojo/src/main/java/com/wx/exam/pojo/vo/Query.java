package com.wx.exam.pojo.vo;

import java.util.HashMap;
import java.util.Map;

public class Query {
    private int pageCode;//页码
    private Integer size;//页的数目
    private Integer pageSize;//页面大小

    //排序字段，默认按照id进行排序
    public Map sortByParams = new HashMap();
    {
        sortByParams.put("id","ASC");
    }

    public Map getSortByParams() {
        return sortByParams;
    }

    public void setSortByParams(Map sortByParams) {
        this.sortByParams = sortByParams;
    }

    private int startRecord;//用户分页数据

    public int getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    public Query() {
    }

    public int getPageCode() {
        return pageCode;
    }

    public void setPageCode(int pageCode) {
        this.pageCode = pageCode;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
