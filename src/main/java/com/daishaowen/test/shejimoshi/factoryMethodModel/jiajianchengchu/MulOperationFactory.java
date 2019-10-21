package com.daishaowen.test.shejimoshi.factoryMethodModel.jiajianchengchu;

import com.daishaowen.test.shejimoshi.simpleFactoryModel.MulOperation;
import com.daishaowen.test.shejimoshi.simpleFactoryModel.Operation;

/**
 * Created by disvenk.dai on 2018-11-07 17:52
 */
public class MulOperationFactory implements IFactory {
    @Override
    public Operation generateOper() {
        return new MulOperation();
    }
}
