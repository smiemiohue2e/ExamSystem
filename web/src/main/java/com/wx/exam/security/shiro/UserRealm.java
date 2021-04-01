package com.wx.exam.security.shiro;


import com.wx.exam.mapper.ManagerMapper;
import com.wx.exam.mapper.StudentMapper;
import com.wx.exam.mapper.TeacherMapper;
import com.wx.exam.pojo.*;
import com.wx.exam.pojo.data.ManagerDO;
import com.wx.exam.pojo.data.StudentDO;
import com.wx.exam.pojo.data.TeacherDO;
import com.wx.exam.pojo.vo.ManagerVO;
import com.wx.exam.pojo.vo.StudentVO;
import com.wx.exam.pojo.vo.TeacherVO;
import com.wx.exam.service.ManagerService;
import com.wx.exam.service.StudentService;
import com.wx.exam.service.TeacherService;
import com.wx.exam.utils.Result;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    @Autowired
    ManagerMapper managerMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TeacherMapper teacherMapper;
    
    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    ManagerService managerService;
    


    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernameTypePasswordToken token = (UsernameTypePasswordToken) authenticationToken;
        if (token.userType == UsernameTypePasswordToken.MANAGER) {
            /*ManagerExample managerExample=new ManagerExample();
            ManagerExample.Criteria criteria = managerExample.createCriteria();
            criteria.andNameLike(token.getUsername());
            List<Manager> managers = managerMapper.selectByExample(managerExample);
            if(managers!=null&&!managers.isEmpty()){
                Manager manager = managers.get(0);
                SimpleAuthenticationInfo info
                        =new SimpleAuthenticationInfo(manager,manager.getPassword(),getName());
                info.setCredentialsSalt(ByteSource.Util.bytes(manager.getName().getBytes()));
                return info;
            }
            return null;*/
            ManagerVO managerVO=new ManagerVO();
            managerVO.setName(token.getUsername());
            ManagerDO manager = managerService.listManager(managerVO);
            SimpleAuthenticationInfo info
                    =new SimpleAuthenticationInfo(manager,manager.getPassword(),getName());
            info.setCredentialsSalt(ByteSource.Util.bytes(manager.getName().getBytes()));
            return info;
        }

        if (token.userType == UsernameTypePasswordToken.STUDENT) {
           /* StudentExample studentExample=new StudentExample();
            StudentExample.Criteria criteria = studentExample.createCriteria();
            criteria.andNameLike(token.getUsername());
            List<Student> students = studentMapper.selectByExample(studentExample);*/
            StudentVO studentVO=new StudentVO();
            studentVO.setName(token.getUsername());
            StudentDO student = studentService.listStudent(studentVO);
                SimpleAuthenticationInfo info
                        =new SimpleAuthenticationInfo(student,student.getPassword(),getName());
                info.setCredentialsSalt(ByteSource.Util.bytes(student.getName().getBytes()));
            return info;

        }
        if (token.userType == UsernameTypePasswordToken.TEACHER) {
            TeacherVO teacherVO=new TeacherVO();
            teacherVO.setName(token.getUsername());
            TeacherDO teacher = teacherService.findTeacherByName(teacherVO);
                SimpleAuthenticationInfo info
                        =new SimpleAuthenticationInfo(teacher,teacher.getPassword(),getName());
                info.setCredentialsSalt(ByteSource.Util.bytes(teacher.getName().getBytes()));
                return info;
        }
        return null;
    }
}
