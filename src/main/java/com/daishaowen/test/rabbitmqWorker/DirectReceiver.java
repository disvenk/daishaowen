//package com.daishaowen.test.rabbitmqWorker;
//
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.support.AmqpHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.Headers;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Map;
//
///**
// * Created by disvenk.dai on 2018-09-07 17:04
// */
//
//@Component
//public class DirectReceiver {
//
//    @RabbitListener(queues = "disvenk1")
//    @RabbitHandler
//    public void disvenk1(String msg ,Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
//        System.out.println("Receiver-disvenk1  : " + msg);
//        //消息发生异常，重试期间不会抛出异常，如果重试最后一次不成功就会抛出异常,并且消息不会再回队列了，直接丢弃
//        //开启了重试就会按照重试的来，不开启就会无限重试
//        //int i = 1/0;
//        //channel.basicAck(tag,false);
//    }
//
//    @RabbitListener(queues = "disvenk2")
//    @RabbitHandler
//    public void disvenk2(String msg,Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
//        System.out.println("Receiver-disvenk2  : " + msg);
//        //channel.basicAck(tag,false);
//    }
//}
