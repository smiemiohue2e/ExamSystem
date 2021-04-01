package com.wx.exam.service;



import com.wx.exam.pojo.data.GradeDO;
import com.wx.exam.pojo.vo.GradeVO;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;

import java.util.List;

/**
 * <br/>
 * Created by weidong on 2018/07/18
 */
public interface GradeService {

	Result addGrade(GradeVO gradeVO) throws Exception;

	Result updateGrade(GradeVO gradeVO) throws Exception;
	
	Result findDetailGrade(GradeVO gradeVO) throws Exception;

	PageBean<GradeDO> listGrade(GradeVO gradeVO) throws Exception;

    Result listGradePage(GradeVO gradeVO) throws Exception;
	
	Result countGrade(GradeVO gradeVO) throws Exception;
	
	Result deleteGrade(GradeVO gradeVO) throws Exception;

	public List<GradeDO> listAll();
}