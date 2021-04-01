package com.wx.exam.service;

import com.wx.exam.pojo.vo.ExamClassVO;
import com.wx.exam.utils.Result;

import java.util.ArrayList;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/24
 */
public interface ExamClassService {


	Result updateExamClass(ExamClassVO examClassVO) throws Exception;
	
	Result findDetailExamClass(ExamClassVO examClassVO) throws Exception;
	
	Result listExamClass(ExamClassVO examClassVO) throws Exception;

    Result listExamClassPage(ExamClassVO examClassVO) throws Exception;
	
	Result countExamClass(ExamClassVO examClassVO) throws Exception;
	
	Result deleteExamClass(ExamClassVO examClassVO) throws Exception;

	Result addExamClass(Integer examId, ArrayList<ExamClassVO> list);
}