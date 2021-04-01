package com.wx.exam.controller.manager;

import com.wx.exam.pojo.vo.ClassVO;
import com.wx.exam.pojo.vo.StudentListVO;
import com.wx.exam.pojo.vo.StudentVO;
import com.wx.exam.service.StudentService;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping("/admin/student")
public class ManageStudentController {

    int pageSize=5;

    int size=5;

    @Autowired
    StudentService studentService;


    @RequestMapping("/list")
    public String list(StudentVO studentVO, Model model,String search){
        model.addAttribute("function",5);
        studentVO.setPageSize(pageSize);
        studentVO.setSize(size);
        studentVO.setName(search);
        PageBean<StudentListVO> pageBean = studentService.listStudentByPage(studentVO);
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("search",studentVO.getName());
        return "admin/student_list";
    }

    //检查学号是否存在
    @RequestMapping("/check")
    @ResponseBody
    public Result check(String id){
        StudentVO studentVO=new StudentVO();
        studentVO.setSno(id);
       return studentService.isExist(studentVO);
    }

    //添加

    @RequestMapping("/add")
    @ResponseBody
    public Result add(StudentVO studentVO,Integer id,String name,Integer clazz) throws Exception {
        studentVO.setId(UUID.randomUUID().toString());
        studentVO.setSno(id+"");
        studentVO.setName(name);
        studentVO.setPassword(new Md5Hash("admin",studentVO.getName()).toString());
        studentVO.setFkClass(clazz);
        studentVO.setModified(0);
        studentVO.setDelFlag("0");

        Result result = studentService.addStudent(studentVO);
        return result;
    }

    //删除

    @RequestMapping("/delete/{sid}")
    @ResponseBody
    public Result delete(@PathVariable  String sid) throws Exception {
        StudentVO studentVO=new StudentVO();
        studentVO.setSno(sid);
        Result result = studentService.deleteStudent(studentVO);
        return result;
    }

    //编辑
    //班级id确定了，专业和年级就有了
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(String name,Integer id,Integer clazz,String studentId) throws Exception {
        StudentVO studentVO=new StudentVO();
        studentVO.setName(name);
        studentVO.setSno(id+"");
        studentVO.setFkClass(clazz);
        studentVO.setId(studentId);
        Result result = studentService.updateStudent(studentVO);
        return result;
    }
}
