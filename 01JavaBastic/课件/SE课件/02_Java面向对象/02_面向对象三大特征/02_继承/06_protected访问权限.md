###### <sub><font color = orange>JAVASE基础教程</font></sub><br />——<br /><sup><font color=white>卷3</font></sup><font color=white>继承</font><br/><sup><sub><font color=cyan>节6</font></sub><font color=cyan>protected访问权限</font></sup><br/><br/>	``#最新版本|V2.0#(purple) ``<br/>**长风**<br/>*COPYRIGHT ⓒ 2021. 王道版权所有*

[TOC]

# 概述

> `>(green)`
>
> 关于访问权限的概念，我们在面向对象基础时，已经提到过一次了。但那时，我们还没有继承的概念，所以protected修饰符没有讲解。这里我们来补充讲解一下。
>
> 首先，我们回顾一下Java当中类中成员和构造器的四个访问权限等级：
>
> 1. private：只能够在同一类中能够访问，私有的，外面谁都不能用。
> 2. （缺省的）不写任何关键字：同一包中的子类或者其它类能够访问，同包中都可以使用。
> 3. protected：**不同包的子类能够访问。**
> 4. public：不同包的其他类能够访问，相当于没有做访问权限。
>
> > ###### 成员访问权限修饰符
> >
> > |              |            public            |          protected           |           （缺省）           |           private            |
> > | ------------ | :--------------------------: | :--------------------------: | :--------------------------: | :--------------------------: |
> > | 同一类中     | <font color=red>**✔**</font> | <font color=red>**✔**</font> | <font color=red>**✔**</font> | <font color=red>**✔**</font> |
> > | 同一包其他类 | <font color=red>**✔**</font> | <font color=red>**✔**</font> | <font color=red>**✔**</font> |                              |
> > | 不同包子类   | <font color=red>**✔**</font> | <font color=red>**✔**</font> |                              |                              |
> > | 不同包其他类 | <font color=red>**✔**</font> |                              |                              |                              |
>
> 注：protected关键字是不能修饰普通类的，但是嵌套定义的内部类可以。（后面再讲）

# 何为不同包子类能够访问？

> `>(red)`
>
> protected关键字的使用定义上，很含糊的说了，protected修饰的成员在不同包的子类中可以访问，但实际上访问的方式仍然可以细分：
>
> 1. 不同包的子类中，创建父类对象，用父类对象访问protected成员。
> 2. 不同包的子类中，创建该类的其他子类对象（"兄弟姐妹对象"），用这个其他子类对象访问父类的protected成员。
> 3. 不同包的子类中，创建子类对象，用子类对象访问protected成员。
>
> 我们可以创建以下Java类，来进行测试操作：
>
> > 1. 在包名为**one**的包中创建类：
> >    1. **public class CurrentClazz**，即当前类，是存放protected修饰的成员变量的父类。
> >    2. **public class SamePackageAnotherClazz**，即同包下非子类。
> >    3. **public class SamePackageSonClazz**，即同包下子类。
> > 2. 在包名为**another**的包中创建类：
> >    1. **AnotherPackageAnotherClazz**，即不同包下非子类。
> >    2. **AnotherPackageSonClazz**，即不同包下子类。
>
> 类定义完毕后，就可以逐一测试，能否访问。

# 结论

> `>(red)`
>
> protected修饰的成员，在同类、同包下是可以随意访问的。
>
>  <span style=color:red;background:yellow>**但是在不同包下，必须在子类中，创建子类自身对象，才能够访问它从父类那里继承过来的protected成员，其它方式创建对象都不可以访问。**</span>
>
> ---
>
> 实际代码中，怎么查看protected成员的访问权限呢？
>
> 1. 先看是否同包中访问，只要同包就可以任意访问。
> 2. 如果是非同包，一定要是子类中，才可以访问。而且必须是<font color=red>**创建子类自身对象**</font>，才可以访问它继承自父类的protected成员。

# 为什么这么设置protected访问权限？

> `>(green)`
>
> 在面向对象访问权限控制的整个体系中，实际上如果没有继承，那么只需要两个访问权限就足够了：要么是给别人用的public，要么是不给别人用的private。
>
> 但是有了继承后，**如果类中的某个成员，非常有价值，我们希望这个成员总是被子类使用，而不会被滥用**，出于保护这样一个成员的目的，protected就有意义了。
>
> 被protected修饰的成员，在不同包下（正常情况下，代码在使用时都不会同包），一定能够保证该成员被子类自身所使用：
>
> 1. 不能用父类的，创建父类对象访问不到。
> 2. 不能用"兄弟姐妹"的，创建非自身的其它子类对象，也访问不到。
>
> 这样就充分保证了<font color=red>**子类拥有对自己继承的protected成员最大的权限。**</font>想一想，将来有一天，你的财产也总是希望被亲近的人继承吧？而且给它最大的控制权限吧？
>
> ---
>
> 注：既然子类有最大的控制权限，那么如果它愿意上交国家也是可以的。在子类中，可以重写从父类继承过来的方法的访问权限，可以选择从protected改写成public，这仍然属于方法的重写。（后面会讲）

# 经典案例

> `>(red)`
>
> Java所有类都直接或间接地继承了Object，也会同时继承它当中的一个protected修饰的成员方法clone()，那么在下面代码中分析使用对象调用clone()方法：
>
> ###### protected经典案例
>
> ``` java
> public class Demo {
>     public static void main(String[] args) throws CloneNotSupportedException {
>         // 问题1: 这里能否创建Student对象,来调用clone方法?
>         /*
>             Student类是Object类的子类,它继承了clone()方法
>             但是它和Object类是非同包的关系
>             不同包下: 必须在子类中,创建子类自身对象,才能够访问父类中继承过来的受保护成员
>             在当前Demo类当中,叫在"兄弟姐妹"类中,创建对象,很明显不能访问,因为没有权限
>          */
>         Student s = new Student();
>         // 'clone()' has protected access in 'java.lang.Object'
>         // s.clone(); 不行
> 
>         // 问题2: 这里能够调用谁的对象的clone()方法;
>         Demo d = new Demo();
>         d.clone();
> 
>         // 当重写protected访问权限后,可以随意访问
>         Teacher t = new Teacher();
>         t.clone();
>     }
> }
> class Student{
>     public void test() throws CloneNotSupportedException {
>         // 只能创建Student对象,来访问Student从Object类继承过来的clone方法
>         // new Demo().clone(); 不行
>         new Student().clone();
>     }
> }
> class Teacher{
>     @Override
>     protected Object clone() throws CloneNotSupportedException {
>         return super.clone();
>     }
> }
> ```
>
> 结论其实还是那句话：不同包下，必须在子类中，创建子类自身对象，才能访问从父类那里继承过来的protected成员。
>
> 上述代码还演示了方法重写——修改方法的访问权限，作为了解，后面会详细讲解。

###### The End
