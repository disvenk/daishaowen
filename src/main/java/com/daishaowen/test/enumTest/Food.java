package com.daishaowen.test.enumTest;

/**
 * Created by bruce on 2018-03-28 18:16
 */
public interface Food {

    enum Coffee implements Food {
        BLACK_COFFEE,DECAF_COFFEE,LATTE,CAPPUCCINO
    }
    enum Dessert implements Food {
        FRUIT, CAKE, GELATO
    }
}
