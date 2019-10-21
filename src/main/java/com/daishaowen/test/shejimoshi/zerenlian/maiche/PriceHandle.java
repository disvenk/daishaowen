package com.daishaowen.test.shejimoshi.zerenlian.maiche;

public abstract class PriceHandle {
    //下一级责任链
    protected PriceHandle priceHandle;

    public void setPriceHandle(PriceHandle priceHandle){
        this.priceHandle = priceHandle;
    }

    //处理打折请求
    public abstract void discount(double discount);

    public static PriceHandle createHandle(){
        PriceHandle market = new Maket();
        PriceHandle manager = new Manager();
        PriceHandle boss = new Boss();
        market.setPriceHandle(manager);
        manager.setPriceHandle(boss);
        return market;
    }
}
