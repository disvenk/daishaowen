package com.daishaowen.test.shengchanzhexiaofeizhe.awaitNotify;

//生产者
public class Productor implements Runnable{
    private Clerk clerk;

    public Productor(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++){
            try {
                Thread.sleep(200);
            }catch (InterruptedException e){}
            clerk.get();
        }
    }
}
