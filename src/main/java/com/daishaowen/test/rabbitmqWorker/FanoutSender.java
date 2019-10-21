//package com.daishaowen.test.rabbitmqWorker;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
///**
// * Created by disvenk.dai on 2018-09-07 17:10
// */
//
//@Component
//public class FanoutSender {
//
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//    //Fanout广播订阅生产
//    //这里使用了A、B、C三个队列绑定到Fanout交换机上面，发送端的routing_key写任何字符都会被忽略：
//    public void fanout() {
//        String context = "hi, fanout msg ";
//        System.out.println("Sender : " + context);
//        this.rabbitTemplate.convertAndSend("fanoutExchange","", context);
//    }
//}
