[TOC]

# 操作题

> **操作题，无需表现在作业答案中，自己琢磨和练习即可。**（如有留下痕迹的必要，我会具体给出。）

1. 读程序题：读程序，然后分析过程和结果，提供必要的文字说明。

   ``` java
   public class Test {
       public static void main(String[] args) {
           Sub sub = new Sub();
       }
   }
   class Base{
       static {
           System.out.println("base 静态代码块");
       }
       {
           System.out.println("base 构造代码块");
       }
       public Base(){
           System.out.println("base无参构造");
       }
   }
   class Sub extends Base{
       static {
           System.out.println("sub 静态代码块");
       }
       {
           System.out.println("sub 构造代码块");
       }
       public Sub(){
           System.out.println("sub 无参构造");
       }
   }
   ```

   > 答：结果如下：
   >
   > base 静态代码块
   >
   > sub 静态代码块
   >
   > base 构造代码块
   >
   > base 无参构造
   >
   > sub 构造代码块
   >
   > sub 无参构造
   >
   > 分析如下：
   >
   > new Sub();类加载sub类，然后调用构造方法sub(),隐式调用super（）方法，，类加载Base类，类加载结束，

2. 读程序题：读程序，然后分析过程和结果，提供必要的文字说明。

   ``` java
   public class Obj3 extends Obj1 {
       Obj2 ob2 = new Obj2();
       public Obj3(){
           System.out.println("obj3");
       }
       public static void main(String[] args) {
           Obj3 obj3 = new Obj3();
       }
   }
   class Obj1 {
       Obj2 ob2 = new Obj2();
       public Obj1() {
           System.out.println("Obj1");
       }
   }
   class Obj2 {
       public Obj2() {
           System.out.println("obj2");
       }
   }
   ```

   > 答：结果如下：
   >
   > obj2
   >
   > obj1
   >
   > obj2
   >
   > obj3

3. 将以下代码复制到IDEA中，思考代码为什么会编译报错。然后尝试修改代码，让代码不要报错。

   ``` java
   class  Student{
       int age;
       String name;
       int var;
       public Student(){
       }
       public Student(int age){
           this.age = age;
       }
       public Student(String name){
           this(var);
           this.name = name;
       }
   }
   ```

   > 答：结果如下：值var尚未加载进堆区
   >
   > ```java
   > class  Student{
   >     int age;
   >     String name;
   >     static int var;
   >     public Student(){
   >     }
   >     public Student(int age){
   >         this.age = age;
   >     }
   >     public Student(String name){
   >         this(var);
   >         this.name = name;
   >     }
   > }
   > ```

4. 将以下代码复制到IDEA中，然后分析过程和结果。思考结果为什么会如此，提供必要的文字说明。

   ``` java
   public class Test{
       public static void main(String[] args){
           Father f1 = new Son(1000);
   				Father f2 = new Father();
   				Son s = new Son(1000);
       }
   }
   class Father {
       int i = 10;
       public Father() {
           System.out.println(getI());
       }
       public int getI() {
           return i;
       }
   }
   
   class Son extends Father {
       int i = 100;
       public Son(int i) {
           this.i = i;
       }
       public int getI() {
           return i;
       }
   }
   ```

   > 结果如下：
   >
   > 0
   >
   > 10
   >
   > 0
   >
   > 分析：首先类加载，没有什么影响，调用Son构造方法，调用父类无参构造Father(),输出getI（），此时调用的getI应是Son中方法，Son中的i尚未显式赋值，只有一个默认值0，所以输出0；以此类推

# 简答题

> **以下简答题，直接将答案写在题目下面即可。**（都是一些概念，虽然我们学得是技术，但基本的概念还是需要记忆的）

1. 继承使用什么关键字？继承中的两个类是什么关系？

   答：extends，父子关系

2. Java中一个类能否**直接**继承两个类？Java中一个类就只能有一个父类吗？

   答：不能，只能有一个父类

3. 如果一个类没有明确继承某个类，那么它就没有父类吗？

   答：有，Object

4. A继承B，C也继承B，那么A和C**（血缘上是兄弟姐妹关系）**这两个类**从继承上**来说有什么关系？

   答：没有任何关系

