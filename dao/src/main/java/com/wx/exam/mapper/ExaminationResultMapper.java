package com.wx.exam.mapper;


import com.wx.exam.pojo.data.ExaminationResultDO;
import com.wx.exam.pojo.vo.ExaminationResultVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/27
 */
@Repository
public interface ExaminationResultMapper {

    Integer addExaminationResult(ExaminationResultVO examinationResultVO);

    Integer updateExaminationResult(ExaminationResultVO examinationResultVO);

    ExaminationResultDO findDetailExaminationResult(ExaminationResultVO examinationResultVO);

    List<ExaminationResultDO> listExaminationResult(ExaminationResultVO examinationResultVO);

    List<ExaminationResultDO> listExaminationResultPage(ExaminationResultVO examinationResultVO);

    Integer countExaminationResult(ExaminationResultVO examinationResultVO);

    Integer deleteExaminationResult(ExaminationResultVO examinationResultVO);

}
