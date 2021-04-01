package com.wx.exam.controller.manager;


import com.wx.exam.controller.base.BaseController;
import com.wx.exam.pojo.data.GradeDO;
import com.wx.exam.pojo.vo.GradeVO;
import com.wx.exam.service.GradeService;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 年级管理
 */
@Controller
@RequestMapping("admin/grade")
public class ManageGradeController extends BaseController {

    @Autowired
    GradeService gradeService;

//    @Value("#{properties['grade.pageSize']}")
    int pageSize = 5;//列表大小

//    @Value("#{properties['grade.navigatePages']}")
    int size = 5;//页码大小

    /**
     * 查询年级列表
     *
     * @param query 查询条件
     * @param model model
     * @return 跳转年级列表页面
     */
    @RequestMapping("/list")
    public String list(@Validated GradeVO query, Model model) throws Exception {
        model.addAttribute("function", 2);
        query.setPageSize(pageSize);
        query.setSize(size);
        PageBean<GradeDO> pageBean = gradeService.listGrade(query);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("search", query.getSearch());
       // model.addAttribute("query",query);
        return "admin/grade_list";
    }

    /**
     * 通过id删除年级信息
     *
     * @param gid 年级id
     * @return 删除结果
     */
    @RequestMapping("/delete/{gid}")
    @ResponseBody
    public Result delete(@PathVariable Integer gid) throws Exception {
        L.debug(gid + "");
        GradeVO gradeVO = new GradeVO();
        gradeVO.setId(gid);
        return gradeService.deleteGrade(gradeVO);
    }

    /**
     * 插入年级数据
     *
     * @param grade 年级名称
     * @return Result json格式的结果数据
     */
    @RequestMapping("/add/{grade}")
    @ResponseBody
    public Result add(@PathVariable(value = "grade") Integer grade) throws Exception {
        //保存到数据库
        GradeVO gradeVO = new GradeVO();
        gradeVO.setGrade(grade);
        return gradeService.addGrade(gradeVO);
    }
}
