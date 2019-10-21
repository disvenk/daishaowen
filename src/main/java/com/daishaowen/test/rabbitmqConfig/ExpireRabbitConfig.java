//package com.daishaowen.test.rabbitmqConfig;
//
//import com.daishaowen.test.rabbitmqModel.MQConstant;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class ExpireRabbitConfig {
//
//    //交换机配置
//    @Bean
//    public DirectExchange defaultExchange() {
//        return new DirectExchange(MQConstant.DEFAULT_EXCHANGE, true, false);
//    }
//
//    //死信转发队列
//    @Bean
//    public Queue repeatTradeQueue() {
//        Queue queue = new Queue(MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME,true,false,false);
//        return queue;
//    }
//
//    //死信转发队列绑定：队列、交换机、路由键
//    @Bean
//    public Binding  drepeatTradeBinding() {
//        return BindingBuilder.bind(repeatTradeQueue()).to(defaultExchange()).with(MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
//    }
//
//    //死信队列
//    @Bean
//    public Queue deadLetterQueue() {
//        Map<String, Object> arguments = new HashMap<>();
//        arguments.put("x-dead-letter-exchange", MQConstant.DEFAULT_EXCHANGE);//交换机
//        arguments.put("x-dead-letter-routing-key", MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);//死信转发队列路由键
//        Queue queue = new Queue(MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME,true,false,false,arguments);//死信队列
//        System.out.println("arguments :" + queue.getArguments());
//        return queue;
//    }
//
//    //死信队列：队列、交换机，路由键
//    @Bean
//    public Binding  deadLetterBinding() {
//        return BindingBuilder.bind(deadLetterQueue()).to(defaultExchange()).with(MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME);
//    }
//
//
////    /*********************    hello 队列  测试    *****************/
////    @Bean
////    public Queue queue() {
////        Queue queue = new Queue(MQConstant.HELLO_QUEUE_NAME,true);
////        return queue;
////    }
////    @Bean
////    public Binding binding() {
////        return BindingBuilder.bind(queue()).to(defaultExchange()).with(MQConstant.HELLO_QUEUE_NAME);
////    }
//
//
//}
