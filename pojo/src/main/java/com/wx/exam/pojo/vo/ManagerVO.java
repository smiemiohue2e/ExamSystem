package com.wx.exam.pojo.vo;

import java.io.Serializable;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/27
 */
public class ManagerVO extends Query implements Serializable {
    private static final long serialVersionUID = -8209700003657745220L;

    private Integer id;

    private String name;

    private String password;

    private Integer modified;

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
    public String getPassword() {
		return password;
	}
    public void setPassword(String password) {
		this.password = password;
	}
    public Integer getModified() {
		return modified;
	}
    public void setModified(Integer modified) {
		this.modified = modified;
	}
}
