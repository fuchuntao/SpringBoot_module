package com.javen.consumer;

import com.javen.config.Constant;
import com.javen.vo.JsonResult;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {Constant.RABBITMQ_QUEUE_NAME,
        Constant.RABBITMQ_QUEUE_ALL_MESSAGE_QUEUE, Constant.RABBITMQ_QUEUE_FANOUT_A})
public class FirstRabbitmqConsumer {

    @RabbitHandler
    public void consumeMessage(String msg){
        System.out.println("消费者1:"+ msg);
    }

    @RabbitHandler
    public void consumeMessage(JsonResult jsonResult){
        System.out.println("JsonResult消费者1:"+ jsonResult);
    }
}