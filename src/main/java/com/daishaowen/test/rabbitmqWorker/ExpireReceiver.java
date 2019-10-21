//package com.daishaowen.test.rabbitmqWorker;
//
//
//import com.daishaowen.test.rabbitmqModel.DLXMessage;
//import com.daishaowen.test.rabbitmqModel.MQConstant;
//import com.daishaowen.test.util.JsonUtils;
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.support.AmqpHeaders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * Created by disvenk.dai on 2018-09-04 14:13
// */
///*
//* 死信接收处理消费者
//* */
//@Component
//public class ExpireReceiver {
//
//    @Autowired
//    private ExpireSender expireSender;
//
//    @RabbitListener(queues = MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME)
//    @RabbitHandler
//    public void process(String content,Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
//        System.out.println(content);
//        System.out.println("接收时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        DLXMessage message = JsonUtils.jsonToPojo(content, DLXMessage.class);
//       //expireSender.send(message.getQueueName(), message.getContent());
//        //死信队列使用默认手动应答方式
//        channel.basicAck(tag,false);
//    }
//
//}
