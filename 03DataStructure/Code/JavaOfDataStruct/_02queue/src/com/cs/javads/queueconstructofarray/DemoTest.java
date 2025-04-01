package com.cs.javads.queueconstructofarray;

/**
 * @author ：TrialCat
 * @description：测试类
 * @date ：2024/04/01 18:05
 */

public class DemoTest {
    public static void main(String[] args) {
        MyArrayQueue<String> queue = new MyArrayQueue<>(4);
        System.out.println("queue.queueLength() = " + queue.queueLength());

        queue.offer("A");
        queue.offer("B");
        queue.offer("C");
        queue.offer("D");
        System.out.println("queue.queueLength() = " + queue.queueLength());

        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.queueLength() = " + queue.queueLength());
        System.out.println("queue.poll() = " + queue.poll());
        queue.offer("D");
        queue.offer("D");
        System.out.println("");

    }
}
