package com.wx.exam.utils;

public class Result<T> {
    public static final int CODE_SUCCESS=200;
    public static final int CODE_FAILURE=2;

    Integer code;
    T data;
    String msg;

    public Result(Integer code) {
        this.code = code;
    }

    public Result(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
