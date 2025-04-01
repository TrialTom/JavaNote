[TOC]

# 简答题

> **以下简答题，直接将答案写在题目下面即可。**（都是一些概念，虽然我们学得是技术，但基本的概念还是需要记忆的）

1. 总结成员内部类和静态内部类的成员特点和访问特点。

   - 成员内部类：依赖于外围类而存在，持有外围类的引用，与外围类直接访问，不受访问权限限制，没有静态声明
   - 静态内部类：不依赖外围对象的存在而存在，访问外围类直接创建外围类对象即可，不受访问权限限制

2. 什么是功能接口？

   - 功能接口就是接口中有且仅有一个需要子类实现的抽象方法的接口

   1. 功能接口中只能有一个方法吗？

      不，可能还有默认方法和静态方法，此方法不要子类实现，

   2. 功能接口中只能有一个抽象方法吗？ 

      不，功能接口继承父类object 的抽象方法不需要子类实现

> 成员内部类，静态内部类的区别实际上时非常明显的，对象的创建和使用都有很大差异。请完成下列题目：
>
> 根据注释填写（1），（2），（3）处的代码：
>
> ``` java
> public class Test{
> public static void main(String[] args){
>      //(1)创建并初始化Bean1类对象bean1
>     Demo01.Bean1 bean1 = new Demo01().new Bean1();
>      bean1.i++;
>      //(2)创建并初始化Bean2类对象bean2 
>     Bean2 bean2 = new Bean2();
>      bean2.j++;
>      //(3)创建并初始化Bean3类对象bean3
>     Bean.Bean3 bean3 = new Bean().new Bean3();
>      bean3.k++;
> }
> class Bean1{
>      public int i = 0;
> }
> static class Bean2{
>      public int j = 0;
> }
> }
> class Bean{
> class Bean3{
>      public int k = 0;
> }
> }
> ```

## 用内部类来实现接口

```
定义一个接口Compute，用来完成计算器的功能，比如最简单的加减乘除功能。
请用以下两种方式测试：
	1，编写实现类进行测试
	2，用局部内部类进行测试
	3，使用匿名内部类进行测试
```

答：

```java
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

```



## 成员内部类练习

> 成员内部类对象依赖于它的外围类对象

```
定义一个类Dog
属性：age,name
除此之外,Dog类中需要定义一个成员内部类Body,Body类中有属性color
请私有化该成员内部类,然后将该成员内部类对象作为外围类的成员变量私有化。
然后在Dog类提供一个方法,展示Dog类的全部属性（包括成员内部类中的）。

最后在一个外部类中，创建Dog对象，让外界感知不到成员内部类的存在。
思考一下如何做这个需求？
```

答：

```java
package com.cs.javaee.homework.innerpractice;

/**
 * @author Lenovo
 */
public class Demo {

    public static void main(String[] args) {
        Dog dog = new Dog(12, "xiacongming", "red");
        dog.show();
    }
}

/**
 * 定义一个类Dog
 * 属性：age,name
 * 除此之外,Dog类中需要定义一个成员内部类Body,Body类中有属性color
 * 请私有化该成员内部类,然后将该成员内部类对象作为外围类的成员变量私有化。
 * 然后在Dog类提供一个方法,展示Dog类的全部属性（包括成员内部类中的）。
 * 最后在一个外部类中，创建Dog对象，让外界感知不到成员内部类的存在。
 * 思考一下如何做这个需求？
 */


class Dog {
    private int age;
    private String name;

    Body body = new Body();

    private class Body {
        String color;

        public void setColor(String color) {
            this.color = color;
        }
    }

    public Dog(int age, String name, String color) {
        this.age = age;
        this.name = name;
        body.setColor(color);
    }

    public void show(){
        System.out.println(this.age);
        System.out.println(this.name);
        System.out.println(this.body.color);
    }
}
```



## lambda表达式的练习

> lambda表达式的书写，除了注意格式外，最重要的是关注类型推断

- 提供以下6个功能接口，请用lambda表达式分别创建对象，调用test()方法
- 自由发挥lambda表达式的书写

```java 
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
```

答：

```java
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
```



**今天先学会使用匿名内部类和lambda表达式，具体详细的有哪些用途，后面会碰到**

- 如果实在感兴趣想知道，你也可以自己搜一搜，Java**对象数组的排序**

# 预习

> 预习的题目仅为预习提供思路，不用表现在作业中
>
> <font color=red>**请预习：Object类**</font>