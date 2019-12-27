package com.javen.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisReceiver {
    public void receiveMessage(String message){
        log.info("receiveMessage收到消息："+message);
    }
    
    public void receiveMessage2(String message){
        log.info("receiveMessage2收到消息："+message);
    }

}