package com.wx.exam.controller.common;

import com.wx.exam.pojo.data.ClassDO;
import com.wx.exam.pojo.vo.ClassListVO;
import com.wx.exam.pojo.vo.ClassVO;
import com.wx.exam.service.ClassService;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/clazz")
public class ClassController {
    
    @Autowired
    ClassService classService;

    @RequestMapping("/ajax")
    @ResponseBody
    public Result list(Integer grade, Integer major) throws Exception {
        ClassVO classVO=new ClassVO();
        classVO.setFkGrade(grade);
        classVO.setFkMajor(major);
        List<ClassDO> classDOS = classService.listClass(classVO);
        return new Result(Result.CODE_SUCCESS,classDOS);
    }
}
