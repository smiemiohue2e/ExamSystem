package com.wx.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.wx.exam.mapper.ExamQuestionMapper;
import com.wx.exam.mapper.QuestionChoiceMapper;
import com.wx.exam.mapper.QuestionJudgeMapper;
import com.wx.exam.pojo.data.ExamQuestionDO;
import com.wx.exam.pojo.data.QuestionChoiceDO;
import com.wx.exam.pojo.data.QuestionJudgeDO;
import com.wx.exam.pojo.vo.*;
import com.wx.exam.service.ExamQuestionService;
import com.wx.exam.service.ExamService;
import com.wx.exam.service.QuestionService;
import com.wx.exam.utils.*;
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
 * Created by wangxiao on 2018/07/22
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {


    private final static Logger LOG = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Resource
    private QuestionChoiceMapper questionChoiceMapper;

    @Autowired
    QuestionJudgeMapper questionJudgeMapper;

    @Autowired
    ExamQuestionMapper examQuestionMapper;

    @Autowired
    ExamQuestionService examQuestionService;

    @Autowired
    ExamService examService;


    @Override
    public PageBean<QuestionListVO> listQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception {
        //页码越界判断
        Integer max = questionChoiceMapper.countQuestionChoice(questionChoiceVO);
        if (questionChoiceVO.getPageCode() > max) {
            //questionChoiceVO.setPageCode(max);
            throw new PageCodeException("页码越界了", max);
        }

        PageHelper.startPage(DataUtils.getPageCode
                (questionChoiceVO.getPageCode() + ""), questionChoiceVO.getPageSize());
        List<QuestionChoiceDO> questionChoiceDOS = questionChoiceMapper.listQuestionChoice(questionChoiceVO);
        ArrayList<QuestionListVO> list = new ArrayList<>();
        for (QuestionChoiceDO listdo : questionChoiceDOS) {
            QuestionListVO vo = new QuestionListVO();
            BeanUtils.copyProperties(vo, listdo);
            vo.setTitle(listdo.getQuestion());
            vo.setPoint(listdo.getScore().intValue());
            vo.setAnswerFacade(AnswerUtils.getAnserFacade(listdo.getType(), listdo.getAnswer()));
            list.add(vo);
        }
//		PageInfo<QuestionChoiceDO> info=new PageInfo<>(questionChoiceDOS);//为什么这里是do的参数
		/*PageBean<QuestionListVO> pageBean=new PageBean<>(
				list,questionChoiceVO.getPageSize(),questionChoiceVO.getPageCode(),
				max,questionChoiceVO.getSize()
		);*/
        PageBean<QuestionListVO> pageBean = new PageBean<>(
                list, questionChoiceVO.getPageSize(), questionChoiceVO.getPageCode(),
                max, questionChoiceVO.getSize()
        );
        return pageBean;
    }

    //判断题
    @Override
    public PageBean<QuestionListVO> listQuestionJudge(QuestionJudgeVO questionJudgeVO) throws Exception {
        //页码越界判断
        Integer count = questionJudgeMapper.countQuestionJudge(questionJudgeVO);
        int max = (count + questionJudgeVO.getPageSize() - 1) / questionJudgeVO.getPageSize();
        if (max != 0) {//todo  防止重定向次数过多
            if (questionJudgeVO.getPageCode() > max) {
                //questionChoiceVO.setPageCode(max);
                throw new PageCodeException("页码越界了", max);
            }
        }
        PageHelper.startPage(DataUtils.getPageCode
                (questionJudgeVO.getPageCode() + ""), questionJudgeVO.getPageSize());
        List<QuestionJudgeDO> questionJudgeDOS = questionJudgeMapper.listQuestionJudge(questionJudgeVO);
        ArrayList<QuestionListVO> list = new ArrayList<>();
        for (QuestionJudgeDO listdo : questionJudgeDOS) {
            QuestionListVO vo = new QuestionListVO();
            BeanUtils.copyProperties(vo, listdo);
            vo.setTitle(listdo.getQuestion());
            vo.setPoint(listdo.getScore().intValue());
            //vo.setAnswerFacade(listdo.getAnswer());
            vo.setAnswerFacade(AnswerUtils.getAnserFacade(listdo.getType(), listdo.getAnswer()));
            list.add(vo);
        }
        PageBean<QuestionListVO> pageBean = new PageBean<>(
                list, questionJudgeVO.getPageSize(), questionJudgeVO.getPageCode()
                , count, questionJudgeVO.getSize()
        );
        return pageBean;
        //todo 如果数据库中没有这个老师的数据就会重定向次数过多？
    }

    /*添加判断题
     *
     * 如果examId不等于空就是在试题里面添加的题目
     * */
    @Override
    public Result addJudge(QuestionJudgeVO judgeVO, Integer examId) throws Exception {
        Integer row = questionJudgeMapper.addQuestionJudge(judgeVO);
        if (row > 0) {
            if (examId != null) {
                //此时已经将题目信息插入判断题表中
                //需要添加信息到exam_question表中
                System.out.println(judgeVO.getId());
                ExamQuestionVO ev = new ExamQuestionVO();
                ev.setFkExam(examId);
                ev.setFkQuestion(judgeVO.getId());
                ev.setFkQtype(3);
                ev.setDelFlag(0);
                return examQuestionService.addExamQuestion(ev, judgeVO.getScore().intValue());
            }
            return Result.getSuccess("添加成功");
        }
        return Result.getFailure("添加失败");
    }

    @Override
    public Result updateJudge(QuestionJudgeVO judgeVO) {
        Integer row = questionJudgeMapper.updateQuestionJudge(judgeVO);
        if (row > 0) {
            return Result.getSuccess("更新成功");
        }
        return Result.getFailure("更新失败");
    }

    @Override
    public Result addChoice(QuestionChoiceVO listVo, Integer examId) throws Exception {
        Integer row = questionChoiceMapper.addQuestionChoice(listVo);
        if (row > 0) {
            if (examId != null) {
                System.out.println(listVo.getId());
                ExamQuestionVO ev = new ExamQuestionVO();
                ev.setFkExam(examId);
                ev.setFkQuestion(listVo.getId());
                ev.setFkQtype(listVo.getType());
                ev.setDelFlag(0);
                return examQuestionService.addExamQuestion(ev, listVo.getScore().intValue());
            }
            return Result.getSuccess("添加成功");
        }
        return Result.getFailure("添加失败");
    }

    @Override
    public Result updateChoice(QuestionChoiceVO listVo) {
        Integer row = questionChoiceMapper.updateQuestionChoice(listVo);
        if (row > 0) {
            return Result.getSuccess("更新成功");
        }
        return Result.getFailure("更新失败");
    }

    @Override
    public PageBean<QuestionManageVO> listQuestionByExam(Integer eid, Integer pageCode, int pageSize, int size) {
        //根据试卷id查找exam_question表找到所有数据

        ExamQuestionVO examQuestionVO = new ExamQuestionVO();
        examQuestionVO.setFkExam(eid);
        List<ExamQuestionDO> examQuestionDOS = examQuestionMapper.listExamQuestion(examQuestionVO);

        if (examQuestionDOS == null || examQuestionDOS.isEmpty()) {
            return null;
        }

        //选择题和判断题id
        ArrayList<Integer> choiceIds = new ArrayList<>();//1 2 19 20
        ArrayList<Integer> judgeIds = new ArrayList<>();//6 7


        for (ExamQuestionDO qdo : examQuestionDOS) {
            if (qdo.getFkQtype() == QuestionType.JUDGE) {
                judgeIds.add(qdo.getFkQuestion());
            } else {
                choiceIds.add(qdo.getFkQuestion());
            }
        }

        System.out.println(choiceIds + " " + judgeIds);

        //封装查询条件
        QuestionManageQuery query = new QuestionManageQuery();
        query.setChoiceIDs(choiceIds);
        query.setJudgeIDs(judgeIds);
        query.setPageCode(pageCode);
        query.setPageSize(pageSize);
        query.setSize(size);
        query.setStartRecord((pageCode - 1) * pageSize);
        List<QuestionManageVO> list = examQuestionMapper.listExamQuestionByExam(query);
        System.out.println(list);

        //设置门面值
        for (QuestionManageVO questionManageVO : list) {
            questionManageVO.setAnswerFacade(QuestionType.getTypeByNum(Integer.parseInt(questionManageVO.getType())));
        }

        PageBean<QuestionManageVO> pageBean = new PageBean<>(
                list, pageSize, pageCode, examQuestionDOS.size(), size
        );
        return pageBean;
    }

    @Override
    public Result listAllQuestionByType(QuestionBankVO vo) throws InvocationTargetException, IllegalAccessException {
        //定义存储所有题目的集合
        ArrayList<QuestionListVO> list = new ArrayList<>();
        //判断筛选的题型
        if (QuestionType.JUDGE == Integer.parseInt(vo.getType())) {
            //判断题
            //查询所有的判断题
            List<QuestionJudgeDO> judgeDOS = questionJudgeMapper.listQuestionJudge(new QuestionJudgeVO());
            //解析
            parsingJudgeDOS(judgeDOS, list);
        } else {

            QuestionChoiceVO ChoiceVO = new QuestionChoiceVO();
            //单选题
            if (QuestionType.SINGLE == Integer.parseInt(vo.getType())) {
                ChoiceVO.setType(QuestionType.SINGLE);
            } else {
                //多选题
                ChoiceVO.setType(QuestionType.MULTI);
            }

            List<QuestionChoiceDO> choiceDOS = questionChoiceMapper.listQuestionChoice(ChoiceVO);
            parsingChoiceDOS(choiceDOS, list);
        }

        //将试卷上已有的题目去掉
            //遍历已经存在的题目

            for(int j=0;j<vo.getTypes().size();j++){
                TypesVO typesVO = vo.getTypes().get(j);
                if(typesVO.getType()!=null){
                    for(int i=0;i<list.size();i++){
                        QuestionListVO q=list.get(i);
                        //如果有类型相同，并且id也相同则是同一题，应将把他从题目集合移除
                        //String类型用 equal
                        if(typesVO.getType().equals(q.getType())&&Integer.parseInt(typesVO.getId())==q.getId()){
                            list.remove(i);
                        }
                    }
                }
            }


        //System.out.println(list);
        return new Result(Result.CODE_SUCCESS,list);
    }

    //筛选之后添加
    @Override
    public Result addExamQuestion(ArrayList<ExamQuestionVO> list, int examId) throws Exception {
        for (ExamQuestionVO examQuestionVO : list) {
            //先添加试卷题目联系的表 exam_question
            Integer row = examQuestionMapper.addExamQuestion(examQuestionVO);
            if(row>0){
                if(examId!=0){
                   examService.updateExam(examQuestionVO,examQuestionVO.getScore());
                }
            }
        }
        return Result.getSuccess("成功添加试题");
    }

    private void parsingChoiceDOS(List<QuestionChoiceDO> choiceDOS, ArrayList<QuestionListVO> list) throws InvocationTargetException, IllegalAccessException {
        for (QuestionChoiceDO choiceDO : choiceDOS) {
            QuestionListVO vo=new QuestionListVO();
            BeanUtils.copyProperties(vo,choiceDO);
            vo.setTitle(choiceDO.getQuestion());
            vo.setAnswerFacade(AnswerUtils.getAnserFacade(choiceDO.getType(),choiceDO.getAnswer()));
            vo.setPoint(choiceDO.getScore().intValue());
            list.add(vo);
        }
    }

    private void parsingJudgeDOS(List<QuestionJudgeDO> judgeDOS, ArrayList<QuestionListVO> list) throws InvocationTargetException, IllegalAccessException {
        for (QuestionJudgeDO judgeDO : judgeDOS) {
            QuestionListVO vo=new QuestionListVO();
            BeanUtils.copyProperties(vo,judgeDO);
            vo.setTitle(judgeDO.getQuestion());
            vo.setPoint(judgeDO.getScore().intValue());
            vo.setAnswerFacade(AnswerUtils.getAnserFacade(judgeDO.getType(),judgeDO.getAnswer()));
            list.add(vo);
        }
    }


}