//package com.daishaowen.test.rabbitmqnormal;
//
//import org.springframework.amqp.AmqpException;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessagePostProcessor;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//public class Sender implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {
//
//    private RabbitTemplate rabbitTemplate;
//
//    /** * 构造方法注入rabbitTemplate */
//    @Autowired
//    public Sender(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.rabbitTemplate.setMandatory(true);
//        this.rabbitTemplate.setConfirmCallback(this);
//        // rabbitTemplate如果为单例的话，那回调就是最后设置的内容
//        /**
//         * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调。
//         * ReturnCallback接口用于实现消息发送到RabbitMQ交换器，但无相应队列与交换器绑定时的回调。
//         */
//        this.rabbitTemplate.setReturnCallback(this);
//    }
//
//    public void send() {
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
//        this.rabbitTemplate.convertAndSend("exchange", "topic.message", msg, processor,correlationData);
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
