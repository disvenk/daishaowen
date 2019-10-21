package com.daishaowen.test.xianchengchi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by disvenk.dai on 2018-12-21 15:06
 */
//单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
//结果依次输出，相当于顺序执行各个任务。
//当线程执行出现异常时，它会重新创建一个线程替换之前的线程继续执行
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
