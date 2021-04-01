package com.wx.exam.service;


import com.wx.exam.pojo.vo.ExaminationResulquestionVO;
import com.wx.exam.utils.Result;

/**
 * <br/>
 * Created by wangxiao on 2018/07/27
 */
public interface ExaminationResulquestionService {

	Result addExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO) throws Exception;

	Result updateExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO) throws Exception;
	
	Result findDetailExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO) throws Exception;
	
	Result listExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO) throws Exception;

    Result listExaminationResulquestionPage(ExaminationResulquestionVO examinationResulquestionVO) throws Exception;
	
	Result countExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO) throws Exception;
	
	Result deleteExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO) throws Exception;
}