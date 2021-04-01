package com.wx.exam.service;


import com.wx.exam.pojo.vo.*;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * <br/>
 * Created by wangxiao on 2018/07/22
 */
public interface QuestionService {


	//查询选择题
	PageBean<QuestionListVO> listQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception;

	//查询判断题
	PageBean<QuestionListVO> listQuestionJudge(QuestionJudgeVO questionJudgeVO) throws Exception;

	//添加判断题
	Result addJudge(QuestionJudgeVO judgeVO,Integer examId) throws Exception;

	//更新判断题
	Result updateJudge(QuestionJudgeVO judgeVO);

	//添加选择题
	Result addChoice(QuestionChoiceVO listVo,Integer examId) throws Exception;

	//更新选择题
	Result updateChoice(QuestionChoiceVO listVo);

	//根据试卷查询题目
    PageBean<QuestionManageVO> listQuestionByExam(Integer eid, Integer pageCode, int pageSize, int size);

    //从题库中筛选题目添加
    Result listAllQuestionByType(QuestionBankVO vo) throws InvocationTargetException, IllegalAccessException;

    //添加题目
    Result addExamQuestion(ArrayList<ExamQuestionVO> list, int i) throws Exception;
}