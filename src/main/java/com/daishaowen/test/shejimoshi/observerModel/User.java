package com.daishaowen.test.shejimoshi.observerModel;

public class User implements Observer {
    private String name;
    private String message;

    public User(String name) {
        this.name = name;
    }
    @Override
    public void update(String arg) {
        this.message = arg;
        read();
    }

    public String read() {
        System.out.println(name + " 收到推送消息： " + message);
        return "ddd";
    }
}
