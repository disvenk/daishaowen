package com.daishaowen.test.queue;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueTest {
    public static void main(String[] args) {
        //非阻塞式用法，无界队列
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        concurrentLinkedQueue.offer("张三");//add和offer在这个队列中没有区别
        concurrentLinkedQueue.offer("李四");
        //peek方法获取单个队列信息,poll获取队列之后会删除队列信息
        System.out.println(concurrentLinkedQueue.peek());//peek不会删除，poll会删除
        System.out.println(concurrentLinkedQueue.size());
    }

}
