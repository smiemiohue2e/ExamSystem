package com.wx.exam.mapper;


import com.wx.exam.pojo.data.QuestionJudgeDO;
import com.wx.exam.pojo.vo.QuestionJudgeVO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/22
 */
@Repository
public interface QuestionJudgeMapper {

    Integer addQuestionJudge(QuestionJudgeVO questionJudgeVO);

    Integer updateQuestionJudge(QuestionJudgeVO questionJudgeVO);

    QuestionJudgeDO findDetailQuestionJudge(QuestionJudgeVO questionJudgeVO);

    List<QuestionJudgeDO> listQuestionJudge(QuestionJudgeVO questionJudgeVO);

    List<QuestionJudgeDO> listQuestionJudgePage(QuestionJudgeVO questionJudgeVO);

    Integer countQuestionJudge(QuestionJudgeVO questionJudgeVO);

    Integer deleteQuestionJudge(QuestionJudgeVO questionJudgeVO);

    List<QuestionJudgeDO> listQuestionJudgeByIdIn(ArrayList<Integer> judgeIds);
}
