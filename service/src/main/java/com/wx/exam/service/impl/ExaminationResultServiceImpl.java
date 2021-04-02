package com.wx.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.wx.exam.mapper.ExaminationResultMapper;
import com.wx.exam.pojo.data.ExaminationResultDO;
import com.wx.exam.pojo.vo.ExaminationResultVO;
import com.wx.exam.service.ExaminationResultService;

import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;
import com.wx.exam.utils.exception.PageCodeException;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
	public List<ExaminationResultDO> listExaminationResult(ExaminationResultVO query) throws Exception{
	/*	*/
		return examinationResultMapper.listExaminationResult(query);
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

	@Override
	public PageBean<ExaminationResultVO> listExaminationResultBySid(ExaminationResultVO query) throws InvocationTargetException, IllegalAccessException {

		//判断页码越界
		Integer count = examinationResultMapper.countExaminationResult(query);
		int max=(count+query.getPageSize()-1)/query.getPageSize();
		if(query.getPageCode()>max){
			throw new PageCodeException("页码越界啦",max);
		}
		PageHelper.startPage(query.getPageCode(),query.getPageSize());
		List<ExaminationResultDO> ResultDOS =examinationResultMapper.listExaminationResult(query);

		ArrayList<ExaminationResultVO> list=new ArrayList<>();
		for (ExaminationResultDO resultDO : ResultDOS) {
			ExaminationResultVO vo=new ExaminationResultVO();
			BeanUtils.copyProperties(vo,resultDO);
			list.add(vo);
		}
		System.out.println(list);
		PageBean<ExaminationResultVO> pageBean=new PageBean<ExaminationResultVO>(
				list,query.getPageCode(),query.getPageSize(),count,query.getSize()
		);
		return pageBean;
	}

}