package com.wx.exam.pojo.data;

import java.io.Serializable;

/** 
 * <br/>
 * Created by weidong on 2018/07/18
 */
public class GradeDO implements Serializable {
    private static final long serialVersionUID = -8676370744335611940L;

    private Integer id;

    private Integer grade;

    private Integer delFlag;

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
}
