package com.wx.exam.pojo;

public class Student {
    private String id;

    private String sno;

    private String name;

    private String password;

    private Integer fkClass;

    private Boolean modified;

    private String delFlag;

    public Student(String id, String sno, String name, String password, Integer fkClass, Boolean modified, String delFlag) {
        this.id = id;
        this.sno = sno;
        this.name = name;
        this.password = password;
        this.fkClass = fkClass;
        this.modified = modified;
        this.delFlag = delFlag;
    }

    public Student() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
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

    public Integer getFkClass() {
        return fkClass;
    }

    public void setFkClass(Integer fkClass) {
        this.fkClass = fkClass;
    }

    public Boolean getModified() {
        return modified;
    }

    public void setModified(Boolean modified) {
        this.modified = modified;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}