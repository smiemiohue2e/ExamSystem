package com.wx.exam.service.impl;

import com.wx.exam.mapper.ManagerMapper;
import com.wx.exam.pojo.data.ManagerDO;
import com.wx.exam.pojo.data.StudentDO;
import com.wx.exam.pojo.vo.ManagerVO;
import com.wx.exam.service.ManagerService;

import com.wx.exam.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/** 
 * <br/>
 * Created by wangxiao on 2018/07/27
 */
@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

	private final static Logger LOG = LoggerFactory.getLogger(ManagerServiceImpl.class);

	@Resource
	private ManagerMapper managerMapper;

	@Override
	public Result addManager(ManagerVO managerVO) throws Exception {
		return null;
	}

	@Override
	public Result updateManager(ManagerVO managerVO) throws Exception {
		Integer row = managerMapper.updateManager(managerVO);
		if(row>0){
			return Result.getSuccess("修改成功");
		}
		return Result.getFailure("修改失败");
	}
	
	@Override
	public Result findDetailManager(ManagerVO managerVO) throws Exception{
		return null;
	}

	@Override
	public ManagerDO listManager(ManagerVO managerVO){
		List<ManagerDO> managerDOS = managerMapper.listManager(managerVO);
		if(managerDOS!=null&&!managerDOS.isEmpty()){
			return managerDOS.get(0);
		}
		return null;
	}
	
	@Override
	public Result listManagerPage(ManagerVO managerVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countManager(ManagerVO managerVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteManager(ManagerVO managerVO) throws Exception{
		return null;
	}

}