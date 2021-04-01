package com.wx.exam.service;


import com.wx.exam.pojo.data.ClassDO;
import com.wx.exam.pojo.vo.*;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;

import java.util.ArrayList;
import java.util.List;

/** 
 * <br/>
 * Created by weidong on 2018/07/19
 */
public interface ClassService {

	Result addClass(ClassVO classVO) throws Exception;

	Result updateClass(ClassVO classVO) throws Exception;
	
	ClassDO findDetailClass(ClassVO classVO) throws Exception;

	PageBean<ClassListVO> listClassByPage(ClassVO classVO) throws Exception;

	List<ClassDO>  listClass(ClassVO classVO) throws Exception;


	Result listClassPage(ClassVO classVO) throws Exception;
	
	Result countClass(ClassVO classVO) throws Exception;
	
	Result deleteClass(ClassVO classVO) throws Exception;

	//得到教师所教班级
	List<ClassListVO> listClassByTeacher(TeacherVO teacherVO);


	Result listClassByExam(ExamVO examVO);

	Result addExamClass(Integer examId, ArrayList<ExamClassVO> list);
}