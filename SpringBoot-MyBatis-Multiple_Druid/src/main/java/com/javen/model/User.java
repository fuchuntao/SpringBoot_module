package com.javen.model;

import com.javen.enums.SexEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int age;
    private String pwd;
    private SexEnum userSex;
    private String info;
}
