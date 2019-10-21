package com.daishaowen.test.shengchanzhexiaofeizhe.awaitNotify;

public class SetStudent implements Runnable {
    private Student s;
    int x =0;

    public SetStudent(Student s){
        this.s = s;
    }

    @Override
    public void run() {
        while (true){
            synchronized (s){
                if(s.isFlag()){
                    try {
                        s.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(x%2==0){
                    s.setName("林青霞");
                    s.setAge(18);
                }else {
                    s.setName("大蚊子");
                    s.setAge(10);
                }
                s.setFlag(true);
                x++;
                s.notifyAll();
            }
        }

    }
}
