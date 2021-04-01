package com.wx.exam.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/22
 */
public class QuestionChoiceVO extends Query implements Serializable {
    private static final long serialVersionUID = -8458100242647774317L;

    private Integer id;

    private String question;

    private String optiona;

    private String optionb;

    private String optionc;

    private String optiond;

    private String answer;

    private BigDecimal score;

    private String fkTeacher;

    private Integer delFalg;

    private Integer type;

    public Integer getId() {
		return id;
	}
    public void setId(Integer id) {
		this.id = id;
	}
    public String getQuestion() {
		return question;
	}
    public void setQuestion(String question) {
		this.question = question;
	}
    public String getOptiona() {
		return optiona;
	}
    public void setOptiona(String optiona) {
		this.optiona = optiona;
	}
    public String getOptionb() {
		return optionb;
	}
    public void setOptionb(String optionb) {
		this.optionb = optionb;
	}
    public String getOptionc() {
		return optionc;
	}
    public void setOptionc(String optionc) {
		this.optionc = optionc;
	}
    public String getOptiond() {
		return optiond;
	}
    public void setOptiond(String optiond) {
		this.optiond = optiond;
	}
    public String getAnswer() {
		return answer;
	}
    public void setAnswer(String answer) {
		this.answer = answer;
	}
    public BigDecimal getScore() {
		return score;
	}
    public void setScore(BigDecimal score) {
		this.score = score;
	}
    public String getFkTeacher() {
		return fkTeacher;
	}
    public void setFkTeacher(String fkTeacher) {
		this.fkTeacher = fkTeacher;
	}
    public Integer getDelFalg() {
		return delFalg;
	}
    public void setDelFalg(Integer delFalg) {
		this.delFalg = delFalg;
	}
    public Integer getType() {
		return type;
	}
    public void setType(Integer type) {
		this.type = type;
	}
/*
	public String getSortByParams(){
		return null;
	}
*/

}
