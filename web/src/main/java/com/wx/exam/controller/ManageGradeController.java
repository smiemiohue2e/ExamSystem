package com.wx.exam.controller;

import com.wx.exam.mapper.GradeMapper;
import com.wx.exam.pojo.Grade;
import com.wx.exam.pojo.vo.GradeQuery;
import com.wx.exam.service.GradeService;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/grade")
public class ManageGradeController {


    int pageSize=5;
    int size=4;

    @Autowired
    GradeService gradeService;

    @Autowired
    GradeMapper gradeMapper;

    /**
     * 年级列表
     * @param gradeQuery
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(GradeQuery gradeQuery, Model model){
        model.addAttribute("function",2);
        gradeQuery.setPageSize(5);
        gradeQuery.setSize(4);
        PageBean<Grade> pageBean = gradeService.pageSearch(gradeQuery);
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("search",gradeQuery.getSearch());
        return "admin/grade_list";
    }

    /**
     * 添加
     * @param gra
     * @return
     */
    @RequestMapping("/add/{grade_value}")
    @ResponseBody
    public Result addGrade(@PathVariable(value = "grade_value") Integer gra){
        Grade grade = gradeService.findGrade(new Grade(gra));
        if(grade!=null){
            return new Result(Result.CODE_FAILURE,"该年纪名已存在");
        }
        gradeService.addGrade(new Grade(gra));
        return new Result(Result.CODE_SUCCESS,"添加成功");
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    @Transactional
    public Result deleteGrade(@PathVariable(value = "id") Integer id){
        //逻辑删除
        int i = gradeService.deleteGrade(id);
        //同时删除 与此相关的学生和教师信息
        //gradeService.deleteStudentByGrade();
            return new Result(Result.CODE_SUCCESS,"删除成功");
    }
}
