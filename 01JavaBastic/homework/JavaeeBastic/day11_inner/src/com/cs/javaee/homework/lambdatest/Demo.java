package com.cs.javaee.homework.lambdatest;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        INoReturnNoParam iNoReturnNoParam = () -> {
            System.out.println("inner");
        };
        iNoReturnNoParam.test();

        INoReturnOneParam iNoReturnOneParam = Demo::innertest;
        iNoReturnOneParam.test(1);
    }

    public static void innertest(int a) {
        System.out.println("简化的Lambda表达式。");
    }
}

//无返回值无参数的功能接口
@FunctionalInterface
interface INoReturnNoParam {
    void test();
}

//无返回值有一个参数的功能接口
@FunctionalInterface
interface INoReturnOneParam {
    void test(int a);
}

//无返回值两个参数的功能接口
@FunctionalInterface
interface INoReturnTwoParam {
    void test(int a, int b);
}

//有返回值无参数的功能接口
@FunctionalInterface
interface IHasReturnNoParam {
    int test();
}

//有返回值一个参数的功能接口
@FunctionalInterface
interface IHasReturnOneParam {
    int method(int a);
}

//有返回值两个参数的功能接口
@FunctionalInterface
interface IHasReturnTwoParam {
    int test(int a, int b);
}
