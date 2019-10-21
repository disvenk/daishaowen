package com.daishaowen.test.DuXieFenLi;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DynamicDataSource extends AbstractRoutingDataSource {

    //承载数据源的集合
    public static final Map<Object,Object> dataSourceMap = new ConcurrentHashMap<>();

    public DynamicDataSource() {
        this.setTargetDataSources(dataSourceMap);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DatabaseContextHolder.getDataSourceName();
        if(!dataSourceMap.containsKey(dataSourceName)){
            dataSourceMap.put(dataSourceName, "");
            super.setTargetDataSources(dataSourceMap);//动态初始化数据源,每次数据源的增加都要重新设置
            super.afterPropertiesSet();//凡是动态传入数据源后要调用这个方法重新将数据源在内部刷新
        }

        return dataSourceName;//可以决定使用那个db
    }

    //第一次初始化的时候会调用
    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
    }


//    protected DataSource determineTargetDataSource() {
//        Assert.notNull(this.resolvedDataSources, "DataSource router not initialized");
//        Object lookupKey = determineCurrentLookupKey();调用子类方法
//        DataSource dataSource = this.resolvedDataSources.get(lookupKey);查询数据源
//        if (dataSource == null && (this.lenientFallback || lookupKey == null)) {lenientFallback固定为true
//            dataSource = this.resolvedDefaultDataSource;//切换为默认数据源
//        }
//        if (dataSource == null) {
//            throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
//        }
//        return dataSource;
//    }
}
