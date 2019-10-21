package com.daishaowen.test.shejimoshi.menmianModel;

/**
 * Created by disvenk.dai on 2018-11-09 14:31
 * 门面类商店,所有都的人都要通过商店来买
 */
public class Store {

    public CoatFactory coatFactory = new CoatFactory();
    public ComputerFactory computerFactory = new ComputerFactory();

    public Coat saleCoat(){
       return coatFactory.saleCoat();
    }

    public Computer saleComputer(){
        return computerFactory.saleComputer();
    }
}
