package com.wx.exam.mapper;

import com.wx.exam.pojo.data.ExaminationResulquestionDO;
import com.wx.exam.pojo.vo.ExaminationResulquestionVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/27
 */
@Repository
public interface ExaminationResulquestionMapper {

    Integer addExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO);

    Integer updateExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO);

    ExaminationResulquestionDO findDetailExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO);

    List<ExaminationResulquestionDO> listExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO);

    List<ExaminationResulquestionDO> listExaminationResulquestionPage(ExaminationResulquestionVO examinationResulquestionVO);

    Integer countExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO);

    Integer deleteExaminationResulquestion(ExaminationResulquestionVO examinationResulquestionVO);

}