5. 引用数据类型能否发生数据类型转换？如果能发生，前提是什么？

   答：可以发生数据类型转换，向上转型，或者向下转型

6. 从继承方向的角度上，描述引用数据类型的自动类型转换和强制类型转换，并指出它们的特殊叫法。

   答：向上转型，向下转型

7. 描述protected访问权限级别（从同包，不同包的角度回答）

   答：在本类中可以直接使用，在子类中只有子类创建的对象才能使用

8. 私有成员可以被子类继承吗？

   答：可以

9. 构造器能够被子类继承吗？

   答;不能

10. 静态成员可以被子类继承吗？

    答：不能

11. 子类对象隐式初始化的条件是什么？

    答：继承父类，构造方法第一行不是this或者super

12. 描述一下**对象名点访问成员变量**的访问机制

    - 访问范围
    - 访问结果

    从上述两个角度来说明这个机制。

    ​	答：范围由引用确定，结果由对象决定

13. 描述一下**对象名点调用成员方法**的调用机制

    - 调用范围
    - 调用结果

    从上述两个角度来说明这个机制。

    答：范围由引用确定，结果由对象决定

14. 描述一下**方法覆盖/重写**的语法，从以下角度：

    - 访问权限修饰符
    - **返回值类型（重点测试一下）**
    - 方法名
    - 形参列表

    最后一定要使用注解`@Override`来检查方法重写的正确性。

    答：访问权限修饰符只能往松的方向写，返回值类型，方法名一致，形参列表必须不变

15. 哪些方法不能发生方法的重写？

    答：构造方法，静态方法

## 继承基础语法练习

> 明确记住，继承中的两个类应该有is-a关系

```
提供以下两个动物需要描述，请用你的知识来编写代码
猫：姓名，年龄，颜色，可以叫，可以抓老鼠
狗：姓名，年龄，性别，可以叫，可以看门
分析这个案例，设计出合适的继承体系。

最后思考：人类研究出来了机器人，它们也有姓名，年龄，颜色等属性，可以套用本题中的继承体系吗？
```

答：

```java
package com.cs.javaee.extendspractice.gammerpractice;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        Dog dog = new Dog(18,"CoCo","红");
    }
}

class Animal {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void behave(String sound) {
        System.out.println("会"+sound + "叫！");
    }
}

class Dog extends Animal {
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void behave(String sound) {
        super.behave(sound);
    }

    public void skill() {
        System.out.println("会看门");
    }

    public Dog() {

    }

    public Dog(int age,String name,String color){
        super.setAge(age);
        super.setName(name);
        this.setColor(color);
        System.out.println(super.getAge()+"年的"+this.color+super.getName());
        super.behave("狗");
        this.skill();
    }
}

class Cat extends Animal {
    String gender;

    @Override
    public void behave(String sound) {
        super.behave(sound);
    }

    public void skill() {
        System.out.println("捉老鼠");
    }
}
```



## 练习继承中的构造器使用

> 使用**alt  +  insert**快捷键可以快速生成各种类中结构。子类继承父类后，在子类中按**alt + insert**快捷键快速创建构造方法时，第一步会让你选择父类构造器（也就是super(参数)）
>
> 随后才是选择子类自身的成员变量，生成子类构造器。
>
> 当然如果选择使用父类无参构造器，这时`super()`是隐藏的，即子类对象隐式初始化。
>
> 当选择使用子类有参构造器时，这时`super(参数)`必须写在子类构造器第一行，这是子类对象的显式初始化。

```
给出三个层级的继承关系
顶层父类Grandfather，Father继承Grandfather，Son继承Father
成员变量：
Grandfather：gA,a
Father：fA,a
Son：sA,a

成员方法：
Grandfather：testGrand,test
Father：testFather,test
Son：testSon,test

使用快捷键在Son中生成不同的构造方法，调用不同的父类构造器（需要先生成父类相应的构造器）
随后使用多种方式创建Son对象（思考有几种），测试对象名访问成员的机制，理解属性隐藏和方法覆盖
```

## 完成GUI-阶段练习一

> 详见文档说明
