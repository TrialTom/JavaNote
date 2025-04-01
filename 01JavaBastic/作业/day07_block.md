[TOC]

# 操作题

> **操作题，无需表现在作业答案中，自己琢磨和练习即可。**（如有留下痕迹的必要，我会具体给出。）
>
> 
>
> 1. 查看如下代码，回答问题，问题写在注释部分。该题目可以练习Debug的使用。
>
>    ``` java
>    /**
>     * 问题1: 思考注释一下面的代码能否放开?会不会报错?为了更好的代码可读性,可以怎么做?
>     * 问题2: 思考控制台输出的顺序,提供必要的文字说明解释代码为何如此执行。
>     * 问题3: Homework building和Homework constructor会不会输出,为什么?
>     */
>    public class Homework {
>        static Student s = new Student();
>        {
>            System.out.println("Homework building");
>            s = null;
>        }
>        public static void main(String[] args) {
>            System.out.println("main");
>          	//注释一
>            //System.out.println(s.age); 
>            Person p = new Person("刘备");
>            System.out.println(p.name);
>        }
>       
>        public Homework() {
>            System.out.println("Homework constructor");
>        }
>    }
>    class Person{
>        {
>            name = "赵云";
>            System.out.println("Person building");
>        }
>        String name = "曹操";
>        static Student s = new Student();
>       
>        public Person() {
>        }
>       
>        public Person(String name) {
>            System.out.println("Person constructor");
>            this.name = name;
>        }
>    }
>       
>    class Student{
>        int age = 10;
>        {
>            System.out.println("Student building");
>            age = 20;
>        }
>       
>        public Student(int age) {
>            this.age = age;
>        }
>       
>        public Student(){
>           System.out.println("Student constructor");
>        }
>    }
>    ```

# 作图题

> **以下画图题，需要提交相应的图片，并附上必要的文字说明。**（最重要的文字说明，图可以画的粗糙一点。）

​	

# 简答题

> **以下简答题，直接将答案写在题目下面即可。**（都是一些概念，虽然我们学得是技术，但基本的概念还是需要记忆的）

1. 简述一下构造代码块和静态代码块的作用和执行时机。
   - 依照代码执行顺序，顺序执行
2. 总结目前为止，成员变量和静态成员变量的赋值以及顺序。
   - 成员变量：默认赋值，显式赋值，构造赋值
3. 描述一下各种访问权限修饰符的访问级别。（排除protected，因为没讲）
   - public，private
4. 说一下自己对面向对象语言特性和面向对象开发思想的理解。（开放性问题，说出自己的理解即可）
   - 面向对象编程思想总而言之就是抽象出类，实例化对象
5. Getter/Setter方法的好处是什么？定义类时需要无脑加上Getter/Setter方法吗？
   - 保护成员变量
6. 封装的好处是什么？（从代码设计者和使用者角度分别阐述）
   - 代码实现细节对使用者透明，让使用者专注他自己的工作；让代码的复用性提高，降低耦合

## 导包相关练习

> 导包是很基本的操作，某些细节也需要注意，请完成以下练习：

```Java
// 在com.cskaoyan.a包下，定义一个名为MyClazz的类如下
     public class  MyClazz {
        public void hello() {
        System.out.println("a包");
       }
     }
     
// 同时，在com.cskaoyan.b包下，一个类名也为MyClazz
     public class  MyClazz {
       public void hello() {
       System.out.println("b包");
       }
     }

// 同时在com.cskaoyan.b包下定义一个Test类如下：
     public class Test {
       public static void main(String[] args) {
       MyClazz myClazz = new MyClazz();
       myClazz.hello();
       }
     }
```

> 首先根据上述注释，将三个类定义出来
>
> 毫无疑问，直接执行Test中的main方法会输出的是b包，思考以下问题<font color=red>**（独立的三个问题）**</font>：
>
> 1. 不改变Test类中main方法的基础上，让main方法运行之后输出“a包”，应该怎么做？
>    - import com.cskaoyan.a
> 2. 不做任何导包操作的基础上，修改main方法，让main方法运行之后同时输出“a包”和“b包”，应该怎么做？
>    - 用全限定类名
> 3. 在Test类中添加导包语句import com.cskaoyan.a.*，不修改任何代码，执行main方法，输出的是什么？为什么？
>    - 输出的是b包，智能导包只会在代码即将报错的时候导包

