package com.wx.exam.pojo.vo;

/**
 * @description:
 * @author: weidong
 * @create: 2018-07-20 21:06
 **/
public class QuestionManageVO {

    //题目id
    private Integer id;
    private String question;//题目标题
    private String type;//题目类型
    private Double score;//题目分数

    private String answerFacade;//门面值


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAnswerFacade() {
        return answerFacade;
    }

    public void setAnswerFacade(String answerFacade) {
        this.answerFacade = answerFacade;
    }
}
