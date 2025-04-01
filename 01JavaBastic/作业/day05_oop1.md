[TOC]

# 简答题

> 以下简答题，直接将答案写在题目下面即可。（都是一些概念，虽然我们学得是技术，但基本的概念还是需要记忆的）

1. 二维数组的本质是什么？内存中存在二维数组的特殊内存结构吗？

   答：本质就是引用类型的一维数组，

2. 使用递归要注意什么？

   答：栈溢出

3. 递归的优缺点是什么？

   答：栈溢出，有大量重复计算

4. 什么是类？什么是对象？（根据自己理解写一下即可）

   答：类是对客观事物的抽象，对象就是对类的实例化

5. 创建对象和类加载谁先谁后？某个类的类加载在程序某一次运行过程中，有几次？

   答：类加载之后再创建类，类只会进行一次类加载

6. 包装类有哪些？简要描述一下。

   答：Byte，Short，Integer，Long，Double，Float，Boolean，Character

7. 对于下述代码：

   ``` java
   public class Demo{
     public static void main(String[] args){
       Demo d = new Demo();
     }
   }
   ```

   main方法中创建Demo对象，会触发Demo类的类加载吗？答：会

   我们把一定会触发类加载的场景，称之为类加载的时机。总结目前为止，类加载的时机。

8. **创建对象过程中，成员变量的赋值有很多手段，总结到目前为止成员变量的赋值方式。并谈一谈它们执行的先后顺序。**

   答：默认赋值， 显式赋值，构造器

### 题目1

> 某公司该年度，每个季度的销售额（单位：万元）如下：
> 第一季度：30,66, 48
> 第二季度：10, 33, 20
> 第三季度: 10,99,103
> 第四季度: 9,18,27
> 请使用二维数组存储数据
> 并计算：
> 1，每个季度平均销售额
> 2，年度销售总额

~~~java
package com.cs.javaee.homework.saleamount;

import java.util.Scanner;

/**
 * @author Lenovo
 */
public class Demo {

    public final static Integer QUARTER = 4;

    public final static Integer MONTH = 3;

    public static void main(String[] args) {

        /*
         某公司该年度，每个季度的销售额（单位：万元）如下：
          第一季度：30,66, 48
          第二季度：10, 33, 20
          第三季度: 10,99,103
          第四季度: 9,18,27
          请使用二维数组存储数据
          并计算：
          1，每个季度平均销售额
          2，年度销售总额
         */

        Scanner sc = new Scanner(System.in);
        int[][] saleAmount = new int[QUARTER][MONTH];
//        int[][] saleAmount = {{30,66, 48},{10, 33, 20},{10,99,103},{9,18,27}};
        for (int i = 0; i < QUARTER; i++) {
            for (int j = 0; j < MONTH; j++) {
                System.out.println("请输入第" + (i + 1) + "季度的销售额:");
                int temp = sc.nextInt();
                saleAmount[i][j] = temp;
            }
        }
        System.out.println("每个季度平均销售额:" + sumAmount(saleAmount)/4);
        System.out.println("年度销售总额:" + sumAmount(saleAmount));

    }

    public static int sumAmount(int[][] saleAmount) {
        if (saleAmount == null) {
            return -1;
        }
        int sum = 0;
        for (int[] ints : saleAmount) {
            for (int anInt : ints) {
                sum += anInt;
            }
        }
        return sum;
    }
}

~~~



### 题目2

> 已知有3个班级（一班，二班，三班）分别有3人，2人，5人
> 键盘录入每个班级的学生的成绩，并使用二维数组存储数据
> 然后计算：
> 每个班级的平均成绩，每个班级中的最高成绩和最低成绩，并输出

~~~java
同上
~~~



## 练习使用递归

> 递归在实际开发中用途并不广泛，但我们仍有必要学习它的基本使用。
>
> 这道题目如果想不明白，那就百度一下吧。

```java
//使用递归，把十进制正整数（N>=0）转换成二进制数
package com.cs.javaee.homework.recursionpractice;


/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        /*
         使用递归，把十进制正整数（N>=0）转换成二进制数
         */

        int num = 65535;
        String s = "";
        System.out.println(num + "的二进制为：" + binaryConversion(num, s));
    }

    private static String binaryConversion(int num, String s) {
        if (num == 0) {
            return s;
        }
        s = (num % 2) + s;
        return binaryConversion(num / 2, s);
    }
}

```

