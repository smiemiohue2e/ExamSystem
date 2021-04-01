package com.wx.exam.utils;

public interface QuestionType {
    //SINGLE
    //MULTI
    //JUDGE
    public final static int SINGLE = 1;
    public final static int MULTI = 2;
    public final static int JUDGE = 3;

    public final static String SINLE_EN = "SINGLE";
    public final static String MULTI_EN = "MULTI";
    public final static String JUDGE_EN = "JUDGE";

    public final static String SINLE_CN = "单选题";
    public final static String MULTI_CN = "多选题";
    public final static String JUDGE_CN = "判断题";

    public static String getTypeByNum(int type){
        switch (type) {
            case SINGLE:
                return SINLE_CN;
            case MULTI:
                return MULTI_CN;
            case JUDGE:
                return JUDGE_CN;
        }
        return null;
    }


}
