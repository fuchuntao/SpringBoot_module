Exchange（交换机）分四种类型：

- Direct：只有绑定时的 routing_key 与发送消息的 routing_key 完全匹配时，消息才会被交换器投送到绑定的队列中去；
- Topic：按规则转发消息；
- Headers：设置 Header Attribute 参数类型的交换机；
- Fanout：转发消息到所有绑定队列。


Direct 是 RabbitMQ Broker 默认 Exchange，当使用这个类型的 Exchange 时，可以不指定 RoutingKey 的名字。在此类型下创建的 Queue 有一个默认的 RoutingKey，这个 RoutingKey 一般与 Queue 名称相同。


Topic Exchange 是根据 RoutingKey 和 Exchange 的类型将 message 发送到一个或者多个 Queue 中，我们经常拿它来实现各种 Publish/Subscribe，即发布订阅。
[RabbitMQ入门教程](http://www.rabbitmq.com/getstarted.html)

[RabbitMQ：Spring 集成 RabbitMQ 与其概念，消息持久化，ACK机制等](https://github.com/401Studio/WeekLearn/issues/2)

[SpringBoot的RabbitMQ消息队列](https://blog.csdn.net/lxhjh/article/details/69054342)