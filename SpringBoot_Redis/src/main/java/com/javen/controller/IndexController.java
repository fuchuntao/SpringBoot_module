package com.javen.controller;

import com.javen.redis.RedisSubListenerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //向通道发送消息的方法
    @GetMapping("/sendChannelMessage")
    public String sendChannelMessage() {
        try{
            stringRedisTemplate.convertAndSend(RedisSubListenerConfig.channel, "IJPay 订单信息");
            stringRedisTemplate.convertAndSend(RedisSubListenerConfig.channel2, "IJPay已下单");
            return "success";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
