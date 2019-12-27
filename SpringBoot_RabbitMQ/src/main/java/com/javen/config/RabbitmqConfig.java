package com.javen.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    public Queue queue() {
        return new Queue(Constant.RABBITMQ_QUEUE_NAME);
    }

    @Bean
    public Queue orderQueue() {
        return new Queue(Constant.RABBITMQ_QUEUE_ORDER_QUEUE);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(Constant.RABBITMQ_QUEUE_USER_QUEUE);
    }

    @Bean
    public Queue messageQueue() {
        return new Queue(Constant.RABBITMQ_QUEUE_MESSAGE_QUEUE);
    }

    @Bean
    public Queue allMessageQueue() {
        return new Queue(Constant.RABBITMQ_QUEUE_ALL_MESSAGE_QUEUE);
    }

    @Bean
    public Queue AMessage() {
        return new Queue(Constant.RABBITMQ_QUEUE_FANOUT_A);
    }

    @Bean
    public Queue BMessage() {
        return new Queue(Constant.RABBITMQ_QUEUE_FANOUT_B);
    }


    @Bean
    DirectExchange exchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingOrderDirectExchange(Queue orderQueue, DirectExchange exchange) {
        return BindingBuilder.bind(orderQueue).to(exchange).with(Constant.DIRECT_ROUTING_KEY_ORDER);
    }

    @Bean
    Binding bindingUserDirectExchange(Queue userQueue, DirectExchange exchange) {
        return BindingBuilder.bind(userQueue).to(exchange).with(Constant.DIRECT_ROUTING_KEY_USER);
    }

    @Bean
    Binding bindingTopicMessageExchange(Queue messageQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(messageQueue).to(topicExchange).with("message.user");
    }

    @Bean
    Binding bindingTopicAllMessageExchange(Queue allMessageQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(allMessageQueue).to(topicExchange).with("message.#");
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}