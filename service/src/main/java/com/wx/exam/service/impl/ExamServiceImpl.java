package com.wx.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.wx.exam.mapper.*;
import com.wx.exam.pojo.data.*;
import com.wx.exam.pojo.vo.*;
import com.wx.exam.service.ExamService;
import com.wx.exam.service.ExaminationResulquestionService;
import com.wx.exam.service.ExaminationResultService;
import com.wx.exam.utils.*;
import com.wx.exam.utils.exception.BusinessException;
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
import java.util.SortedMap;

/**
 * <br/>
 * Created by wangxiao on 2018/07/24
 */
@Service("examService")
public class ExamServiceImpl implements ExamService {

    private final static Logger LOG = LoggerFactory.getLogger(ExamServiceImpl.class);

    @Resource
    private ExamMapper examMapper;

    @Autowired
    ExamQuestionMapper examQuestionMapper;

    @Autowired
    ExamClassMapper examClassMapper;

    @Autowired
    ExaminationResulquestionService examinationResulquestionService;

    @Autowired
    ExaminationResultService examinationResultService;

    @Autowired
    QuestionChoiceMapper questionChoiceMapper;

    @Autowired
    QuestionJudgeMapper questionJudgeMapper;

    @Autowired
    ExaminationResulquestionMapper examinationResulquestionMapper;

    @Autowired
    ExaminationResultMapper examinationResultMapper;

    @Override
    public Result addExam(ExamVO examVO) throws Exception {
        Integer count = examMapper.addExam(examVO);
        if (count > 0) {
            return Result.getSuccess("添加考试成功");
        }
        return Result.getFailure("添加考试失败");
    }

    @Override
    public Result findDetailExam(ExamVO examVO) throws Exception {
        ExamDO detailExam = examMapper.findDetailExam(examVO);
        ExamDetailsVO detailsVO=new ExamDetailsVO();
        detailsVO.setExamId(detailExam.getId()+"");
        detailsVO.setExamTitle(detailExam.getTitle());
        detailsVO.setExamTime(detailExam.getTimelimit()+"");
        detailsVO.setPoint(detailExam.getPoints()+"");

        //获得判断，单选，多选的选择题数量
        ExamQuestionVO questionVO=new ExamQuestionVO();
        questionVO.setFkExam(detailExam.getId());
        questionVO.setFkQtype(QuestionType.SINGLE);
        Integer row1 = examQuestionMapper.countExamQuestion(questionVO);
        questionVO.setFkQtype(QuestionType.JUDGE);
        Integer row2 = examQuestionMapper.countExamQuestion(questionVO);
        questionVO.setFkQtype(QuestionType.MULTI);
        Integer row3 = examQuestionMapper.countExamQuestion(questionVO);
        int count = row1 + row2 + row3;
        detailsVO.setSingleSize(row1+"");
        detailsVO.setJugdeSize(row2+"");
        detailsVO.setMultiSize(row3+"");
        return new Result(Result.CODE_SUCCESS,detailsVO);
    }

    @Override
    public Result updateExam(ExamQuestionVO examQuestionVO, int i) throws Exception {
        ExamVO examVO = new ExamVO();
        examVO.setId(examQuestionVO.getFkExam());
        //先找到此id对应的考试信息
        ExamDO exam = examMapper.findDetailExam(examVO);
        examVO.setPoints(exam.getPoints() + i);
        examVO.setFkStatus(2);
        if (examQuestionVO.getFkQtype() == QuestionType.JUDGE) {
            examVO.setJudgepoints(exam.getJudgepoints() + i);
        }
        if (examQuestionVO.getFkQtype() == QuestionType.MULTI) {
            examVO.setMultipoints(exam.getMultipoints() + i);
        }
        if (examQuestionVO.getFkQtype() == QuestionType.SINGLE) {
            examVO.setSinglepoints(exam.getSinglepoints() + i);
        }
        Integer num = examMapper.updateExam(examVO);
        if (num > 0) {
            return Result.getSuccess("成功添加试题");
        }
        return Result.getFailure("添加失败");
    }

    @Override
    public PageBean<ExamVO> listExam(ExamVO query) throws Exception {
        int count = examMapper.countExam(query);
        //计算最大页数
        int max = (count + query.getPageSize() - 1) / query.getPageSize();
        if (query.getPageCode() > max) {
            throw new PageCodeException("页码越界啦", 1);
        }

        PageHelper.startPage(query.getPageCode(), query.getPageSize());

        List<ExamDO> exams = examMapper.listExam(query);

        ArrayList examVOS = new ArrayList();
        //门面值的转换
        for (ExamDO exam : exams) {
            ExamVO examVO = new ExamVO();
            BeanUtils.copyProperties(examVO, exam);
            examVO.setStatusFacade(ExamUtils.getStatus(exam.getFkStatus()));
            examVOS.add(examVO);
        }
        PageBean<ExamVO> pageBean = new PageBean<ExamVO>(examVOS, query.getPageSize()
                , query.getPageCode(), count, query.getSize());
        return pageBean;
    }

