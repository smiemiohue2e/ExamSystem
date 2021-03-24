package com.wx.exam.utils;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.servlet.KaptchaServlet;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理数据的工具类
 */
public class DataUtils {

    /**
     * 分页使用，判断是否是大于0的数字
     */
    private static Pattern pattern = Pattern.compile("^[1-9][0-9]*$");

    /**
     * 获得页码
     *
     * @return 返回>=1的数字
     * 如果给定的字符串不合法，返回1
     */
    public static int getPageCode(String str) {
        if (isNumber(str)) {
            return Integer.parseInt(str);
        }
        return 1;
    }

    /**
     * 是不是数字
     *
     * @param num
     */
    public static boolean isNumber(String num) {
        if (!isValid(num)) {
            return false;
        }
        Matcher matcher = pattern.matcher(num);
        return matcher.matches();
    }

    /**
     * 判断多个字符串是否是整数类型
     *
     * @param nums
     * @return 全部是整数时返回true
     */
    public static boolean isNumber(String... nums) {
        for (String num : nums) {
            if (!isNumber(num)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValid(String str) {
        return str != null && !str.trim().equals("");
    }


    /**
     * 判断字符串是否为空
     *
     * @param checkStr
     * @return
     */
    public static boolean isEmpty(Object checkStr) {
        if (checkStr == null) {
            return true;
        } else {
            return "".equals(checkStr) ? true : false;
        }
    }

    /**
     * 校验验证码
     *
     * @param verify  验证码
     * @param session session
     * @return 校验的结果（json格式）
     */
    public static Result checkVerify(String verify, HttpSession session) {
       // String rand = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //if (verify != null && verify.equals(rand)) {
            return new Result(Result.CODE_SUCCESS, "验证码验证成功");
       // } else {
           // return new Result(Result.CODE_FAILURE, "验证码错误");
       // }
    }
}
