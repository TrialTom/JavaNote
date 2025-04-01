package com.cs.javaee.classtest._03sumpractice;

import java.util.concurrent.*;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/18 14:55
 */

public class DemoTest {
    /**
     * create by: TrialCat
     * description: 创建两个线程，一个计算100的累加，一个计算1~200的累加
     * create time: 2024/3/18 14:56
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<Integer> t2 = pool.submit(new Sum(100));
        Future<Integer> t1 = pool.submit(new Sum(200));

        System.out.println(t1.get());
        System.out.println(t2.get());
    }
}

class Sum implements Callable<Integer> {

    int bound;

    public Sum(int bound) {
        this.bound = bound;
    }


    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= this.bound; i++) {
            sum += i;
        }
        return sum;
    }
}
