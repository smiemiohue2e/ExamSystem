package com.wx.exam.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.exam.mapper.ClassMapper;
import com.wx.exam.pojo.data.ClassDO;
import com.wx.exam.pojo.vo.ClassListVO;
import com.wx.exam.pojo.vo.ClassVO;
import com.wx.exam.service.ClassService;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
		return null;
	}

	@Override
	public Result updateClass(ClassVO classVO) throws Exception {
		return null;
	}
	
	@Override
	public Result findDetailClass(ClassVO classVO) throws Exception{
		return null;
	}

	@Override
	public PageBean<ClassListVO> listClass(ClassVO classVO) throws Exception{
		//防止页码越界
		Integer count = classMapper.countClass(classVO);//数据库数据条数
		int max=(count+classVO.getPageSize()-1)/(classVO.getPageSize());//最大页数
		if(classVO.getPageCode()>max){
			classVO.setPageCode(max);
		}
		PageHelper.startPage(classVO.getPageCode(),classVO.getPageSize());
		List<ClassListVO> classes= classMapper.listClassAssociation(classVO);
		PageInfo<ClassListVO> info=new PageInfo<ClassListVO>(classes);
		PageBean<ClassListVO> pageBean=new PageBean(classes,
				info.getPageSize(),info.getPageNum(),(int)info.getTotal(),info.getSize());
		return pageBean;
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
		return null;
	}

}