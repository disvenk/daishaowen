//package com.daishaowen.test.rabbitmqConfig;
//
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.retry.policy.SimpleRetryPolicy;
//import org.springframework.retry.support.RetryTemplate;
//
///**
// * Created by disvenk.dai on 2018-08-23 11:09
// */
//@Configuration
//public class TopicRabbitConfig {
//
//    final static String message = "topic.message";
//    final static String messages = "topic.messages";
//
//    @Bean
//    public Queue queueMessage() {
//        return new Queue(TopicRabbitConfig.message);
//    }
//
//    @Bean
//    public Queue queueMessages() {
//        return new Queue(TopicRabbitConfig.messages);
//    }
//
//    //交换器，模糊匹配
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange("topicExchange");
//    }
//
//    //如果使用message发送，则message和messages都会收到
//    //如果使用messages发送，则只有messages收到
//    //之所以会出现上面的情况，是因为topic类型的消息是通配符匹配的，不是全文匹配
//    //路由键是可以随便写的，不一定要和queue一样
//    //路由键与交换器绑定
//    @Bean
//    Binding bindingExchangeMessage() {
//        //三个参数分别是：队列名，交换器，路由键topic.message
//        return BindingBuilder.bind(queueMessages()).to(exchange()).with("topic.message");
//    }
//    @Bean
//    Binding bindingExchangeMessages() {
//        return BindingBuilder.bind(queueMessages()).to(exchange()).with("topic.messages");
//    }
//
//
//    //消费者模式可以这样配置，通springboot配置的效果一样
////    @Bean
////    public SimpleMessageListenerContainer messageContainer(@Qualifier("connectionFactory")ConnectionFactory connectionFactory) {
////        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
////        //container.setQueues(queueMessages(),queueMessage());//需要监听的队列,可以监听多个
////        container.setQueueNames(messages);//监听两个队列会消费两次，但是回调只会有一次confirm
////        container.setExposeListenerChannel(true);
////        container.setMaxConcurrentConsumers(1);
////        container.setConcurrentConsumers(1);
////        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
////        container.setDefaultRequeueRejected(false);
////        container.setMessageListener(new ChannelAwareMessageListener() {
////
////            @Override
////            public void onMessage(Message message, Channel channel) throws Exception {
////                byte[] body = message.getBody();
////                System.out.println("receive msg : " + new String(body));
////                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
////            }
////        });
////        return container;
////    }
//
//}
