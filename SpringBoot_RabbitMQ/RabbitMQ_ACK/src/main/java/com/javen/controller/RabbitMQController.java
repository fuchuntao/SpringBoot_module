package com.javen.controller;

import com.javen.config.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @GetMapping("/sendMessage")
    public String sendMessage(String msg){
        try {
            rabbitMQSender.sendMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return "send message failure";
        }
        return "send message success";
    }
}
