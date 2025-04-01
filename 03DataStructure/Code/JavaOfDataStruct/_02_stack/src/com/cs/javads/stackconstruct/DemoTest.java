package com.cs.javads.stackconstruct;

/**
 * @author ：TrialCat
 * @description：测试类
 * @date ：2024/04/01 10:55
 */

public class DemoTest {
    public static void main(String[] args) {
        MyStackTool<String> stack = new MyStackTool<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");
        System.out.println("stack.peek() = " + stack.peek());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
    }
}
