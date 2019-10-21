//package com.daishaowen.test.expireOrder;
//
///*
//* 延时订单处理类
//* */
//
//import com.daishaowen.test.model.Order;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.util.concurrent.DelayQueue;
//
//@Service
//@Slf4j
//public class IdelayOrder {
//
//    @Autowired
//    private DlyOrderProcessor dlyOrderProcessor;
//
//    private Thread takeOrder;
//
//    //负责保存限时订单的队列
//    private static DelayQueue<ItemVo<Order>> delayOrder = new DelayQueue<>();
//
//    public void orderDelay(Order order,long expireTime){
//            ItemVo<Order> itemVo = new ItemVo<>(expireTime*1000,order);
//            //推入订单队列
//            delayOrder.put(itemVo);
//            log.info("订单[超时时长："+expireTime+"秒]被推入检查队列，订单详情："+order.toString());
//    }
//
//    //处理到期的订单的线程
//    private class TakeOrder implements Runnable{
//
//        private DlyOrderProcessor dlyOrderProcessor1;
//
//        private TakeOrder(DlyOrderProcessor dlyOrderProcessor){
//            super();
//            this.dlyOrderProcessor1 = dlyOrderProcessor;
//        }
//
//        public void run(){
//            log.info("处理到期订单的线程已启动");
//            while (!Thread.currentThread().isInterrupted()){
//                try{
//                    ItemVo<Order> itemVo = delayOrder.take();
//                    if(itemVo!=null){
//                        dlyOrderProcessor1.checkDelayOrder(itemVo.getData());
//                    }
//                }catch (InterruptedException e){
//                    log.error("The thread is interrupted");
//                }
//            }
//            log.info("处理到期订单线程准备关闭");
//        }
//
//    }
//
//    @PostConstruct
//    public void init(){
//        takeOrder = new Thread(new TakeOrder(dlyOrderProcessor));
//        takeOrder.start();
//    }
//
//    @PreDestroy
//    public void close(){
//        takeOrder.isInterrupted();
//    }
//
//}
