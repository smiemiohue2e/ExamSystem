package com.wx.exam.pojo.data;

import java.io.Serializable;
import java.util.Date;

/** 
 * <br/>
 * Created by weidong on 2018/07/20
 */
public class TeacherDO implements Serializable {
    private static final long serialVersionUID = -9084491413906623253L;

    private String id;

    private String name;

    private String password;

    private Integer modified;

    private Date modifyPwdTime;

    private Integer delFlag;

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
    public Integer getModified() {
		return modified;
	}
    public void setModified(Integer modified) {
		this.modified = modified;
	}
    public Date getModifyPwdTime() {
		return modifyPwdTime;
	}
    public void setModifyPwdTime(Date modifyPwdTime) {
		this.modifyPwdTime = modifyPwdTime;
	}
    public Integer getDelFlag() {
		return delFlag;
	}
    public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
