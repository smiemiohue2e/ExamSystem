package com.wx.exam.controller.teacher;

import com.wx.exam.pojo.Teacher;
import com.wx.exam.pojo.data.TeacherDO;
import com.wx.exam.pojo.vo.ExamVO;
import com.wx.exam.pojo.vo.QuestionManageVO;
import com.wx.exam.service.ExamService;
import com.wx.exam.service.QuestionService;
import com.wx.exam.utils.DataUtils;
import com.wx.exam.utils.ExamUtils;
import com.wx.exam.utils.PageBean;
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
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/teacher/exam")
public class TeacherExamController {

    int pageSize=5;
    int size=5;

    @Autowired
    ExamService examService;

    @Autowired
    QuestionService questionService;

    @RequestMapping("/list")
    public String list(ExamVO examVO,Model model) throws Exception {
        model.addAttribute("function",2);
        examVO.setPageSize(pageSize);
        examVO.setSize(size);
        PageBean<ExamVO> pageBean = examService.listExam(examVO);
        model.addAttribute("pageBean",pageBean);
        return "teacher/exam_list";
    }

    //添加新的考试
    @RequestMapping("/add")
    @ResponseBody
    public Result add(ExamVO examVO, HttpServletRequest request) throws Exception {
        examVO.setEndtime(new Date());//先随便设置
        examVO.setFkStatus(1);
        TeacherDO teacher = (TeacherDO)request.getSession().getAttribute("teacher");
        examVO.setFkTeacher(teacher.getId());
        examVO.setPoints(0);
        examVO.setSinglepoints(0);
        examVO.setMultipoints(0);
        examVO.setJudgepoints(0);
        return examService.addExam(examVO);
    }

    /**
     * 获取试卷列表
     * @param eid
     * @param pageCode
     * @param model
     */
    @RequestMapping("/examManager/{eid}")
    public String examManager(@PathVariable Integer eid,Integer pageCode,Model model){

        pageCode=DataUtils.getPageCode(pageCode+"");
       PageBean<QuestionManageVO> pageBean=
               questionService.listQuestionByExam(eid,pageCode,pageSize,size);
       model.addAttribute("pageBean",pageBean);
       model.addAttribute("examId",eid);
       return "teacher/question_manage";
    }


    @RequestMapping("/get/{eid}")
    @ResponseBody
    public Result get(@PathVariable Integer eid) throws Exception {
       return examService.findDetailExam(ExamVO.getClassByEXAM(eid));
    }

    /**
     * 切换考试状态
     *
     * teacher/exam/status?examId=1&status=RUNNING&days=2
     */
    @RequestMapping("/status")
    @ResponseBody
    public Result status(Integer examId,String status,Integer days){
        ExamVO examVO=new ExamVO();
        examVO.setId(examId);

        //有两种情况 1.开始运行 和重新运行是一样的  2.结束运行
        if(ExamUtils.RUNNING_EN.equals(status)){
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.DATE,days);
            examVO.setEndtime(calendar.getTime());
        }else{
            examVO.setEndtime(new Date());
        }
        examVO.setFkStatus(ExamUtils.getStatusNumByEN(status));
       return examService.updateExamStatus(examVO);
    }
}
