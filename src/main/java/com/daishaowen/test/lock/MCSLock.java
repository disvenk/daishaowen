package com.daishaowen.test.lock;

import java.util.concurrent.atomic.AtomicReference;


//申请线程只在本地变量上自旋，它不断轮询前驱的状态，如果发现前驱释放了锁就结束自旋。
public class MCSLock {
    AtomicReference<QNode> tail;
    ThreadLocal<QNode> myNode;
    
    public MCSLock() {
        tail = new AtomicReference<QNode>(null);
        myNode = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return new QNode();
            }
        };
    }
    
    public void lock() {
        QNode qnode = myNode.get();
        QNode pred = tail.getAndSet(qnode);
        if (pred != null) {
            qnode.locked = true;
            pred.next = qnode;
            while (myNode.get().locked) {
                ;
            }
        }
    }
    
    public void unlock() {
        QNode qnode = myNode.get();
        if (qnode.next == null) {
            if (tail.compareAndSet(qnode, null)) {
                return;
            }
            while (qnode.next == null) {
                ;
            }
        }
        qnode.next.locked = false;
        qnode.next = null;
    }
    
    class QNode {
        boolean locked = false; // 默认锁时空闲的
        QNode next = null;
    }
}
