package com.javen.common;


public enum ErrorEnum {
    SERVICE_TIME_OUT(103, "服务调用超时!"),
    UNEXPECTED_EXCEPTION(500, "系统发生异常，请联系管理员!");

    private int code;
    private String msg;

    private ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
