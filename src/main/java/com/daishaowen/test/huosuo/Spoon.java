package com.daishaowen.test.huosuo;

//定义一个勺子，ower 表示这个勺子的拥有者
public class Spoon {
    Diner owner;//勺子的拥有者

    //获取拥有者
    public String getOwnerName() {
        return owner.getName();
    }
    //设置拥有者
    public void setOwner(Diner diner) {
        this.owner = diner;
    }

    public Spoon(Diner diner) {
        this.owner = diner;
    }
    //表示正在用餐
    public void use() {
        System.out.println(owner.getName() + " use this spoon and finish eat.");
    }
}
