package com.wx.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.wx.exam.mapper.*;
import com.wx.exam.pojo.data.*;
import com.wx.exam.pojo.vo.*;
import com.wx.exam.service.ExaminationResultService;

import com.wx.exam.utils.AnswerUtils;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.QuestionType;
import com.wx.exam.utils.Result;
import com.wx.exam.utils.exception.PageCodeException;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/27
 */
@Service("examinationResultService")
public class ExaminationResultServiceImpl implements ExaminationResultService {

	private final static Logger LOG = LoggerFactory.getLogger(ExaminationResultServiceImpl.class);

	@Resource
	private ExaminationResultMapper examinationResultMapper;

	@Autowired
	ExamMapper examMapper;

	@Autowired
	ExaminationResulquestionMapper resulquestionMapper;

	@Autowired
	QuestionJudgeMapper questionJudgeMapper;

	@Autowired
	QuestionChoiceMapper questionChoiceMapper;

	@Override
	public Result addExaminationResult(ExaminationResultVO examinationResultVO) throws Exception {
		return null;
	}

	@Override
	public Result updateExaminationResult(ExaminationResultVO examinationResultVO) throws Exception {
		return null;
	}
	
	@Override
	public Result findDetailExaminationResult(ExaminationResultVO examinationResultVO) throws Exception{
		return null;
	}

	@Override
	public List<ExaminationResultDO> listExaminationResult(ExaminationResultVO query) throws Exception{
	/*	*/
		return examinationResultMapper.listExaminationResult(query);
	}
	
	@Override
	public Result listExaminationResultPage(ExaminationResultVO examinationResultVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countExaminationResult(ExaminationResultVO examinationResultVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteExaminationResult(ExaminationResultVO examinationResultVO) throws Exception{
		return null;
	}

	@Override
	public PageBean<ExaminationResultVO> listExaminationResultBySid(ExaminationResultVO query) throws InvocationTargetException, IllegalAccessException {

		//判断页码越界
		Integer count = examinationResultMapper.countExaminationResult(query);
		int max=(count+query.getPageSize()-1)/query.getPageSize();
		if(query.getPageCode()>max){
			throw new PageCodeException("页码越界啦",max);
		}
		PageHelper.startPage(query.getPageCode(),query.getPageSize());
		List<ExaminationResultDO> ResultDOS =examinationResultMapper.listExaminationResult(query);

		ArrayList<ExaminationResultVO> list=new ArrayList<>();
		for (ExaminationResultDO resultDO : ResultDOS) {
			ExaminationResultVO vo=new ExaminationResultVO();
			BeanUtils.copyProperties(vo,resultDO);
			list.add(vo);
		}
		System.out.println(list);
		PageBean<ExaminationResultVO> pageBean=new PageBean<ExaminationResultVO>(
				list,query.getPageSize(),query.getPageCode(),count,query.getSize()
		);
		return pageBean;
	}

	@Override
	public ExamResultViewVO listExaminationResultView(Integer eid, String id) {
		//查询考试——结果表
		ExaminationResultVO examinationResultVO=new ExaminationResultVO();
		examinationResultVO.setFkExam(eid);
		List<ExaminationResultDO> examinationResultDOS = examinationResultMapper.listExaminationResult(examinationResultVO);
		if(examinationResultDOS==null||examinationResultDOS.isEmpty()){
			return null;
		}
			ExaminationResultDO resultDO = examinationResultDOS.get(0);
			ExamResultViewVO resultViewVO=new ExamResultViewVO();
			resultViewVO.setDate(resultDO.getTime());
			resultViewVO.setPoint(resultDO.getPoint());
		//查询exam表 封装 各项分数信息
		ExamVO examVO=new ExamVO();
		examVO.setId(eid);
		ExamDO detailExam = examMapper.findDetailExam(examVO);
		resultViewVO.setSinglePoints(detailExam.getSinglepoints());
		resultViewVO.setJudgePoints(detailExam.getJudgepoints());
		resultViewVO.setMultiPoints(detailExam.getMultipoints());
		//查询试卷答题详情表 封装答题结果
		//ExamResultViewVO.ExamResultQuestionVO
		ExaminationResulquestionVO resultQuestionVO=new ExaminationResulquestionVO();
		resultQuestionVO.setFkExaminationResult(eid);
		List<ExaminationResulquestionDO> resultQuestionDOS = resulquestionMapper.listExaminationResulquestion(resultQuestionVO);
		for (ExaminationResulquestionDO resultQuestionDO : resultQuestionDOS) {
			//遍历每一道题目
			if(resultQuestionDO.getFkQtype()==QuestionType.JUDGE){
				QuestionJudgeVO judgeVO=new QuestionJudgeVO();
				//查找到刺刀题目
				judgeVO.setId(resultQuestionDO.getFkQuestion());
				QuestionJudgeDO judge = questionJudgeMapper.findDetailQuestionJudge(judgeVO);
				ExamResultViewVO.ExamResultQuestionVO questionVO=new ExamResultViewVO.ExamResultQuestionVO();
				questionVO.setId(judge.getId());
				questionVO.setPoint(judge.getScore().intValue());
				questionVO.setAnswer(judge.getAnswer());
				questionVO.setTitle(judge.getQuestion());
				questionVO.setType(judge.getType()+"");
				boolean right = resultQuestionDO.getIsRight() == 0;
				questionVO.setRight(right);
				if(!right){
					questionVO.setWrongAnswer(resultQuestionDO.getWrongAnswer());
					questionVO.setWrongAnswerFacade(AnswerUtils.getAnserFacade(resultQuestionDO.getFkQtype(),resultQuestionDO.getWrongAnswer()));
				}
				resultViewVO.addJudgeQuestion(questionVO);
			}else{
				QuestionChoiceVO questionChoiceVO=new QuestionChoiceVO();
				questionChoiceVO.setId(resultQuestionDO.getFkQuestion());
				QuestionChoiceDO choice = questionChoiceMapper.findDetailQuestionChoice(questionChoiceVO);
				ExamResultViewVO.ExamResultQuestionVO questionVO=new ExamResultViewVO.ExamResultQuestionVO();
				questionVO.setId(choice.getId());
				questionVO.setPoint(choice.getScore().intValue());
				questionVO.setAnswer(choice.getAnswer());
				questionVO.setTitle(choice.getQuestion());
				boolean right = resultQuestionDO.getIsRight() == 0;
				questionVO.setRight(right);
				questionVO.setType(choice.getType()+"");
				if(!right){
					questionVO.setWrongAnswer(resultQuestionDO.getWrongAnswer());
					questionVO.setWrongAnswerFacade(AnswerUtils.getAnserFacade(resultQuestionDO.getFkQtype(),resultQuestionDO.getWrongAnswer()));
				}
				if(choice.getType()==QuestionType.SINGLE){
					resultViewVO.addSingleQuestion(questionVO);
				}
				else{
					resultViewVO.addMultiQuestion(questionVO);
				}
			}
		}
		System.out.println(resultViewVO);
		return resultViewVO;
	}

}