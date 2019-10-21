package com.daishaowen.test.aop.chuantongaop;

//目标类
public class OrderServiceImpl implements IorderService {
    @Override
    public void add() {
        System.out.println("OrderService add....");
    }

    @Override
    public void update() {
        System.out.println("OrderService update...");
    }
}
