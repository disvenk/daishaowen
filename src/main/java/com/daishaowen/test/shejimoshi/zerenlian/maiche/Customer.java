package com.daishaowen.test.shejimoshi.zerenlian.maiche;

//顾客类
public class Customer {
    private PriceHandle priceHandle;

    public void setPriceHandle(PriceHandle priceHandle){
        this.priceHandle = priceHandle;
    }

    public void discountReq(double discount){
        priceHandle.discount(discount);
    }
}
