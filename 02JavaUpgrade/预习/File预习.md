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





# File类的使用

> 首先，在使用File之前，再明确一下File类的定义
>
> File是文件和目录（文件夹）路径名的抽象表达形式F

- File类是对文件、目录的抽象表示，**并不代表这个文件和目录就一定存在**

- 创建File类对象的时候，编译器也不会去检查这个File对应的文件和目录是否存在

- 用一个file对象调用以下方法，可判断该目录文件是否存在

  ```java 
  public boolean exists()
  ```

  


> File类的构造方法

```java
//创建一个File对象，该方法一般使用绝对路径来创建对象，也可以使用相对路径
File (String pathname)
    
//和第一种方式类似，只不过把一个路径劈成了两半
//普遍来说，parent路径表示一个绝对路径。child路径跟一个相对路径
File (String parent, Sting child)
    
//和第二种方式一样，只不过，子路径用一个File对象表示
File (File parent, String child)
```



# File API

> 下面来学习File API的使用

## 创建功能

```Java
//只负责创建文件，目录路径如果不存在，会报错而不是帮你创建
public boolean createNewFile() 

//只负责创建目录，但只能创建单层目录，如果有多级目录不存在的话，创建失败
public boolean mkdir()
    
//只负责创建目录，但可以创建多级目录，如果多级目录不存在，则帮你全部创建
public boolean mkdirs()
```

- createNewFile()只能创建文件，不能创建目录，会报错
- mkdir()和mkdirs()的区别就在于能否创建多级目录
  - 需要注意的是，它两个都不能创建文件
  - 如果File对象路径中包括文件名，它会把文件名当成目录名处理





## 删除功能

```Java
public boolean delete()
```

- 删除此抽象路径名表示的文件或目录。如果此路径名表示一个目录，则该目录必须为空才能删除







## 移动且重命名文件功能

```Java
public boolean renameTo(File dest)
```

- 当源文件和修改之后的目标文件，在同一目录的时候，效果只是重命名
- 当源文件和修改之后的目标文件，不在同一目录的时候，效果是移动且重命名
- 当源文件和修改之后的目标文件，同目录同名时，方法返回true，实际没有效果
- 真正操作文件，应该使用（IO流操作）    







## 判断功能

```Java
//判断File对象是否表示的是一个文件
public boolean isFile()
    
//判断File对象是否表示的是一个目录
public boolean isDirectory()
    
//判断File对象表示的文件或目录，是否真实存在
public boolean exists()

```





## 获取功能

```Java
//获取File对象表示的抽象文件的绝对路径
public File getAbsolutePath()

//获取File对象表示的抽象路径名的字符串，简单来说，创建的时候给的是什么就输出什么
public String getPath()

//获取File对象表示的文件或者目录的文件名
public String getName()
    
//返回由此抽象路径名表示的文件的所占硬盘空间大小，以字节为单位
//但是需要注意的是，这个方法只能获取文件的大小，不能获取目录大小
public long length()

//返回此File对象表示的文件的最后一次修改的时间
public long lastModified()
```





## 高级获取功能

```Java
//返回一个字符串数组，这些字符串包括，此抽象的路径名表示的目录中的所有文件和文件夹的名字
//如果File对象表示的是一个文件，则返回null
//只能获取当前目录的下一层，并不是获取所有层级
//如果是一个空目录，返回一个长度为0的数组，而不是null
public String[] list() 
    
    
//返回指定File目录下的文件和文件夹的绝对路径形式的File对象数组
//如果File对象表示的是一个文件，则返回null
//只能获取当前目录的下一层，并不是获取所有层级
//如果是一个空目录，返回一个长度为0的数组，而不是null
public File[] listFiles()
```





## 自定义获取功能

```java 
//获取这个文件夹下，满足filter过滤器的条件的文件
File[] listFiles(FileFilter filter) 
```

- 自定义获取功能是在高级获取功能的基础上，加了一个过滤器，所以高级功能的特点它都有

- FileFilter是一个接口，它只有下面一个方法

  - ```Java
    //测试指定抽象路径名是否应该包含在某个路径名列表中
    boolean accept(File pathname)
    ```

  - 这个方法相当于把高级功能中listFiles()获取的File数组中File对象遍历一遍，然后逐个判断

  - 符合条件的留下，不符合条件的干掉（丢弃）- 

- 常用匿名内部类来做实现

```Java
//留下所有txt文件
public class FileTest2 {
    public static void main(String[] args) {
        File file = new File("E:\\temp");
        //匿名内部类创建一个过滤器
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File dir) {
                //条件是 dir对象是一个文件并且它的名字以txt结尾
                return dir.isFile() && dir.getName().endsWith("txt");
            }
        };
        //在有过滤器的情况下创建一个File[]数组，并且遍历
        File[] files = file.listFiles(fileFilter);
        for(File f : files){
            System.out.println(f);
        }
    }
```

- 补充Arrays.sort(files, new Comparator<File>())方法
  - 带比较器的File数组排序方法



> 递归删除目录的思路

- 获取目录的下的所有File对象（包括文件和文件夹）
- 判断，如果是一个空目录或者file对象不是一个目录而是文件
  - 直接删除
- 程序执行到这里，那么一定是一个目录，且不是空目录
  - 遍历获取的file数组
  - 如果这个file对象仍然是一个目录，递归删除该目录
  - 如果这个file对象是文件，直接删除
- 最后不要忘记删除已经是空目录的当前目录

