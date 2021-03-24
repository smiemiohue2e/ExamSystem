package com.wx.exam.mapper;


import com.wx.exam.pojo.data.ClassDO;
import com.wx.exam.pojo.vo.ClassListVO;
import com.wx.exam.pojo.vo.ClassVO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClassMapper {

    Integer addClass(ClassVO classVO);

    Integer updateClass(ClassVO classVO);

    ClassDO findDetailClass(ClassVO classVO);

    List<ClassDO> listClass(ClassVO classVO);

    List<ClassDO> listClassPage(ClassVO classVO);

    Integer countClass(ClassVO classVO);

    Integer deleteClass(ClassVO classVO);

    //自定义分页查询
    List<ClassListVO> listClassAssociation(ClassVO classVO);
}
