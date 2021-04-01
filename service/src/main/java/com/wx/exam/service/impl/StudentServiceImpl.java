package com.wx.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.exam.mapper.ClassMapper;
import com.wx.exam.mapper.StudentMapper;
import com.wx.exam.pojo.data.ClassDO;
import com.wx.exam.pojo.data.StudentDO;
import com.wx.exam.pojo.vo.*;
import com.wx.exam.service.StudentService;
import com.wx.exam.utils.DataUtils;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;
import com.wx.exam.utils.exception.PageCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/** 
 * <br/>
 * Created by weidong on 2018/07/20
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

	private final static Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Resource
	private StudentMapper studentMapper;

	@Autowired
	ClassMapper classMapper;

	@Override
	public Result addStudent(StudentVO studentVO) throws Exception {
		Integer count = studentMapper.addStudent(studentVO);
		if(count>0){
			return Result.getSuccess("添加成功");
		}
		return Result.getFailure("添加失败");
	}

	@Override
	public Result updateStudent(StudentVO studentVO) throws Exception {

		/*//查询学生是否存在
		StudentDO detailStudent = studentMapper.findDetailStudent(studentVO);
		if(detailStudent==null){
			return Result.getFailure("该学生不存在无法编辑");
		}

		//查询班级信息是否存在
		ClassDO detailClass = classMapper.findDetailClass(new ClassVO(studentVO.getFkClass()));
		if(detailClass==null){
			return Result.getSuccess("该班级信息不存在无法编辑");
		}*/

		Integer row = studentMapper.updateStudent(studentVO);
		if(row>0){
			return Result.getSuccess("编辑成功");
		}
		return Result.getFailure("编辑失败");
	}
	
	@Override
	public StudentDO findDetailStudent(StudentVO studentVO) throws Exception{
		return studentMapper.findDetailStudent(studentVO);
	}

	@Override
	public StudentDO listStudent(StudentVO studentVO) {
		List<StudentDO> studentDOS = studentMapper.listStudent(studentVO);
		if(studentDOS!=null&&!studentDOS.isEmpty()){
			return studentDOS.get(0);
		}
		return null;
	}
	
	@Override
	public Result listStudentPage(StudentVO studentVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countStudent(StudentVO studentVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteStudent(StudentVO studentVO) throws Exception{
		Integer row = studentMapper.deleteStudent(studentVO);
		if(row>0){
			return Result.getSuccess("删除成功");
		}
		return Result.getFailure("删除失败");
	}

	//自定义分页查询
 	public  PageBean<StudentListVO>	listStudentByPage(StudentVO studentVO){
		//页码越界处理
		Integer count = studentMapper.countStudent(studentVO);
		int max = (count + studentVO.getPageSize() - 1) / (studentVO.getPageSize());
		if(studentVO.getPageCode()>max){
			//studentVO.setPageCode(max);
			throw new PageCodeException("页码越界了",max);
		}

		PageHelper.startPage(DataUtils.getPageCode(studentVO.getPageCode()+""),studentVO.getPageSize());
		List<StudentListVO> list = studentMapper.listStudentByPage(studentVO);
		PageInfo<StudentListVO> info=new PageInfo(list);
		PageBean<StudentListVO> pageBean= new PageBean<StudentListVO>(list,info.getPageSize(),
				info.getPageNum(),(int)info.getTotal(),info.getSize()
		);
		return pageBean;
	}

	public Result<StudentDO> isExist(StudentVO studentVO){
		List<StudentDO> studentDOS = studentMapper.listStudent(studentVO);
		if(studentDOS!=null&&!studentDOS.isEmpty()){
			return Result.getFailure("该学号已存在");
		}
		return Result.getSuccess("该学号不存在");
	}

}