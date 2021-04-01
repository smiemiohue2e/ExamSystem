package com.wx.exam.controller.student;

import com.wx.exam.pojo.data.ExamDO;
import com.wx.exam.pojo.data.StudentDO;
import com.wx.exam.pojo.vo.BeginExamVO;
import com.wx.exam.pojo.vo.ClassVO;
import com.wx.exam.pojo.vo.ExamVO;
import com.wx.exam.service.ExamService;
import com.wx.exam.utils.DataUtils;
import com.wx.exam.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("/student/exam")
public class StudentController {

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
}
