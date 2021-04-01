package com.wx.exam.service.impl;

import com.wx.exam.mapper.TeacherClassMapper;
import com.wx.exam.pojo.vo.TeacherClassVO;
import com.wx.exam.service.TeacherClassService;

import com.wx.exam.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("teacherClassService")
public class TeacherClassServiceImpl implements TeacherClassService {

	private final static Logger LOG = LoggerFactory.getLogger(TeacherClassServiceImpl.class);

	@Resource
	private TeacherClassMapper teacherClassMapper;

	@Override
	public Result addTeacherClass(TeacherClassVO teacherClassVO) throws Exception {
		return null;
	}

	@Override
	public Result updateTeacherClass(TeacherClassVO teacherClassVO) throws Exception {
		return null;
	}
	
	@Override
	public Result findDetailTeacherClass(TeacherClassVO teacherClassVO) throws Exception{
		return null;
	}

	@Override
	public Result listTeacherClass(TeacherClassVO teacherClassVO) throws Exception{
		return null;
	}
	
	@Override
	public Result listTeacherClassPage(TeacherClassVO teacherClassVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countTeacherClass(TeacherClassVO teacherClassVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteTeacherClass(TeacherClassVO teacherClassVO) throws Exception{
		return null;
	}

}