    @Override
    public Result listExamPage(ExamVO examVO) throws Exception {
        return null;
    }

    @Override
    public Result countExam(ExamVO examVO) throws Exception {
        return null;
    }

    @Override
    public Result deleteExam(ExamVO examVO) throws Exception {
        return null;
    }

    @Override
    public Result updateExamStatus(ExamVO examVO) {
        Integer row = examMapper.updateExam(examVO);
        if(row>0){
            return new Result(Result.CODE_SUCCESS,"更新成功");
        }
        throw new BusinessException("更新失败");
    }

    /**
     * 查询学生所在班级的考试信息
     * @param query
     */
    @Override
    public PageBean<ExamVO> findExamByClassId(ClassVO query) throws InvocationTargetException, IllegalAccessException {

        //页码越界判断
        ExamClassVO examClassVO=new ExamClassVO();
        examClassVO.setFkClass(query.getId());
        Integer count = examClassMapper.countExamClass(examClassVO);
        int max=(count+query.getPageSize()-1)/query.getPageSize();
        if(max>count){
            throw new PageCodeException("页码越界啦",max);
        }
        //封装查询信息
        PageHelper.startPage(query.getPageCode(),query.getPageSize());
        /*ExamVO examVO=new ExamVO();*/

        List<ExamDO> list= examMapper.listExamByClassId(query);

        ArrayList<ExamVO> voArrayList=new ArrayList<>();

        for (ExamDO examDO : list) {
            ExamVO examVO=new ExamVO();
            BeanUtils.copyProperties(examVO,examDO);
            voArrayList.add(examVO);
        }

        PageBean<ExamVO> pb=new PageBean<>(voArrayList,query.getPageSize(),query.getPageCode(),count,query.getSize());
        return pb;
    }


