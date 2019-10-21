//package com.daishaowen.test.rabbitmqWorker;
//
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.core.Message;
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
// * Created by disvenk.dai on 2018-09-07 17:12
// */
//
//@Component
//public class CallBackTopicReceiver {
//
//    //topic路由绑定消费
//    @RabbitHandler
//    @RabbitListener(queues = {"topic.message"})
//    public void process4(String msg, Channel channel) throws IOException {
//        System.out.println("topicMessageReceiver1  : " +msg);
//    }
//
//    @RabbitHandler
//    @RabbitListener(queues = "topic.messages")
//    public void process5(Message message, String msg, Channel channel, @Headers Map<String,Object> map, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {//加上channel即可实现手工确认
//        //deliveryTag（唯一标识 ID）：当一个消费者向 RabbitMQ 注册后，会建立起一个 Channel ，RabbitMQ 会用 basic.deliver 方法向消费者推送消息，这个方法携带了一个 delivery tag， 它代表了 RabbitMQ 向该 Channel 投递的这条消息的唯一标识 ID，是一个单调递增的正整数，delivery tag 的范围仅限于 Channel
//        //multiple：为了减少网络流量，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
//        System.out.println("topicMessagesReceiver2  : " +msg);
//        int a =1/0;
//        //消息确认时使用ack，并且requeue参数传false
//        //channel.basicAck(tag, false);//手动确认消息
//        //int i = 1/0;抛出异常消息没有得到确认，也会重新回到队列
//        if (map.get("error")!= null){
//            System.out.println("错误的消息");
//            try {
//                //当消息被拒绝后会不停的重试，如果在消费者这边代码出现了异常，也会不停的重试
//                //这个方法是批量拒绝消息，第二个true会重新入队列，false是直接丢弃
//                channel.basicNack((Long)map.get(AmqpHeaders.DELIVERY_TAG),false,true);      //否认消息
//                return;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
////        try {
////            channel.basicAck((Long)map.get(AmqpHeaders.DELIVERY_TAG),false);            //确认消息
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//    }
//}
