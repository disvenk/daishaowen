package com.daishaowen.test.shengchanzhexiaofeizhe.condition.shuzu;

import java.util.Random;

public class Test {
    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        BoundedQueue queue = new BoundedQueue(5);

        for(int i=1;i<=20;i++){
            Thread thread = new Thread(new Producter(queue),String.valueOf(i));
            thread.start();
        }

        for(int i=1;i<=20;i++){
            Thread thread = new Thread(new Consumer(queue),String.valueOf(i));
            thread.start();
        }

    }

    static class Producter implements Runnable{
        private BoundedQueue queue;
        public Producter(BoundedQueue queue){
            this.queue = queue;
        }
        public void produce() throws InterruptedException{
            queue.add(new Integer(random.nextInt(100)));
        }
        @Override
        public void run() {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable{
        private BoundedQueue queue;
        public Consumer(BoundedQueue queue){
            this.queue = queue;
        }
        public Integer remove() throws InterruptedException{
            return queue.remove();
        }
        @Override
        public void run() {
            try {
                remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
