package com.daishaowen.test.shengchanzhexiaofeizhe.awaitNotify;

public class GetStudent implements Runnable {
    private Student s;

    public GetStudent(Student s){
        this.s = s;
    }

    @Override
    public void run() {
        while (true){
            synchronized (s){
                if(!s.isFlag()){
                    try {
                        s.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(s.getName()+":"+s.getAge());
                s.setFlag(false);
                s.notifyAll();
            }

        }

    }
}
