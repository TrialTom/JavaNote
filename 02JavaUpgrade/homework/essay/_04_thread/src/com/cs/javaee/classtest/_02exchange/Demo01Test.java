package com.cs.javaee.classtest._02exchange;


/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/16 10:27
 */


public class Demo01Test {
    /**
     * create by: TrialCat
     * description: TODO
     * create time: 2024/3/16 10:29
     *
     * @param
     */
    public static void main(String[] args) {
        MyBlock myBlock = new MyBlock();
        Thread t1 = new Thread(myBlock);
        Thread t2 = new Thread(myBlock);

        t1.setName("A");
        t2.setName("B");

        t1.start();
        t2.start();
    }
}

class MyBlock implements Runnable {

    int num = 100;
   public static final Object obj = new Object();

    @Override
    public void run() {
        synchronized (obj) {
            while (num > 0){
                if (num % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + "-----" + num--);
                    obj.notify();
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                obj.notifyAll();
                System.out.println(Thread.currentThread().getName() + "-----" + num--);
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            obj.notify();
        }
    }
}