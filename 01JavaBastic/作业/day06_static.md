[TOC]

# 简答题

> 以下简答题，直接将答案写在题目下面即可。（都是一些概念，虽然我们学得是技术，但基本的概念还是需要记忆的）

1. 包装类有哪些？简要描述一下。

   答：有很多

2. 为什么数值包装类型对象，比较数值大小不能使用"=="？推荐使用什么？

   答：包装类型对象是引用数据类型，用==号比较的不是数值而是引用对象的地址，且-128~127的数值共用一个缓存数组；数值包装类对象推荐使用equal方法

3. static修饰的成员属于谁？如何访问？非static修饰的成员属于谁？如何访问？

   答：static修饰的成员属于类，类名.变量名来访问，非static修饰的成员属于对象，用对象名.变量名来访问

4. 静态成员变量在类全局唯一吗？成员变量在类全局唯一吗？

   答：静态成员变量在类全局唯一，成员变量不唯一

## 对象数组练习

> 定义一个Student类：
>
> 成员变量：String name，int stuId，String gender，int age，double score（表示学生Java考试成绩）
>
> 构造器：无参构造器，全参构造器等，自由发挥。
>
> 成员方法：print()，打印对象所有属性的取值。
>
> 类定义完毕后，创建五个Student对象存入Student对象数组中，完成以下两个操作：
>
> 1. 遍历输出每个学生的属性信息（遍历对象调用print()方法）
> 2. 求成绩平均值。

注：

1. **数组既可以存储基本数据类型（的值），也可以存储引用数据类型（的引用）。它们的使用没有本质区别。**
2. 可以考虑提取方法实现功能，不要把所有代码一股脑写在main方法中。

~~~java
package com.cs.javaee.homework.objectarrays;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        Student s1 = new Student("张三", "男", 80.1);
        Student s2 = new Student("李四", "男", 90.1);
        Student s3 = new Student("王五", "男", 70.1);
        Student s4 = new Student("赵六", "男", 80.1);
        Student s5 = new Student("刘七", "男", 95.1);

        Student[] arr = {s1, s2, s3, s4, s5};

        for (Student student : arr) {
            Student.print(student);
        }

        double averageGander = Student.average(arr);
        System.out.println("average of student: " + averageGander);
    }

}

class Student {
    static int count = 0;
    String name;
    int stuId = 2041392401;
    String gender;

    static int age = 20;
    double score;

    public Student() {
    }

    public Student(String name, String gender, double score) {
        this.name = name;
        this.stuId += count;
        this.gender = gender;
        this.score = score;
        count++;
    }

    public static void print(Student student) {
        System.out.println(
                "name: " + student.name + "\t" +
                        "studentId: " + student.stuId + "\t" +
                        "gender: " + student.gender + "\t" +
                        "age: " + Student.age + "\t" +
                        "score: " + student.score);
    }

    public static double average(Student[] student) {
        double sum = 0;
        for (Student s1 : student) {
            sum += s1.score;
        }
        return sum/student.length;
    }
}

~~~



## static基础语法练习

> 当类中的成员变量被static修饰时，那么它就是一个静态成员变量。当类中的成员方法被static修饰时，那么它就是一个静态成员方法。
>
> 普通成员（变量和方法）必须创建对象才能够访问，它们是属于对象的的。静态成员（变量和方法） 属于类，通过"类名点"就可以访问，它们是属于类的。
>
> 静态成员变量还有一个非常重要特性：**由于类加载只有一次，所以静态成员变量在类全局都是唯一的！**
>
> 现在你需要完成以下基础语法练习：
>
> 
>
> **编写一个类模拟银行账户（Account）的功能，包含的属性有"账户名（ID）"、"密码"、"账户余额"、"储蓄年利率"等。**
> 其中：
>
> 1. **账号名：**要求每个账户的账户名都是唯一的，且按照固定格式自动生成：
>    - 第一位账户的账户名是"10001"
>    - 其后账户的账户名依次加1。比如第二位账户的账户名就是"10002"，第三位就是"10003".....
> 2. **密码：**新建账户时，可以选择自定义密码，也可以选择使用默认密码："000000"（6个0）
> 3. **账户余额：**可以选择在新建账户时预设一个值，也可以保持默认值0
> 4. **储蓄年利率：**由银行设置，和账户本身没有关系。你可以将它设置为**"0.3%"**（活期年利率一般都非常低，聊胜于无）
>
> 定义完属性后，再给出两个成员方法：
>
> 1. 打印账户<font color=red>**自身**</font>所有属性。
>
> 2. 根据传入的年限和储蓄年利率，计算本息合计后的余额。
>
>    **注：计算利息时简单点，不考虑利滚利。即利息 = 利率 * 时间 * 本金**

