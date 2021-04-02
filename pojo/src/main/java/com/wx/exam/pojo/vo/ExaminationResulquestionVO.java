package com.wx.exam.pojo.vo;

import java.io.Serializable;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/27
 */
public class ExaminationResulquestionVO implements Serializable {
    private static final long serialVersionUID = -8623451538160317724L;

    private Integer id;

    private Integer isRight;

    /**
	 * 错误答案
	 */

    Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	private String wrongAnswer;

    private Integer fkExaminationResult;

    private Integer fkQuestion;

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
    public Integer getDelFlag() {
		return delFlag;
	}
    public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
