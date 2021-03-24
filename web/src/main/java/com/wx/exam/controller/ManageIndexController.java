package com.wx.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ManageIndexController {

    @RequestMapping("/index")
    public String password(Model model){
        model.addAttribute("function",1);
        return "admin/index";
    }
}
