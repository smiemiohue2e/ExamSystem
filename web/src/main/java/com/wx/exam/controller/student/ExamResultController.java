package com.wx.exam.controller.student;

import com.sun.glass.ui.Size;
import com.wx.exam.pojo.Manager;
import com.wx.exam.pojo.data.StudentDO;
import com.wx.exam.pojo.vo.ExaminationResultVO;
import com.wx.exam.service.ExaminationResultService;
import com.wx.exam.utils.DataUtils;
import com.wx.exam.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("student/notes")
public class ExamResultController {

    int pageSize=5;

    int navigateSize=5;

    @Autowired
    ExaminationResultService examinationResultService;

    @RequestMapping()
    public String notes(HttpSession session, Model model) throws Exception {
       return notes(1,session,model);
    }

    @RequestMapping("/{pn}")
    public String notes(@PathVariable Integer pn,HttpSession session,Model model) throws Exception {
        //根据学生查询考试结果表
        StudentDO student = (StudentDO)session.getAttribute("student");
        ExaminationResultVO resultVO=new ExaminationResultVO();
        resultVO.setFkStudent(student.getId());
        resultVO.setPageCode(DataUtils.getPageCode(pn+""));
        resultVO.setPageSize(pageSize);
        resultVO.setSize(navigateSize);
      // PageBean<ExaminationResultVO> pageBean= examinationResultService.listExaminationResult(resultVO);
        PageBean<ExaminationResultVO> pageBean= examinationResultService.listExaminationResultBySid(resultVO);
        model.addAttribute("pageBean",pageBean);
        return "student/examinationResult_list";
    }
}
