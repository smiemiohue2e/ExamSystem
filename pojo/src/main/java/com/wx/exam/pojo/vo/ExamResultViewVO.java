package com.wx.exam.pojo.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 详细的考试结果
 */
public class ExamResultViewVO {

    private int point;
    private Date date;

    //各个题目类型的分数
    private int singlePoints;
    private int multiPoints;
    private int judgePoints;


    //题目信息+答题信息
    private List<ExamResultQuestionVO> singleQuestions = new ArrayList<ExamResultQuestionVO>();
    //多选题
    private List<ExamResultQuestionVO> multiQuestions = new ArrayList<ExamResultQuestionVO>();
    //判断题
    private List<ExamResultQuestionVO> judgeQuestions = new ArrayList<ExamResultQuestionVO>();


    public void addSingleQuestion(ExamResultQuestionVO question) {
        this.singleQuestions.add(question);
    }

    public void addMultiQuestion(ExamResultQuestionVO question) {
        this.multiQuestions.add(question);
    }

    public void addJudgeQuestion(ExamResultQuestionVO question) {
        this.judgeQuestions.add(question);
    }


    public static class ExamResultQuestionVO extends QuestionListVO{
        //添加自己的字段
        //是否答对
        private boolean right;

        private String  wrongAnswer;
        //错误答案门面值
        private String wrongAnswerFacade;


        public boolean isRight() {
            return right;
        }

        public void setRight(boolean right) {
            this.right = right;
        }

        public String getWrongAnswer() {
            return wrongAnswer;
        }

        public void setWrongAnswer(String wrongAnswer) {
            this.wrongAnswer = wrongAnswer;
        }

        public String getWrongAnswerFacade() {
            return wrongAnswerFacade;
        }

        public void setWrongAnswerFacade(String wrongAnswerFacade) {
            this.wrongAnswerFacade = wrongAnswerFacade;
        }
    }


    //------------------------------------------------------------------------

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public List<ExamResultQuestionVO> getSingleQuestions() {
        return singleQuestions;
    }

    public void setSingleQuestions(List<ExamResultQuestionVO> singleQuestions) {
        this.singleQuestions = singleQuestions;
    }

    public List<ExamResultQuestionVO> getMultiQuestions() {
        return multiQuestions;
    }

    public void setMultiQuestions(List<ExamResultQuestionVO> multiQuestions) {
        this.multiQuestions = multiQuestions;
    }

    public List<ExamResultQuestionVO> getJudgeQuestions() {
        return judgeQuestions;
    }

    public void setJudgeQuestions(List<ExamResultQuestionVO> judgeQuestions) {
        this.judgeQuestions = judgeQuestions;
    }
}
