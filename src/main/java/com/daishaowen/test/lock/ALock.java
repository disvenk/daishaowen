package com.daishaowen.test.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class ALock {
    //线程本地变量，保存当前线程在队列中的索引值
    ThreadLocal<Integer> mySlotIndex = new ThreadLocal<Integer>();
    //队列尾部
    AtomicInteger tail;
    //保存着所有线程的状态
    volatile boolean [] flag;
    //队列长度，也是容纳线程的最大数量
    int size;
    
    public ALock(int size) {
        this.size = size;
        tail = new AtomicInteger(0);
        flag = new boolean[size];
        flag[0] = true;
    }

    //加锁操作
    public void lock() {
        //获取当前队列的尾部索引值，tail加一
        int slot = tail.getAndIncrement() % size;
        //将当前线程本地变量设置为尾部索引值，也就是将当前线程入队列
        mySlotIndex.set(slot);
        //判断自己的状态值，如果状态值为true，成功获取锁，否则自旋
        while (! flag[slot]) {
            ;
        }
    }

    //解锁操作
    public void unlock() {
        //获取当前线程的索引值
        int slot = mySlotIndex.get();
        //设置该索引处的状态为false
        flag[slot] = false;
        //通知后继线程
        flag[(slot + 1) % size] = true;
    }
}
