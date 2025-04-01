[TOC]

# 操作题

> <span style=color:red;background:yellow>**操作题，无需表现在作业答案中，自己琢磨和练习即可。**</span>
>
> 1. 练习使用Junit，按照上课时候讲的步骤逐步操作即可。**顺便试一试在Junit中使用Scanner键盘录入。**
>
>    注：一般情况下，Junit下是不能使用Scanner的，感兴趣可以尝试百度解决一下。
>
> 2. 熟悉Java转义字符，可以在IDEA中自己打印测试一下看一看效果。顺便可以看一看编码值，编码表等概念，当然这些概念后面也会详细讲解。
>
> 3. 理解全限定类名，尝试使用IDEA直接复制某个类的全限定类名。（不会就百度一下，或者问问同学老师）
>
> 4. **Debug模式练习**
>
>    尝试Debug以下代码，练习IDEA的Debug模式。具体练习时，可以对照文档一边看一边练习每个按钮的作用。该题不要求留下作业痕迹，但一定要自己操作几次。
>
>    ``` java
>    public class Demo {
>        public static void main(String[] args) {
>            System.out.println("hello world");
>            int a =  10;
>            int b  = 20;
>            int c;
>            c = a + b;
>            Scanner sc = new Scanner(System.in);
>            int num = sc.nextInt();
>            if (num > 0) {
>                System.out.println("你输入的数大于0");
>            } else if (num == 0) {
>                System.out.println("你输入的数等于0");
>            } else {
>                System.out.println("你输入的数小于0");
>            }
>            for (int i = 0; i < 5; i++) {
>                for (int j = 0; j < 3; j++) {
>                    System.out.print("#");
>                }
>                System.out.println();
>            }
>            method();
>        }
>        public static void method() {
>            System.out.println("test");
>            System.out.println("test");
>            System.out.println("test");
>            System.out.println("test");
>        }
>    }                                                                                     
>    ```
>

> 2. 以下画图题，需要提交相应的图片，并附上必要的文字说明

# 简答题

> 以下简答题，直接将答案写在题目下面即可。（都是一些概念，虽然我们学得是技术，但基本的概念还是需要记忆的）

1. 在使用Scanner做键盘录入时，为什么不能混用next系列方法和nextLine方法？
   - 答：**next系列方法以碰到第一个不是空格（换行，制表等）的字符开始，以碰到第一个空格、换行、制表结束；nextLine方法以碰到的第一个换行结束；next系列方法和nextLine方法混用，可能会导致next系列方法的结束的回车被nextLine接收，从而无法给nextLine传递参数**
2. 方法的实参和形参的区别是什么?
   - 答：**实参是一个数据类型，而形参相当于占位符，**
3. 方法重载发生的条件是什么?形参的名字不同能否构成方法的重载?
   - 答：**方法名相同，形参的数量不同；方法名相同，形参数量相同，形参的数据类型不同；方法名相同，形参数据类型相同，形参数量相同，但形参的位置不同**
4. 方法可以没有返回值吗？可以没有返回值类型吗?
   - 答：**方法可以没有返回值，但不可以没有返回值类型**
5. 什么是全限定类名？它的作用是什么？
   - 答：**方法所在包名+方法名，可以用来区分不同包中相同的方法**
6. 方法的返回值类型或者形参类型是double，意味着可以使用什么类型的返回值或实参？（自动类型转换）
   - 答：**返回值可以用double类型，实参可以用byte，char，int，float，long**

# 编程题

> 编程题的答题要求：
>
> 1. 编程题，需要先自己编写代码，执行调试完毕
>
> 2. 将代码以代码块（CTRL+A贴入整个Java文件内容，而不是一个main方法）的格式贴入md文件
>
>    有两种方式可以以代码块形式将代码贴入md文件：
>
>    1. 可以直接从idea复制代码，然后粘贴进md文档，Typora会自动转换成代码块的格式
>    2. 可以在md文档空白处中右键，然后插入代码块，再把代码复制进来（熟练了可以使用快捷键Ctrl + Shif + Y）
>    3. 代码块右下角可以填入语言，建议直接填入Java（这样做会有颜色标记关键字）
>
> 3. 在代码块下附上执行结果图片。可以使用微信/QQ/windows/ <font color=red>**Snipaste**</font>截图等截图工具截图到计算机粘贴板，然后直接粘贴到md文档中，也可以在md文档空白处右键，然后插入图像，自己选择本地图片的路径（可以用，但不推荐），推荐使用Snipaste软件截图后，然后复制粘贴就可以插入图片。
>
>    **如果你实在没有办法导入执行结果图片，请将代码执行结果，直接贴在题目的后面。**

