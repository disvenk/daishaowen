package com.daishaowen.test.shejimoshi.factoryMethodModel.jiajianchengchu;

import com.daishaowen.test.shejimoshi.simpleFactoryModel.Operation;

/**
 * Created by disvenk.dai on 2018-11-07 17:49
 * 定义一个大工厂接口
 */
public interface IFactory {
    public Operation generateOper();
}
