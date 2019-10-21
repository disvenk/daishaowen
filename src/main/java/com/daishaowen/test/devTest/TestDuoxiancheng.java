package com.daishaowen.test.devTest;

import org.apache.commons.lang3.time.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestDuoxiancheng {

    public static Lock lock = new ReentrantLock();
    public static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    public static Condition condition = lock.newCondition();

    @RequestMapping("duoxiancheng")
    public String duoxiancheng() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        findA();
        findB();
        stopWatch.stop();
        System.out.println("耗时"+stopWatch.getTime());
        return "success";
    }

    @RequestMapping("duoxiancheng1")
    public static void  main(String[] args) throws InterruptedException, ExecutionException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        findAll();
        stopWatch.stop();
        System.out.println("耗时"+stopWatch.getTime());
    }

    //这只适合有限的方法，如果是同一个方法多线程下则要使用线程池
    public static Map<String,Object> findAll() throws ExecutionException, InterruptedException {
        Map<String,Object> map = new HashMap<>();
        Callable a = ()->{return findA();};
        Callable b = ()->{return findB();};

        FutureTask<List<String>> fa = new FutureTask<List<String>>(a);
        FutureTask<List<String>> fb = new FutureTask<List<String>>(b);
//        fixedThreadPool.submit(fa);
//        fixedThreadPool.submit(fb);
        new Thread(fa).start();
        new Thread(fb).start();
        return map;
    }

    public static String findA(){
        try {
            lock.lock();
            System.out.println("线程1拿到了锁");
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("线程1释放了锁");
            lock.unlock();
        }
        return null;
    }

    public static String findB(){
        try {
            lock.lock();
            System.out.println("线程2拿到了锁");
            condition.await();
            //Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("线程2释放了锁");
            lock.unlock();
        }
        return null;

    }
}