## 基础语法练习

> 方法是Java代码中最常用的语法结构，这里对方法的语法做一下练习。
>
> 在使用方法或者想要写一个方法时，首先要考虑的是方法需要的参数是什么数据类型，需要几个（形式参数），再考虑方法需要返回什么结果（返回值类型）。在调用方法时，需要搞清楚具体传入什么数值（实际参数）。
>
> <span style=color:red;background:yellow>**完成如下练习题，要求提取出必要的方法，然后在main方法中调用测试：**</span>
>
> 1. 使用for循环遍历**n以内**的奇数，并计算所有的奇数的和并输出。
>
>    答：如下
>
> ~~~java
>  @Test
>     public void oddAnd() {
>         System.out.println("请输入n的值：");
>         Scanner Odd = new Scanner(System.in);
>         int n = Odd.nextInt();
>         int sum = 0;
>         for (int i = 1; i <= n; i++) {
>             if (i % 2 != 0) {
>                 sum += i;
>             }
>         }
>         System.out.println("所有的奇数的和为:" + sum);
> 
>     }
> ~~~

> 2. 打印倒三角形，并且行数（line）是可控制的。比如我想打印6行的倒三角形，则如下图：
>
> ``` 
> ******
> *****
> ****	
> ***	
> **	
> *
> ```

> ​		答：如下
>
> ~~~java
>  @Test
>     public void invertedTriangle() {
>         System.out.println("请输入行数：");
>         Scanner sc = new Scanner(System.in);
>         int num = sc.nextInt();
>         for (int i = num; i > 0; i--) {
>             for (int j = num; j >= num - i + 1; j--) {
>                 System.out.print("*");
>             }
>             System.out.println();
>         }
>     }
> ~~~
>
> 
>
> 3. 根据键盘录入打印nn乘法表，n是可变的。
>
> 答：如下
>
> ~~~java
>  @Test
>     public void multiplicationTable() {
>         System.out.println("请输入行数：");
>         Scanner sc = new Scanner(System.in);
>         int num = sc.nextInt();
>         for (int i = 1; i <= num; i++) {
>             for (int j = 1; j <= i; j++) {
>                 System.out.print(j + "*" + i + "=" + (i * j) + "\t");
>             }
>             System.out.println();
>         }
>     }
> ~~~
>
> 
>
> 4. 键盘录入一个int数字，判断它是奇数还是偶数。
>
> 答：如下：
>
> ~~~java
>   @Test
>     public void oddAndEvenNumber() {
>         System.out.println("请输入数：");
>         Scanner sc = new Scanner(System.in);
>         int num = sc.nextInt();
>         if (num % 2 == 0) {
>             System.out.println("偶数");
>             return;
>         }
>         System.out.println("奇数");
>     }
> ~~~
>
> 
>
> 5. 根据键盘录入打印n行m列井号，n和m是可变的。
>
> 答：如下
>
> ~~~java
>  @Test
>     public void print() {
>         System.out.println("请输入行数：");
>         Scanner sc = new Scanner(System.in);
>         int col = sc.nextInt();
>         System.out.println("请输入列数：");
>         int row = sc.nextInt();
>         for (int i = 0; i < col; i++) {
>             for (int j = 0; j < row; j++) {
>                 System.out.print("#");
>             }
>             System.out.println();
>         }
>     }
> ~~~
>
> 
>
> 6. 输出前n个数当中的所有素数，并统计个数。
>
> 注：题目6是扩展题，可以选择不做。这道题并不高明，主要是要知道如何判断素数。
>
> ​	答：如下
>
> ~~~java
> @Test
>     public void statisticalComposites() {
>         System.out.println("请输入数：");
>         Scanner sc = new Scanner(System.in);
>         int num = sc.nextInt();
>         int sum = 0;
>         for (int i = 1; i <= num; i++) {
>             int pow = (int) Math.pow(i, 0.5);
>             for (int j = 2; j <= pow; j++) {
>                 if (i % j == 0) {
>                     sum += 1;
>                 }
>             }
>         }
>         System.out.println("素数个数为：" + sum);
>     }
> ~~~
>

# 预习

> 预习的题目仅为预习提供思路，不用表现在作业中
>
> 请预习：数组
>
> 今天的作业不算多，留给大家重组的时间预习和复习。