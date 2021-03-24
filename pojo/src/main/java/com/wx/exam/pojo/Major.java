package com.wx.exam.pojo;

public class Major {
    private Integer id;

    private String name;

    private Boolean delFlag;

    public Major(Integer id, String name, Boolean delFlag) {
        this.id = id;
        this.name = name;
        this.delFlag = delFlag;
    }

    public Major() {
        super();
    }

    public Major(String name) {
        this.name = name;
    }

    public Major(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}