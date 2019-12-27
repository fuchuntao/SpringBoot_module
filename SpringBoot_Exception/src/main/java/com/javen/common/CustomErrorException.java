package com.javen.common;

import lombok.Data;

/**
 * 自定义业务异常
 */
@Data
public class CustomErrorException extends RuntimeException {
    private int code;
    private String message;

    public CustomErrorException(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMsg();
    }
}