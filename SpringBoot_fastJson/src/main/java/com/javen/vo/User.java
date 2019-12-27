package com.javen.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private long id;
    private int age;
    private String name;
    private int sex;
    private String desc;
}
