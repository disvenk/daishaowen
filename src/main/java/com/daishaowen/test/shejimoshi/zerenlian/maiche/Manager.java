package com.daishaowen.test.shejimoshi.zerenlian.maiche;

//经理类
public class Manager extends PriceHandle {
    @Override
    public void discount(double discount) {
        if(discount<=0.2){
            System.out.println("manager处理了请求"+discount);
        }else {
            priceHandle.discount(discount);
        }
    }
}
