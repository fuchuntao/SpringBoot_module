package com.javen.consumer;

import com.javen.config.Constant;
import com.javen.vo.JsonResult;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {Constant.RABBITMQ_QUEUE_NAME,
        Constant.RABBITMQ_QUEUE_ORDER_QUEUE, Constant.RABBITMQ_QUEUE_USER_QUEUE,
        Constant.RABBITMQ_QUEUE_MESSAGE_QUEUE, Constant.RABBITMQ_QUEUE_FANOUT_B})
public class SecondRabbitConsumer {

    @RabbitHandler
    public void consumeRabbitmqMessage(String msg){
        System.out.println("消费者2:" + msg);
    }

    @RabbitHandler
    public void consumeMessage(JsonResult jsonResult){
        System.out.println("JsonResult消费者2:"+ jsonResult);
    }
}