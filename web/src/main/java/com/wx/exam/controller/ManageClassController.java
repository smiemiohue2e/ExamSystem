package com.wx.exam.controller;

import com.wx.exam.pojo.Clazz;
import com.wx.exam.pojo.vo.ClassListVO;
import com.wx.exam.pojo.vo.ClassVO;
import com.wx.exam.service.ClassService;
import com.wx.exam.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/clazz")
public class ManageClassController {

    @Autowired
    ClassService classService;

    @Value("#{properties['clazz.pageSize']}")
    int pageSize;
    @Value("#{properties['clazz.navigatePages']}")
    int navigatePages;

    @RequestMapping("/list")
    public String pageSearch(ClassVO classQuery, Model model) throws Exception {
        model.addAttribute("function",4);
        classQuery.setPageSize(pageSize);
        classQuery.setSize(navigatePages);

      PageBean<ClassListVO> pageBean= classService.listClass(classQuery);
      model.addAttribute("pageBean",pageBean);



      return "admin/clazz_list";
    }
}
