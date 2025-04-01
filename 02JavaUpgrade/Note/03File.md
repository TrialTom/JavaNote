[TOC]

# 一、File概述

## 1.为什么需要File

在操作系统中，需要永久保存的数据，都是以文件形式存在的，所以想要操作这些被永久保存的数据，就需要在Java中描述文件

## 2.绝对路径与相对路径

- 绝对路径：完整的路径名，不需要任何其他信息就可以定位它所表示的文件

- 相对路径：必须使用取自其他路径名的信息进行解释（不完整的路径名）

  > 1. java语言中，相对路径默认相对于谁？
  >    - 默认情况下，Java.io包中的类总是根据当前用户目录来解析相对路径名。此目录由系统属性user.dir指定，通常是java虚拟机的调用目录
  >    - `System.getProperty("user.dir");`查看相对路径

## 3.File类概述

文件和目录，路径名的抽象表达式形式

# 二、File类

## 1.构造方法

```JAVA
//pathname可为文件路径，也可是目录路径
File(String pathname);
File(String parent,String child);
File(File parent,String child);
```

## 2.成员方法

1. 创建功能

   - public boolean createNewFile();
     - 只负责创建文件，目录路径如果不存在，则报错
   - public boolean mkdir();
     - 只负责创建目录，但只能创建单层目录，如果多级目录不存在的话，创建失败
   - public boolean mkdirs();
     - 只负责创建目录，但可以创建多级目录，如果多级目录不存在的话，则全部创建

   > mkdir和mkdirs创建目录的区别
   >
   > - mkdir只能在已存在的目录下，创建新的目录
   > - mkidrs，当目标目录的父目录不存在的时候，它会将不存在的目标目录的父目录连同目标目录一同创建好。

2. 重命名功能

   ```java
   // 重新命名此抽象路径名表示文件
   public boolean renameTo(File dest);
   ```

   > - 在源文件，和修改之后的目标文件在同一目录的时候：效果只是重命名
   > - 当源文件和修改之后的目标文件不在同一目录的时候：移动文件，重命名

3. 删除功能

   ```java
   // 删除此抽象路径名表示的文件或目录。如果此路径名表示一个目录，则该目录必须为空才能删除
   // delete不会因为文件不存在，路径名不正确而抛出异常，只会返回false，并且不会进入回收站
   public boolean delete();
   ```

4. 判断功能

   ```java
   // 判断File对象是否表示一个文件
   public boolean isFile();
   
   // 判断File对象是否表示的是一个目录
   public boolean isDirectory();
   
   // 判断，File对象表示的文件，是否物理存在
   public boolean exists();
   ```

5. 基本获取功能

   ```java
   // 获取File对象表示的抽象文件的绝对路径
   public String getAbsolutePath();
   
   // 获取File对象指向的文件名或目录名（传什么输什么）
   public String getPath();
   
   // 获取文件或者目录的名字
   public String getName();
   
   // 返回由此抽象路径名表示的文件的长度。不能返回文件夹的长度
   // 此抽象路径名表示的文件的长度，以字节为单位，如果文件不存在，则返回0L
   public long length();
   
   // 返回此抽象路径名表示的文件最后一次被修改的时间
   // 表示文件最后一次被修改时间的long值，毫秒数表示
   public long lastModified();
   ```

6. 高级获取功能

   ```java
   // 返回一个字符串数组，这些字符串包括此抽象的路径名表示的目录中的所有文件和文件夹的名字
   // 如果File对象表示的是一个文件，则返回null；空目录返回一个长度为0的数组
   // 只能获取当前目录的下一层，而不是所有层级
   public String[] list();
   
   // 返回指定File目录下的文件和文件夹的绝对路径形式的File对象数组
   // 如果File对象表示一个文件，则返回null；如果是一个空目录，则返回一个长度为0的数组
   // 只能获取当前目录下的下一层级，不是获取所有层级
   public File() listFiles();
   ```

   

## 3.文件过滤器（FileFilter）

- FileFilter是一个接口，它只有方法

  ```java
  // 测试指定抽象路径名是否应该包含在某个路径名列表中
  boolean accept(File pathname);
  ```

  - 这个方法相当于把高级功能中listFiles()获取的File数组对象遍历一遍，然后逐个判断，符合条件的留下，不符合条件的丢弃

