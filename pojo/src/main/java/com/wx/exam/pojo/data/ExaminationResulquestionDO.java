package com.wx.exam.pojo.data;

import java.io.Serializable;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/28
 */
public class ExaminationResulquestionDO implements Serializable {
    private static final long serialVersionUID = -6100551985651950547L;

    private Integer id;

    /**
	 * 是否正确
	 */
	private Integer isRight;

    /**
	 * 错误答案
	 */
	private String wrongAnswer;

    /**
	 * 对应examination_result
	 */
	private Integer fkExaminationResult;

    private Integer fkQuestion;

    private Integer fkQtype;

    private Integer delFlag;

    public Integer getId() {
		return id;
	}
    public void setId(Integer id) {
		this.id = id;
	}
    public Integer getIsRight() {
		return isRight;
	}
    public void setIsRight(Integer isRight) {
		this.isRight = isRight;
	}
    public String getWrongAnswer() {
		return wrongAnswer;
	}
    public void setWrongAnswer(String wrongAnswer) {
		this.wrongAnswer = wrongAnswer;
	}
    public Integer getFkExaminationResult() {
		return fkExaminationResult;
	}
    public void setFkExaminationResult(Integer fkExaminationResult) {
		this.fkExaminationResult = fkExaminationResult;
	}
    public Integer getFkQuestion() {
		return fkQuestion;
	}
    public void setFkQuestion(Integer fkQuestion) {
		this.fkQuestion = fkQuestion;
	}
    public Integer getFkQtype() {
		return fkQtype;
	}
    public void setFkQtype(Integer fkQtype) {
		this.fkQtype = fkQtype;
	}
    public Integer getDelFlag() {
		return delFlag;
	}
    public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
