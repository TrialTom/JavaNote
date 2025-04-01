[TOC]

# 简答题

> 以下简答题，直接将答案写在题目下面即可。（都是一些概念，虽然我们学得是技术，但基本的概念还是需要记忆的）

1. 数组操作中会碰到哪些异常？是什么原因导致的?
   - 空指针异常，传参传入空指针
2. 数组遍历方式中，普通for循环和增强for循环有什么不同？它们的使用场景是什么样的？
   - 增强for只能访问数组，无法对数组进行操作；
   - 普通for一般用在增删改查，增强for一般查
3. 什么是值传递？什么是引用传递？Java方法的传值方式是什么？这意味着什么？（方法能够对传入的实参数值做什么操作？）
   - 传值指的是，传入的参数是实参的拷贝；引用传递，传递的参数是引用本身；
   - Java方法的传值方式是值传递，意味着传递的实参的拷贝，操作传入的数据不会影响原数据
4. 可变参数的本质是什么？在方法体中怎么使用可变参数？
   - 可变参数的本质就是数组；for（数据类型... 变量名）

## 数组基本使用练习

> 1. 定义一个double数组用来存放学生成绩，然后键盘录入10位同学的成绩并存入数组，求这10位同学成绩的平均值。
> 2. 定义一个String数组，输出该数组的长度，并用多种方式遍历打印数组元素（常见的方式遍历即可）

注：

1. **请合理使用方法，不要胡子眉毛一把抓把代码全部写在main方法里。**
2. 数组遍历的方式，比如for、增强for或者使用工具类等等。

~~~java
package com.cs.javaee.array02.arraypractice;

import java.util.Scanner;

/**
 * @author Lenovo
 */
public class Demo {
    public final static Integer STUDENTNUM = 10;

    public static void main(String[] args) {

        /**
         *
         *
         * 1. 定义一个double数组用来存放学生成绩，然后键盘录入10位同学的成绩并存入数组，求这10位同学成绩的平均值。
         *
         */
        double[] grades = new double[STUDENTNUM];

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < STUDENTNUM; i++) {
            System.out.println("请输入第" + (i + 1) + "位学生的成绩:");
            double temp = sc.nextDouble();
            grades[i] = temp;
        }
        System.out.println("学生平均成绩为：" + average(grades));

    }

    private static double average(double[] arr) {
        double sum = 0;
        for (double grades : arr) {
            sum += grades;
        }
        return sum / STUDENTNUM;
    }

}

~~~

~~~java
package com.cs.javaee.array02.arraypractice;

import java.util.Arrays;

/**
 * @author Lenovo
 */
public class Demo02 {
    public static void main(String[] args) {
        /**
         *
         *2. 定义一个String数组，输出该数组的长度，并用多种方式遍历打印数组元素（常见的方式遍历即可
         */
        String[] arr = {"a", "b", "c", "d"};
        System.out.println("arr数组的长度为："+arr.length+",\narr的遍历："+Arrays.deepToString(arr));

    }
}

~~~



## 数组综合练习——删除元素

> <font color=red>**对于某个String类型数组，将其中的某个元素全部去掉，得到一个新数组，并统计去掉了几个元素。**</font>
>
> 举例，对于String数组["abc", "123", "123", "123", "666", "777"] 将其中的元素"123"全部去掉，就得到了新数组["abc","666", "777"]，一共去掉了3个元素。

注：

1. 这里说的去掉，不是指用0/null等默认值替代原先的元素，而是指真正的删除掉。
2. **思考：同一个数组能不能实现这个功能？如果不能，应该怎么完成呢？**

~~~java
package com.cs.javaee.array02.elementofarraydelete;

import java.util.Arrays;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        /**
         *
         *对于某个String类型数组，将其中的某个元素全部去掉，得到一个新数组，并统计去掉了几个元素。
         *
         * 举例，对于String数组["abc", "123", "123", "123", "666", "777"]
         * 将其中的元素"123"全部去掉，就得到了新数组["abc","666", "777"]，一共去掉了3个元素
         */

        String[] arr = {"abc", "123", "123", "123", "666", "777"};
        String[] arr01 = elementDelete(arr, "123");
        System.out.println(Arrays.toString(arr01));
        System.out.println("删除" + (arr.length - arr01.length) + "个元素");

    }

    private static String[] elementDelete(String[] arr, String s) {
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == s) {
                continue;
            }
            arr[temp] = arr[i];
            temp++;
        }
        String[] arr01 = new String[temp];
        for (int i = 0; i < temp; i++) {
            arr01[i] = arr[i];
        }
        return arr01;
    }

}

