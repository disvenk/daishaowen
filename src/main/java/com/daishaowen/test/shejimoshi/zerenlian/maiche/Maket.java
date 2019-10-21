package com.daishaowen.test.shejimoshi.zerenlian.maiche;

//销售员类
public class Maket extends PriceHandle {
    @Override
    public void discount(double discount) {
        if(discount<=0.1){
            System.out.println("market处理了请求"+discount);
        }else {
            priceHandle.discount(discount);
        }
    }
}
