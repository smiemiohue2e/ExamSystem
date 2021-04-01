package com.wx.exam.pojo.vo;

import java.util.ArrayList;

/**
 * 查询某个试卷的题目的vo
 * 题目添加的VO
 */
public class QuestionManageQuery extends Query {

    ArrayList<Integer> choiceIDs;
    ArrayList<Integer> judgeIDs;

    public ArrayList<Integer> getChoiceIDs() {
        return choiceIDs;
    }

    public void setChoiceIDs(ArrayList<Integer> choiceIDs) {
        this.choiceIDs = choiceIDs;
    }

    public ArrayList<Integer> getJudgeIDs() {
        return judgeIDs;
    }

    public void setJudgeIDs(ArrayList<Integer> judgeIDs) {
        this.judgeIDs = judgeIDs;
    }
}
