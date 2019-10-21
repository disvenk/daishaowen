//package com.daishaowen.test.task;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class MyTask {
//
//    //定义每过3秒执行一次任务
//    @Scheduled(fixedRate = 3000)//这个只支持单一表达式
//    //@Scheduled(cron = "")这种表达式可以写每日、周、月，位数的6位，不支持年
//    //表达式的生成地址http://cron.qqe2.com/可以任意操作
//    public void reportDo(){
//        System.out.println(new Date());
//    }
//
//}
