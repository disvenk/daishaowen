package com.daishaowen.test.shejimoshi.templateModel.hookmethod;

/**
 * Created by disvenk.dai on 2018-11-07 11:16
 */
public abstract class HummerModel {
    protected abstract void start(); //发动
    protected abstract void stop();  //停止
    protected abstract void alarm(); //鸣笛
    protected abstract void engineBoom(); //轰鸣
    final protected void run() { //车总归要跑
        this.start();
        this.engineBoom();
        if(this.isAlarm()) {//想让它叫就叫，不想就不叫
            this.alarm();
        }
        this.stop();
    }
    protected boolean isAlarm() { //我们加了一个判断方法，默认返回true
        return true;
    }
}
