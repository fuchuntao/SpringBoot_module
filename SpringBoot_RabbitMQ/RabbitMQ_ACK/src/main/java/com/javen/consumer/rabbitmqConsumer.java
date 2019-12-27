package com.javen.consumer;

import com.javen.config.Constant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;


@Component
@RabbitListener(queues = {Constant.RABBITMQ_QUEUE_FANOUT_B})
@Slf4j
public class rabbitmqConsumer {
    private final AtomicLong counter = new AtomicLong();

    @RabbitHandler
    public void consumeMessage(String msg, Message message, Channel channel) {

        try {
            System.out.println("消费者：" + msg);

            if (msg.equals("test")) {
                if (counter.incrementAndGet() >= 2){
                    //确定被消费
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                    log.info("receiver2 success");
                    return;
                }
                //拒绝消息并重新返回到队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                log.error("receiver reject");
            } else {
                //确定被消费
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                log.info("receiver success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}