    /**
     * 判断学生是否参加过考试
     * @param eid
     * @param id
     * @return
     */
    @Override
    public Boolean hasJoined(Integer eid, String id) throws Exception {
        ExaminationResultVO resultVO=new ExaminationResultVO();
        resultVO.setFkStudent(id);
        List<ExaminationResultDO>  ResultDOS = examinationResultService.listExaminationResult(resultVO);
        if(ResultDOS!=null&&!ResultDOS.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 查询试卷上的信息
     * @param eid
     */
    @Override
    public BeginExamVO joined(Integer eid) {
        //查询标题之类信息
        ExamVO examVO=new ExamVO();
        examVO.setId(eid);
        List<ExamDO> list = examMapper.listExam(examVO);
        ExamDO exam = list.get(0);
            BeginExamVO beginExamVO=new BeginExamVO();
            beginExamVO.setTitle(exam.getTitle());
            beginExamVO.setLimit(exam.getTimelimit());
            beginExamVO.setPoints(exam.getPoints()+"");
            beginExamVO.setSinglePoints(exam.getSinglepoints());
            beginExamVO.setMultiPoints(exam.getMultipoints());
            beginExamVO.setJudgePoints(exam.getJudgepoints());

            //查询具体的题目
            //先查询选择题和判断题的id
            ArrayList<Integer> choiceIds=new ArrayList<>();

            ArrayList<Integer> judgeIds=new ArrayList<>();
            //调用方法将题目id存储到集合中
            addQuestionId(eid,choiceIds,judgeIds);
            // System.out.println(choiceIds+" "+judgeIds);

            //查到所有单选题信息
       List<QuestionChoiceDO> choiceDOS=  questionChoiceMapper.listQuestionChoiceByIdIn(choiceIds);
        for (QuestionChoiceDO choiceDO : choiceDOS) {
            QuestionListVO vo=new QuestionListVO();
            //vo.setAnswer(choiceDO.getAnswer());
            vo.setPoint(choiceDO.getScore().intValue());
            //vo.setAnswerFacade(AnswerUtils.getAnserFacade(choiceDO.getType(),choiceDO.getAnswer()));
            vo.setTitle(choiceDO.getQuestion());
            //vo.setType(choiceDO.getType()+"");
            vo.setOptiona(choiceDO.getOptiona());
            vo.setOptionb(choiceDO.getOptionb());
            vo.setOptionc(choiceDO.getOptionc());
            vo.setOptiond(choiceDO.getOptiond());
            vo.setId(choiceDO.getId());
            if(choiceDO.getType()==QuestionType.SINGLE){
                vo.setType(QuestionType.SINLE_CN);
                beginExamVO.addSingleQuestion(vo);
            }else{
                vo.setType(QuestionType.MULTI_CN);
                beginExamVO.addMultiQuestion(vo);
            }
        }

        //判断题
        List<QuestionJudgeDO> judgeDOS=  questionJudgeMapper.listQuestionJudgeByIdIn(judgeIds);
        for (QuestionJudgeDO judgeDO : judgeDOS) {
            QuestionListVO vo=new QuestionListVO();
            vo.setId(judgeDO.getId());
            vo.setTitle(judgeDO.getQuestion());
            vo.setPoint(judgeDO.getScore().intValue());
            vo.setType(QuestionType.JUDGE_CN);
            beginExamVO.addJudgeQuestion(vo);
        }
       // System.out.println(beginExamVO);
        return beginExamVO;

    }

    /**
     * 提交后评分
     * @param examAnswerVO 学生答案
     * @param sid 学生id
     * @return
     */
    @Override
    public Result marking(ExamAnswerVO examAnswerVO, String sid) {
        //通过试卷id查询试卷详情
        ExamVO examVO=new ExamVO();
        examVO.setId(Integer.parseInt(examAnswerVO.getEid()));
        ExamDO detailExam = examMapper.findDetailExam(examVO);
        
        //查询到此套试卷所有选择题和判断题id
        ArrayList<Integer> choiceIds=new ArrayList<>();
        ArrayList<Integer> judgeIds=new ArrayList<>();

        addQuestionId(Integer.parseInt(examAnswerVO.getEid()),choiceIds,judgeIds);
        
        //根据题目id分别获取题目详情
        List<QuestionChoiceDO> choiceDOS = questionChoiceMapper.listQuestionChoiceByIdIn(choiceIds);

        List<QuestionJudgeDO> JudgeDOS = questionJudgeMapper.listQuestionJudgeByIdIn(judgeIds);

        //记录总分
        int points=0;
        //遍历选择题
        for (QuestionChoiceDO choiceDO : choiceDOS) {
            //遍历学生答案
            for (ExamAnswerQuestions answer : examAnswerVO.getQuestions()) {
                //如果id和类型一致
                if(choiceDO.getId()==Integer.parseInt(answer.getId())&&choiceDO.getType()==answer.getType()){
                    ExaminationResulquestionVO resulquestionVO=new ExaminationResulquestionVO();
                    //试卷表，考试结果表的id与考试详情表的外键 一样
                    resulquestionVO.setFkExaminationResult(detailExam.getId());
                    resulquestionVO.setFkQuestion(choiceDO.getId());
                    resulquestionVO.setType(choiceDO.getType());
                    //判断答案答对与否
                    if(choiceDO.getAnswer().equals(answer.getAnswer())){
                        points+=choiceDO.getScore().intValue();
                        resulquestionVO.setIsRight(0);
                    }else{
                        resulquestionVO.setIsRight(1);
                        resulquestionVO.setWrongAnswer(answer.getAnswer());
                    }
                    examinationResulquestionMapper.addExaminationResulquestion(resulquestionVO);
                }
            }
        }

        //遍历判断题
        for (QuestionJudgeDO judgeDO : JudgeDOS) {
            for (ExamAnswerQuestions answer : examAnswerVO.getQuestions()) {
                //如果id和类型一致
                if(judgeDO.getId()==Integer.parseInt(answer.getId())&&judgeDO.getType()==answer.getType()){
                    ExaminationResulquestionVO resulquestionVO=new ExaminationResulquestionVO();
                    //试卷表，考试结果表的id与考试详情表的外键 一样
                    resulquestionVO.setType(judgeDO.getType());
                    resulquestionVO.setFkExaminationResult(detailExam.getId());
                    resulquestionVO.setFkQuestion(judgeDO.getId());
                    //判断答案答对与否
                    if(judgeDO.getAnswer().equals(answer.getAnswer())){
                        resulquestionVO.setIsRight(0);
                        points+=judgeDO.getScore().intValue();
                    }else{
                        resulquestionVO.setIsRight(1);
                        resulquestionVO.setWrongAnswer(answer.getAnswer());
                    }
                    examinationResulquestionMapper.addExaminationResulquestion(resulquestionVO);
                }
            }
        }
        //将考试结果信息插入表中
        ExaminationResultVO resultVO=new ExaminationResultVO();
        resultVO.setId(detailExam.getId());
        resultVO.setFkStudent(sid);
        resultVO.setDelFlag(0);
        resultVO.setExamTitle(detailExam.getTitle());
        resultVO.setFkExam(detailExam.getId());
        resultVO.setPoint(points);
        resultVO.setTime(detailExam.getEndtime());
        Integer row = examinationResultMapper.addExaminationResult(resultVO);
        if(row>0){
            return new Result(Result.CODE_SUCCESS,resultVO);
        }

        return null;
    }

    private void addQuestionId(Integer eid, ArrayList<Integer> choiceIds, ArrayList<Integer> judgeIds) {
    //查询考试——题目表
        ExamQuestionVO examQuestionVO=new ExamQuestionVO();
        examQuestionVO.setFkExam(eid);
        List<ExamQuestionDO> examQuestionDOS =examQuestionMapper.listExamQuestion(examQuestionVO);
        for (ExamQuestionDO examQuestionDO : examQuestionDOS) {
            if(examQuestionDO.getFkQtype()==QuestionType.JUDGE){
                judgeIds.add(examQuestionDO.getFkQuestion());
            }
            else{
                choiceIds.add(examQuestionDO.getFkQuestion());
            }
        }
    }
}