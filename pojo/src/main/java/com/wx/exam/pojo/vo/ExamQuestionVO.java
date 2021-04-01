package com.wx.exam.pojo.vo;

import java.io.Serializable;

/**
 * <br/>
 * Created by wangxiao on 2018/07/25
 */
public class ExamQuestionVO extends Query implements Serializable {
    private static final long serialVersionUID = -9204071817100689762L;

    private Integer id;

    private Integer fkExam;

    private Integer fkQuestion;

    /**
     * 题目类型
     */
    private Integer fkQtype;

    private Integer delFlag;


    Integer score;//用于更新试卷的分数

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkExam() {
        return fkExam;
    }

    public void setFkExam(Integer fkExam) {
        this.fkExam = fkExam;
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
