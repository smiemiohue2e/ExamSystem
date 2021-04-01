package com.wx.exam.pojo.vo;

import java.io.Serializable;

/**
 *
 * <br/>
 * Created by weidong on 2018/07/18
 */
public class GradeVO extends Query implements Serializable {
    private static final long serialVersionUID = -7566467981501414899L;

    Integer search;

    private Integer id;

    private Integer grade;

    private Integer delFlag;

    public GradeVO() {}

    //为了方便创建GradeVO对象，所以提供此方法
    public GradeVO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;

    }

/*
    public String getSortByParams(){
        return null;
    }
*/

    public Integer getSearch() {
        return search;
    }

    public void setSearch(Integer search) {
        this.search = search;
    }
}
