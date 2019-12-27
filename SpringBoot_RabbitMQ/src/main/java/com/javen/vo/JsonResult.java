package com.javen.vo;

import lombok.Data;

@Data
public class JsonResult<T> {

    private int code;
    private String msg;
    private T data;


    public JsonResult() {
        this.code = 0;
        this.msg = "success";
    }


    public JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public JsonResult(T data) {
        this.data = data;
        this.code = 0;
        this.msg = "success";
    }


    public JsonResult(T data, String msg) {
        this.data = data;
        this.code = 0;
        this.msg = msg;
    }
}