package com.wx.exam.service.impl;

import com.wx.exam.mapper.ExaminationResulquestionMapper;
import com.wx.exam.pojo.vo.ExaminationResulquestionVO;
import com.wx.exam.service.ExaminationResulquestionService;

import com.wx.exam.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/28
 */
@Service("examinationResulquestionService")
public class ExaminationResulquestionServiceImpl implements ExaminationResulquestionService {

	private final static Logger LOG = LoggerFactory.getLogger(ExaminationResulquestionServiceImpl.class);

	@Resource
	private ExaminationResulquestionMapper examinationResulquestionMapper;

	@Override
	public Result addExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO) throws Exception {
		return null;
	}

	@Override
	public Result updateExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO) throws Exception {
		return null;
	}
	
	@Override
	public Result findDetailExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO) throws Exception{
		return null;
	}

	@Override
	public Result listExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO) throws Exception{
		return null;
	}
	
	@Override
	public Result listExaminationResulquestionPage(ExaminationResulquestionVO examinationResulquestionVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO) throws Exception{
		return null;
	}

}