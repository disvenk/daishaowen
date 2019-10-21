package com.daishaowen.test.xianshidingdan;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/*
* 存放到队列里的元素，对业务数据进行包装
* */
public class ItemVo<T> implements Delayed {

    private long activeTime;//到期时间，单位毫秒，实际计算为纳秒
    private T data;//业务数据

    //传入的这个参数activeTime指的是过期的时长
    public ItemVo(long activeTime,T data){
        super();
        this.activeTime=activeTime+System.currentTimeMillis();
        this.data=data;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public T getData() {
        return data;
    }

    //这个方法返回到激活日期剩余的时间，时间单位由单位参数决定
    @Override
    public long getDelay(TimeUnit unit) {
        long d = unit.convert(this.activeTime-System.currentTimeMillis(),unit);
        return d;
    }

    //Delayed接口继承了Comparable接口，按剩余时间排序，由大到小
    @Override
    public int compareTo(Delayed o) {
        long d = getDelay(TimeUnit.NANOSECONDS)-o.getDelay(TimeUnit.NANOSECONDS);
        return (d==0)? 0:((d<0)? -1:1);
    }
}
