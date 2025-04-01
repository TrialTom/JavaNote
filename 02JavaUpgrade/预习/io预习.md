

# 预习

## 概述(掌握)

- io是什么?
  - input  输入
  - output 输出
-  java中如何实现io?
  - 通过流的方式  创建相应的输入输出流对象去实现其io功能
- io流的分类?
  - 字节流: 一连串的0 1 二进制  , 以字节为单位
  - 字符流 : 一连串的字符序列 , 以字符为单位



**4个基类是什么?** 

**重点掌握read  write方法**

| 抽象基类 | 字节流       | 字符流 |
| -------- | ------------ | ------ |
| 输入流   | InputStream  | Reader |
| 输出流   | OutputStream | Writer |

- **InputStream 字节输入流**   **3个read方法**

  - | abstract  int | read()        从输入流中读取数据的下一个字节。               |
    | ------------- | ------------------------------------------------------------ |
    | int           | read(byte[] b)        从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。 |
    | int           | read(byte[] b,  int off, int len)       将输入流中最多 len 个数据字节读入 byte 数组。 |

- **OutputStream 字节输出流**    **3个write方法**

  - | 方法摘要       |                                                              |
    | -------------- | ------------------------------------------------------------ |
    | void           | close()        关闭此输出流并释放与此流有关的所有系统资源。  |
    | void           | flush()        刷新此输出流并强制写出所有缓冲的输出字节。    |
    | void           | write(byte[] b)        将 b.length 个字节从指定的 byte 数组写入此输出流。 |
    | void           | write(byte[] b,  int off, int len)       将指定 byte 数组中从偏移量 off 开始的  len 个字节写入此输出流。 |
    | abstract  void | write(int b)        将指定的字节写入此输出流。               |

- **Reader 字符输入流**    **3个read方法**

  - | int           | read()        读取单个字符。                                 |
    | ------------- | ------------------------------------------------------------ |
    | int           | read(char[] cbuf)        将字符读入数组。                    |
    | abstract  int | read(char[] cbuf,  int off, int len)       将字符读入数组的某一部分。 |

- **Writer 字符输出流**   **5个write方法**

  - | void           | write(char[] cbuf)        写入字符数组。                     |
    | -------------- | ------------------------------------------------------------ |
    | abstract  void | write(char[] cbuf,  int off, int len)       写入字符数组的某一部分。 |
    | void           | write(int c)        写入单个字符。                           |
    | void           | write(String str)        写入字符串。                        |
    | void           | write(String str,  int off, int len)       写入字符串的某一部分 |



**体系结构**

| 类型     | 字节输出流           | 字节输入流          | 字符输出流         | 字符输入流        |
| -------- | -------------------- | ------------------- | ------------------ | ----------------- |
| 文件相关 | FileOutputStream     | FileInputStream     | FileWriter         | FileReader        |
| 缓冲相关 | BufferedOutputStream | BufferedInputStream | BufferedWriter     | BufferedReader    |
| 转换相关 |                      |                     | OutputStreamWriter | InputStreamReader |
| 数据相关 | DataOutputStream     | DataInputStream     |                    |                   |
| 打印相关 | PrintStream          |                     | PrintWriter        |                   |
| 对象相关 | ObjectOutputStream   | ObjectInputStream   |                    |                   |



## 字节输出流具体子类(重要)

- **FileOutputStream    文件字节输出流**
- **BufferedOutputStream    字节缓冲输出流**

**重点掌握:**

1. 怎么创建字节输出流对象?
2. 怎么向文件中写数据?3个write方法 写完数据记得close
3. 对于缓冲输出流来说,如果写完数据,不close,会发生什么事情?

写入Demo

```java
// 创建字节输出流对象        
FileOutputStream out = new FileOutputStream("a.txt");
// write(int b) 写单个字节        
out.write(97);
// write(byte[] b) 写字节数组
// getBytes()方法把字符串转为字节数组
out.write("hello world".getBytes());
// 关闭资源
out.close();
```





## 字节输入流具体子类(重要)

- **FileInputStream    文件字节输入流**
- **BufferedInputStream    文件字节输入流**

**重点掌握**

1. 怎么创建字节输入流对象
2. 怎么去读取文件中的数据?3个read方法
3. 怎么循环读取?

读取Demo

