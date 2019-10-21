//package com.daishaowen.test.rabbitmqWorker;
//
//import com.alibaba.fastjson.JSONObject;
//import com.daishaowen.test.rabbitmqModel.DLXMessage;
//import com.daishaowen.test.rabbitmqModel.MQConstant;
//import org.springframework.amqp.AmqpException;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessagePostProcessor;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * Created by disvenk.dai on 2018-09-04 14:10
// * 死信消息发送者
// */
//
//@Component
//public class ExpireSender {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//
//    public void send(String queueName, String msg) {
//        rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE,queueName, msg);
//    }
//
//
//    public void send(String queueName, String msg, long times) {
//        DLXMessage dlxMessage = new DLXMessage(queueName,msg,times);
//        MessagePostProcessor processor = new MessagePostProcessor(){
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                message.getMessageProperties().setExpiration(times + "");
//                return message;
//            }
//        };
//        dlxMessage.setExchange(MQConstant.DEFAULT_EXCHANGE);
//        System.out.println("发送时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//            rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE,MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, JSONObject.toJSONString(dlxMessage), processor);
//    }
//
//}
