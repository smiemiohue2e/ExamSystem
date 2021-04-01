package com.wx.exam.controller.manager;

import com.wx.exam.pojo.data.GradeDO;
import com.wx.exam.pojo.data.MajorDO;
import com.wx.exam.pojo.vo.ClassListVO;
import com.wx.exam.pojo.vo.ClassVO;
import com.wx.exam.pojo.vo.TeacherVO;
import com.wx.exam.service.ClassService;
import com.wx.exam.service.GradeService;
import com.wx.exam.service.MajorService;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/clazz")
public class ManageClassController {

    @Autowired
    ClassService classService;

    @Autowired
    MajorService majorService;

    @Autowired
    GradeService gradeService;

//    @Value("#{properties['clazz.pageSize']}")
    int pageSize = 5;
//    @Value("#{properties['clazz.navigatePages']}")
    int navigatePages = 5;

    @RequestMapping("/list")
    public String pageSearch(ClassVO classQuery, Model model,Integer grade,Integer major) throws Exception {
        model.addAttribute("function",4);
        classQuery.setPageSize(pageSize);
        classQuery.setSize(navigatePages);
        classQuery.setFkGrade(grade);
        classQuery.setFkMajor(major);
      PageBean<ClassListVO> pageBean= classService.listClassByPage(classQuery);
      model.addAttribute("pageBean",pageBean);

      //获得所有年级 和专业
        List<GradeDO> gradeDOS = gradeService.listAll();

        List<MajorDO> majorDOS = majorService.listAll();

        model.addAttribute("grades",gradeDOS);
        model.addAttribute("majors",majorDOS);
        model.addAttribute("query",classQuery);

        return "admin/clazz_list";
    }

    //删除

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) throws Exception {
        ClassVO classVO=new ClassVO();
        classVO.setId(id);
        Result result = classService.deleteClass(classVO);
        return result;
    }

    //添加
    @RequestMapping("/add")
    @ResponseBody
    public Result add(Integer grade,Integer major,Integer clazz) throws Exception {
        ClassVO classVO=new ClassVO();
        classVO.setFkGrade(grade);
        classVO.setFkMajor(major);
        classVO.setCno(clazz);
        Result result = classService.addClass(classVO);
        return result;
    }

    //获取教师所教学生的班级

    @RequestMapping("/listByTeacher")
    @ResponseBody
    public Result listByTeacher(TeacherVO teacherVO,String tid){
        teacherVO.setId(tid);
        List<ClassListVO> classListVOS = classService.listClassByTeacher(teacherVO);
        Result<ClassListVO> list = new Result(Result.CODE_SUCCESS, classListVOS);
        return list;
    }
}
