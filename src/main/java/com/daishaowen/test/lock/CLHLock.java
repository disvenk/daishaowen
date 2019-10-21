package com.daishaowen.test.lock;

import java.util.concurrent.atomic.AtomicReference;


//直接前驱负责通知其结束自旋
public class CLHLock {
    AtomicReference<QNode> tail;
    ThreadLocal<QNode> myPred;
    ThreadLocal<QNode> myNode;
    
    public CLHLock() {
        tail = new AtomicReference<QNode>(new QNode());
        myPred = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return null;
            }
        };
        myNode = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return new QNode();
            }
        };
    }
    
    public void lock() {
        QNode qnode = myNode.get();
        qnode.locked = true;
        myPred.set(tail.getAndSet(qnode));
        while (myPred.get().locked) {
            ;
        }
    }
    
    public void unlock() {
        QNode qnode = myNode.get();
        qnode.locked = false;
        myNode.set(myPred.get());
    }
    
    class QNode {
        volatile boolean locked = false;//如果不使用volatile修饰，另一线程看不到会造成死循环
    }
}
