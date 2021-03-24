package com.wx.exam.pojo;

public class Clazz {
    private Integer id;

    private Integer cno;

    private Integer fkGrade;

    private Integer fkMajor;

    private Boolean delFlag;

    public Clazz(Integer id, Integer cno, Integer fkGrade, Integer fkMajor, Boolean delFlag) {
        this.id = id;
        this.cno = cno;
        this.fkGrade = fkGrade;
        this.fkMajor = fkMajor;
        this.delFlag = delFlag;
    }

    public Clazz() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public Integer getFkGrade() {
        return fkGrade;
    }

    public void setFkGrade(Integer fkGrade) {
        this.fkGrade = fkGrade;
    }

    public Integer getFkMajor() {
        return fkMajor;
    }

    public void setFkMajor(Integer fkMajor) {
        this.fkMajor = fkMajor;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}