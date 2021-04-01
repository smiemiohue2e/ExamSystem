package com.wx.exam.controller.manager;



import com.wx.exam.pojo.vo.MajorVO;
import com.wx.exam.service.MajorService;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 专业管理
 */
@Controller
@RequestMapping("/admin/major")
public class ManageMajorController {
    @Resource
    private MajorService majorService;

    //@Value("#{properties['major.pageSize']}")
    private int pageSize=5;
    //显示的页码数量
   // @Value("#{properties['major.navigatePages']}")
    private int navigatePages=5;

    @RequestMapping("/list")
    public String list(@Validated MajorVO query, BindingResult bindingResult, Model model) throws Exception {
        model.addAttribute("function", 3);
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "查询条件错误");
            return "admin/grade_list";
        }
        query.setPageSize(pageSize);
        query.setSize(navigatePages);
        query.setName(query.getSearch());
        PageBean pageBean = majorService.listMajor(query);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("search" ,query.getSearch());
        return "admin/major_list";
    }

    /**
     * 添加专业
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result add(MajorVO major1,String major) throws Exception {
        major1.setName(major);
       return majorService.addMajor(major1);
    }

    /**
     * 编辑专业
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(MajorVO majorVO,String major) throws Exception {
        majorVO.setName(major);
        return majorService.updateMajor(majorVO);
    }

    /**
     * 通过id删除专业信息
     * @param mid 专业id
     * @return 删除结果
     */
    @RequestMapping("/delete/{mid}") //    /delete/16
    @ResponseBody
    public Result delete(@PathVariable Integer mid) throws Exception {
        return majorService.deleteMajor(new MajorVO(mid));
    }

}
