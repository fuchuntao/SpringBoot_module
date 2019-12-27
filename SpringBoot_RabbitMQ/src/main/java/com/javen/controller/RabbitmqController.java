package com.javen.controller;

import com.javen.config.Constant;
import com.javen.vo.JsonResult;
import com.javen.vo.User;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitmqController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/sendMessage")
    public String sendMessageToMq(String msg) {
        try {
            amqpTemplate.convertAndSend(Constant.RABBITMQ_QUEUE_NAME, msg);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "send message failure";
        }
        return "send message success";

    }

    @RequestMapping("/sendSecondMessage")
    public String sendSecondMessage(String msg) {
        try {
            amqpTemplate.convertAndSend(Constant.RABBITMQ_QUEUE_NAME, msg);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "send message failure";
        }
        return "send message success";
    }

    @RequestMapping("/sendUserMessage")
    public String sendRedColorMessage(String msg) {
        try {
            amqpTemplate.convertAndSend("directExchange", "user", msg);

        } catch (AmqpException e) {
            e.printStackTrace();
            return "send message failure";
        }
        return "send message success";
    }

    @RequestMapping("/sendOrderMessage")
    public String sendOrderMessage(String msg) {
        try {
            amqpTemplate.convertAndSend("directExchange", "order", msg);
        } catch (AmqpException e) {
            e.printStackTrace();
            return "send message failure";
        }
        return "send message success";
    }

    @RequestMapping("/sendTopicMessage")
    public String sendTopicMessage(String msg) {
        try {
            amqpTemplate.convertAndSend("topicExchange", "message.user", msg);
        } catch (AmqpException e) {
            e.printStackTrace();
            return "send message failure";
        }
        return "send message success";
    }

    @RequestMapping("/sendTopicMessage2")
    public String sendTopic2Message() {
        try {
            JsonResult<User> jsonResult = new JsonResult<>(User.builder()
                    .age(18)
                    .name("Javen")
                    .desc("RabbitMQ")
                    .build());
            amqpTemplate.convertAndSend("topicExchange", "message.user", jsonResult);
        } catch (AmqpException e) {
            e.printStackTrace();
            return "send message failure";
        }
        return "send message success";
    }

    @RequestMapping("/sendTopicAllMessage")
    public String sendTopicAllMessage(String msg) {
        try {
            amqpTemplate.convertAndSend("topicExchange", "message.other", msg);
        } catch (AmqpException e) {
            e.printStackTrace();
            return "send message failure";
        }
        return "send message success";
    }

    @RequestMapping("/sendFanoutMessage")
    public String sendFanoutMessage(String msg) {
        try {
            amqpTemplate.convertAndSend("fanoutExchange", "",msg);
        } catch (AmqpException e) {
            e.printStackTrace();
            return "send message failure";
        }
        return "send message success";
    }
}