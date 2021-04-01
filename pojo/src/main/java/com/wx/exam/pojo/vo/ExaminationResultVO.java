package com.wx.exam.pojo.vo;

import java.io.Serializable;
import java.util.Date;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/27
 */
public class ExaminationResultVO extends Query implements Serializable {
    private static final long serialVersionUID = -8953009843880828446L;

    private Integer id;

    private Integer point;

    private Date time;

    private String examTitle;

    /**
	 * 试卷
	 */
	private Integer fkExam;

    /**
	 * 答题的学生
	 */
	private String fkStudent;

    private Integer delFlag;

    public Integer getId() {
		return id;
	}
    public void setId(Integer id) {
		this.id = id;
	}
    public Integer getPoint() {
		return point;
	}
    public void setPoint(Integer point) {
		this.point = point;
	}
    public Date getTime() {
		return time;
	}
    public void setTime(Date time) {
		this.time = time;
	}
    public String getExamTitle() {
		return examTitle;
	}
    public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}
    public Integer getFkExam() {
		return fkExam;
	}
    public void setFkExam(Integer fkExam) {
		this.fkExam = fkExam;
	}
    public String getFkStudent() {
		return fkStudent;
	}
    public void setFkStudent(String fkStudent) {
		this.fkStudent = fkStudent;
	}
    public Integer getDelFlag() {
		return delFlag;
	}
    public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
