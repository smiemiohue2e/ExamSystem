package com.wx.exam.pojo.vo;


import java.util.ArrayList;

/**
 * 字段名称必须和前台传来的一致才能封装进去
 */
public class QuestionBankVO {
    String type;//筛选的题型
    ArrayList<TypesVO> types;//试卷上已存在的题目信息
    String examId;//添加时要用到考试的id

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<TypesVO> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<TypesVO> types) {
        this.types = types;
    }
}