## 对象与类基础语法练习题

> 设计物品（Item）类
>
> 它具有以下属性：
>
> 1. 名字
> 2. 价格
> 3. 类别
>
> 它具有以下行为：
>
> 1. 售卖。该方法需要传入一个参数表示购买使用的金钱，如果金钱足够则打印**"xx物品被卖出"**的信息，并将找零作为返回值。
>
>    否则打印**"金钱不足，购买失败"**，并将"-1"作为返回值表示购买失败。
>
> 类定义完毕后，试着创建三个对象并使用**"对象名点"**完成属性赋值：
>
> 1. "手机类的Samsung Galaxy S21 8000元"
> 2. "家具类的海尔冰箱 3000元"
> 3. "日用品类的海飞丝洗发水 30元"
>
> 然后自行调用一下售卖方法，测试一下。

注：

1. 建议用一个public class作为测试类，用于创建对象，调用方法，访问属性等。
2. 具体类（比如本题中的Item）的定义，建议在public class下面定义非public class，这样比较方便快捷。
3. <font color=red>**不要将类定义在其它类的内部（内部类），在定义class时，一定要看清楚大括号！**</font>
4. <font color=red>**以上三条，（如无特别要求）适用于后续面向对象学习中的所有习题。**</font>

~~~java
package com.cs.javaee.homework.classpractice;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        Item item = new Item("Samsung Galaxy S21", 8000, "手机类");
        Item item01 = new Item("海尔冰箱", 3000, "家具类");
        Item item02 = new Item("海飞丝洗发水", 30, "日用品类");

        item.shipping(100000);

    }
}
class Item{
    String name;
    int price;
    String category;

    public Item() {
    }

    public Item(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    public void shipping(int money){
        if(money < price){
            System.out.println("金钱不足，购买失败！");
            return;
        }
        System.out.println(this.name+"物品被卖出。");
    }
}
~~~



## 构造方法和this关键字练习题

> 定义一个Teacher类：
>
> 包含4个成员变量 String name, int age, String gender, int teacherId
>
> 然后定义5个构造方法：
>
> 1. 无参构造
> 2. name单参构造方法
> 3. name和teacherId双参构造方法
> 4. name，age和gender的三参构造器
> 5. name，age，gender和teacherId的四参构造器（要求用this调用已存在的三参构造器）
>
> 最后再定义一个成员方法：
>
> print()：用于打印这个Teacher类对象的基本信息。
>
> 回答下列问题：
>
> 1. 在上面5个构造方法的基础上，再定义一个构造方法单独给gender赋值，能够做到吗？为什么？
>
>    答曰：不可，不能方法重复，不能构成方法重载 
>
>    ---
>
>    
>
> 2. 定义完上述结构后，再定义一个成员方法setGender，用于给gender赋值。要求方法的形参就是String gender，这个方法如何写呢？
>
>    答：this.gender
>
>    ---
>
>    
>
> 定义完这个类后，创建两个Teacher对象分别为t1和t2
>
> 1. 要求t1对象的四个成员变量name, age, gender, teacherId的值分别为"张三"、18、"男"、 1
>
> 2. 要求t2对象的四个成员变量name, age, gender, teacherId的值分别为"李四"、25、"女"、 2
>
>    <font color=red>**注意在使用构造器时，对应位置的实参和形参要一一对应。**</font>
>
> 最后分别用两个对象调用print方法，两次调用方法输出的结果相同吗？为什么？
>
> 答：不同

~~~java
package com.cs.javaee.homework.constructionpractice;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {

    }
}

class Teacher{
    String name;
    int age;
    String gender;
    int teacherId;

    public Teacher() {
    }

    public Teacher(String name) {
        this.name = name;
    }

    public Teacher(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public Teacher(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }


    public Teacher(String name, int age, String gender, int teacherId) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.teacherId = teacherId;
    }

    public void print(){
        System.out.println(this.name+","+this.age+","+this.gender+","+this.teacherId);
    }
}
~~~

