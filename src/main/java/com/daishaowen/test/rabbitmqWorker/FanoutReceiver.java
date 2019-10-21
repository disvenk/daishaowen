//package com.daishaowen.test.rabbitmqWorker;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * Created by disvenk.dai on 2018-09-07 17:11
// */
//
//@Component
//public class FanoutReceiver {
//
//    //Fanout广播订阅消费
//    @RabbitHandler
//    @RabbitListener(queues = "fanout.A")
//    public void process6(String msg) {
//        System.out.println("FanoutReceiverA  : " + msg);
//    }
//    @RabbitHandler
//    @RabbitListener(queues = "fanout.B")
//    public void process7(String msg) {
//        System.out.println("FanoutReceiverB  : " + msg);
//    }
//    @RabbitHandler
//    @RabbitListener(queues = "fanout.C")
//    public void process8(String msg) {
//        System.out.println("FanoutReceiverC  : " + msg);
//    }
//}
