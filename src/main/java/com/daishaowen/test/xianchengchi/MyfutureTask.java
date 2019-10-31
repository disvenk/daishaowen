package com.daishaowen.test.xianchengchi;

import java.util.concurrent.*;

public class MyfutureTask<v> implements Runnable, Future<v> {

    private Callable<v> callable;

    v result=null;

    public MyfutureTask(Callable<v> callable){
        this.callable=callable;
    }

    @Override
    public void run() {
        try {
            result=callable.call();
            synchronized (this) {
                this.notifyAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public v get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public v get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if(result!=null){
            return result;
        }
        synchronized (this){
            this.wait();
        }
        return result;
    }
}
