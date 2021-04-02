package com.wx.exam.controller.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.wx.exam.pojo.data.ExamDO;
import com.wx.exam.pojo.data.StudentDO;
import com.wx.exam.pojo.vo.*;
import com.wx.exam.service.ExamService;
import com.wx.exam.utils.DataUtils;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("/student/exam")
public class StudentExamController {

    @Autowired
    ExamService examService;

    int pageSize=5;

    int navigatePages=5;

    /**
     * 学生考试列表无页码
     * @param session
     * @param model
     */
    @RequestMapping("/list")
    public String list(HttpSession session, Model model) throws Exception {
        return list(1,session,model);
    }


    /**
     * 和上面是重载方法 ，有页码的查询
     * 根据登陆的学生所在班级id查询考试信息，显示出来
     * @param pn
     * @param session
     * @param model
     */
    @RequestMapping("/list/{pn}")
    public String list(@PathVariable Integer pn, HttpSession session, Model model) throws Exception {
        StudentDO student = (StudentDO)session.getAttribute("student");
        //封装classvo
        ClassVO classVO=new ClassVO();
        classVO.setId(student.getFkClass());//查询到学生所在的班级
        //封装 页码 页面大小和导航大小
        classVO.setPageSize(pageSize);
        classVO.setPageCode(DataUtils.getPageCode(pn+""));
        classVO.setSize(navigatePages);
        PageBean<ExamVO> pageBean = examService.findExamByClassId(classVO);
        model.addAttribute("pageBean",pageBean);
        return "student/exam_list";
    }

    @RequestMapping("/joined/{eid}")
    public String joined(@PathVariable Integer eid,HttpSession session,Model model) throws Exception {
        //判断此学生是否参加过考试
        StudentDO student =(StudentDO) session.getAttribute("student");
       Boolean flag= examService.hasJoined(eid,student.getId());
       if(flag){
           //参与考试
            model.addAttribute("message","你已经参加过本次考试");
            return "error";
       }
       //查询考试的信息
        BeginExamVO exam = examService.joined(eid);
          model.addAttribute("exam",exam);
        return "student/exam_take";
    }

    /**传来的数据
     * result: {"eid":"26","questions":[{"id":"19","type":1,"answer":"2"},{"id":"25","type":1,"answer":"2"},
     * {"id":"2","type":2,"answer":"0,1"},{"id":"20","type":2,"answer":"2"},
     * {"id":"1","type":3,"answer":"1"},{"id":"6","type":3,"answer":"0"}]}
     *
     * 和筛选题库有所不同的是 上面是相对于有key值的
     * {"types":[{"id":"1","type":"1"},{"id":"2","type":"2"},{"id":"19","type":"1"},
     * {"id":"20","type":"2"},{"id":"6","type":"3"}],"type":"1"}
     */
    @RequestMapping("/submit")
    @ResponseBody
    public Result submit(String result,HttpSession session) throws IOException {
        //json数据转为java对象
        ObjectMapper mapper=new ObjectMapper();
        ExamAnswerVO examAnswerVO = mapper.readValue(result, ExamAnswerVO.class);
        // System.out.println(result);
        StudentDO student = (StudentDO)session.getAttribute("student");
       return examService.marking(examAnswerVO,student.getId());
    }
}
