package com.wx.exam.shiro;


import com.wx.exam.mapper.ManagerMapper;
import com.wx.exam.mapper.StudentMapper;
import com.wx.exam.mapper.TeacherMapper;
import com.wx.exam.pojo.*;
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
            ManagerExample managerExample=new ManagerExample();
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
            return null;
        }

        if (token.userType == UsernameTypePasswordToken.STUDENT) {
            StudentExample studentExample=new StudentExample();
            StudentExample.Criteria criteria = studentExample.createCriteria();
            criteria.andNameLike(token.getUsername());
            List<Student> students = studentMapper.selectByExample(studentExample);
            if(students!=null&&!students.isEmpty()){
                Student student = students.get(0);
                SimpleAuthenticationInfo info
                        =new SimpleAuthenticationInfo(student,student.getPassword(),getName());
                info.setCredentialsSalt(ByteSource.Util.bytes(student.getName().getBytes()));
            return info;
            }
            return null;
        }
        if (token.userType == UsernameTypePasswordToken.TEACHER) {
            TeacherExample teacherExample=new TeacherExample();
            TeacherExample.Criteria criteria = teacherExample.createCriteria();
            criteria.andNameLike(token.getUsername());
            List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
            if(teachers!=null&&!teachers.isEmpty()){
                Teacher teacher = teachers.get(0);
                SimpleAuthenticationInfo info
                        =new SimpleAuthenticationInfo(teacher,teacher.getPassword(),getName());
                info.setCredentialsSalt(ByteSource.Util.bytes(teacher.getName().getBytes()));
                return info;
            }
            return null;
        }
        return null;
    }
}
