package com.wx.exam.service.impl;

import com.wx.exam.mapper.ExamMapper;
import com.wx.exam.mapper.ExamQuestionMapper;
import com.wx.exam.pojo.data.ExamDO;
import com.wx.exam.pojo.vo.ExamQuestionVO;
import com.wx.exam.pojo.vo.ExamVO;
import com.wx.exam.service.ExamQuestionService;
import com.wx.exam.service.ExamService;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.QuestionType;
import com.wx.exam.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/25
 */
@Service("examQuestionService")
public class ExamQuestionServiceImpl implements ExamQuestionService {

	private final static Logger LOG = LoggerFactory.getLogger(ExamQuestionServiceImpl.class);

	@Resource
	private ExamQuestionMapper examQuestionMapper;

	@Autowired
	ExamService examService;

	@Autowired
	ExamMapper examMapper;

	@Override
	public Result addExamQuestion(ExamQuestionVO examQuestionVO, int i) throws Exception {
		Integer row = examQuestionMapper.addExamQuestion(examQuestionVO);
		if(row>0){
           return examService.updateExam(examQuestionVO,i);
		}

		return Result.getFailure("添加失败");
	}

	@Override
	public Result updateExamQuestion(ExamQuestionVO examQuestionVO) throws Exception {
		return null;
	}
	
	@Override
	public Result findDetailExamQuestion(ExamQuestionVO examQuestionVO) throws Exception{
		return null;
	}

	@Override
	public Result listExamQuestion(ExamQuestionVO examQuestionVO) throws Exception{
		return null;
	}
	
	@Override
	public Result listExamQuestionPage(ExamQuestionVO examQuestionVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countExamQuestion(ExamQuestionVO examQuestionVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteExamQuestion(ExamQuestionVO examQuestionVO) throws Exception{
		return null;
	}

}