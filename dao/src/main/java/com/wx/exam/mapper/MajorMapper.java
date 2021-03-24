package com.wx.exam.mapper;

import com.wx.exam.pojo.Major;
import com.wx.exam.pojo.MajorExample;
import com.wx.exam.pojo.vo.MajorQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MajorMapper {
    int countByExample(MajorExample example);

    int deleteByExample(MajorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Major record);

    int insertSelective(Major record);

    List<Major> selectByExample(MajorExample example);

    Major selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Major record, @Param("example") MajorExample example);

    int updateByExample(@Param("record") Major record, @Param("example") MajorExample example);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);

    List<Major> pageSearch(String search);

    int addMajor(Major major);

    void updateMajor(Major major);
}