package com.daishaowen.test.shejimoshi.factoryMethodModel.jiajianchengchu;

import com.daishaowen.test.shejimoshi.simpleFactoryModel.AddOperation;
import com.daishaowen.test.shejimoshi.simpleFactoryModel.Operation;

/**
 * Created by disvenk.dai on 2018-11-07 17:50
 * 分厂，每个分厂都有自己的功能
 */
public class AddOperationFactory implements IFactory {
    @Override
    public Operation generateOper() {
        return new AddOperation();
    }
}
