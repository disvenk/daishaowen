package com.daishaowen.test.bianchengshiwuOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class OrderFaHuo {

    @Autowired
    private TransactionTemplate template;

    //此注解意在告知此方法不被事务全局控制，将来会在方法中使用编程式事务控制部分代码
    @Transactional(propagation = Propagation.NEVER)
    public Boolean updateByTemplate(){
       String orderId = "getOrderId";
       Boolean success = false;
       success =  template.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                //更改订单状态为发货中，状态为4
                //更新时采用状态机的乐观锁，防止重复提交，控制幂等
                //假如更新受影响的行数==1，就返回true
                int row = 1; // update order set order_status=4 where id=? and order_status=3
                return 1== row;
                //在此方法结束后就释放数据连接池了
            }
        });

      //如果上一个操作成功了，才去调用远程接口
      if(success){
          //凡是不在编程事务中的代码执行时，数据库连接池均处于释放状态
         Boolean isFaoHuo = true;//这里调用远程接口
         success =  template.execute(new TransactionCallback<Boolean>() {
              @Override
              public Boolean doInTransaction(TransactionStatus status) {
                  //将结果更新到订单状态中，如果失败就将其还原成没进行发货操作之前的状态3,3为已支付状态
                  if(isFaoHuo){
                      int row = 1;//update order set order_status=5 where id=? and order_status=4
                  }else {
                      int row = 1;//update order set order_status=3 where id=? and order_status=4
                  }
                  return isFaoHuo;
              }
          });
      }
        return success;
    }
    //以上代码在支付订单的时候同样适用
}
