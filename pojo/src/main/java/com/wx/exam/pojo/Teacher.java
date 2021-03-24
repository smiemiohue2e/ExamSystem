package com.wx.exam.pojo;

import java.util.Date;

public class Teacher {
    private String id;

    private String name;

    private String password;

    private Boolean modified;

    private Date modifyPwdTime;

    private Boolean delFlag;

    public Teacher(String id, String name, String password, Boolean modified, Date modifyPwdTime, Boolean delFlag) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.modified = modified;
        this.modifyPwdTime = modifyPwdTime;
        this.delFlag = delFlag;
    }

    public Teacher() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Boolean getModified() {
        return modified;
    }

    public void setModified(Boolean modified) {
        this.modified = modified;
    }

    public Date getModifyPwdTime() {
        return modifyPwdTime;
    }

    public void setModifyPwdTime(Date modifyPwdTime) {
        this.modifyPwdTime = modifyPwdTime;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}