# 补充讲解

> 尝试讲解几个比较难以理解的知识点，加深印象

[TOC]

## String相关

> String类主要有两个特点：

1. String对象不可变
2. 字符串常量池共享字符串对象

### String对象不可变

> 什么是String对象不可变？

```
《Effective Java》一书中这么解释，不可变对象(Immutable Object)：
		对象一旦被创建后，对象所有的状态及属性在其生命周期内不会发生任何变化。
```

- 从对象的角度来看，**某个String对象创建完成后，就不能再改变它对象的状态**，这就是所谓的对象不可变
  - 对象的状态不改变指的是对象中成员变量不能改变，这包括
    - 基本数据类型成员变量的取值不能改变
    - 引用数据类型成员变量的引用不能指向新的对象
    - 引用数据类型成员变量的引用，所指向的对象的状态也不可改变

当然这里面有个细节，要搞清楚，**是String对象不可变，而不是String引用不可变**

比如下列代码：

```Java
String s = "hello";  
System.out.println("s = " + s);  
  
s = "hello 张三";  
System.out.println("s = " + s);  
```

> 输出结果显然应该是：
>
> s = hello
> s = hello 张三

这里我们要明确"hello"或者“hello 张三”都是String对象，它们创建完成后，对象就不可变了

而s只是一个引用，引用仍然可以指向不同的String对象

如下图所示（引用s两次指向了不同的对象，但两个String对象都是不可变的）：

<img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202110180105175.png" alt="image-20210908211936309" style="zoom:50%;" />

---



- 从具体String实现代码上看，**Java8中String类的主要成员变量就剩下了两个：**

```java 
/** The value is used for character storage. */  
private final char value[];

/** Cache the hash code for the string */  
private int hash;
```

> 不难看出

String类字符串实际上就是对字符char数组的封装，用一个final修饰的char类型value数组存放字符

而成员变量hash，是该String对象的哈希值的缓存，这里可以放到后面再谈

实际上，一个String对象的内存图如下所示：

<img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202110180105517.png" alt="image-20210908213242864" style="zoom:50%;" />

一个String对象中，value数组是final修饰的，意味着不能指向新的数组，这个时候：

- 数组本身长度不能改变，如果想要依赖于修改value数组内容直接改变String字符串局限性太大
- value数组是private修饰的，在String外部无法修改并且String类也没有提供方法修改value数组

以上，不能通过修改value数组来修改String中表示的字符串，String对象一旦创建出来就是不可变的

> 补充说一点：

String对象不可变，并不是绝对的（不是天下无敌）

实际上，

我们通过“**反射**”破解value数组的私有权限，然后修改value数组内容，但是我们一般不这么做（除非吃饱了撑的）

String对象不可变仍然是正确的

### 字符串常量池

> 什么是字符串常量池？

字符串的分配和其他对象分配一样，是需要消耗高昂的时间和空间的，而且字符串使用的非常多

JVM为了提高性能和减少内存的开销，在实例化字符串对象的时候进行了一些优化：

**使用字符串常量池。**

```
每当创建字符串常量对象时，JVM会首先检查字符串常量池，如果该字符串对象已经存在常量池中，那么就直接返回常量池中的实例引用。如果字符串对象不存在于常量池中，就会实例化该字符串并且将其放到常量池中。
```

而且由于String对象不可变，字符串常量池中一定不存在两个相同的字符串对象。

```Java
String s1 = "hello world!";
String s2 = "hello world!";
System.out.println(s1 == s2);
```

> 以上Java代码，运行结果显然是true，字符串常量池中的"hello world!"对象只存在一份

两个细节：

1. 字符串常量池位于堆中
2. new创建String对象在堆上，变量拼接字符串，新对象创建也是在堆上

以下代码：

```Java
String s1 = "Hello";
String s2 = "Hello";
String s3 = "Hel" + "lo";
String s4 = "Hel" + new String("lo");
String s5 = new String("Hello");
String s7 = "H";
String s8 = "ello";
String s9 = s7 + s8;
          
System.out.println(s1 == s2);
System.out.println(s1 == s3);
System.out.println(s1 == s4);
System.out.println(s1 == s9);
System.out.println(s4 == s5);
```

程序输出的结果应当是：

> true
> true
> false
> false
> false

### intern方法

> intern是String类的一个成员方法，以Java8版本的intern方法实现为标准

它是一个native方法，该方法首先会从字符串常量池中检测该对象是否已存在：

- 如果存在就返回字符串常量池中，该对象的引用
- 如果不存在就将存在于堆上的字符串对象的引用存入常量池（注意不会在常量池中创建新对象）

例如：

```Java
String s1 = "Hello";
String s2 = new String("Hello");
String s3 = new String("World");
String s4 = s2 + s3;
System.out.println(s1 == s2.intern());
System.out.println(s2 == s2.intern());
System.out.println(s3 == s3.intern());
System.out.println(s4 == s4.intern());
```

程序运行的结果应该是

> true
> false
> false
> true



经典面试题（来自《深入理解java虚拟机 第二版》）：

```Java
String str1 = new StringBuilder("计算机").append("软件").toString();
System.out.println(str1.intern() == str1);

String str2 = new StringBuilder("ja").append("va").toString();
System.out.println(str2.intern() == str2);
```

