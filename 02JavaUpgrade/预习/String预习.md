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

<img src="https://gitee.com/uncleTao/picture-bed/raw/master/img/202110180106030.png" alt="image-20210908211936309" style="zoom:50%;" />

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

**使用字符串常量池。**首先要明确，Java的双引号引起来的字面值常量字符串，它们都是对象。这些对象比较特殊，程序在编译时期就能确定它们的值，在类加载时期就能够读取进字符串常量池，从而完成以下操作：

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
>     - 原因是变量参与了运算，无法在编译时期确定其值，就不能在编译时期加入常量池
>  - 只有参与字符串拼接运算的两个字符串，都是字符串字面值常量的时候
>   - 此时不会在堆上创建新的字符串对象，而是在常量池中直接拼接创建对象
>   - 如果已存在，则不创建新的对象





# String API

> String是开发中最常见的需要操作的对象，没有之一
>
> String天天见，所以我们要对String的操作至少有个脸熟，大概知道String类能做什么
>
> 以下方法不要求都背住，但是应该能够了解有这 些方法，并可以在实际开发中随着使用而熟记





> String类的判断功能

```java 
//用来比较字符串的内容，注意区分大小写
boolean equals(Object obj)
    
//忽略字符串大小写比较字符串内容，常见用于比较网址URL
boolean equalsIgnoreCase(String str)
    
//判断当前字符串对象是否包含，目标字符串的字符序列，常见用于确定是否有盗链行为
boolean contains(String str)
    
//判断当前字符串对象，是否已目标字符串的字符序列开头
boolean startsWith(String str)
    
//判断当前字符串，是否以目标字符串对象的字符序列结尾，常用于确定文件后缀名格式
boolean endsWith(String str)
    
//判断一个字符串，是不是空字符串
boolean isEmpty()
```





> String类的获取功能

```Java
// 获取当前字符串对象中，包含的字符个数
int length()  
    
//获取字符串对象代表字符序列中，指定位置的字符
char charAt(int index) 
    
//在当前字符串对象中查找指定的字符，如果找到就返回字符，首次出现的位置，如果没找到返回-1
//也可以填字符
int indexOf(int ch) 
    
//指定从当前字符串对象的指定位置开始，查找首次出现的指定字符的位置，(如果没找到返回-1)
//可以填入字符
int indexOf(int ch,int fromIndex) 
    
//查找当前字符串中，目标字符串首次出现的位置(如果包含)，找不到，返回-1
//这里的位置是指目标字符串的第一个字符,在当前字符串对象中的位置
int indexOf(String str)

//指定，从当前字符串对象的指定位置开始,查找首次出现的指定字符串的位置(如果没找到返回-1)
//这里的位置是指目标字符串的第一个字符,在当前字符串对象中的位置
int indexOf(String str,int fromIndex) ，

//返回字符串，该字符串只包含当前字符串中，从指定位置开始(包含指定位置字符)到结束的那部分字符串
String substring(int start) 
    
//返回字符串，只包含当前字符串中，从start位置开始(包含)，到end(不包含)指定的位置的字符串
String substring(int start,int end) 
```





> String类的转换功能

```Java
//获取一个用来表示字符串对象字符序列的，字节数组
byte[] getBytes()
    
//获取的是用来表示字符串对象字符序列的，字符数组
char[] toCharArray() 

//使用指定字符集，将字符编码成字节序列，并将结果存储到一个新的 byte 数组中
getBytes(String charsetName) 

//将字符从此字符串复制到目标字符数组
getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
    
//把字符数组转换成字符串
static String valueOf(char[] chs)

//把各种基本数据类型和对象转换成字符串
static String valueOf(int i/double...)


//把字符串全部转化为小写
String toLowerCase() 
    
//把字符串全部转换为大写
String toUpperCase()

//字符串拼接，作用等价于 + 实现的字符串拼接
String concat(String str) 
```



> String类的其他功能

- 替换功能

  - ```Java
    // 在新的字符串中，用新(new)字符，替换旧(old)字符
    String replace(char old,char new)
        
    //在新的字符串中，用新的字符串(new), 替换旧(old)字符串
    String replace(String old,String new)
    ```

  - 需要注意的是，替换不是在原对象上替换，而是创建了新的对象

- 去除空格字符

  - ```Java
    //在新的字符串中，去掉开头和结尾的空格字符
    String trim() 
    ```



- 比较功能

  - ```Java
    String类的比较功能
    int compareTo(String str)
    int compareToIgnoreCase(String str)
    ```

  - 详解字符串如何能够比较大小？怎么比较呢？

  - 首先要明确字符串是由字符组成的，比较字符串的大小，首先要知道字符的大小如何比较

    - 单个字符如何比较大小呢？
      - 单个字符按照字典顺序去比较，实际上比较基于Unicode的编码值
      - 处在字典后面的字符编码值要大，就认为这个字符大

  - String类的compareTo()方法在比较时，通过查看源码，不难得出它是这么比较的

    - 从左往右逐个比较对应字符，如果出现某单个字符不相同，那么返回它们的编码值之差
      - 例如"bcd"和"abc"比较，直接在第一位b就能够确定，b的编码值比a大1
      - 所以该方法返回1，大于0的数
      - 认为"bcd"比"abc"大
    - 如果逐个字符都相同，则返回它们的长度之差
      - 例如"abc"和"abcd"比较，abc都是相同的，得不出结果
      - 由于"abcd"比"abc"长1位
      - 所以该方法返回-1，小于0的数
      - 认为"abc"比“abcd”小
    - 只有完全相同的字符串，才会返回0，认为它们相等
      - 比如"abc"和"abc"方法就会返回0
      - 认为它两相等



