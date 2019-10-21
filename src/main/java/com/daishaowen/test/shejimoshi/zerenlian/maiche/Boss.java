package com.daishaowen.test.shejimoshi.zerenlian.maiche;

//老板类
public class Boss extends PriceHandle {
    @Override
    public void discount(double discount) {
        if(discount<=0.5){
            System.out.println("boss处理了请求"+discount);
        }else {
            System.out.println("boss拒绝了请求"+discount);
        }
    }
}
