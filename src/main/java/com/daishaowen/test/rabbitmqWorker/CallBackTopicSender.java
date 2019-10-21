//package com.daishaowen.test.rabbitmqWorker;
//
//import org.springframework.amqp.AmqpException;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessagePostProcessor;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.UUID;
//
///**
// * Created by disvenk.dai on 2018-08-23 14:39
// */
//@Configuration
//public class CallBackTopicSender implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {
//
//    //@Autowired
//    private RabbitTemplate rabbitTemplate;
//    @Autowired
//    AmqpTemplate amqpTemplate;
//
//    public void send() {
//        this.rabbitTemplate= (RabbitTemplate) amqpTemplate;
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setConfirmCallback(this);
//        rabbitTemplate.setReturnCallback(this);
//        String msg="callbackSender : i am callback sender";
//
//        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString()+"大蚊子");
//        MessagePostProcessor processor = new MessagePostProcessor(){
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                //在此方法中设置header
//                message.getMessageProperties().setHeader("error", null);
//                return message;
//            }
//        };
//
//        this.rabbitTemplate.convertAndSend("topicExchange", "topic.message", msg, processor,correlationData);
//    }
//
//    //发送确认
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        //数据到达交换机即可返回，与消费者是否确认无关
//        System.out.println("confirm--:correlationData:"+correlationData.getId()+",ack:"+ack+",cause:"+cause);
//
//    }
//
//    //接收确认，失败后才会产生这个回调
//    @Override
//    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//        System.out.println("return--message:"+new String(message.getBody())+",replyCode:"+replyCode+",replyText:"+replyText+",exchange:"+exchange+",routingKey:"+routingKey);
//    }
//}
