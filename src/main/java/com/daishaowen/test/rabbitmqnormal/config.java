//package com.daishaowen.test.rabbitmqnormal;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class config {
//    @Autowired
//    @Qualifier("connectionFactory")
//    ConnectionFactory connectionFactory;
//
//    @Autowired
//    Receiver receiver;
//
//    public static final String EXCHANGE = "exchange";
//    public static final String ROUTINGKEY = "will.message";
//
//    @Bean
//    public DirectExchange defaultExchange() {
//        return new DirectExchange(EXCHANGE);
//    }
//
//    @Bean
//    public Queue queue() {
//        return new Queue("will.message", true,false,false,null); //队列持久
//    }
//
//    @Bean
//    public Binding binding() {
//        return BindingBuilder.bind(queue()).to(defaultExchange()).with(ROUTINGKEY);
//    }
//
//    @Bean
//    public SimpleMessageListenerContainer messageContainer() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory );
//        container.setQueues(queue());
//        container.setExposeListenerChannel(true);
//        container.setMaxConcurrentConsumers(10);
//        container.setConcurrentConsumers(1);
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
//        container.setDefaultRequeueRejected(true);//如果消费失败，重新将数据压入队列,重试次数超过上面的设置之后是否丢弃（false不丢弃时需要写相应代码将该消息加入死信队列）
//        container.setPrefetchCount(10000);//指定一个请求能处理多少个消息，如果有事务的话，必须大于等于transaction数量
//        container.setConcurrency("10");//#消费者最小的数量
//        container.setMaxConcurrentConsumers(10);//#消费者最大数量
//        container.setMessageListener(receiver);//
//        return container;
//    }
//
//}
