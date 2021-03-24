package com.wx.exam.service;

import com.wx.exam.mapper.ManagerMapper;
import com.wx.exam.pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    @Autowired
    ManagerMapper managerMapper;

    public int update(Manager manager) {
      return  managerMapper.updateByPrimaryKey(manager);
    }
}
