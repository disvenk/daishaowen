//package com.daishaowen.test.rabbitmqConfig;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by disvenk.dai on 2018-09-07 16:59
// */
//@Configuration
//public class DirectRabbitConfig {
//
//    final static String message = "disvenk1";
//    final static String messages = "disvenk2";
//
//    //交换器，全文匹配
//    @Bean
//    DirectExchange exchangeDirect() {
//        return new DirectExchange("exchangeDirect");
//    }
//
//    //坑点：队列的方法名不能和其它的方法名相同,包括交换机和下面绑定的方法名，否则会生成队列失败和发不出消息
//    @Bean
//    public Queue disvenk1() {
//        return new Queue(message);
//    }
//
//    @Bean
//    public Queue disvenk2() {
//        return new Queue(messages);
//    }
//
//    //路由键与交换器绑定
//    @Bean
//    Binding bindingDirect1() {
//        //三个参数分别是：队列名，交换器，路由键topic.message
//        return BindingBuilder.bind(disvenk1()).to(exchangeDirect()).with("disvenk.1");
//    }
//    @Bean
//    Binding bindingDirect2() {
//        return BindingBuilder.bind(disvenk2()).to(exchangeDirect()).with("disvenk.2");
//    }
//}