~~~



## 值传递练习

> Java有且仅有值传递，Java中的方法不能直接改变实参本身。
>
> 对于下列代码：
>
> ``` java
> public static void main(String[] args) {
> int a = 10;
> int b = 20;
> method(a, b);
> System.out.println("a = " + a);
> System.out.println("b = " + b);
> }
> 
> public static void method(int a, int b) {
> a *= 2;
> b *= 2;
> }
> ```
>
> 思考以下问题：
>
> 1. 对于以上Java代码，method()方法可以把a，b的取值变为原先的2倍吗？如果不能，为什么？
>
>    - 不能，传入的参数是实参的拷贝
>
> 2. 上述main方法不变，修改method方法实现，让程序输出：
>
>    1. a = 20
>    2. b = 40
>
>    如何实现呢？
>
>    注：这是个脑筋急转弯，不会的可以互相问问或者查一查。
>
>    - 使用数组



## 可变参数练习

> 可变参数的本质是数组，完成下列需求：
>
> 求不限定个数参数的最大值（要求使用可变参数，使用int类型即可）
>
> - for循环实现即可

## 数组综合练习——合并数组（扩展）

> 首先准备两个数组（简单起见，用两个int数组即可）
>
> 然后写方法，将这两个数组合并。

~~~java
package com.cs.javaee.array02.mergesort;

import java.util.Arrays;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        int[] arr01 = {1, 4, 5, 7, 8, 90, 234};
        int[] arr02 = {2, 34, 55, 67, 89, 123, 456, 765};

        int[] mergeArr = mergeSort(arr01, arr02);
        System.out.println(Arrays.toString(mergeArr));
    }

    private static int[] mergeSort(int[] arr01, int[] arr02) {
        int[] arrOfMerge = new int[(arr01.length + arr02.length)];
        for (int i = 0, j = 0, k = 0; i < arrOfMerge.length; i++) {
            if (j == arr01.length) {
                arrOfMerge[i] = arr02[k];
                k++;
                continue;
            }
            if (k == arr02.length) {
                arrOfMerge[i] = arr01[j];
                j++;
                continue;
            }
            if (arr01[j] < arr02[k]) {
                arrOfMerge[i] = arr01[j];
                j++;
                continue;
            }
            arrOfMerge[i] = arr02[k];
            k++;
        }
        return arrOfMerge;
    }
}

~~~



## 数组排序（扩展）

> 随意给出一个长度为10的int数组，然后升序排列它。

注：

1. 如果想自己写排序实现，可以使用最简单的冒泡排序或者选择排序。（不会就百度一下）
2. 排序对于数组而言是非常常见的操作，Java源码工具类中，早有对应的实现。请百度查询一下，不需要了解它具体怎么实现的，会用即可。（百度并学习是非常重要的能力，我们不可能在课堂上学会所有的知识）

~~~java
package com.cs.javaee.array02.quicksort;

import java.util.Arrays;

/**234, 532, 23, 5, 34, 6, 6, 7, 134, 6, 3, 634, 24, 326324, 6223, 4, 5, 67, 8, 756, 23
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        int[] arr = {234,4234,5,4,35642,23423,5,75,87,99,78,9,8786,46,67,67,34,796,23,37465,875,35423,65,6556,9,935,34};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr,int low,int high) {
        if(low >= high){
            return;
        }
        int pivot = orderly(arr, low, high);
        quickSort(arr,low,pivot-1);
        quickSort(arr,pivot+1,high);
    }

    private static int orderly(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            while (arr[high] >= pivot && high>low) {
                high--;
            }
            arr[low] = arr[high];
            while (arr[low] < pivot && high>low){
                low++;
            }
            arr[high]=arr[low];
        }
        arr[low]=pivot;
        return low;
    }

}

~~~



# 预习

> 预习的题目仅为预习提供思路，不用表现在作业中
>
> <font color=red>**请自行学习一下：二维数组**</font>
>
> 预习一下：递归
>
> 预习一下：面向对象第一阶段