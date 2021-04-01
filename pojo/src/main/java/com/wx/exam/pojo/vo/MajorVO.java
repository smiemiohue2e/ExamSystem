package com.wx.exam.pojo.vo;

import java.io.Serializable;

/**
 * <br/>
 * Created by weidong on 2018/07/18
 */
public class MajorVO extends Query implements Serializable {
    private static final long serialVersionUID = -7561034379901089956L;

    String search;

    private Integer id;

    private String name;

    private Integer delFlag;

    public MajorVO() {
    }

    public MajorVO(Integer id) {
        this.id = id;
    }

    public MajorVO(String name) {
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
        this.name = name;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

   /* public String getSortByParams() {
        return null;
    }*/

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