## 工具类禁止创建对象

> 普遍来说，工具类不应该创建对象，应该私有化构造方法

```
需求一：在昨天自己的作业，数组工具类的基础上，私有化它的构造方法
需求二：
定义一个Scanner工具类ScannerUtils，提供以下方法：
	1，键盘录入字符串
	2，键盘录入int整数
	3，键盘录入一个Person对象（Person类中有age和name属性）
	不要忘记私有化构造器
```

注：录入对象，无非就是先录入成员变量取值，然后构造器创建对象。

```java
package com.cs.javaee.homework.scannertools;


import java.util.Scanner;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        ScannerUtils.setStr();
        String s = ScannerUtils.getStr();
        System.out.println(s);
    }
}

class ScannerUtils {
    private static String str;

    Scanner sc = new Scanner(System.in);


    public static String getStr() {
        return ScannerUtils.str;
    }

    public static void setStr(){
        new ScannerUtils();
    }

    private ScannerUtils() {
        System.out.println("Input:");
        ScannerUtils.str = sc.nextLine();
    }
}

```



## 禁止创建对象

> 综合访问权限修饰符，static等知识点，做一个综合的练习。

```
定义一个Student类，并要求在其他类中，最多只能创建10个Student类的对象。
	分析：
		1，如果允许外部直接创建对象，显然无法控制创建对象的个数
		2，需要计数器指示外部创建对象的个数
```



```java
package com.cs.javaee.homework.banpractice;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        Student s1 = Student.creatObject();
        System.out.println(Student.getCount());
        Student s2 = Student.creatObject();

    }
}

class Student {

    private static int count = 0;

    public static int getCount() {
        return count;
    }

    public static Student creatObject() {
        if (Student.count < 2) {
            Student.count++;
            System.out.println("创建成功！");
            return new Student();
        }
        System.out.println("创建失败!");
        return null;
    }

    private Student() {

    }
}

```



## Getter/Setter方法

> 当属性私有化，又有外界访问需求时，提供Getter/Setter方法

```
创建两个类，分别用来表示长方形和正方形。
同时定义所需的成员变量（边长），代表长方形或者正方形的边长（私有化成员变量，并提供相应的Getter/Setter方法，获取以及改变长方形和正方形的边长。）
然后在两个类中分别定义两个成员方法，用于求对应图形的面积和周长。
最后，写代码测试一下创建对象，方法调用等。
```

注：Getter/Setter方法可以选择手写一个，其余的用快捷键自动生成。

```java
package com.cs.javaee.homework.rectanglepractice;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        /*
        需求：
        创建两个类，分别用来表示长方形和正方形。
        同时定义所需的成员变量（边长），代表长方形或者正方形的边长
        （私有化成员变量，并提供相应的Getter/Setter方法，获取以及改变长方形和正方形的边长。）
        然后在两个类中分别定义两个成员方法，用于求对应图形的面积和周长。
        最后，写代码测试一下创建对象，方法调用等。
         */

        RecTangle recTangle = new RecTangle();
        System.out.println(RecTangle.area(3, 4));
    }
}
class Shape{
    private int wide;
    private int length;

    protected static int circumference(int wide,int length){
        return wide*length;
    }

    protected static int area(int wide,int length){
        return (wide+length)*2;
    }

}
class RecTangle extends Shape{


}

class Square extends Shape{

}

```



## Debug和代码执行顺序练习

> 读下列代码，指出代码的执行流程，并给出必要的说明。
>
> ``` java
> public class TestStaticDemo {
>     public static void main(String[] args) {
>         staticMethod();
>     }
> 
>     static TestStaticDemo ts = new TestStaticDemo();
> 
>     static {
>         System.out.println("静态代码块");
>     }
> 
>     {
>         System.out.println("构造代码块");
>     }
> 
>     public TestStaticDemo() {
>         System.out.println("无参构造器");
>         System.out.println("a=" + a + ",b=" + b);
>     }
> 
>     public static void staticMethod() {
>         System.out.println("静态成员方法");
>     }
> 
>     int a = 666;
>     static int b = 777;
> }
> ```
>
> 做一下练习即可。



# 预习

> 预习的题目仅为预习提供思路，不用表现在作业中
>
> <span style=color:red;background:yellow>**请预习：继承，继承是面向对象的核心知识点。**</span>

