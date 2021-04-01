package com.wx.exam.service;

import com.wx.exam.pojo.data.TeacherDO;
import com.wx.exam.pojo.vo.TeacherClassVO;
import com.wx.exam.pojo.vo.TeacherVO;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;

import java.util.ArrayList;
import java.util.List;

public interface TeacherService {

	Result addTeacher(TeacherVO teacherVO)throws Exception;

	Result updateTeacher(TeacherVO teacherVO) throws Exception;
	
	Result findDetailTeacher(TeacherVO teacherVO) throws Exception;

	PageBean<TeacherDO>  listTeacher(TeacherVO teacherVO) ;

    Result listTeacherPage(TeacherVO teacherVO) throws Exception;
	
	Result countTeacher(TeacherVO teacherVO) throws Exception;
	
	Result deleteTeacher(TeacherVO teacherVO) throws Exception;

	TeacherDO findTeacherByName(TeacherVO teacherVO);


	Result addClass(ArrayList<TeacherClassVO> list, Integer tid);
}