package com.wx.exam.mapper;


import com.wx.exam.pojo.data.GradeDO;
import com.wx.exam.pojo.vo.GradeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by weidong on 2018/07/18
 */
@Repository
public interface GradeMapper {
    Integer addGrade(GradeVO gradeVO);
    Integer updateGrade(GradeVO gradeVO);
    GradeDO findDetailGrade(GradeVO gradeVO);
    List<GradeDO> listGrade(GradeVO gradeVO);
    List<GradeDO> listGradePage(GradeVO gradeVO);
    Integer countGrade(GradeVO gradeVO);
    Integer deleteGrade(GradeVO gradeVO);
    //自定义分页查询
    List<GradeDO> grades(GradeVO gradeVO);
}
