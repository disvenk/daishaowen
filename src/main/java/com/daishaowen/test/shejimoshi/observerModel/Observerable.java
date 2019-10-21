package com.daishaowen.test.shejimoshi.observerModel;

/*
* 抽象主题
* */
public interface Observerable{
    //注册新的观察者
    public void registerObserver(Observer o);

    //删除观察者
    public void removeObserver(Observer o);

    //通知观察者
    public void notifyObserver();
}
