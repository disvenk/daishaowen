package com.daishaowen.test.shejimoshi.templateModel.hookmethod;

/**
 * Created by disvenk.dai on 2018-11-07 11:17
 */
public class HummerH1 extends HummerModel {
    private boolean alarmFlag = true; //判断标记
    @Override
    public void start() {
        System.out.println("H1发动……");
    }

    @Override
    public void stop() {
        System.out.println("H1停止……");
    }

    @Override
    public void alarm() {
        System.out.println("H1鸣笛……");
    }

    @Override
    public void engineBoom() {
        System.out.println("H1轰鸣……");
    }

    @Override
    protected boolean isAlarm() { //覆写isAlarm方法，返回判断标记
        return this.alarmFlag;
    }

    public void setAlarm(boolean isAlarm) { //设置判断标记
        this.alarmFlag = isAlarm;
    }

    public void doSomeThing(){
        run();
    }
}
