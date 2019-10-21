package com.daishaowen.test.shejimoshi.menmianModel;

/**
 * Created by disvenk.dai on 2018-11-09 14:29
 * 生产电脑的厂家
 */
public class ComputerFactory {
    //厂家买电脑
    public Computer saleComputer(){
        return new Computer();
    }
}
