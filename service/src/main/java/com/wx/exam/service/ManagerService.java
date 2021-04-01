package com.wx.exam.service;


import com.wx.exam.pojo.data.ManagerDO;
import com.wx.exam.pojo.vo.ManagerVO;
import com.wx.exam.utils.Result;

/**
 * <br/>
 * Created by wangxiao on 2018/07/27
 */
public interface ManagerService {

	Result addManager(ManagerVO managerVO) throws Exception;

	Result updateManager(ManagerVO managerVO) throws Exception;
	
	Result findDetailManager(ManagerVO managerVO) throws Exception;
	
	ManagerDO listManager(ManagerVO managerVO);

    Result listManagerPage(ManagerVO managerVO) throws Exception;
	
	Result countManager(ManagerVO managerVO) throws Exception;
	
	Result deleteManager(ManagerVO managerVO) throws Exception;
}