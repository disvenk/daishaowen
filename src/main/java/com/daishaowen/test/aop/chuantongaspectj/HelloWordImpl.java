package com.daishaowen.test.aop.chuantongaspectj;

public class HelloWordImpl implements HelloWord {
    @Override
    public void printHelloWord() {
        System.out.println("printHelloWord");
    }

    @Override
    public void doPrint() {
        System.out.println("doPrint");
    }
}
