package com.daishaowen.test.devTest;

import com.daishaowen.test.chinaMobile.utils.PropertyUtil;
import com.daishaowen.test.jwt.JwtUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

public class Test1 {

    public static void main(String[] args) throws Exception{
        Target target = new Target();

        Unsafe unsafe =null;
        try {
            //通过反射获取rt.jar下的Unsafe类
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            System.out.println("Get Unsafe instance occur error"+ e);
        }
        long intParam = unsafe.objectFieldOffset(Target.class.getDeclaredField("intParam"));
        System.out.println(intParam);
        int anInt = unsafe.getInt(intParam);
        System.out.println(anInt);
        unsafe.compareAndSwapInt(target, intParam ,3,10);
        System.out.println(target.intParam);
//        AtomicInteger atomicInteger = new AtomicInteger(0);
//        atomicInteger.compareAndSet(0,1);
       // System.out.println(atomicInteger.get());

    }


}

class Target {
    int intParam=3;
    long longParam;
    String strParam;
    String strParam2;
}
