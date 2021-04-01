package com.wx.exam.utils;

/**
 * 封装json数据
 * 所有返回结果使用该类
 */
public class Result<T> {
    //状态码
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_FAILURE = 2;

    private Integer code;
    private String msg;
    private T data;

    public Result() {
    }

    //结果码 + 数据
    public Result(Integer code, T data) {
        this.code = code;
        this.data = data;
    }
    //只有结果码
    public Result(Integer code) {
        this.code = code;
    }
    //结果码+提示信息
    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取成功结果对象
     * @param msg 消息
     * @return 成功结果对象
     */
    public static Result getSuccess(String msg) {
        Result result = new Result();
        result.setCode(CODE_SUCCESS);
        if (msg == null) {
            result.setMsg("操作成功");
        } else {
            result.setMsg(msg);
        }
        return result;
    }
    /**
     * 获取失败结果对象
     * @param msg 消息
     * @return 失败结果对象
     */
    public static Result getFailure(String msg) {
        Result result = new Result();
        result.setCode(CODE_FAILURE);
        if (msg == null) {
            result.setMsg("未知错误");
        } else {
            result.setMsg(msg);
        }
        return result;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}