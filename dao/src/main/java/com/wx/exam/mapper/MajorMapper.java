package com.wx.exam.mapper;


import com.wx.exam.pojo.data.MajorDO;
import com.wx.exam.pojo.vo.GradeVO;
import com.wx.exam.pojo.vo.MajorVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by weidong on 2018/07/18
 */
@Repository
public interface MajorMapper {

    Integer addMajor(MajorVO majorVO);

    Integer updateMajor(MajorVO majorVO);

    MajorDO findDetailMajor(MajorVO majorVO);

    List<MajorDO> listMajor(MajorVO majorVO);

    List<MajorDO> listMajorPage(MajorVO majorVO);

    Integer countMajor(MajorVO majorVO);

    Integer deleteMajor(MajorVO majorVO);

    List<MajorDO> listMajorByGrade(GradeVO gradeVO);
}
