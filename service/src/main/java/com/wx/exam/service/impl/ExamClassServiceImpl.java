package com.wx.exam.service.impl;

import com.wx.exam.mapper.ExamClassMapper;
import com.wx.exam.pojo.vo.ExamClassVO;
import com.wx.exam.service.ExamClassService;
import com.wx.exam.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/24
 */
@Service("examClassService")
public class ExamClassServiceImpl implements ExamClassService {

	private final static Logger LOG = LoggerFactory.getLogger(ExamClassServiceImpl.class);

	@Resource
	private ExamClassMapper examClassMapper;

	@Override
	public Result updateExamClass(ExamClassVO examClassVO) throws Exception {
		return null;
	}
	
	@Override
	public Result findDetailExamClass(ExamClassVO examClassVO) throws Exception{
		return null;
	}

	@Override
	public Result listExamClass(ExamClassVO examClassVO) throws Exception{
		return null;
	}
	
	@Override
	public Result listExamClassPage(ExamClassVO examClassVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countExamClass(ExamClassVO examClassVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteExamClass(ExamClassVO examClassVO) throws Exception{
		return null;
	}

	@Override
	public Result addExamClass(Integer examId, ArrayList<ExamClassVO> list) {
		Integer row = examClassMapper.deleteExamClass(examId);
		for (ExamClassVO examClassVO : list) {
			examClassMapper.addExamClass(examClassVO);
		}
		return Result.getSuccess("操作成功");
	}

}