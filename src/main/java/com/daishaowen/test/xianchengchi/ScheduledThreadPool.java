package com.daishaowen.test.xianchengchi;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by disvenk.dai on 2018-12-21 15:03
 */
//定长线程池，支持定时及周期性任务执行
public class ScheduledThreadPool {
    //表示延迟3秒执行。
    public static void main1(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, SECONDS);
    }

    //表示延迟1秒后每3秒执行一次,
    //间隔时间一到立马执行下一次，与程序执行时间无关
    // 如果程序执行时间超过间隔时间，程序执行完后立刻执行下一次
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        ScheduledFuture<?> scheduledFuture = scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }, 0, 3, SECONDS);

        //20秒之后取消这个定时任务,然后关闭线程池
        scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                scheduledFuture.cancel(true);
                scheduledThreadPool.shutdown();
            }
        }, 20, SECONDS);
    }

    //与上面不同的是
    //从程序执行完后算起，再过一个间隔时间再执行下一次
    //如果程序执行时间超过间隔时间，等待程序执行完毕后，再过一个间隔时间执行下次
    public static void main2(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
       scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
              }
        }, 0, 3, SECONDS);
    }
}
