package com.cs.javaee.homework.waitandnotifiytest;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：TrialCat
 * @description：三线程练习
 * @date ：2024/03/20 16:34
 */

public class DemoTest {
    /**
     * create by: TrialCat
     * description: 三个线程递增打印数字
     * create time: 2024/3/20 16:34
     */
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(new PrintNum(0));
        pool.submit(new PrintNum(1));
        pool.submit(new PrintNum(2));

    }
}

/**
 * create by: TrialCat
 * description: 打印逻辑
 * create time: 2024/3/20 17:06
 *
 */
class PrintNum implements Runnable {

    final Class obj = PrintNum.class;
    static int number = 0;
    static int tag = 0;
    int threadId;

    public PrintNum(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public void run() {
        synchronized (obj) {
            while (number < 75) {
                if (threadId % 3 == tag) {
                    System.out.println(Thread.currentThread().getName() + "---" + number);
                    number++;
                    if (number % 5 == 0) {
                        tag = (tag + 1) % 3;
                        System.out.println(threadId);
                        System.out.println(tag);
                        try {
                            obj.notifyAll();
                            System.out.println(4);
                            obj.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    // 跳转逻辑
                    try {
                        obj.notify();
                        obj.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }
    }
}