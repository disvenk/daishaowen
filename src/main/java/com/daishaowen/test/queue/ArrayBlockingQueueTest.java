package com.daishaowen.test.queue;

import java.util.ArrayDeque;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        //阻塞式队列
        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<Object>(3);
        arrayBlockingQueue.offer("张三");
        arrayBlockingQueue.offer("李四");
        //添加阻塞式，只要添加超时时间就是阻塞式时间
        //因为到第三个的时候队列还没满所以不用等3秒，可以立马入队
        arrayBlockingQueue.offer("代绍文",3, TimeUnit.SECONDS);
        //这里会等待3秒多一点点，因为队列到第四个的时候已经满了，没有移除操作，会等3秒
        //如果有空缺或者3秒之内有队列出队腾出为止，则马上入队，否则在等待3秒之后丢弃队列信息，然后走下面代码
        arrayBlockingQueue.offer("disvenk",3,TimeUnit.SECONDS);
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        //如果队列是空的，这里也会等待3秒，看看3秒之内有没有新的队列入队，如果有则3秒之内就返回
        //如果没有3秒过后，返回null后直接放弃走下段逻辑
        System.out.println(arrayBlockingQueue.poll(3,TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.size());
    }
}
