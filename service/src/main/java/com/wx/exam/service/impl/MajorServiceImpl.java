package com.wx.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.wx.exam.mapper.MajorMapper;
import com.wx.exam.pojo.data.MajorDO;
import com.wx.exam.pojo.vo.GradeVO;
import com.wx.exam.pojo.vo.MajorVO;
import com.wx.exam.service.MajorService;
import com.wx.exam.utils.DataUtils;
import com.wx.exam.utils.PageBean;
import com.wx.exam.utils.Result;
import com.wx.exam.utils.exception.BusinessException;
import com.wx.exam.utils.exception.PageCodeException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 专业Service
 * <br/>
 * Created by weidong on 2018/07/18
 */
@Service("majorService")
public class MajorServiceImpl implements MajorService {

    @Resource
    private MajorMapper majorMapper;

    @Override
    public Result addMajor(MajorVO majorVO) throws Exception {
        if (findGradeByName(majorVO.getName())) {
            return Result.getFailure("专业已经存在");
        } else {
            majorMapper.addMajor(majorVO);
            return Result.getSuccess("添加成功");
        }
    }

    /**
     * 根据年级名称查询年级是否存在
     * @param name 年级信息
     * @return true 存在， false 不存在
     */
    public boolean findGradeByName(String name){
        MajorVO majorVO = new MajorVO();
        majorVO.setName(name);
        List<MajorDO> majorDOS = majorMapper.listMajor(majorVO);
        if (majorDOS != null && !majorDOS.isEmpty()) {
            return true;
        }
        return false;
    }


    /**
     * 进行编辑是需要对专业进行判断，如果专业名称已经存在
     * 那么就不能进行更新
     * @param majorVO 专业数据
     * @return 编辑结果
     */
    @Override
    public Result updateMajor(MajorVO majorVO) throws Exception {
        //根据id查询专业是否存在
        MajorDO detailMajor = majorMapper.findDetailMajor(majorVO);
        if (detailMajor == null) {
            return Result.getFailure("专业不存在，请重新刷新后再进行编辑");
        }
        //判断专业是否存在，只根据查询名称和删除状态进行查询
        MajorVO majorQuery = new MajorVO();
        majorQuery.setName(majorVO.getName());
        majorQuery.setDelFlag(0);
        List<MajorDO> majors = majorMapper.listMajor(majorQuery);

        if (majors != null && !majors.isEmpty()) {
            return Result.getFailure("专业已经存在，无法编辑");
        }

        Integer count = majorMapper.updateMajor(majorVO);
        if (count > 0) {
            return Result.getSuccess("编辑成功");
        }
        throw new BusinessException("更新专业错误");
    }

    @Override
    public Result findDetailMajor(MajorVO majorVO) throws Exception {
        return null;
    }

    /**
     * 查询专业列表
     *
     * @param query 查询条件
     * @return 分页数据
     */
    @Override
    public PageBean listMajor(MajorVO query) throws Exception {

        //防止页码越界
        Integer count = majorMapper.countMajor(query);
        int max = (count + query.getPageSize() - 1) / query.getPageSize();
        if (query.getPageCode() > max) {
            //query.setPageCode(max);
            throw new PageCodeException("页码越界了",max);
        }

        PageHelper.startPage(DataUtils.getPageCode(query.getPageCode()+""), query.getPageSize());
        List<MajorDO> majors = majorMapper.listMajor(query);

        //插件提供的分页信息
        PageInfo<MajorDO> info = new PageInfo<>(majors);

        //根据插件提供的分页信息，构造我们自己的分页信息
        PageBean<MajorDO> pageBean = new PageBean<MajorDO>(majors, info.getPageSize()
                , info.getPageNum(), (int) info.getTotal(), info.getSize());
        System.out.println(majors);
        return pageBean;
    }

    @Override
    public Result listMajorPage(MajorVO majorVO) throws Exception {
        return null;
    }

    @Override
    public Result countMajor(MajorVO majorVO) throws Exception {
        return null;
    }

    @Override
    public Result deleteMajor(MajorVO majorVO) throws Exception {
        if (!findGradeByName(majorVO.getName())) {
            return Result.getFailure("专业不存在，无法删除");
        }
        majorVO.setDelFlag(1);
        Integer count = majorMapper.deleteMajor(majorVO);
        if (count > 0) {
            return new Result(Result.CODE_SUCCESS, "删除专业成功");
        }
        throw new BusinessException("删除专业错误");
    }

    @Override
    public List<MajorDO> listAll() {
        return majorMapper.listMajor(new MajorVO());
    }


    /**
     * 根据年级id查询专业信息
     */
    @Override
    public Result listMajorByGrade(GradeVO gradeVO) {
        List<MajorDO> list = majorMapper.listMajorByGrade(gradeVO);
        return new Result(Result.CODE_SUCCESS,list);
    }


}