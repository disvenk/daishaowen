package com.daishaowen.test.yizhuduocong;


import org.junit.Test;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DynamicDataSource1 extends AbstractRoutingDataSource {

    public static final String[] read = new String[]{"read1","read2","read3"};

    public static final String[] write = new String[]{"write1","write2","write3"};

    // 轮询计数,初始为-1,AtomicInteger是线程安全的
    private AtomicInteger counter = new AtomicInteger(3);

    //承载数据源的集合
    public static final Map<Object,Object> dataSourceMap = new ConcurrentHashMap<>();

    public DynamicDataSource1() {
        this.setTargetDataSources(dataSourceMap);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String readOrWirte = DatabaseContextHolder1.getDataSourceName();
        String slaveKey="readAndWrite";
       if("read".equals(readOrWirte)){
            slaveKey = getSlaveKey(read);
       }else if("write".equals(readOrWirte)) {
            slaveKey = getSlaveKey(write);
       }

        return slaveKey;//可以决定使用那个db
    }

    //第一次初始化的时候会调用
    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
    }

    /**
     * 轮询算法实现
     *
     * @return
     */
    public String getSlaveKey(String[] dbs) {
        // 得到的下标为：0、1、2、3……
        Integer index = counter.incrementAndGet() % dbs.length;
        if (counter.get() > 9999) { // 以免超出Integer范围
            counter.set(-1); // 还原
        }
        System.out.println(dbs[index]);
        return dbs[index];
    }

    @Test
    public void test(){
       getSlaveKey(read);
    }
}
