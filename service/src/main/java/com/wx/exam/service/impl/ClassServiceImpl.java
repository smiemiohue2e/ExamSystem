package com.wx.exam.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.exam.mapper.ClassMapper;
import com.wx.exam.pojo.data.ClassDO;
import com.wx.exam.pojo.data.MajorDO;
import com.wx.exam.pojo.vo.*;
import com.wx.exam.service.ClassService;
import com.wx.exam.utils.DataUtils;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;
import com.wx.exam.utils.exception.PageCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/** 
 * <br/>
 * Created by weidong on 2018/07/19
 */
@Service("classService")
public class ClassServiceImpl implements ClassService {

	private final static Logger LOG = LoggerFactory.getLogger(ClassServiceImpl.class);

	@Resource
	private ClassMapper classMapper;

	@Override
	public Result addClass(ClassVO classVO) throws Exception {
		//先查询添加的班级是否存在
		List<ClassDO> classListVOS = classMapper.listClass(classVO);
		if(classListVOS!=null&&!classListVOS.isEmpty()){
			return Result.getFailure("该班级已存在");
		}
		Integer integer = classMapper.addClass(classVO);
		if(integer>0){
			return  Result.getSuccess("添加成功");
		}
		return  Result.getFailure("失败");
	}

	@Override
	public Result updateClass(ClassVO classVO) throws Exception {
		return null;
	}
	
	@Override
	public ClassDO findDetailClass(ClassVO classVO) throws Exception{
		return classMapper.findDetailClass(classVO);
	}

	@Override
	public List<ClassDO> listClass(ClassVO classVO) throws Exception{
		List<ClassDO> classListVOS = classMapper.listClass(classVO);
		return  classListVOS;
	}
	
	@Override
	public Result listClassPage(ClassVO classVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countClass(ClassVO classVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteClass(ClassVO classVO) throws Exception{
		//先查找此条记录是否存在
		if(findClass(classVO)){
			return Result.getFailure("班级不存在无法删除");
		}
		classVO.setDelFlag(1);
		Integer integer = classMapper.deleteClass(classVO);
		if(integer>0){
			return Result.getSuccess("删除成功");
		}
		return Result.getSuccess("删除失败");
	}

	public boolean findClass(ClassVO classVO){
		List<ClassDO> classListVOS = classMapper.listClass(classVO);
		if(classListVOS!=null){
			return false;
		}
		return true;
	}

	@Override
	public PageBean<ClassListVO> listClassByPage(ClassVO classVO) throws Exception{

		//防止页码越界
		Integer count = classMapper.countClass(classVO);
		int max = (count + classVO.getPageSize() - 1) / classVO.getPageSize();
		if (classVO.getPageCode() > max) {
			//classVO.setPageCode(max);
            throw new PageCodeException("页码越界了",max);
		}

		PageHelper.startPage(DataUtils.getPageCode(classVO.getPageCode()+""), classVO.getPageSize());
		List<ClassListVO> classes = classMapper.listClassAssociation(classVO);

		//插件提供的分页信息
		PageInfo<ClassListVO> info = new PageInfo<>(classes);

		//根据插件提供的分页信息，构造我们自己的分页信息
		PageBean<ClassListVO> pageBean = new PageBean<ClassListVO>(classes, info.getPageSize()
				, info.getPageNum(), (int) info.getTotal(), info.getSize());

		return pageBean;
	}

	//CTRL+SHIFT+N
	public  List<ClassListVO> listClassByTeacher(TeacherVO teacherVO){
		List<ClassListVO> list=	classMapper.listClassByTeacher(teacherVO);
		return list;
	}



	@Override
	public Result listClassByExam(ExamVO examVO) {
		List<ClassListVO> classListVOS = classMapper.listClassByExam(examVO);
		return new Result(Result.CODE_SUCCESS,classListVOS);
	}


	@Override
	public Result addExamClass(Integer examId, ArrayList<ExamClassVO> list) {
		return null;
	}

}