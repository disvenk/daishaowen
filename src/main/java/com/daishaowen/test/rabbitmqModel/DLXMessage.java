package com.daishaowen.test.rabbitmqModel;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by disvenk.dai on 2018-09-04 14:11
 * 死信消息载体
 */
@Data
public class DLXMessage implements Serializable{

    private static final long serialVersionUID = 9956432152000L;

    public DLXMessage() {
        super();
    }

    public DLXMessage(String queueName, String content, long times) {
        super();
        this.queueName = queueName;
        this.content = content;
        this.times = times;
    }

    public DLXMessage(String exchange, String queueName, String content, long times) {
        super();
        this.exchange = exchange;
        this.queueName = queueName;
        this.content = content;
        this.times = times;
    }


    private String exchange;

    private String queueName;

    private String content;

    private long times;

}
