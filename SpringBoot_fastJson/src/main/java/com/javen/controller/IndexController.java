package com.javen.controller;

import com.javen.vo.JsonResult;
import com.javen.vo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/json")
public class IndexController {
    private final AtomicLong counter = new AtomicLong();
    @RequestMapping("/")
    public JsonResult<User> index(){
        User user = User.builder()
                .id(counter.incrementAndGet())
                .name("欧先生")
                .age(18)
                .desc("SpringBoot 使用fastjson替换默认的jackson")//默认值为""
//                .sex(1) //默认值为0
                .build();
        return  new JsonResult<>(user);
    }
}
