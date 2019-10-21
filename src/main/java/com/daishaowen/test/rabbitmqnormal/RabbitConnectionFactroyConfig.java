//package com.daishaowen.test.rabbitmqnormal;
//
//import com.rabbitmq.client.*;
//import com.rabbitmq.client.impl.AMQImpl;
//import com.rabbitmq.client.impl.ChannelN;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ChannelListener;
//import org.springframework.amqp.rabbit.connection.CompositeChannelListener;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeoutException;
//
///**
// * Created by disvenk.dai on 2018-08-21 18:31
// */
//
////普通队列配置
//@Configuration
//public class RabbitConnectionFactroyConfig {
//
//    @Value("${spring.rabbitmq.host}")
//    private String addresses;
//
//    @Value("${spring.rabbitmq.port}")
//    private String port;
//
//    @Value("${spring.rabbitmq.username}")
//    private String username;
//
//    @Value("${spring.rabbitmq.password}")
//    private String password;
//
//    @Value("${spring.rabbitmq.virtual-host}")
//    private String virtualHost;
//
//    @Value("${spring.rabbitmq.publisher-confirms}")
//    private boolean publisherConfirms;
//
//    @Value("${spring.rabbitmq.publisher-returns}")
//    private boolean publisherReturns;
//
//    @Value("${spring.rabbitmq.template.mandatory}")
//    private boolean mandatory;
//
//    @Value("${spring.rabbitmq.listener.simple.acknowledge-mode}")
//    private String acknowledgeMode;
//
//    @Value("${spring.rabbitmq.listener.simple.retry.max-attempts}")
//    private String maxAttempts;
//
//    @Value("${spring.rabbitmq.listener.simple.retry.enabled}")
//    private boolean enabled;
//
//    @Value("${spring.rabbitmq.listener.simple.retry.initial-interval}")
//    private long initialInterval;
//
//    @Value("${spring.rabbitmq.listener.simple.default-requeue-rejected}")
//    private boolean defaultRequeueRejected;
//
//    @Bean
//    @Qualifier("connectionFactory")
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setAddresses(addresses+":"+port);
//        connectionFactory.setUsername(username);
//        connectionFactory.setPassword(password);
//        connectionFactory.setVirtualHost("/");
//        /** 如果要进行消息回调，则这里必须要设置为true */
//        connectionFactory.setPublisherConfirms(publisherConfirms);
//        connectionFactory.setPublisherReturns(publisherReturns);
//
//        // 关键所在，指定线程池
//        ExecutorService service = Executors.newFixedThreadPool(500);
//        connectionFactory.setExecutor(service);
//        //connectionFactory.setChannelCacheSize(100);会出现一些connection closed错误,增加连接可保证传输过程不丢失，但是强制关闭服务端可能会导致消息丢失
//        return connectionFactory;
//    }
//
//}
