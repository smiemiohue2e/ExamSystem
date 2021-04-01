package com.wx.exam.pojo.data;

import java.io.Serializable;

/** 
 * <br/>
 * Created by weidong on 2018/07/21
 */
public class TeacherClassDO implements Serializable {
    private static final long serialVersionUID = -5922665516539313739L;

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
