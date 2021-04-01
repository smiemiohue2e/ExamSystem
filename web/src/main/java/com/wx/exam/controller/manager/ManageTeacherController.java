package com.wx.exam.controller.manager;

import com.wx.exam.mapper.TeacherMapper;
import com.wx.exam.pojo.data.TeacherDO;
import com.wx.exam.pojo.vo.StudentVO;
import com.wx.exam.pojo.vo.TeacherClassVO;
import com.wx.exam.pojo.vo.TeacherVO;
import com.wx.exam.service.TeacherService;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/teacher")
public class ManageTeacherController {

    int pageSize=5;
    int size=5;

    @Autowired
    TeacherService teacherService;

    @RequestMapping("/list")
    public String list(TeacherVO teacherVO, Model model){
        model.addAttribute("function",6);
        teacherVO.setPageSize(pageSize);
        teacherVO.setSize(size);
        PageBean<TeacherDO> pageBean = teacherService.listTeacher(teacherVO);
        model.addAttribute("pageBean",pageBean);
        return "admin/teacher_list";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(TeacherVO teacherVO) throws Exception {
        teacherVO.setPassword(new Md5Hash("admin",teacherVO.getName()).toString());
        Result result = teacherService.addTeacher(teacherVO);
        return result;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable String id) throws Exception {
        TeacherVO teacherVO=new TeacherVO();
        teacherVO.setId(id);
        Result result = teacherService.deleteTeacher(teacherVO);
        return result;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(TeacherVO teacherVO) throws Exception {
        Result result = teacherService.updateTeacher(teacherVO);
        return result;
    }

    //添加教师所教的班级

    @RequestMapping("/addClass")
    @ResponseBody
    public Result addClass(String ids,Integer tid){
        ArrayList<TeacherClassVO> list=new ArrayList<>();
        if(ids!=null){
            for (String id:ids.split(",")) {
                TeacherClassVO teacher=new TeacherClassVO();
                teacher.setFkClass(Integer.parseInt(id));
                teacher.setFkTeacher(tid+"");
                list.add(teacher);
            }
        }

        Result result = teacherService.addClass(list, tid);
        return result;
    }
}
