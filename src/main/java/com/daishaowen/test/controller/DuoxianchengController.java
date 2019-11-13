package com.daishaowen.test.controller;

import org.apache.commons.lang3.time.StopWatch;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.callback.Callback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by disvenk.dai on 2018-11-28 17:43
 */
@RestController
@RequestMapping("duoxiancheng")
public class DuoxianchengController {

  public static final  ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

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
    public String duoxiancheng1() throws InterruptedException, ExecutionException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        findAll();
        stopWatch.stop();
        System.out.println("耗时"+stopWatch.getTime());
        return "success";
    }

    //这只适合有限的方法，如果是同一个方法多线程下则要使用线程池
    public Map<String,Object> findAll() throws ExecutionException, InterruptedException {
        Map<String,Object> map = new HashMap<>();
        Callable a = ()->{return findA();};
        Callable b = ()->{return findB();};

//        Callable c = new Callable() {
//            @Override
//            public Object call() throws Exception {
//                return findA();
//            }
//        };


        FutureTask<List<String>> fa = new FutureTask<List<String>>(a);
        FutureTask<List<String>> fb = new FutureTask<List<String>>(b);

        Future<?> submit = fixedThreadPool.submit(fa);
        //fixedThreadPool.submit(fb);
       new Thread(fa).start();
        new Thread(fb).start();
        System.out.println(fa.get());
        System.out.println(fb.get());
        System.out.println("先执行打印");
        map.put("a",fa.get() );

        map.put("b",fb.get() );
        return map;
    }

    public List<String> findA() throws InterruptedException {
        List<String> list = new ArrayList<>();
        list.add("a");
        Thread.sleep(1000);
        return list;
    }

    public List<String> findB() throws InterruptedException {
        List<String> list = new ArrayList<>();
        list.add("b");
        Thread.sleep(1500);
        return list;
    }

    //使用spring提供的callable
    @RequestMapping("sync")
    public Callable<String> sync(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Callable<String> callable =()->{
            return findAll().toString();
        };
        stopWatch.stop();
        System.out.println("耗时"+stopWatch.getTime());
        return callable;
    }
}