提示：

1. 账户ID是独属于某个账户的，它肯定是一个成员变量。但是它的赋值却是在一个初始值的基础上，创建一个对象就+1，这需要一个不受创建对象影响的变量。
2. 储蓄年利率肯定不是属于账户对象的。

~~~java
package com.cs.javaee.homework.staticpractice;

import java.util.Scanner;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {

        System.out.println("-------momo银行账户--------");
        Account account = new Account(1000);
        Scanner sc = new Scanner(System.in);
        System.out.println("please input password: ");
        account.password = sc.nextLine();
        account.print();
    }
}

class Account {
    static int count = 0;
    int accountID = 10001;
    String password = "000000";
    double balance = 0;
    static double annualInterestRate = 0.3;


    public Account(int balance) {
        this.balance = balance;
        this.accountID += Account.count;
        Account.count++;
    }

    public Account(String password, double balance) {
        this.accountID += Account.count;
        this.password = password;
        this.balance = balance;
        Account.count++;
    }

    public void print() {
        System.out.println(
                "Account Name: " + this.accountID + "\n"
                        + "Account Password: " + this.password + "\n"
                        + "Account Balance: " + this.balance + "\n"
                        + "Account Rate: " + Account.annualInterestRate
        );
    }
}
~~~



## 猜数小游戏

> 百度和各种搜索引擎就是生产力，尝试自己解决以下问题：
>
> 先生成一个随机数（1~100之间的整数），再键盘输入猜测的数
> 如果猜的数大了或者小了，给出提示，继续猜，直到猜中为止。

注：Java如何生成随机数，自己百度查一下非常简单。这点学习能力还是需要具备的。

~~~java
package com.cs.javaee.homework.guessnumber;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        /*
            先生成一个随机数（1~100之间的整数），再键盘输入猜测的数
            如果猜的数大了或者小了，给出提示，继续猜，直到猜中为止。
         */
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        while (true) {
            int r = random.nextInt(100) + 1;
            System.out.println("是否开始游戏？按任意键游戏开始（Y/N）");
            String answer = sc.nextLine();
            if (answer.equals("N")) {
                System.out.println("欢迎下次光临！");
                break;
            }
            System.out.println("游戏开始！");
            while (true) {
                System.out.println("请输入你要猜的数字:");
                String num = sc.nextLine();
                int n = Integer.parseInt(num);
                if (n == r) {
                    System.out.println("Game success!");
                    break;
                }
                if (n > r) {
                    System.out.println("你输入的数字偏大！");
                    continue;
                }
                System.out.println("你输入的数字偏小！");
            }

        }
    }
}

~~~



## 自己编写数组工具类

> 工具类：指的是类中方法全部是静态方法的类，工具类在使用时无需创建对象（静态方法无需对象调用）
>
> 以下方法的实现，都是我们上课写过的代码，如果觉得自己很熟练了，直接抄代码即可
>
> 如果觉得不熟悉或者想再练习一遍，再写一遍也可以~

```
写一个数组的工具类ArrayTool, 要求提供如下方法：
	遍历，求最大值，最小值，逆置数组元素
	查询（在数组中查找指定元素，若不存在，待查找元素返回-1，若存在返回元素在数组中首次出现的位置)
	查询（在数组中查找指定元素，若不存在，待查找元素返回-1，若存在返回元素在数组中最后一次出现的位置)
```

~~~java
package com.cs.javaee.homework.arraytool;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        ArrayTool.traverse(arr);
        ArrayTool.reverse(arr);
        ArrayTool.traverse(arr);
        System.out.println("MaxNumber of Array = " + ArrayTool.getMax(arr));
        System.out.println("MinNumber of Array = " + ArrayTool.getMin(arr));
    }
}

class ArrayTool {
    /**
     * 数组的遍历
     *
     * @param arr
     */
    public static void traverse(int[] arr) {
        System.out.print("[");
        for (int i : arr) {
            System.out.print(i + " ,");
        }
        System.out.println("\b]");
    }

    /**
     * 数组逆置
     *
     * @param arr
     */
    public static void reverse(int[] arr) {
        for (int i = 0; i < (arr.length / 2); i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - (i + 1)];
            arr[arr.length - (i + 1)] = temp;
        }
    }

    /**
     * 求数组最大值
     *
     * @param arr
     * @return
     */
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * 求数组最小值
     *
     * @param arr
     * @return
     */
    public static int getMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

}

~~~