[TOC]



# 一、String概述

## 1.基本介绍

- String代表字符串，Java程序中的所有字符串字面值都作为此类的实例实现
- 在Java.lang包下，是Java核心类，但是它不是基本数据类型

## 2.构造方法

> 掌握如下即可：
>
> - public String();
>   - 空字符串
> - public String(byte[] bytes);
>   - 利用字节数组，创建字节数组所表示的字符串
> - public String(byte[] bytes,int offset,int length);
>   - 利用字节数数组的一部分，创建字符序列。
> - public String(char[] value);
> - public String(char[], int offset, int count);
> - public String(String original);

# 二、String特点

## 1.String对象不可变

1. 简述：String对象一旦被创建后，对象所有的的状态及属性在其生命周期内不会发生任何变化

   > 对象的状态不改变指的是对象中成员变量不能改变，包括：
   >
   > - 基本数据类型成员变量的取值不能改变
   > - 引用数据类型成员变量的引用不能指向新的对象
   > - 引用数据类型成员变量的引用，所指向的对象的状态也不可改变。

2. 原因：

   - String是一个final类，代表不可变的字符序列
   - 字符串是常量，用双引号引起来，他们的值在创建之后不可更改
   - String对象的内容是存储在字符数组被final修饰的value[]中的

## 2.字符串常量池

1. 由来：字符串分配和其他对象分配一样，是需要消耗高昂的时间和空间的，而且字符串使用的非常多，JVM为了提高性能和减少内存的开销，在实例化字符串对象的时候进行了一些优化：**使用字符串常量池**

2. 具体操作：

   > 每当创建字符串常量对象时，JVM会首先检查字符串常量池，
   >
   > - **<span style="background:yellow;color:red">如果该字符串对象已经存在常量池中，那么就直接返回常量池中的实例引用。</span>**
   > - **<span style="background:yellow;color:red">如果字符串对象不在常量池中，就会实例化该字符串并且将其放到常量池中。</span>**

3. 补充：JDK1.8之前字符串常量池放在方法区中，之后没有方法区（made space），字符串常量池放到了堆中。

## 3.String两种实例化方式的区别

1. 两种方式
   - 直接赋值 `String s = "abc";`
   - 通过构造方法赋值 `String s = new String("abc");`
2. 区别：
   - 直接赋值，常量池中没有，创建一个放入常量池中，常量池中有，直接返回常量池中的引用
   - 构造方法时，在堆上创建字符串对象，如果常量池中有，不在常量池中创建；如果没有，创建一个。

## 4.String使用

1. 字符串的比较
   - ==,对于基本数据类型而言，比较的是内容；对于引用数据类型而言，比较的是引用变量，即所指向的地址
   - equals方法是Object的方法，默认是比较2个对象的地址，但String中重写了equals方法。
   
2. 字符串的拼接

   - 直接在常量池中创建新的拼接对象

   - 在堆上创建新的拼接对象

     - 当参与字符串拼接的是两个字符串，只要有1个引用变量的形式出现时，则会在堆上创建新的字符串对象

       > 原因：因为参与了运算，无法在编译时期确定其值，就不能在编译时期加入常量池

     - 只有参与字符串拼接的2个字符串都是字面值常量的时候，则会在常量池创建拼接对象

# 三、String API

## 1.判断功能

```java
// 用来比较字符串的内容，区分大小写
boolean equals(Object obj);

// 忽略字符串大小写比较字符串内容，常用于比较网址URL
boolean equalsIgnoreCase(String str);

//判断当前字符串对象是否包含了目标字符串的字符序列
boolean contains(String str);

//判断当前字符串对象，是否以目标字符串的字符序列开头
boolean startsWith(String str);

//判断当前字符串，是否以目标字符串对象的字符序列结尾，常用于确定文件后缀名格式
boolean endsWith(String str);

//判断一个字符串，是不是空字符串
boolean isEmpty();
```

## 2.获取功能

```java
//获取字符串对象代表字符序列中，指定位置的字符
char charAt(int index);

//在当前字符串对象中查找指定的字符，如果找到就返回字符首次出现的位置，如果没有找到就返回-1
int indexOf(int ch);

//指定从当前字符串对象的指定位置开始，查找首次出现的指定字符的位置
int indexOf(int ch, int fromIndex);

//查找当前字符串中，目标字符串首次出现的位置
int indexOf(String str);

//指定，从当前字符串对象的指定位置开始，查找首次出现的指定字符串的位置(不是相对位置)
int indexOf(String str, int fromIndex);

//返回字符串，该字符串只包含当前字符串中，从指定位置（包含指定位置字符）开始到结束的那部分字符串(分割)
String substring(int start);

String substring(int start, int end);
```

