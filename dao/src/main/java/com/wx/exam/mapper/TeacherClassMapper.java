package com.wx.exam.mapper;


import com.wx.exam.pojo.data.TeacherClassDO;
import com.wx.exam.pojo.vo.TeacherClassVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by weidong on 2018/07/21
 */
@Repository
public interface TeacherClassMapper {

    Integer addTeacherClass(TeacherClassVO teacherClassVO);

    Integer updateTeacherClass(TeacherClassVO teacherClassVO);

    TeacherClassDO findDetailTeacherClass(TeacherClassVO teacherClassVO);

    List<TeacherClassDO> listTeacherClass(TeacherClassVO teacherClassVO);

    List<TeacherClassDO> listTeacherClassPage(TeacherClassVO teacherClassVO);

    Integer countTeacherClass(TeacherClassVO teacherClassVO);

    Integer deleteTeacherClass(TeacherClassVO teacherClassVO);

    Integer deleteTeacherClassById(Integer id);
}
