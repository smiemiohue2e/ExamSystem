package com.wx.exam.controller.common;

import com.wx.exam.pojo.vo.GradeVO;
import com.wx.exam.service.MajorService;
import com.wx.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 查询专业信息  通用
 */
@Controller
@RequestMapping("/major")
public class MajorController {

    @Autowired
    MajorService majorService;

    @RequestMapping("/ajax")
    @ResponseBody
    public Result getMajor(Integer grade){
        GradeVO gradeVO=new GradeVO(grade);
        Result result = majorService.listMajorByGrade(gradeVO);
        return result;
    }
}
