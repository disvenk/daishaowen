//package com.daishaowen.test.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.daishaowen.test.rocketmq.TestTransactionListener;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.apache.rocketmq.client.producer.SendCallback;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.client.producer.TransactionMQProducer;
//import org.apache.rocketmq.common.message.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("rocketmq")
//@Slf4j
//public class RocketMqController {
//    @Autowired
//    private DefaultMQProducer defaultMQProducer;
//
////  @Autowired
//  private TransactionMQProducer producer;
//
//    @Autowired
//    private TestTransactionListener testTransactionListener;
//
//    @GetMapping("/test")
//    public void test(String info) throws Exception {
//        Message message = new Message("TopicTest", "Tag1", "12345", "rocketmq测试成功".getBytes());
//        // 这里用到了这个mq的异步处理，类似ajax，可以得到发送到mq的情况，并做相应的处理
//        //不过要注意的是这个是异步的
//        defaultMQProducer.send(message, new SendCallback() {
//            @Override
//            public void onSuccess(SendResult sendResult) {
//                log.info("传输成功");
//                log.info(JSONObject.toJSONString(sendResult));
//            }
//            @Override
//            public void onException(Throwable e) {
//                log.error("传输失败", e);
//            }
//        });
//    }
//
//    @GetMapping("t_test")
//    public void Ttest(String info) throws Exception {
//        Message message = new Message("t_TopicTest", "Tag1", "12345", "rocketmq测试成功".getBytes());
//        producer.setTransactionListener(testTransactionListener);
//        producer.setSendMsgTimeout(10000);
//        producer.sendMessageInTransaction(message, new SendCallback() {
//            @Override
//            public void onSuccess(SendResult sendResult) {
//                log.info("传输成功");
//                log.info(JSONObject.toJSONString(sendResult));
//            }
//            @Override
//            public void onException(Throwable e) {
//                log.error("传输失败", e);
//            }
//        });
//    }
//}
