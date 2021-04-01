package com.wx.exam.controller.common;

import com.wx.exam.pojo.data.GradeDO;
import com.wx.exam.service.GradeService;
import com.wx.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    GradeService gradeService;


    //获得所有年级
    @RequestMapping("/ajax")
    @ResponseBody
    public Result<GradeDO> list(){
        List<GradeDO> gradeDOS = gradeService.listAll();
        return new Result(Result.CODE_SUCCESS,gradeDOS);
    }
}
