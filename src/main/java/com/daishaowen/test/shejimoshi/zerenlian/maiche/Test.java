package com.daishaowen.test.shejimoshi.zerenlian.maiche;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setPriceHandle(PriceHandle.createHandle());
        Random random = new Random();
        for(int i=1;i<10;i++){
            customer.discountReq(random.nextDouble());
        }
    }
}
