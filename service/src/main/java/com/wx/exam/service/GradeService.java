package com.wx.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.exam.mapper.GradeMapper;
import com.wx.exam.pojo.Grade;
import com.wx.exam.pojo.GradeExample;
import com.wx.exam.pojo.vo.GradeQuery;
import com.wx.exam.utils.DataUtils;
import com.wx.exam.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;


@Service
public class GradeService {
    @Autowired
    GradeMapper gradeMapper;
    
    public PageBean<Grade> pageSearch(GradeQuery gradeQuery) {
        PageHelper.startPage(DataUtils.getPageCode(gradeQuery.getPageCode()+""),gradeQuery.getPageSize());
       /* GradeExample gradeExample=new GradeExample();
        GradeExample.Criteria criteria = gradeExample.createCriteria();
        if(gradeQuery.getSearch()!=null){
           criteria.andGradeEqualTo(gradeQuery.getSearch());
        }
        List<Grade> grades = gradeMapper.selectByExample(gradeExample);*/

        List<Grade> grades = gradeMapper.pageSearch(gradeQuery.getSearch());
        //根据插件提供的分页信息
        PageInfo<Grade> info=new PageInfo<>(grades);
        //构建自己的分页信息
        PageBean<Grade> pb=new PageBean(grades,info.getPageSize(),
                info.getPageNum(),(int)info.getTotal(),info.getSize()
        );
        return pb;
    }


    public void addGrade(Grade grade) {
        gradeMapper.insert(grade);
    }

    //根据年级名称查找年级信息
    public Grade findGrade(Grade grade) {
       return gradeMapper.findGrade(grade);
    }

    /**
     * 删除年级信息
     * @param id
     */
    public int deleteGrade(Integer id) {
        return gradeMapper.deleteGrade(id);
    }

}
