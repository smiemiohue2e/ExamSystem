package com.wx.exam.controller.manager;

import com.wx.exam.pojo.Manager;
import com.wx.exam.pojo.data.ManagerDO;
import com.wx.exam.pojo.vo.ManagerVO;
import com.wx.exam.service.ManagerService;
import com.wx.exam.utils.Result;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class ManagerInfoController {
    @Autowired
    ManagerService managerService;

    @RequestMapping("/password")
    public String password(){
        return "admin/password";
    }

    //校验原先密码

    @RequestMapping("/password/check")
    @ResponseBody
    public Result check(String password, HttpSession httpSession){
        ManagerDO manager = (ManagerDO)httpSession.getAttribute("manager");
        Md5Hash md5Hash=new Md5Hash(password,manager.getName());
        if(manager.getPassword().equals(md5Hash.toString())){
            return new Result(Result.CODE_SUCCESS,"密码正确");
        }
        return new Result(Result.CODE_FAILURE);
    }

    //修改密码
    @RequestMapping("/password/modify")
    public String modify(String oldPassword,String newPassword,
                         HttpSession httpSession,Model model) throws Exception {
        ManagerDO manager = (ManagerDO) httpSession.getAttribute("manager");

        Md5Hash md5Hash=new Md5Hash(oldPassword,manager.getName());
        if(!manager.getPassword().equals(md5Hash.toString())){//防止暴力
            return "error";
        }

        manager.setModified(1);
        //manager.setPassword(new Md5Hash(newPassword,manager.getName()).toString());
       //int i= managerService.update(manager);
        ManagerVO managerVO=new ManagerVO();
        managerVO.setId(manager.getId());
        //BeanUtils.copyProperties(managerVO,manager);
        managerVO.setPassword(new Md5Hash(newPassword,manager.getName()).toString());
        Result result = managerService.updateManager(managerVO);
        model.addAttribute("message",result.getMsg());
       model.addAttribute("url","/admin/index");
       return "success";
    }
}
