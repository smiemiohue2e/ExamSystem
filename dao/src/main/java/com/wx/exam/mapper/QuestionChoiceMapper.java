package com.wx.exam.mapper;


import com.wx.exam.pojo.data.QuestionChoiceDO;
import com.wx.exam.pojo.vo.QuestionChoiceVO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/22
 */
@Repository
public interface QuestionChoiceMapper {

    Integer addQuestionChoice(QuestionChoiceVO questionChoiceVO);

    Integer updateQuestionChoice(QuestionChoiceVO questionChoiceVO);

    QuestionChoiceDO findDetailQuestionChoice(QuestionChoiceVO questionChoiceVO);

    List<QuestionChoiceDO> listQuestionChoice(QuestionChoiceVO questionChoiceVO);

    List<QuestionChoiceDO> listQuestionChoicePage(QuestionChoiceVO questionChoiceVO);

    Integer countQuestionChoice(QuestionChoiceVO questionChoiceVO);

    Integer deleteQuestionChoice(QuestionChoiceVO questionChoiceVO);

    List<QuestionChoiceDO> listQuestionChoiceByIdIn(ArrayList<Integer> choiceIds);
}
