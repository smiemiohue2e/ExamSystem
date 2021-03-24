package com.wx.exam.controller;

import com.wx.exam.pojo.Major;
import com.wx.exam.pojo.vo.MajorQuery;
import com.wx.exam.service.MajorService;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/major")
public class ManageMajorController {

    @Autowired
    MajorService majorService;

    @Value("#{properties['major.pageSize']}")
    int pageSize;

    @Value("#{properties['major.size']}")
    int size;

    @RequestMapping("/list")
    public String list(MajorQuery majorQuery, Model model){
        model.addAttribute("function",3);
        majorQuery.setPageSize(pageSize);
        majorQuery.setSize(size);
      PageBean<Major> pageBean= majorService.pageSearch(majorQuery);
      model.addAttribute("pageBean",pageBean);
      model.addAttribute("search",majorQuery.getSearch());
      return "admin/major_list";
    }

    /**
     * 添加专业
     * @param major
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result addMajor(String major){
        if(majorService.findMajor(new Major(major))){
            return new Result(Result.CODE_FAILURE,"该专业已存在");
        }
        majorService.addMajor(new Major(major));
        return new Result(Result.CODE_SUCCESS,"添加专业成功");
    }
    /**
     * 编辑专业
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result editMajor(Integer id,String major){
        if(majorService.findMajor(new Major(major))){
            return new Result(Result.CODE_FAILURE,"该专业已存在");
        }
        majorService.updateMajor(new Major(id,major));
        return new Result(Result.CODE_SUCCESS,"修改专业成功");
    }
}