```java
// 创建字节输入流对象  可以使用相对路径  绝对路径
FileInputStream in = new FileInputStream("a.txt");
// read() 读取单个字节
// readData就代表了读取到的字节值
int readData = in.read();
System.out.println(((char) readData));
// read(byte[] b) 读取多个字节
// 一般来说这个数组大小是1024或其整数倍 习惯
byte[] bytes = new byte[1024];
// readCount就代表了读取到的字节的个数
int readCount = in.read(bytes);
System.out.println(new String(bytes,0,readCount));
in.close();
```

循环读

```java
package com.cskaoyan.bytestream.in;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @description: 循环读取数据
 * @author: songtao@cskaoyan.onaliyun.com
 **/

public class Demo4 {
    public static void main(String[] args) throws IOException {
        // 创建输入流对象
        FileInputStream in = new FileInputStream("a.txt");
        // 循环读取
        //readWhile1(in);

        // 固定写法
        // 单个字节方式
        // readWhile2(in);

        // 多个字节方式
        byte[] bytes = new byte[1024];
        int readCount;
        while ((readCount = in.read(bytes)) != -1) {
            System.out.println(new String(bytes,0,readCount));
        }
        // close
        in.close();
    }

    private static void readWhile2(FileInputStream in) throws IOException {
        int readData;
        while ((readData = in.read()) != -1) {
            System.out.println(readData);
        }
    }

    private static void readWhile1(FileInputStream in) throws IOException {
        // 方式一
        while (true) {
            int readData = in.read();
            System.out.println(readData);
            if (readData == -1) {
                // 如果等于-1 表示读取到了文件末尾
                break;
            }
        }
    }
}

```



## 如何通过字节流实现文件复制?(重点)

思路是什么?  (源文件)磁盘→内存→磁盘(新文件)

- 先把文件从磁盘中读取到内存  用变量接收
- 把内存中的变量接收的数据  写入到磁盘(循环读写,读取一个写一个)
- 所以肯定要有输入流对象 输出流对象 并利用其read   write方法 边读边写

Q:

- 文本文件可以吗?
- 图片文件可以吗?
- 视频文件可以吗?

