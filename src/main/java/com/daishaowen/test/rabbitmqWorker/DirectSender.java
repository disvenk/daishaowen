//package com.daishaowen.test.rabbitmqWorker;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * Created by disvenk.dai on 2018-09-07 17:00
// */
//
//@Component
//public class DirectSender {
//
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//    public void send(){
//        rabbitTemplate.convertAndSend("exchangeDirect", "disvenk.1", "1号disvenk");
//    }
//}
