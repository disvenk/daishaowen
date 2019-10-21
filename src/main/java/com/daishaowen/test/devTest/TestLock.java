package com.daishaowen.test.devTest;

import com.sun.corba.se.impl.ior.NewObjectKeyTemplateBase;
import org.apache.cxf.common.commands.ResultBufferedCommand;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();
    public static void main(String[] args) {
        TestLock testLock = new TestLock();
        Thread1 thread1 = testLock.new Thread1(testLock);
        Thread2 thread2 = testLock.new Thread2(testLock);
        thread1.start();
        thread2.start();
    }

    public void a(){
        try {
            lock.lock();
            System.out.println("线程1拿到了锁");
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void b(){
        try {
            lock.lock();
            System.out.println("线程2拿到了锁");
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

 class Thread1 extends  Thread{
        private TestLock testLock;

        public Thread1(TestLock testLock){
            this.testLock=testLock;
        }

    @Override
    public void run() {


    }
}

    class Thread2 extends  Thread{
        private TestLock testLock;

        public Thread2(TestLock testLock){
            this.testLock=testLock;
        }
        @Override
        public void run() {

        }
    }

}
