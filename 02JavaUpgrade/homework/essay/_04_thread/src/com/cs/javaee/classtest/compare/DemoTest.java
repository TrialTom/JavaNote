package com.cs.javaee.classtest.compare;

import org.testng.annotations.Test;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/15 16:24
 */

public class DemoTest {

    @Test
    public void test01(){
        ThreadTest test01 = new ThreadTest();
        ThreadTest test02 = new ThreadTest();
        test01.setPriority(Thread.MAX_PRIORITY);
        System.out.println("test01.getPriority() = " + test01.getPriority());
        test02.setPriority(Thread.MIN_PRIORITY);
        System.out.println("test02.getPriority() = " + test02.getPriority());

        test01.start();
        test02.start();
    }

}

class ThreadTest extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + "====" + i);
        }
    }
}
