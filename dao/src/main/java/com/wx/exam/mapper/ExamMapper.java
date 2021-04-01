package com.wx.exam.mapper;


import com.wx.exam.pojo.data.ExamDO;
import com.wx.exam.pojo.vo.ClassVO;
import com.wx.exam.pojo.vo.ExamVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/24
 */
@Repository
public interface ExamMapper {

    Integer addExam(ExamVO examVO);

    Integer updateExam(ExamVO examVO);

    ExamDO findDetailExam(ExamVO examVO);

    List<ExamDO> listExam(ExamVO examVO);

    List<ExamDO> listExamPage(ExamVO examVO);

    Integer countExam(ExamVO examVO);

    Integer deleteExam(ExamVO examVO);

    List<ExamDO> listExamByClassId(ClassVO query);
}
