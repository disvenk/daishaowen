package com.daishaowen.test.shejimoshi.factoryMethodModel.jiajianchengchu;

import com.daishaowen.test.shejimoshi.simpleFactoryModel.Operation;
import com.daishaowen.test.shejimoshi.simpleFactoryModel.SubOperation;

/**
 * Created by disvenk.dai on 2018-11-07 17:52
 */
public class SubOperationFactory implements IFactory {
    @Override
    public Operation generateOper() {
        return new SubOperation();
    }
}
