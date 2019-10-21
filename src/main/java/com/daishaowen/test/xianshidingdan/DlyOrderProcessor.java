//package com.daishaowen.test.expireOrder;
//
//import com.daishaowen.test.mapper.OrderMapper;
//import com.daishaowen.test.model.Order;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///*
//* 用来处理超时订单的类
//* */
//@Slf4j
//@Service
//public class DlyOrderProcessor {
//
//    @Autowired
//    private OrderMapper orderMapper;
//
//    //检查数据库指定id的订单状态，如果为未支付，则变成已过期
//    public void checkDelayOrder(Order order){
//        Order dbOrder = orderMapper.selectByPrimaryKey(order.getId());
//        if(dbOrder.getOrderStatus()==ExpireContants.UN_PAY){
//            log.info("订单["+order.toString()+"]未支付已过期，需要更改为过期订单！");
//            orderMapper.updateExpireOrder(order.getId());
//        }else {
//            log.info("已支付订单["+order.toString()+"]无需修改");
//        }
//    }
//
//}
