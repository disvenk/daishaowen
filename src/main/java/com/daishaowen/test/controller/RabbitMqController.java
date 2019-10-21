//package com.daishaowen.test.controller;
//
//import com.daishaowen.test.rabbitmqWorker.*;
//
//import com.daishaowen.test.rabbitmqModel.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Created by disvenk.dai on 2018-08-21 18:58
// */
//
//@RestController
//public class RabbitMqController {
////
////    @Autowired
////    private NormalSender normalSender;
//
//    @Autowired
//    CallBackTopicSender callBackSender;
//
////    @Autowired
////    ExpireSender expireSender;
////
////    @Autowired
////    FanoutSender fanoutSender;
////
////    @Autowired
////    DirectSender directSender;
////
////    // normal一对一生产消费
////    @RequestMapping("rabbitmq")
////    public void hello() throws Exception {
////        //使用一个的时候，如果有多个接受者，每调一次，就会均匀切换，你一下我一下
////        normalSender.sendHello();
////    }
////
////    // normal多对多生产消费
////    @RequestMapping("rabbitmq1")
////    public void hello1() throws Exception {
////        //使用多个发送者的时候，如果有多个接受者
////        //如果是偶数个接受者，偶数个发送者，那么就会每次均匀同时打印1,2
////        //如果是偶数个接收者，奇数个发送者，那么每次就会出现2,1,2,1,2，必有一方少
////        //如果是奇数个接收者，发送者是接收者的倍数，就会均匀的1,2,3,1,2,3
////        //如果是奇数个接收者，发送者不是接收者的倍数，就会1,2,3但是总有一个是接收不到的，要么1要么2要么3
////        normalSender.sendHello();
////        normalSender.sendHello();
////        normalSender.sendHello();
////        normalSender.sendHello();
////        normalSender.sendHello();
////    }
////
////    // normal发送实体对象
////    @RequestMapping("rabbitmq2")
////    public void user() throws Exception {
////        normalSender.sendUser(new User("1","disvenk",20));
////    }
//
//    //callback-topic路由绑定，路由键通配符匹配
//    @RequestMapping("rabbitmq3")
//    public void topic() throws Exception {
//        //发送send1时，首先到达交换器，然后交换器在根据绑定的路由键进行分发，由于交换器下绑定了两个路由键topic.message和topic.message
//        //监听topic.message队列的毋庸置疑一定能收到，监听topic.#由于使用通配符匹配了所有以topic.结尾的队列，所以交换机也会发送给它
//        //如果在使用了交换器的模式下，前面的一对多和多对多的现象都是一样的
//        callBackSender.send();
//    }
//
////    //Fanout广播订阅，忽略路由键，广播群发
////    @RequestMapping("rabbitmq4")
////    public void fanout() throws Exception {
////        fanoutSender.fanout();
////    }
////
////    //死信消息
////    @RequestMapping("rabbitmq5")
////    public void deadMsg() throws Exception {
////        expireSender.send("没有队列","测试延迟消息发送",10000);
////    }
////
////    //direct消息，全文(全部相等才行)匹配路由键
////    @RequestMapping("rabbitmq6")
////    public void directMsg() throws Exception {
////        directSender.send();
////    }
//}
