package com.daishaowen.test.shengchanzhexiaofeizhe.awaitNotify;

public class Clerk {
    private int product = 0;

    //进货
    public synchronized void get(){
        while(product>=10){
            System.out.println("产品已满");

            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+":"+ ++product);
        this.notifyAll();
    }

    //卖货
    public synchronized void sale(){
        while(product<=0){
            System.out.println("缺货!");

            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+":"+ --product);
        this.notifyAll();
    }
}
