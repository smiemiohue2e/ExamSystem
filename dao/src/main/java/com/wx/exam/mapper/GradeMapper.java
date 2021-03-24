package com.wx.exam.mapper;

import com.wx.exam.pojo.Grade;
import com.wx.exam.pojo.GradeExample;
import com.wx.exam.pojo.vo.GradeQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GradeMapper {
    int countByExample(GradeExample example);

    int deleteByExample(GradeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Grade record);

    int insertSelective(Grade record);

    List<Grade> selectByExample(GradeExample example);

    Grade selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Grade record, @Param("example") GradeExample example);

    int updateByExample(@Param("record") Grade record, @Param("example") GradeExample example);

    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);

    Grade  findGrade(Grade grade);

    int deleteGrade(Integer id);

    List<Grade> pageSearch(Integer search);
}