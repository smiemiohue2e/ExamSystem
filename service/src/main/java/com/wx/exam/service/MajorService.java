package com.wx.exam.service;



import com.wx.exam.pojo.data.MajorDO;
import com.wx.exam.pojo.vo.GradeVO;
import com.wx.exam.pojo.vo.MajorVO;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;

import java.util.List;

/**
 * <br/>
 * Created by weidong on 2018/07/18
 */
public interface MajorService {

	Result addMajor(MajorVO majorVO) throws Exception;

	Result updateMajor(MajorVO majorVO) throws Exception;
	
	Result findDetailMajor(MajorVO majorVO) throws Exception;
	
	PageBean listMajor(MajorVO majorVO) throws Exception;

    Result listMajorPage(MajorVO majorVO) throws Exception;
	
	Result countMajor(MajorVO majorVO) throws Exception;
	
	Result deleteMajor(MajorVO majorVO) throws Exception;

    List<MajorDO> listAll();
	/**
	 * 根据年级id查询专业信息
	 */
	Result listMajorByGrade(GradeVO majorVO);
}