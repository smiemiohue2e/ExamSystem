package com.wx.exam.mapper;


import com.wx.exam.pojo.data.TeacherDO;
import com.wx.exam.pojo.vo.TeacherVO;
import com.wx.exam.utils.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by weidong on 2018/07/20
 */
@Repository
public interface TeacherMapper {

    Integer addTeacher(TeacherVO teacherVO);

    Integer updateTeacher(TeacherVO teacherVO);

    TeacherDO findDetailTeacher(TeacherVO teacherVO);

    List<TeacherDO> listTeacher(TeacherVO teacherVO);

    List<TeacherDO> listTeacherPage(TeacherVO teacherVO);

    Integer countTeacher(TeacherVO teacherVO);

    Integer deleteTeacher(TeacherVO teacherVO);



}
