# File类

> File类概述

> 什么是file类？为什么要学习file类

- 在操作系统中，数据都是以文件的形式，永久保存在外部储存设备上的
- 做开发的时候，难免需要对文件进行操作，比如检查作业，上传作业
- 根据Java当中一切皆对象的思想，这个时候我们需要一个对象用来操作文件
- 于是Java引入了File类用来描述文件或者文件夹（目录）
- File类位于java.io包下，是Java进行IO操作的核心类
- File是文件和目录（文件夹）路径名的抽象表达形式





> 绝对路径和相对路径

- 绝对路径
  - 绝对路径名是完整的路径名，根据绝对路径可以唯一确认文件和目录
  - 例如：E:\demo\first\a.txt
- 相对路径
  - 相对路径是不完整的路径名，只依赖相对路径不能唯一确认文件和目录
  - 相对路径名必须使用其他路径名的信息进行解释，也就是常说地相对于“某个路径”
  - 相对路径是不跟盘符和路径分隔符的
  - 例如相对于（E:\demo）下的first\a.txt文件







> 绝对路径可以唯一确认一个文件，相对路径却不可以
>
> 那么我们在IDEA中写代码，如果我们使用一个相对路径来表示文件
>
> 那这个相对路径又是相对于谁呢？

- 默认情况下，java.io包中的类总是根据当前用户目录来解析相对路径名

- 此目录由系统属性user.dir指定，通常是 Java虚拟机的调用目录

- 可以使用以下代码获取

  - ```Java
    System.getProperty("user.dir")
    ```

- 这个属性默认是project的根目录

- 可以在run configuration中修改

- 一般情况下默认就好了

- 普遍来说，在Java程序中应该优先使用绝对路径，因为相对路径会随着环境的改变而指向不同的文件







> 不同操作系统下路径名表示的符号其实是有区别的
>
> Microsoft Windows平台

- Windows操作系统下，包含盘符的路径名前缀由驱动器号和一个 ":" 组成

- 后面不同层级目录用“\”或者“\\\”表示

- 例如

  - ```
    绝对路径： e:\demo\a.txt
    相对路径： （相对于e:\）demo\a.txt
    ```



> 类Unix平台
>
> 包括Unix系统，Linux系统，macOS系统

- 这些系统是没有盘符标识的，而是用一个“/”表示根目录

- 绝对路径就是从根目录开始的，一个完整的目录，后面的每个层级都用“/”分隔

- 相对路径则不从根目录开始

- 例如

  - ```
    绝对路径：/home/demo/a.txt
    相对路径：（相对于/home/demo）a.txt
    根目录：/
    ```



> 转义字符 ‘\xxx’

- ```
  '\t'表示制表符
  ```

- ```
  '\r'表示回车
  ```

- ```
  '\n'表示换行
  ```

- ```
  '\\'表示字符串"\"
  ```




> 根据以上种种特征，那么我们怎么在Java程序中表示一个文件或者目录呢？
>
> 难道我们需要在用Windows写代码测试的时候用“\\\”，而在代码上线后用"/"吗？

- 当然不需要，Java早已是一门成熟的语言，跨平台性上，已经对路径符号作了优化
- 你可以自由选择以下一种方式书写路径名，都是可以的
  - 全部用“//”
  - 全部用“\\\”（推荐使用）
  - 全部用“/”
- 不要使用“\”，单独使用“\”，这是一个转义字符