## 3.转换功能

```java
//获取一个用来表示字符串对象字符序列的，字节数组(Ascall码值)
byte[] getBytes();

//获取一个用来表示字符串对象字符序列的，字符数组
char[] toCharArray();

//把字符数组转换成字符串
static String valueOf(char[] chs);

//把各种基本数据类型和对象转换成字符串
static String valueOf(int i /double ...);

//把字符串全部转化为小写
String toLowerCase();
//大写
String toUpperCase();

//字符串拼接，作用等价于 + 实现的字符串拼接
String concat(String str);
```

## 4.其他功能

```java
// 1.替换功能
// 用new替换old
String replace(char old, char new);
String replace(String old, String new);

// 2.截取功能
// 返回一个新的Sting，开头到结束
public String substring(int start);
public String substring(int start, int end);

// 在新的字符串中，去掉开头和结尾的空格字符
String trim();

// 3.分隔功能
// 将字符串按照符号分隔成字符串数组
String[] split(String re);

// 4.比较功能
int compareTo(String str);
int compareToIgnoreCase(String str);
```

>字符串的比较
>
>1. 如何比较？
>   - 按照字典序，比较字符串的大小
>2. compareTo方法
>   - 字符串长度一样，逐一比较返回第一个不一样字符的编码值的差值
>   - 字符串长度不一样，并且前面的字符都相同，返回数组长度的差值
>   - 长度一样，逐位字符也一样，返回0，表示相等。

# 四、自然排序

## 1.Comparable接口

1. 概述：**一个类实现了Comparable接口，就可以对这个类的对象进行排序**。称之为自然排序，其中的comparaTo方法被称为它的自然比较方法，实现此接口的类，其对象数组（array）或者对象容器（collection）就可以通过Arrays.sort()或Collections.sort()进行自动排序。

2. 排序规则：

   > 对于实现该接口的A类来说，其对象a1.compareTo（a2）方法返回值
   >
   > - 小于0，a1在自然排序中处于a2前面的位置
   > - 大于0，a1在自然排序中处于a2后面的位置
   > - 等于0，不排

## 2.Comparator接口（比较器）

1. 概述：带比较器的Arrays.sort方法，即`sort(T[] a,Comparator<? super T> c)`。根据指定比较器产生的顺序对指定对象数组进行排序。其中Comparator接口的实现类对象就是比较器，该对象通过compare方法传入比较的规则。
2. 三种方式：
   - 手写接口类实现
   - 匿名内部类
   - lambda表达式

# 五、StringBuffer（可变长字符串）

## 1.构造方法

```java
//默认容量是16
public StringBuffer();

//可以指定容量
public StringBuffer(int capacity);

//str的长度+16
public StringBuffer(String str);
```

##  2.成员方法

```java
//添加功能
public StringBuffer append(String str);
public StringBuffer insert(int offset,String str);

//删除功能
public StringBuffer deleteCharAt(int index);
public StringBuffer delete(int start, int end);

//替换功能
public StringBuffer replace(int start, int end, String str);

//反转功能
public StringBuffer reverse();

```

## 3.常见问题

1. String如何转换为Stringbuffer

   `StringBuffer stringBuffer = new StringBuffer();`

2. StringBuffer如何转换为String

   `String s1 = stringBuffer.toString();`

3. String与StringBuffer的区别

   - StringBuilder类和StringBuffer类的对象能够被多次的修改，并且不产生新的未使用对象，不会产生效率问题和空间浪费问题
   - StringBuffer是线程安全的，StringBuilder是线程不安全的（synchronized关键字加锁）
     - StringBuilder 的效率要比StringBuffer效率更高，单线程的程序推荐使用StringBulider
     - 在多线程的程序中，应该优先考虑使用StringBuffer，安全性更重要。

# 六、intern方法

## 1.概述：

intern方法是一个native方法，该方法首先会从字符串常量池中检测该对象是否已存在

- 如果存在就返回字符串常量池中该对象的引用
- 如果不存在，就将存在于堆上的字符串对象的引用存入常量池

