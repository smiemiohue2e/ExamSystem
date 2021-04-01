package com.wx.exam.service;


import com.wx.exam.pojo.vo.ExamQuestionVO;
import com.wx.exam.utils.Result;

/**
 * <br/>
 * Created by wangxiao on 2018/07/25
 */
public interface ExamQuestionService {

	Result addExamQuestion(ExamQuestionVO examQuestionVO, int i) throws Exception;

	Result updateExamQuestion(ExamQuestionVO examQuestionVO) throws Exception;
	
	Result findDetailExamQuestion(ExamQuestionVO examQuestionVO) throws Exception;
	
	Result listExamQuestion(ExamQuestionVO examQuestionVO) throws Exception;

    Result listExamQuestionPage(ExamQuestionVO examQuestionVO) throws Exception;
	
	Result countExamQuestion(ExamQuestionVO examQuestionVO) throws Exception;
	
	Result deleteExamQuestion(ExamQuestionVO examQuestionVO) throws Exception;
}