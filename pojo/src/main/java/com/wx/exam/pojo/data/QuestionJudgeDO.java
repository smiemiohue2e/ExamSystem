package com.wx.exam.pojo.data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <br/>
 * Created by wangxiao on 2018/07/22
 */
public class QuestionJudgeDO implements Serializable {
    private static final long serialVersionUID = -5220543495085360862L;

    private Integer id;

    private String question;

    private BigDecimal score;

    private String answer;

    private String fkTeacher;

    private Integer type;

    private Integer delFalg;

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

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFkTeacher() {
        return fkTeacher;
    }

    public void setFkTeacher(String fkTeacher) {
        this.fkTeacher = fkTeacher;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDelFalg() {
        return delFalg;
    }

    public void setDelFalg(Integer delFalg) {
        this.delFalg = delFalg;
    }
}
