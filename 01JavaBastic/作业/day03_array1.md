[TOC]

# 简答题

> 以下简答题，直接将答案写在题目下面即可。（都是一些概念，虽然我们学得是技术，但基本的概念还是需要记忆的）

1. 数组的声明方式有几种？分别是什么？应该使用哪种方式？
   - 两种声明
   - Java版和C语言版
   - 使用Java版
2. 数组的初始化方式有几种？分别是什么？它们分别在什么场景中使用？
   - 初始化方式有两种
   - 静态初始化和动态初始化
   - 静态初始化在明确知道数组元素时使用，动态初始化在不明确数组元素时使用，由JVM默认初始化
3. 数组创建后，还能修改它的长度吗？可以修改元素的取值吗？
   - 数组长度不可变
   - 可以修改元素的取值
4. 动态初始化数组并没有指出数组中元素的具体取值，这时数组可用吗？为什么？请指出各种数据类型数组对象中的元素默认值。
   - 可用
   - 因为JVM会给动态初始化数组中的各元素赋予默认值
   - byte，short，int：0；long：0L；float：0.0F；double：0.0；boolean：false；char：\u0000;
5. （数组）对象中的元素具有默认值，那么引用具有默认值吗？
   - 引用不具有默认值，它是在栈中的局部变量，需要人为赋值
6. 说出你对引用数据类型的理解。（可以结合JVM内存模型）
   - 引用数据类型本质还是栈中的局部变量，它是与堆中对象建立联系的接口，从而实现在栈间接访问堆中的对象。
7. JVM内存模型中，栈和堆的作用是什么？（简要说明即可）
   - 栈：存放需要执行的方法的栈帧
   - 堆：存放对象数据

## Scanner基础使用练习

> 使用Scanner依次接收以下三种数据类型的值：
>
> 1. int类型
> 2. String类型
> 3. double类型
>
> 接收三个值后，再计算：
>
> 1. 求和：int + double
> 2. 拼接字符串：int + double + String
>
> 分别接收计算结果后，输出该计算结果。

<span style=color:red;background:yellow>**注：**</span>

1. **使用Scanner时，注意不要混用next系列和nextLine方法。**（原因可以查看详细文档说明）
2. 代码直接全部写在main方法中即可。

~~~java
package com.cs.array.scanner;

import java.util.Scanner;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {

        differenceTypeOfCalculation();
    }

    /**
     * 使用Scanner依次接收以下三种数据类型的值：
     * 1. int类型
     * 2. String类型
     * 3. double类型
     * 接收三个值后，再计算：
     * 1. 求和：int + double
     * 2. 拼接字符串：int + double + String
     * 分别接收计算结果后，输出该计算结果
     */
    public static void differenceTypeOfCalculation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("int类型数据输入：");
        String numInt = sc.nextLine();
        System.out.println("String类型数据输入：");
        String numString = sc.nextLine();
        System.out.println("double类型数据输入：");
        String numDouble = sc.nextLine();

        System.out.println("int类型和double类型数据相加得：" + (Double.parseDouble(numInt) + Double.parseDouble(numDouble)));

        System.out.println("拼接字符串结果为：" + numInt + numString + numDouble);

    }

}

~~~



<span style=color:red;background:yellow>**注：今天的数组只讲了基本使用和概念，没有讲具体的使用。但是不重要，下列题目请大家自行摸索一下先！！**</span>

## 数组基础练习——除以首元素

> 定义一个double类型的数组，让数组中每个元素（包括首位元素）都除以首位元素，得到的结果过作为该位置上的新元素。请在原先数组数组上操作，并打印新数组。
> 例如数组[ 2.0 , 4.0 , 6.0 , 4.0 ] 经过方法运算得到新数组 [ 1.0 ,2.0 , 3.0 ,2.0 ]

注：注意元素取值的变化。

~~~java
package com.cs.array.dividebythefirstelement;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        /**
         *
         *
         * 定义一个double类型的数组，让数组中每个元素
         （包括首位元素）都除以首位元素，
         * 得到的结果过作为该位置上的新元素。
         请在原先数组数组上操作，并打印新数组。
         * 例如数组[ 2.0 , 4.0 , 6.0 , 4.0 ]
         * 经过方法运算得到新数组 [ 1.0 ,2.0 , 3.0 ,2.0 ]
         */
        double[] numDouble = {2,4,6,4};
        divideByTheFirstElement(numDouble);
        for(int i =0;i < numDouble.length;i++){
            System.out.println(numDouble[i]);
        }
    }

    private static void divideByTheFirstElement(double[] numDouble) {
        for(int i =numDouble.length-1;i>=0;i--){
            numDouble[i]/=numDouble[0];
        }
    }
}

~~~



## 数组基本使用练习

> 现在你去参加歌唱比赛。有10个评委打分，规则是去掉最高分和最低分，求平均分是最终成绩。请编码计算出你的最终成绩~

注：

1. **请合理使用方法，不要胡子眉毛一把抓把代码全部写在main方法里。**

2. **最高分和最低分可能有多个，但只需要去掉其中一个即可（也就是说平均成绩要除以8）**

3. 建议给出下面的三个方法：

   1. 求数组中元素最大值的方法
   2. 求数组中元素最小值的方法
   3. 求数组中元素平均分的方法

   当然如果你有自己的想法，也可以用自己的方式去实现功能。
   
   ~~~java
   package com.cs.array.average;
   
   import java.util.Scanner;
   
   /**
    * @author Lenovo
    */
   public class Demo {
       /**
        * 现在你去参加歌唱比赛。有10个评委打分，
        * 规则是去掉最高分和最低分，求平均分是最终成绩。
        * 请编码计算出你的最终成绩~
        */
       public static void main(String[] args) {
           System.out.println("请输入十位评委的打分（中间用一个空格分隔）");
           Scanner sc = new Scanner(System.in);
           String s = sc.nextLine();
   
           String[] strings = s.split(" ");
           int[] fraction = new int[10];
           for (int i = 0; i < fraction.length; i++) {
               fraction[i] = Integer.parseInt(strings[i]);
           }
           //最大值
           int max = getMax(fraction);
           //最小值
           int min = getMin(fraction);
           //求和
           int sum = getSum(fraction);
           System.out.println("平均值为:" + (sum - max - min) / 8);
       }
   
       private static int getSum(int[] fraction) {
           int sum = 0;
           for (int i = 0; i < fraction.length; i++) {
               sum += fraction[i];
           }
           return sum;
       }
   
       private static int getMin(int[] fraction) {
           int min = fraction[0];
           for (int i = 1; i < fraction.length; i++) {
               if (min > fraction[i]) {
                   min = fraction[i];
               }
           }
           return min;
       }
   
       private static int getMax(int[] fraction) {
           int max = fraction[0];
           for (int i = 1; i < fraction.length; i++) {
               if (max < fraction[i]) {
                   max = fraction[i];
               }
           }
           return max;
       }
   
   
   }
   //此代码健壮性得不到保障
   ~~~
   
   

# 预习

> 预习的题目仅为预习提供思路，不用表现在作业中
>
> 请预习：数组后面的知识。
>
> <span style=color:red;background:yellow>**今天的作业不多，请大家自行复习和预习。JVM内存模型是非常重要的知识点，一定要搞清楚！！！**</span>