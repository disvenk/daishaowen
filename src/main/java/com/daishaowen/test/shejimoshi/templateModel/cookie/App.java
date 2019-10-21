package com.daishaowen.test.shejimoshi.templateModel.cookie;

/**
 * Created by disvenk.dai on 2018-11-06 17:32
 */
public class App {
    public static void main(String[] args) {
        DodishTemplate eggsWithTomato = new EggsWithTomato();
        eggsWithTomato.dodish();

        System.out.println("-----------------------------");

        DodishTemplate bouilli = new Bouilli();
        bouilli.dodish();
    }
}
