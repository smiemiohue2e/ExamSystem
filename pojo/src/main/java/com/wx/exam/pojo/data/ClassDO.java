package com.wx.exam.pojo.data;

import java.io.Serializable;

/** 
 * <br/>
 * Created by weidong on 2018/07/19
 */
public class ClassDO implements Serializable {
    private static final long serialVersionUID = -6068282934677574658L;

    /**
	 * 主键id
	 */
	private Integer id;

    /**
	 * 班级编号
	 */
	private Integer cno;

    /**
	 * 年级id
	 */
	private Integer fkGrade;

    private Integer fkMajor;

    private Integer delFlag;

    public Integer getId() {
		return id;
	}
    public void setId(Integer id) {
		this.id = id;
	}
    public Integer getCno() {
		return cno;
	}
    public void setCno(Integer cno) {
		this.cno = cno;
	}
    public Integer getFkGrade() {
		return fkGrade;
	}
    public void setFkGrade(Integer fkGrade) {
		this.fkGrade = fkGrade;
	}
    public Integer getFkMajor() {
		return fkMajor;
	}
    public void setFkMajor(Integer fkMajor) {
		this.fkMajor = fkMajor;
	}
    public Integer getDelFlag() {
		return delFlag;
	}
    public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
