package com.daishaowen.test.huosuo;

//定义一个晚餐类
public class Diner {
    public Diner(boolean isHungry, String name) {
        this.isHungry = isHungry;
        this.name = name;
    }

    private boolean isHungry;//是否饿了
    private String name;//定义当前用餐者的名字

    public String getName() {//获取当前用餐者
        return name;
    }

    //可以理解为和某人吃饭
    public void eatWith(Diner spouse, Spoon sharedSpoon) {
        try {
            synchronized (sharedSpoon) {
                while (isHungry) {
                    //当前用餐者和勺子拥有者不是同一个人，则进行等待
                    while (!sharedSpoon.getOwnerName().equals(name)) {
                        sharedSpoon.wait();
                        //System.out.println("sharedSpoon belongs to" + sharedSpoon.getOwnerName())
                    }
                    //spouse此时是饿了，把勺子分给他，并通知他可以用餐
                    if (spouse.isHungry) {
                        System.out.println("I am " + name + ", and my " + spouse.getName() + " is hungry, I should give it to him(her).\n");
                        sharedSpoon.setOwner(spouse);
                        sharedSpoon.notifyAll();
                    } else {
                        //用餐
                        sharedSpoon.use();
                        sharedSpoon.setOwner(spouse);
                        isHungry = false;
                    }
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            System.out.println(name + " is interrupted.");
        }
    }

}
