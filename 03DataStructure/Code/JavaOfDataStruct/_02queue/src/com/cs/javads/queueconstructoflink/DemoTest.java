package com.cs.javads.queueconstructoflink;

/**
 * @author ：TrialCat
 * @description：测试类
 * @date ：2024/04/01 17:36
 */

public class DemoTest {
    public static void main(String[] args) {
        MyLinkQueue<String> queue = new MyLinkQueue<>();
        queue.offer("A");
        queue.offer("B");
        queue.offer("C");
        queue.offer("D");

        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.peek() = " + queue.peek());
    }
}
