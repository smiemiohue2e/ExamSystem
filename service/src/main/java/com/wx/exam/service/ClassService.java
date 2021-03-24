package com.wx.exam.service;


import com.wx.exam.pojo.vo.ClassListVO;
import com.wx.exam.pojo.vo.ClassVO;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;

/** 
 * <br/>
 * Created by weidong on 2018/07/19
 */
public interface ClassService {

	Result addClass(ClassVO classVO) throws Exception;

	Result updateClass(ClassVO classVO) throws Exception;
	
	Result findDetailClass(ClassVO classVO) throws Exception;

	PageBean<ClassListVO> listClass(ClassVO classVO) throws Exception;

    Result listClassPage(ClassVO classVO) throws Exception;
	
	Result countClass(ClassVO classVO) throws Exception;
	
	Result deleteClass(ClassVO classVO) throws Exception;
}