package com.wx.exam.service;


import com.wx.exam.pojo.vo.BeginExamVO;
import com.wx.exam.pojo.vo.ClassVO;
import com.wx.exam.pojo.vo.ExamQuestionVO;
import com.wx.exam.pojo.vo.ExamVO;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;

import java.lang.reflect.InvocationTargetException;

/**
 * <br/>
 * Created by wangxiao on 2018/07/24
 */
public interface ExamService {

	Result addExam(ExamVO examVO) throws Exception;

	Result findDetailExam(ExamVO examVO) throws Exception;
	
	Result updateExam(ExamQuestionVO examQuestionVO,int i) throws Exception;

	PageBean<ExamVO> listExam(ExamVO examVO) throws Exception;

    Result listExamPage(ExamVO examVO) throws Exception;
	
	Result countExam(ExamVO examVO) throws Exception;
	
	Result deleteExam(ExamVO examVO) throws Exception;

	Result updateExamStatus(ExamVO examVO);

    PageBean<ExamVO> findExamByClassId(ClassVO classVO) throws InvocationTargetException, IllegalAccessException;

    //判断学生是否参加过考试
	Boolean hasJoined(Integer eid, String id) throws Exception;

	BeginExamVO joined(Integer eid);
}