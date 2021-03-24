package com.wx.exam.pojo;

public class Manager {
    private Integer id;

    private String name;

    private String password;

    private Boolean modified;

    public Manager(Integer id, String name, String password, Boolean modified) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.modified = modified;
    }

    public Manager() {
        super();
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
}