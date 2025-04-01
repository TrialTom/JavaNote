package com.cs.javaee.classtest._03sumpractice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/18 18:59
 */

public class Demo01Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask integerFutureTask = new FutureTask(new Sum01(100));
        FutureTask integerFutureTask1 = new FutureTask(new Sum01(200));

        Thread t1 = new Thread(integerFutureTask);
        Thread t2 = new Thread(integerFutureTask1);

        t1.start();
        t2.start();

        System.out.println(integerFutureTask.get());
        System.out.println(integerFutureTask1.get());
    }
}

class Sum01 implements Callable<Integer> {

    int num;

    public Sum01(int num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= this.num; i++) {
            sum += i;
        }
        return sum;
    }
}
