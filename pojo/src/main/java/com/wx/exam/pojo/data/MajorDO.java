package com.wx.exam.pojo.data;

import java.io.Serializable;

/** 
 * <br/>
 * Created by weidong on 2018/07/18
 */
public class MajorDO implements Serializable {
    private static final long serialVersionUID = -5645487607886949925L;

    private Integer id;

    private String name;

    private Integer delFlag;

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
}
