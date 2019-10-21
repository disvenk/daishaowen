package com.daishaowen.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolUtil {
    private static Logger logger = LoggerFactory.getLogger(ThreadPoolUtil.class);

    /**
     * 可缓存线程池
     */
    private volatile static ExecutorService cachedThreadPool;

    /**
     * [获取线程池实例,单例模式]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    private static ExecutorService getExecutorServiceInstance() {
        if (cachedThreadPool == null) {
            synchronized (ThreadPoolUtil.class) {
                if (cachedThreadPool == null) {
                    cachedThreadPool = Executors.newCachedThreadPool();
                }
            }
        }
        return cachedThreadPool;
    }

    /**
     * [执行任务]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void execute(Runnable runable) {
        getExecutorServiceInstance().execute(runable);
    }

    /**
     * [执行任务,任务执行超时时间{timeout}秒]
     *
     * @author Chris li[黎超]
     * @version [版本, 2017-04-12]
     */
    public static void execute(List<Callable<String>> callableList, Long timeout) {
        try {
            List<Future<String>> futureList = new ArrayList<Future<String>>();
            if (timeout == null || timeout <= 0) {
                futureList = getExecutorServiceInstance().invokeAll(callableList);
            } else {
                futureList = getExecutorServiceInstance().invokeAll(callableList, timeout, TimeUnit.SECONDS);
            }
            for (Future<String> future : futureList) {
                try {
                    future.get();
                } catch (CancellationException e) {
                    FutureTask<String> futureTask = (FutureTask<String>) future;
                    Field runner = futureTask.getClass().getDeclaredField("runner");
                    runner.setAccessible(true);
                    Thread execThread = (Thread) runner.get(futureTask);
                    logger.error("多线程任务执行超时,将强制杀死当前线程[" + execThread.getName() + "]并释放资源!");
                    execThread.stop();
                    execThread = null;
                    System.gc();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("多线程任务执行异常,错误信息:" + e.getLocalizedMessage());
        }
    }
}
