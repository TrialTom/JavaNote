[TOC]

# 简答题

> **以下简答题，直接将答案写在题目下面即可。**（都是一些概念，虽然我们学得是技术，但基本的概念还是需要记忆的）

1. 指出多态的发生条件，并说明不能发生多态的场景。

   答：发生条件：子类继承父类，父类引用指向子类，方法重写

   不能发生的场景：不能继承的类，不能重写的方法

2. 请描述抽象类的成员特点（变量、方法、构造器、代码块等）

   **注：实际上你完全可以用一句话来回答这道题。**

   答：抽象方法必须写在抽象类中，抽象类无法被实例化，其余和普通类没太大区别

3. 请描述接口的成员特点（变量、方法、构造器、代码块等）

   答：接口声明隐含了abstract，

   接口变量都是默认public static final 修饰的全局变量

   接口中的方法在JDK8之前都是只有抽象方法，JDK8以后加入了默认方法和静态方法，

   接口没有构造器，它不需要给成员变量赋值

   没有代码块

4. 总结接口、抽象类、普通类的继承和实现（谁可以继承谁，谁能够实现谁，具体有什么特点等）

   答：继承不能跨越种族，类是单继承，接口是多继承的

   实现仅发生在接口和类之间，类实现接口；若一个类同时发生继承和实现，那么先继承后实现

## 多态语法基础练习

> 牢记多态发生的条件

```
请根据题目，作出合理设计，定义如下类：
父类Person
	属性：String name，int age
	行为：eat();
	
子类SouthPerson
	属性：String name，int age，double salary
	行为：eat()，swim()

子类NorthPerson
	属性：String name，int age，double height
	行为：eat()，drink()

写代码实现，eat()方法的多态效果
	1，人都要吃饭
	2，南方人喜欢吃米饭
	3，北方人喜欢吃面食

最后，在测试类中，编写测试代码，要求进行如下测试：
	1，编写测试方法，	
		要求该方法允许传入SouthPerson对象和NorthPerson对象，并在方法体中调用它们的eat()方法
		方法调用的结果一致吗？
	2，用父类引用指向子类对象的方式创建SouthPerson对象，能否直接访问salary属性和swim()方法？
		如果不能，应该怎么写代码让它能够正常调用？
	3，用父类引用指向子类对象的方式创建NorthPerson对象，能否（直接或写代码）访问salary属性和swim()方法？如果不能，将该对象引用强转为SouthPerson引用，能否成功？为什么？
```

```java
package com.cs.javaee.homwork.polymorphism;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        Person person = new Person();
        person.eat();

        Person southPerson = new SouthPerson();
        southPerson.eat();

        Person northPerson = new NorthPerson();
        northPerson.eat();
        NorthPerson northPerson1 = (NorthPerson) northPerson;
        northPerson1.drink();
    }
}

class Person{
    String name;
    int age;

    public void eat(){
        System.out.println("我要吃饭！");
    }
}

class SouthPerson extends Person{
    double salary;

    @Override
    public void eat() {
        System.out.println("我喜欢吃面食。");
    }
    public void swim(){
        System.out.println("I like swim");
    }
}

class NorthPerson extends Person{
    double height;

    @Override
    public void eat() {
        System.out.println("我喜欢吃米饭。");
    }
    public void drink(){
        System.out.println("I like drink orange.");
    }
}
```



## 抽象类基础语法练习

> 完成抽象类的基础语法练习，按照说明操作即可。

```
定义抽象类A，抽象类B继承A，普通类C继承B。
A类中，定义成员变量a赋值为10，抽象showA方法
B类中，定义成员变量b赋值为20，抽象showB方法
C类中，定义成员变量c赋值为30，重写showA方法打印a，重写showB方法打印b，定义showC方法，打印c

然后在测试类中，创建C类的对象，调用showA方法，showB方法，showC方法。
然后查看方法调用结果，思考为什么会出现这种现象。
```

```java
package com.cs.javaee.homwork.abstractpractice;


import org.junit.Test;

/**
 * @author Lenovo
 */

public class Demo {
    public static void main(String[] args) {
        C c = new C();
        c.showA();
        c.showB();
        c.showC();
    }
}

abstract class AbstractA{
    int a =10;

    public abstract void showA();
}

abstract class AbstractB extends AbstractA{
    int b = 20;

    public abstract void showB();
}

class C extends AbstractB{
    int c = 30;

    @Override
    public void showA() {
        System.out.println(a);
    }

    @Override
    public void showB() {
        System.out.println(b);
    }

    public void showC(){
        System.out.println(c);
    }
}
```



## 接口与抽象类基础语法练习

> 从实际角度出发，接口和抽象类的差异是十分明显的。可以参考完成以下案例：

```
学生和老师都有共同的属性: name、gender、age
共同的行为：eat() sleep() 
	注：虽然行为一致，但实现会不同。
现在为了提升自身素质，大家都需要额外进行技能的学习：学生需要增强实践动手能力，老师需要增强语言能力。
请定义抽象类和接口，描述以上体系。

然后用以下方式进行测试：
	1，用不同的父类指向不同的子类对象，理解方法调用时“编译时看左边”
	2，用这些引用调用方法，理解方法调用时“运行时看右边”
```

```java
package com.cs.javaee.homwork.implpractice;

import java.util.Scanner;

/**
 * @author Lenovo
 */
public class Demo {
    public static void main(String[] args) {
        Play student = new Student();
        student.comeOn();
        Person student1 = new Student();
        student1.eat();
        student1.sleep();
    }
}

interface Play{
    void comeOn();
}
interface Enable{
    void language();
}
abstract class Person{
    int name;
    int gender;
    int age;
    abstract void eat();
    abstract void sleep();
}
class Student extends Person implements Play{
    @Override
    void eat() {
        System.out.println("吃吃吃，疯狂吃。");
    }

    @Override
    void sleep() {
        System.out.println("睡睡睡，疯狂睡。");
    }

    @Override
    public void comeOn() {
        System.out.println("玩玩玩，疯狂玩。");
    }
}
class Teacher extends Person implements Enable{
    @Override
    void eat() {
        System.out.println("细嚼慢咽");
    }

    @Override
    void sleep() {
        System.out.println("下班，睡觉。");
    }

    @Override
    public void language() {
        System.out.println("教书育人。");
    }
}
```

