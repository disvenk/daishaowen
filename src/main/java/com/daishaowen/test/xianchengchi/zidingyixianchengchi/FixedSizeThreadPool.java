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
            while(this.pool.isWorking || this.pool.blockingQueue.size()>0){
                Runnable task=null;
                try{
                    if(this.pool.isWorking){
                        task=this.pool.blockingQueue.take();
                    }else {
                        task=this.pool.blockingQueue.poll();
                    }
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
        if(isWorking){
            return this.blockingQueue.offer(task);
        }else {
            return false;
        }
    }

    //关闭的方法
    //要去干什么
    //1.停止让客人进来排队
    //2.看仓库，如果还有任务就执行完
    //3.去仓库中拿任务，就不应该阻塞
    //4.一旦任务阻塞了，就必须要中断
    private volatile boolean isWorking=true;
    public void shutDown(){
        this.isWorking=false;
        for(Thread thread:workers){
            if(Thread.State.BLOCKED.equals(thread.getState())){
                thread.interrupt();//中断线程
            }
        }
    }

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
