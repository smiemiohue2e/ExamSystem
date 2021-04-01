package com.wx.exam.utils;

/**
 * @description:
 * @author: weidong
 * @create: 2018-07-22 14:53
 **/
public class ExamUtils {
    public static final int UNINIT = 1;
    public static final int NOTRUN = 2;
    public static final int RUNNING = 3;
    public static final int RUNNED = 4;

    public static final String UNINIT_EN = "UNINIT";
    public static final String NOTRUN_EN = "NOTRUN";
    public static final String RUNNING_EN = "RUNNING";
    public static final String RUNNED_EN = "RUNNED";


    public static final String UNINIT_CN = "未初始化";
    public static final String NOTRUN_CN = "没有运行";
    public static final String RUNNING_CN = "正在运行";
    public static final String RUNNED_CN = "运行结束";

    public static String getStatus(int num) {
        switch (num) {
            case UNINIT:
                return UNINIT_CN;
            case NOTRUN:
                return NOTRUN_CN;
            case RUNNING:
                return RUNNING_CN;
            case RUNNED:
                return RUNNED_CN;
        }
        return null;
    }

    /**
     * 根据String获取int值
     * @param en
     * @return
     */
    public static int getStatusNumByEN(String en) {
        switch (en) {
            case UNINIT_EN:
                return UNINIT;
            case NOTRUN_EN:
                return NOTRUN;
            case RUNNING_EN:
                return RUNNING;
            case RUNNED_EN:
                return RUNNED;
        }
        return NOTRUN;
    }
}
