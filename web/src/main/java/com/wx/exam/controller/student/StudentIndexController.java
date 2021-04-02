package com.wx.exam.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentIndexController {

    @RequestMapping("/index")
    public String index(){
        return "student/index";
    }
}
