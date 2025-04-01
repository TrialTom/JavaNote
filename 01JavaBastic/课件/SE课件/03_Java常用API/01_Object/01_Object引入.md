###### <sub><font color = orange>JAVASE基础教程</font></sub><br />——<br /><sup><font color=white>卷1</font></sup><font color=white>Object类</font><br/><sup><sub><font color=cyan>节1</font></sub><font color=cyan>Object引入</font></sup><br/><br/>	``#最新版本|V1.0#(purple) ``<br/>**长风**<br/>*COPYRIGHT ⓒ 2021. 王道版权所有*

[TOC]

# API的概述

> `>(green)`
>
> API，全称`Application Programming Interface`，也就是应用程序编程接口。这个接口，我们在接口那一章节中，已经说过了。此接口并不是Java语法中的`interface`，而是<font color=red>**指一些已经预先定义好的，暴露给外界使用的方法或者工具。**</font>
>
> API的学习，其实主要就是学习方法的调用，本质上非常简单。到这里，我们面向对象当中，繁杂的概念还写不出几行代码的窘境就会差不多结束了。学习一些API后，我们就可以写出一些代码了。
>
> API的作用是：开发者可以在不关注具体实现细节的前提下，使用这些已经预先定义好的类和工具实现自己的需求。
>
> 像官方JDK中的API，我们可以通过官方JDK文档去学习，或者百度搜索一下去学习。而以后公司，程序员之间互相交流接口，就需要依赖于公司的API文档。所以写文档，也是程序员的必备技能。

# Object类概述

> `>(green)`
>
> Object类，虽然我们没有详细了解，但我们知道了：Object类是所有类继承层次的祖先类，Java中所有类（包括数组）都直接或者间接的继承自该类，都实现了该类的方法。
>
> 自定义类时，我们并不需要特别的标注`extends Object`，这是一个隐式的继承。如果一个类没有明确的指出它的父类，Object类就默认是这个类的父类，`extends Object`则已经隐含了，不需要写出来。
>
> **Object是没有成员变量定义的，并且由于子类对象的隐式初始化，Object类有且仅有一个默认提供的无参构造方法。所以我们学习Object类，主要关注它的成员方法。**

# Object类的成员方法

> `>(red)`
>
> Object的成员方法中，提供给外界访问权限的有：
>
> ###### Object类的成员方法
>
> ``` java
> public final Class getClass()
> public String toString()
> public boolean equals(Object obj)
> public int hashCode()
> protected void finalize()
> protected Object clone()
> ```
>
> 注：像`notify()`、`wait()`等成员方法，它们虽然也是public修饰的，但它们都和Java的线程有关系，多线程中再学习。
>
> ---
>
> 逐一解释：
>
> 1. `getClass()`方法是Java反射的前置知识点。
> 2. `toString()`方法提供了将对象字符串化的方式。
> 3. `equals(Object obj)` <span style=color:red;background:yellow>**方法用于判断对象相等，非常重要。**</span>
> 4. `hashCode()`**方法用于获取哈希值，在集合使用中非常重要。**
> 5. `finalize()`方法，仅作了解，没有实际意义。
> 6. `clone()`方法，克隆，一种创建对象的新方式。
>
> 下面，我们按照顺序，逐一学习其中的方法。

###### The End