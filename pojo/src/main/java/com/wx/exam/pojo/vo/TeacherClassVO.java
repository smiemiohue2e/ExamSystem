package com.wx.exam.pojo.vo;

import java.io.Serializable;

/** 
 * <br/>
 * Created by weidong on 2018/07/21
 */
public class TeacherClassVO implements Serializable {
    private static final long serialVersionUID = -6902402986522457252L;

    private Integer id;

    private String fkTeacher;

    private Integer fkClass;

    public Integer getId() {
		return id;
	}
    public void setId(Integer id) {
		this.id = id;
	}
    public String getFkTeacher() {
		return fkTeacher;
	}
    public void setFkTeacher(String fkTeacher) {
		this.fkTeacher = fkTeacher;
	}
    public Integer getFkClass() {
		return fkClass;
	}
    public void setFkClass(Integer fkClass) {
		this.fkClass = fkClass;
	}
}
