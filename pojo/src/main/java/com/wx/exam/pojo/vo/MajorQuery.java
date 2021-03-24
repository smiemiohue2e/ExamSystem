package com.wx.exam.pojo.vo;

public class MajorQuery extends Query {
    String search;

    public MajorQuery() {
    }

    public MajorQuery(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
