package com.daishaowen.test.rabbitmqModel;

public class MQConstant {

    //交换机 name
    public static final String DEFAULT_EXCHANGE = "KSHOP";

    //DLX QUEUE
    public static final String DEFAULT_DEAD_LETTER_QUEUE_NAME = "kshop.dead.letter.queue";

    //DLX repeat QUEUE 死信转发队列
    public static final String DEFAULT_REPEAT_TRADE_QUEUE_NAME = "kshop.repeat.trade.queue";


//    //Hello 测试消息队列名称
//    public static final String HELLO_QUEUE_NAME = "HELLO";
}
