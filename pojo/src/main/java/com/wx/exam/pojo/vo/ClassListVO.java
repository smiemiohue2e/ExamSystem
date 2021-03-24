package com.wx.exam.pojo.vo;

import com.wx.exam.pojo.Student;
import com.wx.exam.pojo.data.GradeDO;
import com.wx.exam.pojo.data.MajorDO;

import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: weidong
 * @create: 2018-07-18 16:45
 **/
public class ClassListVO {
    private int id;
    private int cno;
    private GradeDO grade;
    private MajorDO major;
    private Set<Student> students = new HashSet<>();

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public ClassListVO(int id) {
        this.id = id;
    }

    public ClassListVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    public GradeDO getGrade() {
        return grade;
    }

    public void setGrade(GradeDO grade) {
        this.grade = grade;
    }

    public MajorDO getMajor() {
        return major;
    }

    public void setMajor(MajorDO major) {
        this.major = major;
    }
}
