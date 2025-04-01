[TOC]

# 简答题

> **以下简答题，直接将答案写在题目下面即可。**（都是一些概念，虽然我们学得是技术，但基本的概念还是需要记忆的）

1. 描述一下运行时Class对象。

   答：JVM会在类加载时期，自动地在堆上创建一个Class对象，该类装着类的类型信息。

2. equals方法和hashCode方法的重写需要注意什么事项？

   答：自反性，排他性，一致性，对称性，传递性；equals方法和hashCode方法要么都不重写，要么都重写，且重写的依据是一样的；如果调用equals方法，两个对象是相等，那么对这两个对象中的每个对象调用hashCode方法都必须生成相同的整数结果。

3. 给一个类的对象做克隆操作的步骤是什么？

   答：首先重写方法，设置访问权限限制，类实现的接口Cloneable，还可以重写返回值的类型。

4. 什么是深度克隆？怎么做深度克隆？

   答：深度克隆即克隆后得到新对象，且新对象中的引用所指向的对象也是新对象。

   ​	将新对象中引用所指向的对象也克隆一个

## getClass()方法练习

> 定义两个类，然后分别创建对象，调用getClass方法
>
> 用“==”号比较它们的运行时Class对象是否相等，并说明原因
>
> 理解运行时类对象、类加载、类的对象的区别

```java
public class Demo {
    public static void main(String[] args) {
        Student student = new Student();
        Teacher teacher = new Teacher();
        Class stu = student.getClass();
        Class teacher01 = teacher.getClass();
        System.out.println(stu == teacher01);
    }
}
```



## equals方法练习

```
定义一个Animal类
成员变量：
	int age,String name,double price
手写它的equals方法，比较getClass和instanceof的区别
```

```java
package com.cs.javaee.homework.equalstest;

import com.sun.org.apache.xpath.internal.operations.Equals;
import javafx.scene.media.EqualizerBand;

import javax.lang.model.element.VariableElement;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/02/28 15:44
 */

public class Demo {
    public static void main(String[] args) {
        Animal animal01 = new Animal(2, "狒狒", 200);
        Animal animal02 = new Animal(2, "狒狒", 200);
        Animal animal03 = new Animal(2, "狒狒1号", 200);
        System.out.println(animal01.equals(animal02));
        System.out.println(animal01.equals(animal03));
    }
}

/**
 * create by: TrialCat
 * description: 定义一个Animal类
 * 成员变量：
 * int age,String name,double price
 * 手写它的equals方法，比较getClass和instanceof的区别
 * create time: 2024/2/28 15:45
 *
 * @param
 * @return
 */
class Animal {
    int age;
    String name;
    double price;

    public Animal(int age, String name, double price) {
        this.age = age;
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null ) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        if (this.age != ((Animal) obj).age) {
            return false;
        }
        if (this.name != ((Animal) obj).name) {
            return false;
        }
        return this.price == ((Animal) obj).price;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
```



## 深度克隆练习

> String虽然也是引用数据类型，但无需考虑它的深度克隆。

```
分别定义以下类：
教师类Teacher
属性：int age;String name;Student stu
学生类Student
属性：int age；String name；Star s
明星类Star
属性：int age，String name

尝试完成Teacher对象的深度克隆，并写代码进行测试
```

```java
package com.cs.javaee.homework.clonetest;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/02/28 16:32
 */

public class Demo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Teacher teacher = new Teacher(20, "杨老师", new Student(17, "xxx", new Star(40, "韩寒")));

        Teacher teacherClone = (Teacher) teacher.clone();
        teacherClone.stu = ((Student) teacher.stu.clone());
        teacherClone.stu.s = ((Star) teacher.stu.s.clone());
        teacherClone.stu.age = 100;
        teacherClone.stu.s.name = "林尼";
        System.out.println(teacher.toString());
        System.out.println(teacherClone.toString());

    }
}

/**
 * create by: TrialCat
 * description:
 * 分别定义以下类：
 * 教师类Teacher
 * 属性：int age;String name;Student stu
 * 学生类Student
 * 属性：int age；String name；Star s
 * 明星类Star
 * 属性：int age，String name
 * 尝试完成Teacher对象的深度克隆，并写代码进行测试
 * create time: 2024/2/28 16:32
 *
 * @param
 * @return
 */

class Teacher implements Cloneable {
    int age;
    String name;
    Student stu;

    public Teacher(int age, String name, Student stu) {
        this.age = age;
        this.name = name;
        this.stu = stu;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", stu=" + stu.toString() +
                '}';
    }
}

class Student implements Cloneable {
    int age;
    String name;
    Star s;

    public Student(int age, String name, Star s) {
        this.age = age;
        this.name = name;
        this.s = s;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", s=" + s.toString() +
                '}';
    }
}

class Star implements Cloneable {
    int age;
    String name;

    public Star() {
    }

    public Star(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Star{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
```



# 预习

> 预习的题目仅为预习提供思路，不用表现在作业中
>
> <font color=red>**请预习：String类**</font>