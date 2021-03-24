package com.wx.exam.pojo;

public class BaseUser {
    protected String id;
    protected String name;
    protected String password;
    protected boolean modified;

    public BaseUser() {
    }

    public BaseUser(String name) {
        this.name = name;
    }

    public BaseUser(String id, String name, String password, boolean modified) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.modified = modified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

}
