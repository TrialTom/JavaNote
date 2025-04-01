# String

> String概述

- 在Java语言中，所有类似“ABC”这样用双引号引起来的字符串，都是String类的对象

    所以这里需要额外注意一下，"hello"既是一个字面值常量，同时也是一个Java对象

- String类位于java.lang包下，是Java语言的核心类（几乎找不到一个比它更常见、常用的类了~）

- String类提供了字符串表示、比较、查找、截取、大小写转换等各种针对字符串的操作





> 构造方法（constructor）

- 使用String类时尤其要注意导包问题，导错包会直接导致不能运行main方法
- 使用构造方法创建String是一种不太常见的方式，仅供了解

```Java
//创建空字符串对象，需要注意的是null != ""
public String()
//把字节数组中的元素转换成字符串，字节数组中可以是字符，也可以是ASCII码值
public String(byte[] bytes)
//同上，不过指定了开始下标和长度
public String(byte[] bytes,int offset,int length)
//同字节数组
public String(char[] value)
//同上，不过指定了开始下标和长度
public String(char[] value,int offset,int count)
//套娃
public String(String original)
```



以上，String的简单概述就完了，接下来更重要的是了解String的特点~

---

## String相关

> String类主要有两个特点：

1. String对象不可变
2. 字符串常量池共享字符串对象

### String对象不可变

> 先看引例，需求是：

```
请键盘录入一个任意字符串s，并用一个temp字符串引用也指向它。
这个时候修改temp字符串的内容，请问s字符串的内容会随之改变吗？
```

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

<img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202110180106030.png" alt="image-20210908211936309" style="zoom:50%;" />

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

<img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202110180106080.png" alt="image-20210908213242864" style="zoom:50%;" />

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

> 首先仍然是一个引例，请看以下代码：

```java
String s1 = "我是神";
String s2 = "我是神";
String s3 = new String("我是神");
```

这三个字符串对象引用，如果互相用双等号连接，结果分别是什么呢？又为什么呢？

> 什么是字符串常量池？

字符串的分配和其他对象分配一样，是需要消耗高昂的时间和空间的，而且字符串使用的非常多

JVM为了提高性能和减少内存的开销，在实例化字符串对象的时候进行了一些优化：

**使用字符串常量池。**首先要明确，Java的双引号引起来的字面值常量字符串，它们都是对象。这些对象比较特殊，程序在编译时期就能确定它们的值，编译时期就能作为对象加入“静态常量池”，在类加载时期就能够读取进字符串常量池，从而完成以下操作：

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

正是因为字符串是不可变的，且具有字符串常量池的概念，所以字符串对象是可以共享的。字符串是JVM堆内存中最多的对象，尽量减少它们的创建，能够节省堆内存空间，也能提升JVM运行效率。

除此之外，不可变的对象往往都会更安全（在网络传输种不至于随意被更改），特别的String对象不可变后效率提升（字符串不可变不需要频繁计算hashCode()值，所以String是Map中最常见的key）



> 小试牛刀

- 看程序说结果

  - ```Java
    String s = "hello";
    s += "world";
    System.out.println(s);
    ```

- 下面两种赋值方式有什么区别？

  - ```Java
    String s1 = "我是猪";
    String s = new String("我是猪");
    ```
  
- 看程序说结果

  - ```java
    String s1 = new String("hello");
    String s2 = new String("hello");
    System.out.println(s1 == s2);
    System.out.println(s1.equals(s2));
    
    String s3 = new String("hello");
    String s4 = "hello";
    System.out.println(s3 == s4);
    System.out.println(s3.equals(s4));
    
    String s5 = "hello";
    String s6 = "hello";
    System.out.println(s5 == s6);
    System.out.println(s5.equals(s6));
    ```



- 看程序说结果

  - ```Java
    String s1 = "hello";
    String s2 = "world";
    String s3 = "helloworld";
    System.out.println(s3==(s1+s2));
    System.out.println(s3.equals(s1+s2));
    
    System.out.println(s3==("hello"+"world"));
    System.out.println(s3.equals("hello"+"world"));
    ```



>  使用加号对字符串进行拼接操作，会有下述两种结果
>
>  - 直接在常量池中创建新的拼接对象
>  - 在堆上创建新的拼接对象
>
>  经过测试我们发现
>
>  - 当参与字符串拼接的两个字符串中，至少有一个是以引用变量的形式出现时
>   - 必然会在堆上创建新的字符串对象
>      - 原因是变量参与了运算，无法在编译时期确定其值，就不能在编译时期加入常量池
>  - 只有参与字符串拼接运算的两个字符串，都是字符串字面值常量的时候
>   - 此时不会在堆上创建新的字符串对象，而是在常量池中直接拼接创建对象
>   - 如果已存在，则不创建新的对象

