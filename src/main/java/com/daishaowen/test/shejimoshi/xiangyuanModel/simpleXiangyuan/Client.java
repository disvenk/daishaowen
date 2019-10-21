package com.daishaowen.test.shejimoshi.xiangyuanModel.simpleXiangyuan;

import com.daishaowen.test.shejimoshi.xiangyuanModel.Flyweight;

/**
 * Created by disvenk.dai on 2018-11-16 15:53
 */
public class Client {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fly = factory.factory(new Character('a'));
        fly.operation("First Call");

        fly = factory.factory(new Character('b'));
        fly.operation("Second Call");

        fly = factory.factory(new Character('a'));
        fly.operation("Third Call");
    }
}
