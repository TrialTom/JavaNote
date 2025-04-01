package com.cs.javaee.classtest.compare;

import org.testng.annotations.Test;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/15 17:02
 */

public class Demo01Test {
    @Test
    public void test01(){
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.setName("A");
        t2.setName("B");

        t1.start();
        t2.start();
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 1; i < 11; i++) {
            System.out.println(getName() + "-----" + i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Thread.yield();
        }
    }
}