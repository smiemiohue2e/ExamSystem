package com.wx.exam.service;


import com.wx.exam.pojo.data.ExaminationResultDO;
import com.wx.exam.pojo.vo.ExamResultViewVO;
import com.wx.exam.pojo.vo.ExaminationResultVO;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * <br/>
 * Created by wangxiao on 2018/07/27
 */
public interface ExaminationResultService {

	Result addExaminationResult(ExaminationResultVO examinationResultVO) throws Exception;

	Result updateExaminationResult(ExaminationResultVO examinationResultVO) throws Exception;
	
	Result findDetailExaminationResult(ExaminationResultVO examinationResultVO) throws Exception;

	List<ExaminationResultDO> listExaminationResult(ExaminationResultVO examinationResultVO) throws Exception;

    Result listExaminationResultPage(ExaminationResultVO examinationResultVO) throws Exception;
	
	Result countExaminationResult(ExaminationResultVO examinationResultVO) throws Exception;
	
	Result deleteExaminationResult(ExaminationResultVO examinationResultVO) throws Exception;

	PageBean<ExaminationResultVO> listExaminationResultBySid(ExaminationResultVO resultVO) throws InvocationTargetException, IllegalAccessException;

	/**
	 * 查询自己考试每道题的具体情况
	 * @param eid
	 * @param id
	 * @return
	 */
    ExamResultViewVO listExaminationResultView(Integer eid, String id);
}