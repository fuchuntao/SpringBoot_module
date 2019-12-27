package com.javen.common;

import lombok.Data;

/**
 * 自定义业务异常
 */
@Data
public class ViewErrorException extends RuntimeException {
    private int code;
    private String message;

    public ViewErrorException(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMsg();
    }
}