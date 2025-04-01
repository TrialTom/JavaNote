package com.cs.javaee.classtest._02preducterandcomsumer;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/16 11:01
 */

public class Demo01Test {

    public static final Object obj = new Object();
    public static int pond = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (obj) {
                while (true) {
                    if(pond == 100){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        obj.notify();
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    pond++;
                    System.out.println(Thread.currentThread().getName() + "生产一个包子，" + "还有" + pond + "个包子");
                    obj.notify();
                }
            }
        }, "producter").start();

        new Thread(() -> {
            synchronized (obj) {
                while (true) {
                    if(pond == 0){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        obj.notify();
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    pond--;
                    System.out.println(Thread.currentThread().getName() + "吃个包子," + "还有" + pond + "个包子");
                    obj.notify();
                }
            }
        }, "consumer").start();
    }
}
