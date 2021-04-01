package com.wx.exam.pojo.vo;

/**
 * 题目列表vo  单选、多选 、判断
 */
public class QuestionListVO {

    private Integer id;
    private String title;
    private String optiona;
    private String optionb;
    private String optionc;
    private String optiond;
    private int point;
    private String type;
    private String answer;
    private String answerFacade;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerFacade() {
        return answerFacade;
    }

    public void setAnswerFacade(String answerFacade) {
        this.answerFacade = answerFacade;
    }
    public String getSortByParams(){
        return null;
    }
}
