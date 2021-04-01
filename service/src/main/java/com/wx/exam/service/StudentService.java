package com.wx.exam.service;

import com.wx.exam.pojo.data.StudentDO;
import com.wx.exam.pojo.vo.StudentListVO;
import com.wx.exam.pojo.vo.StudentVO;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;

import java.util.List;

/** 
 * <br/>
 * Created by weidong on 2018/07/20
 */
public interface StudentService {

	Result addStudent(StudentVO studentVO) throws Exception;

	Result updateStudent(StudentVO studentVO) throws Exception;
	
	StudentDO findDetailStudent(StudentVO studentVO) throws Exception;
	
	StudentDO listStudent(StudentVO studentVO) ;

    Result listStudentPage(StudentVO studentVO) throws Exception;
	
	Result countStudent(StudentVO studentVO) throws Exception;
	
	Result deleteStudent(StudentVO studentVO) throws Exception;

	PageBean<StudentListVO> listStudentByPage(StudentVO studentVO);

	//查询学号是否存在
    Result<StudentDO> isExist(StudentVO studentVO);
}