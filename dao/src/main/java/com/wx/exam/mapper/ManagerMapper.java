package com.wx.exam.mapper;


import com.wx.exam.pojo.data.ManagerDO;
import com.wx.exam.pojo.vo.ManagerVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/27
 */
@Repository
public interface ManagerMapper {

    Integer addManager(ManagerVO managerVO);

    Integer updateManager(ManagerVO managerVO);

    ManagerDO findDetailManager(ManagerVO managerVO);

    List<ManagerDO> listManager(ManagerVO managerVO);

    List<ManagerDO> listManagerPage(ManagerVO managerVO);

    Integer countManager(ManagerVO managerVO);

    Integer deleteManager(ManagerVO managerVO);

}
