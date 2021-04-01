package com.wx.exam.mapper;

import com.wx.exam.pojo.data.ExamQuestionDO;
import com.wx.exam.pojo.vo.ExamQuestionVO;
import com.wx.exam.pojo.vo.QuestionManageQuery;
import com.wx.exam.pojo.vo.QuestionManageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/25
 */
@Repository
public interface ExamQuestionMapper {

    Integer addExamQuestion(ExamQuestionVO examQuestionVO);

    Integer updateExamQuestion(ExamQuestionVO examQuestionVO);

    ExamQuestionDO findDetailExamQuestion(ExamQuestionVO examQuestionVO);

    List<ExamQuestionDO> listExamQuestion(ExamQuestionVO examQuestionVO);

    List<ExamQuestionDO> listExamQuestionPage(ExamQuestionVO examQuestionVO);

    Integer countExamQuestion(ExamQuestionVO examQuestionVO);

    Integer deleteExamQuestion(ExamQuestionVO examQuestionVO);

    List<QuestionManageVO> listExamQuestionByExam(QuestionManageQuery query);
}
