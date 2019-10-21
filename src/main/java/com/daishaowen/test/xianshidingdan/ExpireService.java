//package com.daishaowen.test.expireOrder;
//
//import com.daishaowen.test.mapper.OrderMapper;
//import com.daishaowen.test.model.Order;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//import java.util.UUID;
//
//@Slf4j
//@Service
//public class ExpireService {
//
//    @Autowired
//    private OrderMapper orderMapper;
//
//    @Autowired
//    IdelayOrder idelayOrder;
//
//    public void insertOrder(){
//        Random random = new Random();
//        for(int i=1;i<=5;i++){
//            int expireTime = random.nextInt(20)+5;//订单超时时长，单位秒
//            Date date = new Date();
//            long l = date.getTime() + expireTime*1000;
//            Order order = new Order();
//            String orderNo = "DISVENK"+expireTime+"S";
//            order.setId(UUID.randomUUID().toString());
//            order.setOrderNo(orderNo);
//            order.setOrderDuration(expireTime);
//            order.setInsertTime(date);
//            order.setExpireTime(new Date(l));
//            order.setOrderStatus(ExpireContants.UN_PAY);
//            orderMapper.insert(order);
//            log.info("保存订单到DB:"+orderNo);
//            //存入队列
//            idelayOrder.orderDelay(order,expireTime );
//        }
//    }
//
//    @PostConstruct
//    public void initDelayOrder(){
//        log.info("系统启动，扫描表中过期未支付的订单并处理");
//        List<Order> orders = orderMapper.selectUnExpireOrder();
//        orders.forEach(n->{
//            long expireTime = n.getExpireTime().getTime()-(new Date().getTime());
//            idelayOrder.orderDelay(n,expireTime);
//        });
//    }
//}
