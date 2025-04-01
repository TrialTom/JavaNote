package com.cs.javaee.classtest._03exchangeprint;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/18 9:27
 */

public class Demo01Test {
    /**
     * create by: TrialCat
     * description: 要求线程交替打印1A2B3C...
     * create time: 2024/3/18 9:28
     */
    public static void main(String[] args) {
        PrintTest printTest = new PrintTest();
        Thread t1 = new Thread(printTest, "A");
        Thread t2 = new Thread(printTest, "B");

        t1.start();
        t2.start();
    }
}

class PrintTest implements Runnable {
    boolean isSure = true;
    boolean sure = true;
    Lock myBlock = new ReentrantLock();

    @Override
    public void run() {
        synchronized (myBlock) {
            while (sure) {
                for (char i = 48; i < 58; i++) {
                    if (isSure) {
                        try {
                            System.out.print(i);
                            isSure = false;
                            myBlock.notify();
                            myBlock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                for (char i = 65; i < 91; i++) {
                    if (!isSure) {
                        try {
                            System.out.print(i);
                            isSure = true;
                            myBlock.notify();
                            myBlock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                sure = false;
            }
        }

    }
}
