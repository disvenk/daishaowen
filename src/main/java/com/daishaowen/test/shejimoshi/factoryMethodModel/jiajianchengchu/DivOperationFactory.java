package com.daishaowen.test.shejimoshi.factoryMethodModel.jiajianchengchu;

import com.daishaowen.test.shejimoshi.simpleFactoryModel.DivOperation;
import com.daishaowen.test.shejimoshi.simpleFactoryModel.Operation;

/**
 * Created by disvenk.dai on 2018-11-07 17:53
 */
public class DivOperationFactory implements IFactory {
    @Override
    public Operation generateOper() {
        return new DivOperation();
    }
}