![image-20220304153158746](https://gitee.com/uncleTao/picture-bed/raw/master/img/image-20220304153158746.png)

## 字节流一些注意的点(重要)

- new 一个FileOutputStream对象之后执行 看看会发生什么?  如果字节输出流对象指向的文件中有内容,再执行一下看看会发生什么?

  - 在写入一个文件时，如果使用构造器FileOutputStream(file)，则目录下有同名文 件将被覆盖。

- 怎么换行?换行符是什么?

  - 换行符 "\r\n"  

- 怎么追加写入?利用构造方法

  - 如果使用构造器FileOutputStream(file,true)，则目录下的同名文件不会被覆盖， 在文件内容末尾追加内容。

- 怎么处理异常? 掌握2种
  - try catch语句

  - try-with-resources语句

    - ```java
      //语法 
      // try() 小括号里放的是资源, 实现了AutoCloseable的接口都可以称为资源
      try(FileOutputStream out= new FileOutputStream){
      	// 代码.....
          // 特点就是当try中代码执行完,那么就会释放掉这个out资源 所以不必再close
      }catch(){
      
      }finally{
      
      }
      ```

      

## 编解码(掌握)

什么是编码表?

对于常见的几种编码表,几个字节代表一个字符? 

常见的编码表

-  utf-8 
-  ISO8859-1 
-  ASCII  
-  gbk

对于gbk与utf-8这2中编码  分别几个字节代表一个汉字?

java中如何实现编解码?

- 编码:   字符数据  →字节数据     怎么把字符串数据转变为字节数据?
- 解码 :   字节数据 → 字符数据    怎么把字节数据转变为字符数据?

操作系统的默认字符集是什么?

- gbk

idea中默认字符集是什么?

- utf-8



## 字符输出流具体子类(重要)

- **OutputStreamWriter  转换流**
- **FileWriter     简化流**
- **BufferedWriter   缓冲流**

**重点掌握**

- 怎么实例化字符输出流对象?

- 怎么向文件中写数据?
  - 利用write方法  
- 写完数据文件里就有数据了吗?
  - 尝试一下

## 字符输入流具体子类(重要)

- **InputStreamReader   转换流**
- **FileReader    简化流**
- **BufferedReader    缓冲流**

**重点掌握**

- 怎么实例化字符输入流对象?

- 怎么从文件中读数据?
  - 利用read方法 



## 字符流实现文件复制(重要)

思路:同字节流实现文件复制的步骤是一样的

Q:

- 文本文件可以吗?
- 图片文件可以吗?
- 视频文件可以吗?



## 其他流(了解)

### 操作java基本数据类型的流DataInputStream   与   DataOutputStream

Q:

- 如果用普通的字节输出流向文件中写入1个int类型的1000,会发生什么?

自己尝试一下

你可能发现打开文件并不是1000 为什么呢?  因为编码表中没有一个编码值与java中的int类型对应

那怎么解决呢?     →   这时候就需要数据流了

### 打印流PrintStream     PrintWriter

核心思想:  就是将不同的数据类型转化为相应的字符串(String中API) 

打印流的四个特点:

- 只能操作目的地
  - 换句话理解,打印流本质上只是输出流  并没有与之相对应的输入流
- 可以写入任意类型的数据
  - 本质就是把不同的数据类型转成字符串写入文件   每一种类型对应一种print方法
- 如果开启了自动刷新功能,可以自动刷新 
  - 有前提条件的 
- 可以直接操作文件



**标准输入输出流**

什么是标准输入流与标准输出流?

- System.in
- System.out

用.var看一下标准输入流与标准输出流是什么类型的?

> - System.in属于普通的字节输入流   InputStream
> - System.out属于字节打印流    PrintStream

可以自行试一下



**如何用标准输入流System.in与BufferedReader结合,模拟scanner中的nextLine()功能**

> - 首先思考scanner中的nextLine方法是读取一行数据,那么BufferedReader中正好有一个readLine方法也是读取一行数据,这正是我们需要的.
> - 创建BufferedReader对象,利用其构造方法,发现需要传入一个底层的字符输入流作为参数,然后问题来了, 所能提供的仅仅是System.in标准输入流,只是一个普通的字节输入流,这时候怎么办呢?
> - 借助转换流,转换流是字节流与字符流的桥梁,(转换流是字符流)

可以自行试一下



### 序列化反序列化流(对象流) ObjectInputStream    ObjectOutputStream

什么是序列化,反序列化?

- 序列化:将对象信息写入文件
- 反序列化:从文件中读取对象信息

如何实现序列化?反序列化?

- 序列化	
  - 需要被序列化的对象的类要实现Serializable接口   比如Student类
  - 创建序列化流对象  创建Student对象
  - writeObject方法 把Student对象写入文件
- 反序列化
  - 创建反序列化流对象
  - readObject方法读取对象信息

如果某个字段不想被序列化,应该怎么做?

- 使用**transient**关键字修饰该字段



**Serializable接口**

描述

类通过实现 java.io.Serializable  接口以启用其序列化功能。未实现此接口的类将无法使其任何状态序列化或反序列化。可序列化类的所有子类型本身都是可序列化的。序列化接口没有方法或字段，仅用于标识可序列化的语义。

 

当遍历一个图形(类)时，可能会遇到不支持 Serializable 接口的对象。在此情况下，将抛出  NotSerializableException，并将标识不可序列化对象的类。 

序列化运行时使用一个称为 serialVersionUID  的版本号与每个可序列化类相关联，该序列号在反序列化过程中用于验证序列化对象的发送者和接收者是否为该对象加载了与序列化兼容的类。如果接收者加载的该对象的类的  serialVersionUID 与对应的发送者的类的版本号不同，则反序列化将会导致 [`InvalidClassException`](../../java/io/InvalidClassException.html)。



显示声明serialVersionUID:

可序列化类可以通过声明名为  `"serialVersionUID"` 的字段（该字段必须是静态 (static)、最终 (final) 的  `long` 型字段）显式声明其自己的 serialVersionUID：

```
 例如:
 ANY-ACCESS-MODIFIER 
 static final long serialVersionUID = 42L;
 
```

未声明serialVersionUID:

如果可序列化类未显式声明 serialVersionUID，则序列化运行时将基于该类的各个方面计算该类的默认 serialVersionUID  值，如“Java(TM) 对象序列化规范”中所述。不过，*强烈建议* 所有可序列化类都显式声明 serialVersionUID  值，原因是计算默认的 serialVersionUID 对类的详细信息具有较高的敏感性，根据编译器实现的不同可能千差万别，这样在反序列化过程中可能会导致意外的  `InvalidClassException`。因此，为保证 serialVersionUID 值跨不同 java  编译器实现的一致性，序列化类必须声明一个明确的 serialVersionUID 值。还强烈建议使用 `private` 修饰符显示声明  serialVersionUID（如果可能），原因是这种声明仅应用于直接声明类 -- serialVersionUID  字段作为继承成员没有用处。数组类不能声明一个明确的 serialVersionUID，因此它们总是具有默认的计算值，但是数组类没有匹配  serialVersionUID 值的要求。



