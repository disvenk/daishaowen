package com.daishaowen.test.huosuo;

public class LiveLockTest {

    public static void main(String[] args) {
        final Diner husband = new Diner(true, "husband");//创建一个丈夫用餐类
        final Diner wife = new Diner(true, "wife");//创建一个妻子用餐类
        final Spoon sharedSpoon = new Spoon(wife);//创建一个勺子，初始状态并由妻子持有

        //创建一个 线程，由丈夫进行用餐
        Thread h = new Thread() {
            @Override
            public void run() {
                //表示和妻子用餐，这个过程判断妻子是否饿了，如果是，则会把勺子分给妻子，并通知她
                husband.eatWith(wife, sharedSpoon);
            }
        };
        h.start();

        //创建一个 线程，由妻子进行用餐
        Thread w = new Thread() {
            @Override
            public void run() {
                //表示和丈夫用餐，这个过程判断丈夫是否饿了，如果是，则会把勺子分给丈夫，并通知他
                wife.eatWith(husband, sharedSpoon);
            }
        };
        w.start();

        try

        {
            Thread.sleep(10000);
        } catch (
                InterruptedException e)

        {
            e.printStackTrace();
        }
        h.interrupt();
        w.interrupt();

        try

        {
            h.join();//join()方法阻塞调用此方法的线程(calling thread)，直到线程t完成，此线程再继续；通常用于在main()主线程内，等待其它线程完成再结束main()主线程。
            w.join();
        } catch (
                InterruptedException e)

        {
            e.printStackTrace();
        }
    }
}



