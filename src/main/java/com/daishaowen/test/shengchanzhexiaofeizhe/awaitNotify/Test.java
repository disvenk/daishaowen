package com.daishaowen.test.shengchanzhexiaofeizhe.awaitNotify;

public class Test {
    public static void main(String[] args) {
        //这里只是用flag来表示消费了，并不是说这个对象被消费了就是个null
        Student student = new Student();
        SetStudent setStudent = new SetStudent(student);
        GetStudent getStudent = new GetStudent(student);
        new Thread(setStudent).start();
        new Thread(getStudent).start();
    }
}
