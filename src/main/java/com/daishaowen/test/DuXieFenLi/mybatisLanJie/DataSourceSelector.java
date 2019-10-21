package com.daishaowen.test.DuXieFenLi.mybatisLanJie;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataSourceSelector extends AbstractRoutingDataSource {

    //承载数据源的集合
    public static final Map<Object,Object> dataSourceMap = new ConcurrentHashMap<>();

    public DataSourceSelector(DataSource dataSource1,DataSource dataSource2) {
        dataSourceMap.put("master",dataSource1);
        dataSourceMap.put("slave",dataSource2);
        this.setTargetDataSources(dataSourceMap);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSourceType();
    }
}
