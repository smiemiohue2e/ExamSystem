package com.wx.exam.controller.teacher;

import com.wx.exam.pojo.data.TeacherDO;
import com.wx.exam.pojo.vo.*;
import com.wx.exam.service.QuestionService;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.QuestionType;
import com.wx.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;

@Controller
@RequestMapping("/teacher/question")
public class TeacherQuestionController {

    @Autowired
    QuestionService questionService;

    int pageSize=5;

    int size=5;

    @RequestMapping("/singles")
    public String single(QuestionChoiceVO questionChoiceVO, Model model, HttpServletRequest request) throws Exception {
        return singles(1,questionChoiceVO,model,request);
    }


    @RequestMapping("/singles/{pn}")
    public String singles(@PathVariable Integer pn, QuestionChoiceVO questionChoiceVO, Model model, HttpServletRequest request) throws Exception {
        TeacherDO teacherDO=(TeacherDO)request.getSession().getAttribute("teacher");
        questionChoiceVO.setFkTeacher(teacherDO.getId());
        questionChoiceVO.setType(QuestionType.SINGLE);
        questionChoiceVO.setPageCode(pn);
        model.addAttribute("function",4);
        return choice(questionChoiceVO,QuestionType.SINLE_EN,model);
    }


    @RequestMapping("/multis")
    public String multi(QuestionChoiceVO questionChoiceVO, Model model, HttpServletRequest request) throws Exception {
        return multis(1,questionChoiceVO,model,request);
    }

    @RequestMapping("/multis/{pn}")
    public String multis(@PathVariable Integer pn, QuestionChoiceVO questionChoiceVO, Model model, HttpServletRequest request) throws Exception {
        TeacherDO teacherDO=(TeacherDO)request.getSession().getAttribute("teacher");
        questionChoiceVO.setFkTeacher(teacherDO.getId());
        questionChoiceVO.setType(QuestionType.MULTI);
        questionChoiceVO.setPageCode(pn);
        model.addAttribute("function",5);
        return choice(questionChoiceVO,QuestionType.MULTI_EN,model);
    }

    //查询选择题
    public String choice(QuestionChoiceVO questionChoiceVO,String type,Model model) throws Exception {
        questionChoiceVO.setPageSize(pageSize);
        questionChoiceVO.setSize(size);
        PageBean<QuestionListVO> pageBean=questionService.listQuestionChoice(questionChoiceVO);
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("type",type);
        return "teacher/question_list";
    }

    //查询判断题
    @RequestMapping("/judges")
    public String judge(QuestionJudgeVO questionJudgeVO, Model model, HttpServletRequest request) throws Exception {
        return judges(1,questionJudgeVO,model,request);
    }

    @RequestMapping("/judges/{pn}")
    public String judges(@PathVariable Integer pn, QuestionJudgeVO questionJudgeVO, Model model, HttpServletRequest request) throws Exception {
        TeacherDO teacherDO=(TeacherDO)request.getSession().getAttribute("teacher");
        questionJudgeVO.setFkTeacher(teacherDO.getId());
        questionJudgeVO.setType(QuestionType.JUDGE);
        questionJudgeVO.setPageCode(pn);
        model.addAttribute("function",6);
        return judgeProblem(questionJudgeVO,QuestionType.JUDGE_EN,model);
    }

    public String judgeProblem(QuestionJudgeVO questionJudgeVO,String type,Model model) throws Exception {
        questionJudgeVO.setPageSize(pageSize);
        questionJudgeVO.setSize(size);
        PageBean<QuestionListVO> pageBean=questionService.listQuestionJudge(questionJudgeVO);
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("type",type);
        return "teacher/question_list";
    }
    /*
        1.题目添加和编辑
        2.试题里根据examId添加和编辑 vo.getId()==0
    */
    @RequestMapping("/save")
    @ResponseBody
    public Result save(QuestionAddVO vo,Integer examId,HttpServletRequest request) throws Exception {
        //区分是判断题 单选题 多选题
        if(vo.getType().equals(QuestionType.JUDGE_EN)){
            QuestionJudgeVO JudgeVO=new QuestionJudgeVO();
            JudgeVO.setId(null);
            JudgeVO.setQuestion(vo.getTitle());
            JudgeVO.setScore(new BigDecimal(vo.getPoint()));
            JudgeVO.setAnswer(vo.getAnswer());
            JudgeVO.setType(QuestionType.JUDGE);
            JudgeVO.setDelFalg(0);
            TeacherDO teacher = (TeacherDO)request.getSession().getAttribute("teacher");
            JudgeVO.setFkTeacher(teacher.getId());
            //判断是添加，还是编辑
            if(vo.getId()==-1||vo.getId()==0){
                return questionService.addJudge(JudgeVO,examId);
            }
            else{
                JudgeVO.setId(vo.getId());
                return questionService.updateJudge(JudgeVO);
            }
        }
        else{
            QuestionChoiceVO listVo=new QuestionChoiceVO();
            listVo.setQuestion(vo.getTitle());
            listVo.setOptiona(vo.getOptionA());
            listVo.setOptionb(vo.getOptionB());
            listVo.setOptionc(vo.getOptionC());
            listVo.setOptiond(vo.getOptionD());
            listVo.setAnswer(vo.getAnswer());
            listVo.setScore(new BigDecimal(vo.getPoint()));
            TeacherDO teacher = (TeacherDO)request.getSession().getAttribute("teacher");
            listVo.setFkTeacher(teacher.getId());
            listVo.setDelFalg(0);

            //判断多选 单选
            if(vo.getType().equals(QuestionType.SINLE_EN)){
                listVo.setType(QuestionType.SINGLE);
            }
            else{
                listVo.setType(QuestionType.MULTI);
            }
            if(vo.getId()==-1||vo.getId()==0){
                listVo.setId(null);
                return  questionService.addChoice(listVo,examId);
            }else{
                listVo.setId(vo.getId());
                return  questionService.updateChoice(listVo);
            }
        }
    }

    /**
     * 考试的试题列表：筛选题库操作
     * todo 有 bug ：只能传入当前页的题目
     *
     * @param vo
     * @return
     */
    @RequestMapping("/repository")
    @ResponseBody
    public Result repository(@RequestBody QuestionBankVO vo) throws Exception {

//        ArrayList<TypesVO> types = vo.getTypes();
//        String type = vo.getType();
        return questionService.listAllQuestionByType(vo);
    }

    /**
     * 筛选完题库勾选题目后，添加操作
     */
    @RequestMapping("/addExamQuestion")
    @ResponseBody
    public Result addExamQuestion(@RequestBody QuestionBankVO vo) throws Exception {
        //后台传来的数据如下：
        //{"types":[{"id":"19","point":"11"},{"id":"25","point":"5"}],"examId":"2","type":"1"}
        //封装要插入表中的信息
        ArrayList<ExamQuestionVO> list=new ArrayList<>();
        ArrayList<TypesVO> types = vo.getTypes();
        for (TypesVO type : types) {
            ExamQuestionVO eqvo=new ExamQuestionVO();
            eqvo.setFkExam(Integer.parseInt(vo.getExamId()));
            eqvo.setFkQuestion(Integer.parseInt(type.getId()));
            eqvo.setFkQtype(Integer.parseInt(vo.getType()));
            eqvo.setDelFlag(0);
            eqvo.setScore(Integer.parseInt(type.getPoint()));
            list.add(eqvo);
        }
        return questionService.addExamQuestion(list,Integer.parseInt(vo.getExamId()));
    }
}
