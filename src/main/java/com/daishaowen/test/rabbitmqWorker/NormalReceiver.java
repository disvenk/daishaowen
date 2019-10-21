//package com.daishaowen.test.rabbitmqWorker;
//
//import com.daishaowen.test.rabbitmqModel.User;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * Created by disvenk.dai on 2018-08-21 18:37
// */
//@Component
//public class NormalReceiver {
//
//    //无交换机一对一，一对多，多对多消费
//    @RabbitListener(queues = "hello")
//    @RabbitHandler
//    public void process1(String hello) {
//        System.out.println("Receiver1  : " + hello);
//    }
//    @RabbitListener(queues = "hello")
//    @RabbitHandler
//    public void process2(String hello) {
//        System.out.println("Receiver2  : " + hello);
//    }
//    @RabbitListener(queues = "hello")
//    @RabbitHandler
//    public void process3(String hello) {
//        System.out.println("Receiver3  : " + hello);
//    }
//
//    //无交换机对象发送消费
//    @RabbitListener(queues = "user")
//    @RabbitHandler
//    public void process4(User user) {
//        System.out.println("Receiver  : " + user.getName());
//    }
//
//
//}
