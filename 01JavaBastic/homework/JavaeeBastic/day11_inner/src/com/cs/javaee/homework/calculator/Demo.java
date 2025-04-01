package com.cs.javaee.homework.calculator;

/**
 * @author Lenovo
 */
public class Demo {
    /**
     *
     *
     定义一个接口Compute，用来完成计算器的功能，比如最简单的加减乘除功能。
     请用以下两种方式测试：
     1，编写实现类进行测试
     2，用局部内部类进行测试
     3，使用匿名内部类进行测试
     * @since
     */

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.sum(1, 3));
        class Calculator01 implements Compute{
            @Override
            public double sum(int a, int b) {
                return a+b;
            }

            @Override
            public double sub(int a, int b) {
                return a-b;
            }

            @Override
            public double mul(int a, int b) {
                return a*b;
            }

            @Override
            public double division(double a, int b) {
                return a/b;
            }
        }
        Calculator01 calculator01 = new Calculator01();
        System.out.println(calculator01.division(5, 2));
        System.out.println(new Compute() {
            @Override
            public double sum(int a, int b) {
                return a + b;
            }

            @Override
            public double sub(int a, int b) {
                return a - b;
            }

            @Override
            public double mul(int a, int b) {
                return a * b;
            }

            @Override
            public double division(double a, int b) {
                return a / b;
            }
        }.mul(5, 6));
    }

}

interface Compute{
    /**
     * 加法
     * @param a 被加数
     * @param b 加数
     * @return
     */
    double sum(int a, int b);

    /**
     * 减法
     * @param  a
     * @return
     */
    double sub(int a, int b);

    /**
     * 乘法
     * @return
     */
    double mul(int a, int b);

    /**
     * 除法
     * @return
     */
    double division(double a, int b);
}

class Calculator implements Compute{
    @Override
    public double sum(int a, int b) {
        return a+b;
    }

    @Override
    public double sub(int a, int b) {
        return a-b;
    }

    @Override
    public double mul(int a, int b) {
        return a*b;
    }

    @Override
    public double division(double a, int b) {
        return a/b;
    }
}
