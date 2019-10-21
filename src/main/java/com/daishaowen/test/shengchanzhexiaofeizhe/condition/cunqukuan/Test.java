package com.daishaowen.test.shengchanzhexiaofeizhe.condition.cunqukuan;

import com.daishaowen.test.shengchanzhexiaofeizhe.condition.cunqukuan.DrawThread;
import com.daishaowen.test.shengchanzhexiaofeizhe.condition.cunqukuan.MyCount;
import com.daishaowen.test.shengchanzhexiaofeizhe.condition.cunqukuan.SaveThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
// 创建并发访问的账户
        MyCount myCount = new MyCount("95599200901215522", 10000);
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(3); //假设改成2会怎么样??
        Thread t1 = new SaveThread("张三", myCount, 1000);
        Thread t2 = new SaveThread("李四", myCount, 1000);
        Thread t3 = new DrawThread("王五", myCount, 12600);
        Thread t4 = new SaveThread("老张", myCount, 600);
        Thread t5 = new DrawThread("老牛", myCount, 1300);
        Thread t6 = new DrawThread("胖子", myCount, 800);
        Thread t7 = new SaveThread("测试", myCount, 2100);
        // 执行各个线程
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        pool.execute(t7);
        // 关闭线程池
        pool.shutdown();
    }

}
