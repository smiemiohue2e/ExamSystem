package com.wx.exam.controller;

import com.wx.exam.utils.Base64Util;

public class T {

    //老师说 一般不会用中文加盐
    //可以了解一下工具类 BaseUtil的编码解码形式

    public static void main(String[] args) throws Exception {
        System.out.println(Base64Util.encode("你好".getBytes()));
        System.out.println(Base64Util.decode("5L2g5aW9"));
    }
}
