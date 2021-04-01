package com.wx.exam.controller.teacher;

import com.wx.exam.pojo.data.ClassDO;
import com.wx.exam.pojo.vo.ExamClassVO;
import com.wx.exam.pojo.vo.ExamVO;
import com.wx.exam.service.ClassService;
import com.wx.exam.service.ExamClassService;
import com.wx.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher/clazz")
public class TeacherClassController {

    @Autowired
    ClassService classService;

    @Autowired
    ExamClassService examClassService;


    //根据考试的试卷id查找所适用的班级
    @RequestMapping("/list")
    @ResponseBody
    public Result list(Integer examId){
        return classService.listClassByExam(ExamVO.getClassByEXAM(examId));
    }

    //试卷分配给班级
    @RequestMapping("/reset")
    @ResponseBody
    public Result reset(Integer examId,String clazzIds){
        ArrayList<ExamClassVO> list=new ArrayList<>();
        if(clazzIds!=null&&!clazzIds.equals(""))
        for (String id : clazzIds.split(",")) {
            ExamClassVO vo=new ExamClassVO();
            vo.setFkClass(Integer.parseInt(id));
            vo.setFkExam(examId);
            list.add(vo);
        }
       return examClassService.addExamClass(examId,list);

    }
}
