package com.wx.exam.pojo.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 开始考试的VO
 */
public class BeginExamVO {

    String title;//试卷的标题
    int limit;//试卷的标题
    String points;//试卷的标题

    //三种题目的总分
    private int singlePoints;//单选题分数
    //多选题分数
    private int multiPoints;
    private int judgePoints;//判断题分数

    //题目的数据
    //单选题
    private List<QuestionListVO> singleQuestions = new ArrayList<QuestionListVO>();
    //多选题
    private List<QuestionListVO> multiQuestions = new ArrayList<QuestionListVO>();
    //判断题
    private List<QuestionListVO> judgeQuestions = new ArrayList<QuestionListVO>();


    //为了方便操作题目的集合，所以添加三个便捷的方法

    public void addSingleQuestion(QuestionListVO QuestionListVO) {
        singleQuestions.add(QuestionListVO);
    }

    public void addMultiQuestion(QuestionListVO QuestionListVO) {
        multiQuestions.add(QuestionListVO);
    }

    public void addJudgeQuestion(QuestionListVO QuestionListVO) {
        judgeQuestions.add(QuestionListVO);
    }

    //--------------------------------------------------------------------------------------------------

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public int getSinglePoints() {
        return singlePoints;
    }

    public void setSinglePoints(int singlePoints) {
        this.singlePoints = singlePoints;
    }

    public int getMultiPoints() {
        return multiPoints;
    }

    public void setMultiPoints(int multiPoints) {
        this.multiPoints = multiPoints;
    }

    public int getJudgePoints() {
        return judgePoints;
    }

    public void setJudgePoints(int judgePoints) {
        this.judgePoints = judgePoints;
    }

    public List<QuestionListVO> getSingleQuestions() {
        return singleQuestions;
    }

    public void setSingleQuestions(List<QuestionListVO> singleQuestions) {
        this.singleQuestions = singleQuestions;
    }

    public List<QuestionListVO> getMultiQuestions() {
        return multiQuestions;
    }

    public void setMultiQuestions(List<QuestionListVO> multiQuestions) {
        this.multiQuestions = multiQuestions;
    }

    public List<QuestionListVO> getJudgeQuestions() {
        return judgeQuestions;
    }

    public void setJudgeQuestions(List<QuestionListVO> judgeQuestions) {
        this.judgeQuestions = judgeQuestions;
    }
}