## Comparable接口

> 一个类实现了Comparable接口，就可以对这个类的对象（容器或集合）进行排序
>
> 称之为自然排序，其中的compareTo方法被称为它的自然比较方法

- 实现此接口的类，其对象数组（array）或对象容器（collection）
  - 就可以通过**Arrays.sort()**或**Collections.sort()**进行自动排序
- 对于实现该接口的A类来说，其对象a1.compareTo(a2)方法返回值
  - 小于0，表示a1对象小于a2，在自然排序中处于前面的位置
  - 大于0，表示a1对象大于a2，在自然排序中处于后面的位置
  - 等于0，表示a1对象等于a2
- 除了compareTo方法，类中还有equals方法判断两个对象是否相等
  - 建议这两个方法同true同0，同false非0，这样从逻辑上更顺畅
    - 比如一个有序集合，compareTo认为不相等，equals方法认为相等，可能导致集合添加元素失败
  - 实际上，所有实现 Comparable 的 Java 核心类都具有与 equals 一致的自然排序
  - 两个方法都用成员变量的取值重写即可



## Comparator接口

> 一个类实现了Comparable接口，往往需要同时重写equals方法，这会增加一些思考量
>
> 并且也不是什么时候都能够去随心所欲的修改源码，或者实现一个接口
>
> 假如仅仅只是做一次比较，那么用匿名内部类或者lambda表达式
>
> 去使用带Comparator比较器的sort方法（Arrays和Conlections中都有该方法）会比较好	

- Comparator接口中的compare方法表示一种排序规则，方法会返回一个int值
  - 该sort方法逐个比较容器中的每两个对象
    - 方法返回负数表示排序中，处在前面的位置
    - 方法返回正数表示排序中，处在后面的位置
  - 最终的效果和Comparable接口一样，但是这种方式需要直接改实体类代码，更灵活
- Comparator接口中看起来有两个抽象方法compare和equals
  - 但实际上只需要实现compare就可以了，因为任何类都有默认的equals实现
  - 它仍然是一个功能接口，可以使用lambda表达式





# 可变字符串 

> String对象是不可变的字符串对象，如果用String直接 “ + ” 拼接频繁创建对象，会有效率问题
>
> 可以用以下代码进行测试

```Java
 String s = "";
for (int i = 0; i < 50000; i++) {
    s += "str";
}
System.out.println(s);
```

> 不难得出结论

- 进行字符串拼接时，如果是简单几次拼接，直接用String无伤大雅，并不会有多大的效率问题
- 但是如果频繁多次修改某个字符串仍然使用String，不仅效率堪忧而且过于浪费空间
- 为了解决这一问题，Java提供了可变的字符串来解决这个问题

> 当需要对字符串频繁修改时，需要使用**StringBuffer**和 **StringBuilder** 类。

- 和 String 类不同的是，StringBuffer 和 StringBuilder 类的对象能够被多次的修改
  - 并且不产生新的未使用对象，不会产生效率问题和空间浪费问题
- StringBuffer是线程安全的，StringBuilder是线程不安全的
  - StringBuilder的效率会比StringBuffer效率更高，单线程的程序推荐使用StringBuilder
  - 在多线程的程序中，应该优先考虑使用StringBuffer，安全性要更重要
  - 它们的效率都比String高很多
- 例子：

```Java
public class Test{
  public static void main(String args[]){
    StringBuffer sb = new StringBuffer("王道在线论坛官网：");
    sb.append("www");
    sb.append(".cskaoyan");
    sb.append(".com");
    System.out.println(sb); 
  }
}
```

以上实例编译运行结果如下：

```
王道在线论坛官网：www.cskaoyan.com
```



## StringBuffer

> 首先是学习构造方法

```java
// public StringBuffer()  无参构造方法
// public StringBuffer(int capacity)  指定容量的字符串缓冲区对象
// public StringBuffer(String str)  指定字符串内容的字符串缓冲区对象
```

> 总结StringBuffer可变长字符串的原理

-  StringBuilder 或 StringBuffer的初始化分配的空间大小取决于调用的构造方法：
   - **无参构造**默认大小为 16
   - 调用int单参数构造方法，初始化大小为指定的int值
   - 调用 String 类型的构造方法，初始化大小为：**字符串的长度 + 16**
-  扩容机制每次扩容大小为：**原数组大小 * 2 + 2**

> StringBuffer常见方法

- 获取功能

```java 
// public int capacity() 返回当前容量,数组的长度,理论值
// public int length() 返回长度(字符的个数),实际值    
```

- 添加功能

```java
// public StringBuffer append(String s) 将指定的字符串(其他类型有重载方法)追加到此字符序列的尾部
//在指定位置把任意类型的数据插入到字符串缓冲区里面
// public StringBuffer insert(int offset,String str) 
```

- 删除功能

```java
// public StringBuffer deleteCharAt(int index)：删除指定位置的字符
// public StringBuffer delete(int start,int end)：删除从指定位置开始指定位置结束的内容
```

- 替换功能

```java
// 使用给定String中的字符替换词序列的子字符串中的字符
// public StringBuffer replace(int start,int end,String str)
```

- 反转功能

```java
// public StringBuffer reverse()：将此字符序列用其反转形式取代，返回对象本身
```

- 截取功能

```java
// public String substring(int start)：返回一个新的String，开头到结束
// public String substring(int start,int end)：返回一个新的String，指定区间
```



> 以上，StringBuilder和StringBuffer的使用API实际上是差不多的，可以自行学习