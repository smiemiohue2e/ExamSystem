package com.wx.exam.security.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;

public class T {

    @Test
    public void t1(){
        Md5Hash md5Hash=new Md5Hash("admin","ζε");
        System.out.println(md5Hash.toString());//83fc45b0eb0d97ccc45c14ead05cc7f6
        Md5Hash md5Hash1=new Md5Hash("admin","ηδΊ");
        System.out.println(md5Hash1.toString());//3f56a3da5002143df504b83118427e9b
        Md5Hash md5Hash2=new Md5Hash("admin","wangwu");
        System.out.println(md5Hash2.toString());//84ea527f5549e2de551f604238f3e5d0
        Md5Hash md5Hash3=new Md5Hash("wx","wx");
        System.out.println(md5Hash3.toString());
    }
}
