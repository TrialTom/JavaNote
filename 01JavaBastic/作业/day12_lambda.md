[TOC]

## Lambda表达式的练习

```
定义一个计算（compute）接口,接口中有加减乘除四个抽象方法。
然后使用匿名内部类去实现加减乘除并测试
```

上述功能，昨天已经实现了，今天考虑用lambda改进它，如下：

- 用以下功能接口，实现一个计算器的工具类
- 在工具类中，只需要一个工具方法，就能够实现所有的计算功能

```Java
@FunctionalInterface
interface Compute {
    double compute(double a, double b);
}
```

工具类和工具方法如下：

```java 
//需要提供一个使用功能接口的方法完成需求
class ComputeTool {
    private ComputeTool() {
    }

    public static void calc(Compute com, double a, double b) {
        //...
    }
}
```

你知道如何使用吗？联系一下，今天讲解的String数组的过滤，<font color=red>**体会Lambda表达式对象表示的规则。**</font>

做完以上练习后，感兴趣可以继续做下面的扩展练习。

答：

```java
package com.cs.javaee.homework.lambdatest.computer;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        ComputeTool.calc((a,b)->a+b,1,3);
        ComputeTool.calc((a,b)->a*b,1,3);
        ComputeTool.calc((a,b)->a/b,1,3);
    }
}


/**
 * 需要提供一个使用功能接口的方法完成需求
 */
class ComputeTool {
    private ComputeTool() {
    }

    public static void calc(Compute com, double a, double b) {
        System.out.println(com.compute(a, b));
    }
}
@FunctionalInterface
interface Compute{
    double compute(double a,double b);
}
```



## 扩展:完成以下练习

> 参考课堂上讲的，使用Lambda表达式实现过滤数组元素的方案，实现数组的映射。
>
> 将一个对象数组，映射成另一个对象数组。
>
> 比如：
>
> ​		将一个Student对象数组，映射为装所有学生的成绩的数组。
>
> ​		将一个String对象数组，映射为装所有字符串对象长度的数组。
>
> .....
>
> 所以这个映射的规则就是将一个Object对象转换为另一个Object对象，将这个规则应用到整个数组中，就完成了数组元素的映射。
>
> 当然，这对于大家而言，不算简单，思考思考，不行可以直接参考一下我的实现。

答：

```java
package com.cs.javaee.homework.lambdatest.maptest;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        Student stu01 = new Student(1, 300);
        Student stu02 = new Student(2, 334);
        Student stu03 = new Student(3, 234);

        Student[] students = {stu01, stu02, stu03};
        Object[] arr = mapObjectArrByMap(students, o -> ((Student) o).stuID);
        for (Object ele : arr) {
            System.out.println(ele);
        }
        System.out.println("----------------");

        String[] strings = {"123", "djsal", "12313", "123kjkj4", "123k423"};
        Object[] arr1 = mapObjectArrByMap(strings, o -> ((String) o).length());
        for (Object ele : arr1) {
            System.out.println(ele);
        }


    }

    public static Object[] mapObjectArrByMap(Object[] o, IMap map) {
        if (o.length == 0) {
            return null;
        }
        Object[] arr = new Object[o.length];
        for (int i = 0; i < o.length; i++) {
            arr[i] = map.accept(o[i]);
        }
        return arr;
    }
}

class Student {
    int stuID;
    int grade;

    public Student(int a, int b) {
        this.stuID = a;
        this.grade = b;
    }
}
@FunctionalInterface
interface IMap {
    Object accept(Object o);
}
```



# 预习

> 预习的题目仅为预习提供思路，不用表现在作业中
>
> <font color=red>**请预习：Object类**</font>