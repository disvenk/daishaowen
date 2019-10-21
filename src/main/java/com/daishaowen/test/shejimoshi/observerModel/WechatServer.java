package com.daishaowen.test.shejimoshi.observerModel;

import java.util.ArrayList;
import java.util.List;

//我们也可以使用jdk自带的观察者模式进行开发，继承就可以了
public class WechatServer implements Observerable {
    private List<Observer> list;

    private String message;

    public WechatServer(){
        list = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer o) {
        list.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if(!list.isEmpty()){
            list.remove(o);
        }

    }

    //遍历观察者，群推消息
    @Override
    public void notifyObserver() {
        for(int i=0;i<list.size();i++){
            Observer observer = list.get(i);
            observer.update(message);
        }
    }

    //具体业务推送方法
    public void setInformation(String s){
        this.message=s;
        System.out.println("微信服务更新消息"+s);
        //消息更新通知所有观察者
        notifyObserver();
    }
}
