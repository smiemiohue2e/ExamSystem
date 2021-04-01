package com.wx.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.wx.exam.mapper.GradeMapper;
import com.wx.exam.pojo.data.GradeDO;
import com.wx.exam.pojo.vo.GradeVO;
import com.wx.exam.service.GradeService;
import com.wx.exam.utils.DataUtils;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;
import com.wx.exam.utils.exception.BusinessException;
import com.wx.exam.utils.exception.PageCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 年级管理
 * <br/>
 * Created by weidong on 2018/07/18
 */
@Service("gradeService")
public class GradeServiceImpl implements GradeService {

	private final static Logger LOG = LoggerFactory.getLogger(GradeServiceImpl.class);

	@Resource
	private GradeMapper gradeMapper;

	@Override
	public Result addGrade(GradeVO gradeVO) throws Exception {
		if (findGradeByName(gradeVO)) {
			return Result.getFailure("此年级存在");
		}
		//保存到数据库
		int count = gradeMapper.addGrade(gradeVO);
		if (count > 0) {
			return new Result(Result.CODE_SUCCESS, "添加年级成功");
		}
		throw new RuntimeException("添加错误");
	}

	@Override
	public Result updateGrade(GradeVO gradeVO) throws Exception {
		return null;
	}
	
	@Override
	public Result findDetailGrade(GradeVO gradeVO) throws Exception{
		return null;
	}

	/**
	 * 查询年级列表
	 */
	@Override
	public PageBean<GradeDO> listGrade(GradeVO query) throws Exception{
		//页码越界
		//获取数据的总个数
		int count = gradeMapper.countGrade(query);
		//计算最大页数
		int max = (count + query.getPageSize() - 1) / query.getPageSize();
		if (query.getPageCode() > max) {
			//query.setPageCode(max);
			throw new PageCodeException("页码越界了",max);
		}

		int pageCode = DataUtils.getPageCode(query.getPageCode() + "");
		PageHelper.startPage(pageCode, query.getPageSize());
		//CTRL + ALT + 鼠标左    键进入方法的实现
		List<GradeDO> grades = gradeMapper.grades(query);
		//插件提供的分页信息
		PageInfo<GradeDO> info = new PageInfo<>(grades);
		//根据插件提供的分页信息，构造我们自己的分页信息
		PageBean<GradeDO> pageBean = new PageBean<GradeDO>(grades,info.getPageSize()
				,info.getPageNum(),(int)info.getTotal(),info.getSize());
		//System.out.println(grades);
		return pageBean;
	}

	@Override
	public List<GradeDO> listAll(){
		return gradeMapper.listGrade(new GradeVO());
	}

	@Override
	public Result listGradePage(GradeVO gradeVO) throws Exception{
		return null;
	}

	@Override
	public Result countGrade(GradeVO gradeVO) throws Exception{
		return null;
	}

	/**
	 * 根据年级名称查询年级是否存在
	 * @param gradeVO 年级信息
	 * @return true 存在， false 不存在
	 */
	public boolean findGradeByName(GradeVO gradeVO){
		List<GradeDO> gradeDOS = gradeMapper.listGrade(gradeVO);
		if (gradeDOS != null && !gradeDOS.isEmpty()) {
			return true;//存在
		}
		return false;
	}


	@Override
	public Result deleteGrade(GradeVO gradeVO) throws Exception{
		if (!findGradeByName(gradeVO)) {
			return Result.getFailure("年级不存在，无法删除");
		}
		gradeVO.setDelFlag(1);
		Integer count = gradeMapper.deleteGrade(gradeVO);
		if (count > 0) {
			return new Result(Result.CODE_SUCCESS, "删除年级成功");
		}
		throw new BusinessException("添加错误");
	}

}