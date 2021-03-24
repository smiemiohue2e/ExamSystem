package com.wx.exam.controller;

import com.wx.exam.pojo.Manager;
import com.wx.exam.pojo.Student;
import com.wx.exam.pojo.Teacher;
import com.wx.exam.shiro.UsernameTypePasswordToken;
import com.wx.exam.utils.DataUtils;
import com.wx.exam.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 校验登陆
     */
    @RequestMapping("/login/do")
    public String loginDo(String username, String password, Integer role , HttpSession httpSession, Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernameTypePasswordToken token =
                new UsernameTypePasswordToken(username,password,role);
        subject.login(token);
        try {
            switch (role){
                case 1:{
                    Student student=(Student) subject.getPrincipal();
                    httpSession.setAttribute("student",student);
                    return "student/index";
                }
                case 2:{
                    Teacher teacher=(Teacher) subject.getPrincipal();
                    httpSession.setAttribute("teacher",teacher);
                    return "teacher/index";
                }
                case 3:{
                    Manager Manager = (Manager)subject.getPrincipal();
                    httpSession.setAttribute("manager",Manager);
                    return "admin/index";
                }
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            model.addAttribute("error","用户名或密码错误");
            return "login";
        }
        return "login";
    }

    /**
     * 验证码校验
     */
    @RequestMapping("/login/verify")
    @ResponseBody
    public Result verify(String verify,HttpSession httpSession){
        return DataUtils.checkVerify(verify,httpSession);
    }
}