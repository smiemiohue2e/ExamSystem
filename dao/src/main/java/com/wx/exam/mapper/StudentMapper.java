package com.wx.exam.mapper;

import com.wx.exam.pojo.data.StudentDO;
import com.wx.exam.pojo.vo.ClassListVO;
import com.wx.exam.pojo.vo.StudentListVO;
import com.wx.exam.pojo.vo.StudentVO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentMapper {

    Integer addStudent(StudentVO studentVO);

    Integer updateStudent(StudentVO studentVO);

    StudentDO findDetailStudent(StudentVO studentVO);

    List<StudentDO> listStudent(StudentVO studentVO);

    List<StudentDO> listStudentPage(StudentVO studentVO);

    Integer countStudent(StudentVO studentVO);

    Integer deleteStudent(StudentVO studentVO);

    List<StudentListVO> listStudentByPage(StudentVO studentVO);

}
