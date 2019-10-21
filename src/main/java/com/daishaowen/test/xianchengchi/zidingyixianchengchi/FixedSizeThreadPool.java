package com.daishaowen.test.xianchengchi.zidingyixianchengchi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class FixedSizeThreadPool {
    //仓库
    private BlockingQueue<Runnable> blockingQueue;

    //线程的集合
    private List<Thread> workers;

    //具体的线程
    public static class Worker extends Thread{
        private FixedSizeThreadPool pool;
        public Worker(FixedSizeThreadPool pool){
            this.pool=pool;
        }
        @Override
        public void run() {
            //不断的从队列中拿任务出来
            while(true){
                Runnable task=null;
                try{
                    task=this.pool.blockingQueue.take();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                if(task!=null)
                    task.run();
                System.out.println("线程"+Thread.currentThread().getName()+"执行了");
            }


        }
    }
    //初始化线程池规定仓库大小及线程
    public FixedSizeThreadPool(int poolSize,int taskSize){
        if(poolSize<=0 || taskSize<=0)
            throw new IllegalArgumentException("非法参数");
        this.blockingQueue=new LinkedBlockingDeque<>(taskSize);
        this.workers= Collections.synchronizedList(new ArrayList<>());
        for(int i=0;i<poolSize;i++){
            Worker worker =new Worker(this);
            worker.start();
            workers.add(worker);
        }
    }

    public boolean submit(Runnable task){
        return this.blockingQueue.offer(task);
    }

    //ddd

    public static void main(String[] args) {
        FixedSizeThreadPool pool = new FixedSizeThreadPool(3,3);
        pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("放入一个线程");
            }
        });

    }
}
