//package com.daishaowen.test.rabbitmqWorker;
//
//import com.daishaowen.test.rabbitmqModel.User;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///**
// * Created by disvenk.dai on 2018-08-21 18:36
// */
//@Component
//public class NormalSender {
//
//    @Autowired
//    //private AmqpTemplate rabbitTemplate;
//    private RabbitTemplate rabbitTemplate;
//
//    //一对一，一对多，多对多生产
//    public void sendHello() {
//        String context = "hello " + new Date();
//        System.out.println("Sender : " + context);
//        this.rabbitTemplate.convertAndSend("hello", context);
//    }
//
//    //对发送对象的支持生产
//    public void sendUser(User user) {
//        System.out.println("Sender : " + user);
//        this.rabbitTemplate.convertAndSend("user", user);
//    }
//
//}
