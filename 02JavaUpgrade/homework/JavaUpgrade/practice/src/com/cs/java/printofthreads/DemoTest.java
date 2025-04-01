package com.cs.java.printofthreads;

/**
 * @author ：TrialCat
 * @description：三线程交替打印0~75
 * @date ：2024/03/25 20:54
 */

public class DemoTest {
    public static void main(String[] args) {

    }
}

class PrintOfNum implements Runnable {
    int count = 75;
    int interval = 5;
    final Class<PrintOfNum> aClass = PrintOfNum.class;

    @Override
    public void run() {
        synchronized (aClass) {
            for (int i = 1; i <= count; i++) {
                if (i % 5 == 0) {
                    try {
                        aClass.notify();
                        aClass.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
