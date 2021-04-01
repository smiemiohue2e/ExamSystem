package com.wx.exam.utils;

/**
 * 操作答案数据的工具类
 */
public class AnswerUtils {
    static String[] judgeAnswer = {"对", "错"};
    static String[] choiceAnswer = {"A", "B", "C", "D"};

    public static String getAnserFacade(int type, String answer) {
        String facade = "";
        if (type == QuestionType.SINGLE) {
            facade = choiceAnswer[Integer.parseInt(answer)];
        }
        if (type == QuestionType.MULTI) {
            //0,1
            String[] answers = answer.split(",");
            StringBuilder sb = new StringBuilder();
            for (String a : answers) {
                String s = choiceAnswer[Integer.parseInt(a)];
                sb.append(s).append(",");
            }
            facade = sb.deleteCharAt(sb.length() - 1).toString();
        }
        if (type == QuestionType.JUDGE) {
            facade = judgeAnswer[Integer.parseInt(answer)];
        }
        return facade;
    }


}
