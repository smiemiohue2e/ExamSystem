package com.wx.exam.utils.exception;

/**
 * 参数异常
 */
public class ParameterException extends RuntimeException {
    private Integer code;

    public ParameterException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}