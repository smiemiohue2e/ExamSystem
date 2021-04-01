package com.wx.exam.service.impl;

import com.wx.exam.mapper.ExaminationResultMapper;
import com.wx.exam.pojo.data.ExaminationResultDO;
import com.wx.exam.pojo.vo.ExaminationResultVO;
import com.wx.exam.service.ExaminationResultService;

import com.wx.exam.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/27
 */
@Service("examinationResultService")
public class ExaminationResultServiceImpl implements ExaminationResultService {

	private final static Logger LOG = LoggerFactory.getLogger(ExaminationResultServiceImpl.class);

	@Resource
	private ExaminationResultMapper examinationResultMapper;

	@Override
	public Result addExaminationResult(ExaminationResultVO examinationResultVO) throws Exception {
		return null;
	}

	@Override
	public Result updateExaminationResult(ExaminationResultVO examinationResultVO) throws Exception {
		return null;
	}
	
	@Override
	public Result findDetailExaminationResult(ExaminationResultVO examinationResultVO) throws Exception{
		return null;
	}

	@Override
	public List<ExaminationResultDO> listExaminationResult(ExaminationResultVO examinationResultVO) throws Exception{
		return examinationResultMapper.listExaminationResult(examinationResultVO);
	}
	
	@Override
	public Result listExaminationResultPage(ExaminationResultVO examinationResultVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countExaminationResult(ExaminationResultVO examinationResultVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteExaminationResult(ExaminationResultVO examinationResultVO) throws Exception{
		return null;
	}

}