package com.wx.exam.pojo;

public class Grade {
    private Integer id;

    private Integer grade;

    private Boolean delFlag;

    public Grade(Integer id, Integer grade, Boolean delFlag) {
        this.id = id;
        this.grade = grade;
        this.delFlag = delFlag;
    }

    public Grade() {
        super();
    }

    public Grade(Integer grade) {
        this.grade = grade;
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

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}