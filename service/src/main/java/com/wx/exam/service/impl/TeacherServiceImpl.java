package com.wx.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.exam.mapper.TeacherClassMapper;
import com.wx.exam.mapper.TeacherMapper;
import com.wx.exam.pojo.data.TeacherDO;
import com.wx.exam.pojo.vo.TeacherClassVO;
import com.wx.exam.pojo.vo.TeacherVO;
import com.wx.exam.service.TeacherClassService;
import com.wx.exam.service.TeacherService;
import com.wx.exam.utils.DataUtils;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;
import com.wx.exam.utils.exception.PageCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <br/>
 * Created by weidong on 2018/07/20
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    private final static Logger LOG = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Resource
    private TeacherMapper teacherMapper;

    @Autowired
    TeacherClassMapper teacherClassMapper;

    @Override
    public Result addTeacher(TeacherVO teacherVO) throws Exception {
        //查询工号是否重复
        TeacherDO detailTeacher = teacherMapper.findDetailTeacher(teacherVO);
        if (detailTeacher != null) {
            return Result.getFailure("工号重复");
        }
        Integer row = teacherMapper.addTeacher(teacherVO);
        if (row > 0) {
            return Result.getSuccess("添加成功");
        }
        return Result.getFailure("添加失败");
    }

    @Override
    public Result updateTeacher(TeacherVO teacherVO) throws Exception {
        Integer count = teacherMapper.updateTeacher(teacherVO);
        if (count > 0) {
            return Result.getSuccess("更新成功");
        }
        return Result.getFailure("更新失败");
    }

    @Override
    public Result findDetailTeacher(TeacherVO teacherVO) throws Exception {
        return null;
    }

    @Override
    public PageBean<TeacherDO> listTeacher(TeacherVO teacherVO) {
        //处理越界
        Integer max = teacherMapper.countTeacher(teacherVO);
        if (teacherVO.getPageCode() > max) {
            //teacherVO.setPageCode(count);
            throw new PageCodeException("页码越界了",max);
        }

        PageHelper.startPage(DataUtils.getPageCode(teacherVO.getPageCode() + ""), teacherVO.getPageSize());
        List<TeacherDO> teacherDOS = teacherMapper.listTeacher(teacherVO);
        PageInfo<TeacherDO> info = new PageInfo<>(teacherDOS);
        PageBean<TeacherDO> pageBean = new PageBean<TeacherDO>(teacherDOS, info.getPageSize()
                , info.getPageNum(), (int) info.getTotal(), info.getSize());
        return pageBean;
    }

    //教师登陆
    public TeacherDO findTeacherByName(TeacherVO teacherVO) {
        List<TeacherDO> teacherDOS = teacherMapper.listTeacher(teacherVO);
        if (teacherDOS != null && !teacherDOS.isEmpty()) {
            return teacherDOS.get(0);
        }
        return null;
    }

    @Override
    public Result addClass(ArrayList<TeacherClassVO> list, Integer tid) {
        //先删除该教师教的所有班级
        int i = teacherClassMapper.deleteTeacherClassById(tid);
        //将教师教的班级依次添加
        if(list!=null&&!list.isEmpty()){
            for (TeacherClassVO teacher : list) {
                teacherClassMapper.addTeacherClass(teacher);
            }
        }

        return Result.getSuccess("操作成功");
    }

    @Override
    public Result listTeacherPage(TeacherVO teacherVO) throws Exception {
        return null;
    }

    @Override
    public Result countTeacher(TeacherVO teacherVO) throws Exception {
        return null;
    }

    @Override
    public Result deleteTeacher(TeacherVO teacherVO) throws Exception {
        Integer row = teacherMapper.deleteTeacher(teacherVO);
        if (row > 0) {
            return Result.getSuccess("删除成功");
        }
        return Result.getFailure("删除失败");
    }


}