package com.wx.exam.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsernameTypePasswordToken extends UsernamePasswordToken {
    public static final int STUDENT=1;
    public static final int TEACHER=2;
    public static final int MANAGER=3;

    int userType;

    public UsernameTypePasswordToken(String username,
                                     String password,
                                     int userType) {
        super(username, password);
        this.userType = userType;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
