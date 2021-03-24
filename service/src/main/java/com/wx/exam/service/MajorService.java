package com.wx.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.exam.mapper.MajorMapper;
import com.wx.exam.pojo.Major;
import com.wx.exam.pojo.MajorExample;
import com.wx.exam.pojo.vo.MajorQuery;
import com.wx.exam.utils.DataUtils;
import com.wx.exam.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService {
    @Autowired
    MajorMapper majorMapper;


    public PageBean<Major> pageSearch(MajorQuery majorQuery) {
        PageHelper.startPage(DataUtils.getPageCode(majorQuery.getPageCode()+""),majorQuery.getSize());
        List<Major> majors = majorMapper.pageSearch(majorQuery.getSearch());
        PageInfo<Major> info=new PageInfo<>(majors);
        PageBean<Major> pageBean=new PageBean(majors,info.getPageSize(),
                info.getPageNum(),(int)info.getTotal(),info.getSize()) ;
        return pageBean;
    }

    /**
     * 根据专业名查找
     * @param major
     * @return
     */
    public boolean findMajor(Major major) {
        MajorExample majorExample=new MajorExample();
        MajorExample.Criteria criteria = majorExample.createCriteria();
        criteria.andNameEqualTo(major.getName());
        int i = majorMapper.countByExample(majorExample);
        return i>0;
    }

    /**
     * 添加专业
     * @param major
     */
    public void addMajor(Major major) {
        majorMapper.addMajor(major);
    }

    /**
     * 修改专业
     * @param major
     */
    public void updateMajor(Major major) {
       /* MajorExample majorExample=new MajorExample();
        MajorExample.Criteria criteria = majorExample.createCriteria();
        criteria.andNameEqualTo(major.getName());
        majorMapper.updateByExample(major,majorExample);*/
       majorMapper.updateMajor(major);
    }
}
