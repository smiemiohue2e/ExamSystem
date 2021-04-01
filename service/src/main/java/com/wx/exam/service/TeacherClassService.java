package com.wx.exam.service;


import com.wx.exam.pojo.vo.TeacherClassVO;
import com.wx.exam.utils.Result;

public interface TeacherClassService {

	Result addTeacherClass(TeacherClassVO teacherClassVO) throws Exception;

	Result updateTeacherClass(TeacherClassVO teacherClassVO) throws Exception;
	
	Result findDetailTeacherClass(TeacherClassVO teacherClassVO) throws Exception;
	
	Result listTeacherClass(TeacherClassVO teacherClassVO) throws Exception;

    Result listTeacherClassPage(TeacherClassVO teacherClassVO) throws Exception;
	
	Result countTeacherClass(TeacherClassVO teacherClassVO) throws Exception;
	
	Result deleteTeacherClass(TeacherClassVO teacherClassVO) throws Exception;
}