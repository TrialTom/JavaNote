###### <sub><font color = orange>JAVASE基础教程</font></sub><br />——<br /><sup><font color=white>卷1</font></sup><font color=white>Java基础语法</font><br/><sup><sub><font color=cyan>节2</font></sub><font color=cyan>Java开发环境基础配置</font></sup><br/><br/>	``#最新版本|V3.0#(purple) ``<br/>**长风**<br/>*COPYRIGHT ⓒ 2021. 王道版权所有*

[TOC]

# 概述

> `>(green)`
>
> 这个小节主要讲两个部分：
>
> 1. JDK的安装
> 2. IDEA的安装
>
> 提供给完全没有接触过Java的童鞋，在学习Java之前安装基础开发环境使用。**如果你已经安装过了，也可以大致看一看，有些细节可能需要注意一下。**

# 安装JDK

> [-] 关于JDK
>
> `>(green)`
>
> > JDK（Java Development Kit） 是 Java 开发工具包，是开发 Java 程序必不可少的工具。
> >
> > <span style=color:red;background:yellow>**JDK的版本选择，大版本必须是8，小版本可以忽略不计。**</span>
> >
> > <span style=color:red;background:yellow>**JDK的版本选择，大版本必须是8，小版本可以忽略不计。**</span>
> >
> > <span style=color:red;background:yellow>**JDK的版本选择，大版本必须是8，小版本可以忽略不计。**</span>
>
> **JDK下载地址：**
>
> 1. 官网下载
>
>    - 下载地址：[Oracle官网下载JDK](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
>    - 根据操作系统选择版本后，找到 **Java8** 进行下载，不要下载最新的JDK版本（目前JDK已经更新到17了）。
>
> 2. 百度云下载
>
>    官网下载必须要注册Oracle账号（可能还需要科学手段），如果你不想注册可以选择百度云下载
>
>
> > 百度云下载：                                                                                                                                                                                                     
> >
> > 链接：https://pan.baidu.com/s/1FAPY1q2ipauB8DC6vwLpmg 
> >
> > 提取码：1hnm 
>
> 3. 将安装包下载下来后，双击安装即可，安装过程直接全程 **下一步** 即可。
>
> 稍微需要注意的是：
>
> 1. 不建议将JDK直接装在C盘，建议单独在其它磁盘找一个文件夹放开发相关的软件。（这样开发环境不会因为重装系统而丢失）
> 2. JDK的安装目录<font color=red>**不要带中文，不要有空格**</font>，这有时候会造成一些奇怪的问题。
> 3. <span style=color:red;background:yellow>**一台计算机可以同时安装多个不同版本的JDK，在开发工具IDEA中可以选择使用的版本。**</span>当然我们在开发中仅会使用Java8，没有必要装多个版本的JDK。

# 配置JDK（选做）

> `>(green)`
>
> 配置JDK环境变量的意义在于让命令行在任何目录下，都能够识别`java`等指令。**我们使用IDEA进行Java开发，实际上是无需配置JDK的，该节了解即可。**

## 如何配置

> `>(green)`
>
> 这里以win为例，如果是macOS，想配置就去查一下，不想配就算了。
>
> 1. 首先，打开JDK的安装目录，并进入bin目录，然后复制当前的路径：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202202241358005.png?align=center" alt="配置JDK-1" style="zoom: 67%;" />
>
> 2. 然后右键**我的电脑-属性**，找到**高级系统设置**：
>
>    注：win升级21H1后，我的电脑属性有所改变，自己找一下高级系统设置，如下图                                                                                                                                                                                                                                                                                                         
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202202241358552.png?align=center" alt="配置JDK-2" style="zoom:50%;" />
>
> 3. 在弹出的窗口中，点击**高级 -> 环境变量**，如下图：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202202241358421.png?align=center" alt="配置JDK-3" style="zoom:50%;" />
>
> 4. 在打开的窗口中，选择Path，然后点击**编辑**按钮，如下图：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202202241358854.png?align=center" alt="配置JDK-4" style="zoom:50%;" />
>
> 5. 在打开的窗口中，点击**新建**按钮，然后将刚才拷贝的bin路径粘贴过来，如下图：
>
>    注：这里也可以选择新建`%JAVA_HOME%\bin`，然后在外面创建`JAVA_HOME`参数指向jdk的安装目录                                                                                                                                                                         
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202202241358171.png?align=center" alt="配置JDK-5" style="zoom:50%;" />
>
> 最后，将上述打开的窗口全部点击 **确定** 即可。
>
> ---
>
> 以上，就完成了JDK的配置，接下来我们可以简单测试一下：
>
> 1. 打开命令行工具（**win + R输入cmd**），输入命令`java -version`，输出结果如下图：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202202241358282.png?align=center" alt="cmd测试jdk配置-1" style="zoom: 67%;" />
>
> 2. 输入命令 `javac`，输出结果如下图：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202110152151566.png?align=center" alt="cmd测试jdk配置-2" style="zoom:50%;" />
>
> 3. 还可以输入`java`、`java -help`等命令，发现有输出结果就可以断定配置成功了
>
> <font color=red>**需要注意的是：**</font>
>
> 有些机器在 **C盘** 目录下，操作指令可能无法正常显示结果，如果是这样，只需要换一个目录就可以了。

## 为什么要配置

> `>(green)`
>
> JDK环境变量实质上完全可以不配置，我们可以简单了解一下其中的缘由。
>
> 我们配置环境变量，实际上是为了在 **cmd** 窗口中能够运行各种 **java** 相关的指令。当你在命令行（cmd）中运行某个命令时，如果本地查找不到某个命令或文件，就会到 **path** 声明的目录中去查找。类似`java`、`javac`等命令显然不是系统本身就能够执行的命令，因为它们既不是本地命令，也没有指定路径。于是就需要手动配置path环境变量，指出这些命令所执行文件的路径。配置完成后，就可以在任意目录下去执行`java`、`javac`等命令。
>
> **最后，强调几点：**
>
> 1. 如果不配置path环境变量，我们仍然可以在jdk安装目录的bin目录下使用`java`等命令，感兴趣可以尝试一下
>    - 这个时候，想要使用`java`命令编译java文件，就必须把java文件放在该目录下了
> 2. 只要配置了path环境变量，就可以在任何目录使用`Java`等命令
> 3. **只要是使用IDE进行开发，都不需要设置环境变量，因为不需要手动编译Java文件**
> 4. 实际上很多IDE（比如IDEA）会自带JDK，装了IDEA后可能连JDK都不用安装就可以进行Java开发。但是出于统一环境的考虑，很少有人会使用IDE自带的JDK，而是使用本地JDK。

## 第一个Java程序

> `>(green)`
>
> 对于学习任何编程语言来说，业界有一个共识就是：喜欢使用<font color=red>**HelloWorld**</font>作为第一个入门程序。现在我们就来实现它，这也将是你的第一个Java程序。
>
> <span style=color:red;background:yellow>**这里给一个友情提示：如果你想尝试手动创建java文件并编译执行，请一定要打开操作系统中文件后缀名的显示！**</span> 请打开文件资源管理器，进行如下图操作：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202111051541168.png?align=center" alt="文件扩展名勾选" style="zoom:33%;" />
>
> 接下来按照下述步骤完成：
>
> 1. 首先，新建一个 **txt** 文本文件，起名字为 **HelloWorld.java** ，这时会提示**修改扩展名**点击确认即可。接下来写Java代码，如下：
>
>    ###### hello world案例
>
>    ``` java
>    public class HelloWorld {
>        public static void main(String[] args) {
>            System.out.println("Hello World");
>        }
>    }
>    ```
>
>    目前来说，我们可以把上述程序作为我们写Java代码的一个标准范式，即：
>
>    1. 首先定义一个public修饰的class，并且 <span style=color:red;background:yellow>**该class必须要和文件名同名！**</span>
>    2. 然后写 **main** 方法（格式固定，直接抄写即可）
>    3. 最后在 **main** 方法中写要执行的代码
>
>    需要明确的是： <span style=color:red;background:yellow>**main方法是Java程序的入口，任何想要被执行的代码都必须写在main方法中！**</span>
>
> 2. 然后打开cmd命令行窗口，进入java文件存放的目录（相关操作需要使用dos指令，自行百度）
>
> 3. 使用 `javac` 指令编译java文件（源代码文件），得到class文件（字节码文件）
>
> 4. 使用 `java` 指令解释执行class文件（字节码文件）即可得到结果
>
> 整个操作流程在网络上很容易找到相应视频，不再赘述了。运行 `javac` 命令后，如果成功编译没有错误的话，会出现一个 HelloWorld.class 的文件，如果语法有错误，会给出相关的错误原因。`java`指令后面跟着的是java文件中的类名（而不是文件名）例如 HelloWorld 就是类名，直接` java HelloWorld ` 即可。
>
> **动态Gif演示：**
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/helloworld.gif?align=center" alt="helloworld演示案例" style="zoom: 50%;" />
>
> 以上过程，仅用于给初学者参考，重在理解下图的过程，对实际开发并无太大意义。
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202201221444742.jpeg?align=center" alt="Java程序运行原理"  />
>
> ---
>
> **在实际开发中，Java程序员是不会使用命令行编译执行源代码的，因为这样效率太低了。**

# 安装IDEA

> [-] 关于IDEA
>
> `>(green)`
>
> > IDEA 全称 IntelliJ IDEA，是Java编程语言开发的集成环境。IDEA在业界，长期被公认为最好的Java开发工具，尤其在智能代码助手、代码自动提示、重构、JavaEE支持、各类版本工具(git、svn等)、JUnit、CVS整合、代码分析、 创新的GUI设计等方面的功能可以说是具有划时代领先的。IDEA开发公司是JetBrains公司，这是一家总部位于捷克共和国的首都布拉格的公司，除了Java开发工具，该公司还涉及Python、前端等开发领域的产品，可以说是程序员必须要知道的公司之一。
> >
> > IDEA本身是一个收费软件，并且版本更迭很快。在公司实际生产中，为了避免因工具版本不同带来一些额外的问题，往往都需要统一工具版本号，在这里建议大家：
> >
> > <span style=color:red;background:yellow>**IDEA的版本选择，建议统一为2018.3.6！**</span>
> >
> > <span style=color:red;background:yellow>**IDEA的版本选择，建议统一为2018.3.6！**</span>
> >
> > <span style=color:red;background:yellow>**IDEA的版本选择，建议统一为2018.3.6！**</span>
> >
> > 注：
> >
> > 并不是说其它版本不能用，统一工具版本是出于统一环境、避免环境差异引发问题而考虑的。工作后可以根据公司的实际要求做出调整，但是不管怎么样，<font color=red>**一个软件都非常不建议使用最新版！**</font> 拿IDEA来说，每年有三个大版本，即1，2和3，建议选择每年的最后一个大版本的最后一个小版本作为开发使用。比如如果选择2018年版本的IDEA，就选择大版本号为3，小版本号为6的当年最后一个版本。
> >
> > 如果你觉得18版过老了，可以考虑使用2020.3或者2021.3。不要使用更新的版本了！！！
>
> **IDEA下载地址：**
>
> 1. 官网下载
>
>    - 下载地址：[IDEA官网下载-其它版本](https://www.jetbrains.com/zh-cn/idea/download/other.html)
>    - 根据操作系统选择相应版本，下载版本号为 **2018.3.6** 的IDEA
> 2. 百度云下载
>
>    - 官网下载可能需要科学手段，如果你想更快速的下载，推荐使用百度云盘下载。
>
>
> > 百度云链接：
> >
> > 链接：https://pan.baidu.com/s/1MgINzHbzh4e_Z_mmFgEMag 
> > 提取码：yo7m 
>
> 3. 将安装包下载下来后，直接双击安装，整个安装过程全程 **下一步** 即可！
>
> 稍微需要注意的是：
>
> 1. 不要安装在C盘，建议单独在其它磁盘找一个文件夹放开发相关的软件。（这样开发环境不会因为重装系统而丢失）
> 2. 安装目录<font color=red>**不要带中文，不要有空格**</font>，这有时候会造成一些奇怪的问题。

# IDEA的使用（重要）

> `>(green)`
>
> IDEA之于Java程序员，就像宝剑良骏之于将军。**大家在学习Java语法特性的同时，也要注重学习IDEA的使用!**

## 第一行IDEA代码

> `>(green)`
>
> 「 **这里以使用IDEA实现"Hello World"案例，作为IDEA基础使用的举例~**」

## 创建Project

> [-] 关于IDEA的Project
>
> `>(green)`
>
> > **Project，即工程。它是IDEA进行项目结构管理的顶层概念 ，IDEA中进行Java开发必须要在一个Project中进行。**
> >
> > IDEA的 Project 具有以下特点：
> >
> > 1. <font color=red>**Project只是项目管理的顶层概念，不是物理存在于操作系统上的结构。**</font>（这一点随后你就会明白）
> > 2. 在操作系统层面来看，创建一个project实际上就是新建了一个文件夹。
> > 3. 从IDEA软件层面来说，创建一个project就是创建了一个独立的工作空间。
> >
> > 当然，想要完全理解 **Project** 在IDEA中的涵义，还需要自己多多使用，多多体悟。
>
> <span style=color:red;background:yellow>**在IDEA中创建Project遵循以下步骤：**</span>
>
> 1. 打开 File--->New--->Project或者Create New Project（反正就是要新建Project）
>
> 2. 弹窗界面中，首先会要求选择JDK版本，**请选择版本Java8**，还可以选择模板，但JavaSE部分无需选择模板，直接下一步
>
>    <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202202241407742.png?align=center" alt="新建Project" style="zoom:33%;" />
>
>    **注：这里选择的JDK版本是你装在本地的JDK，具体来说需要直接选择整个JDK目录，参考下图：**
>
>    <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202202241410853.png?align=center" alt="IDEA选择JDK目录" style="zoom: 67%;" />
>
> 3. 下一步来到给Project起名，这里稍微谈一下Project的命名：
>
>    <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202202241411909.png?align=center" alt="新建Project—命名" style="zoom: 50%;" />

> [-] 关于Project的命名问题                                                                                                                                                                                                               
>
> `>(green)`
>
> > 工程的命名格式没有固定的限制，一般具有“见名知意”的作用即可，也就是说工程的名字需要指出该Project的意义。根据日常工作的普遍规律，给出几条起名的限制：
> >
> > 1. **工程名尽量使用正确的英语单词，如无特殊需求不要使用中文或者拼音。**
> > 2. **多个单词之间建议使用下划线或者横杠连接。**
> > 3. <font color=red>**最好通过名字能够得知一个工程的作用和意义，即"见名知意"。**</font>
> >
> > 举个例子，比如我新建一个工程用于做作业，而我是Java38th的，所以我可以将Project命名为**Java38th-homework**这样就是一个不错的命名。当然实际开发中，可以根据公司领导的安排决定。
>
> 1. <span style=color:red;background:yellow>**除了起名字外，Location的含义是工程存放的硬盘位置，建议单独找一个空间存放，不要直接存在默认位置。**</span>
> 2. 其余设置都默认即可，不需要改动，点击 **Finish** 即可。
> 3. 创建完成，可以选择在当前窗口打开Project，还是在新窗口，这个自由选择即可。
>
> 注：这里强烈建议大家专门找一个硬盘区域用来放`idea-project`。做人要经常收拾房间，做程序员也要把自己的硬盘内容管理的井井有序。<span style=color:red;background:yellow>**严厉禁止和谴责直接默认路径（C盘或根目录），默认名（untitled）创建Project的懒惰做法！**</span>

## HelloWorld!

> `>(green)`
>
> 新建Project完毕后，我们就可以打开Project页面，如下图所示：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202202241423677.png?align=center" alt="image-20220224142312446" style="zoom: 33%;" />
>
> 这里我在目录**"E\\idea_space"**下创建了名为**test**的工程。实际上，准备工作到这里，我们就可以开始写Java代码了。
>
> ---
>
> **IDEA中所有Java源代码都必须放在src目录下，才能执行。** 如下图所示：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202202241407887.png?align=center" alt="src目录" style="zoom: 50%;" />
>
> 注：简单来说，src是英语单词"source"的缩写，代指"源代码"，这个缩写以后将会经常看到和使用。<font color=red>**就JavaSE阶段的学习进度而言，所有的代码都需要写在src目录下，也只有放在src目录下的代码，才可以被执行。**</font>
>
> 知道**"src目录"**后，我们按以下步骤进行操作：
>
> 右键单击src目录，选择**New ----> Java Class**新建一个类，然后按照之前一样写main方法，具体代码如下：
>
> ###### hello world案例
>
> ``` java
> //文件 HelloWorld.java中
> public class HelloWorld {
>  public static void main(String[] args) {
>      System.out.println("Hello World");
>  }
> }
> ```
>
> <font color=red>**在IDEA这种集成开发环境中，已经集成了cmd窗口控制台的作用，点击main方法左边的Run，启动main方法即可看到代码执行结果！**</font>
>
> **最后虽然已经强调过了，但是这里还是要强调几点：**
>
> 1. public修饰类的类名必须和文件名保持一致，一个Java文件中只有一个public修饰的类，但非public类可以有多个。
>
>    **比如下面Java代码是允许的：**
>
>    ###### 一个Java文件定义多个类
>
>    ``` java
>    //文件 HelloWorld.java中
>    public class HelloWorld {
>    }
>    class A{}
>    class B{}
>    class C{}
>    ```
>
>    **注：public修饰class的含义，什么是public，它的作用是什么等相关问题，我们放在后面再讲。**
>
> 2. main方法是程序的入口方法，只有存在main方法的Java类可以启动，执行其中的代码。
>
>    注：关于方法的概念，我们会在后面探讨。
>
> 3. 如果Java Class都**直接**放在src目录下，是不允许有同名的class的，包括public修饰的class和非public修饰的class！**这就好比同一个文件夹下，不允许有同名文件一样。**<span style=color:red;background:yellow>**（这里牵扯到一个包的概念，后面会详细讲解）**</span>
>
> ---
>
> 以上，IDEA的基本使用就结束了，下面我们讲一个比较重要的概念——Module。

## 创建Module

> `>(green)`
>
> [-] 关于IDEA的module                                                                                                                                                                                                          
>
> > Project只是IDEA进行项目管理的**顶层概念**，而不是真实存在的基本单元。谁是IDEA进行Java项目开发的基本单元呢？
> >
> > **Module！**
> >
> > 实际上：
> >
> > <font color = red>**每创建一个Project，默认就会创建一个Module，并且该Module的名字和Project相同！**</font>
> >
> > <font color = red>**每创建一个Project，默认就会创建一个Module，并且该Module的名字和Project相同！**</font>
> >
> > <font color = red>**每创建一个Project，默认就会创建一个Module，并且该Module的名字和Project相同！**</font>
> >
> > 当然，在IDEA中要想创建Module，必须在一个Project的基础上才能够完成创建。
> >
> > 从操作系统层面上看，每个Module也是一个独立的文件夹！

> `>(red)`
>
> 在IDEA中创建Module遵循以下步骤：
>
> 1. **右键任何一个Project--->New--->Module（创建Module必须在Project基础上进行）**
>
> 2. 接下来步骤大致和创建Project相同（因为创建Project本质也是新建了一个Module）
>
>    - 选择选择JDK版本（**选择Java8**）
>    - 无需选择模板，直接下一步
>
> 3. Module的命名，规则和Project类似，不再赘述。
>
> 4. 创建Module时，在选择路径时，可以注意一下层级关系：
>
>    <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202202241512636.png?align=center" alt="Module之间的关系" style="zoom: 50%;" />
>
>    1. **如果你希望第二个Module和第一个Module是同级关系，目录路径上要体现同级。**
>    2. **如果你希望第二个Module和第一个Module是上下级关系，目录路径上要体现上下级。**
>    3. 这里有一个细节就是：设置的时候只需要修改<span style=color:red;background:yellow>**Content root**</span>即可，其它部分都会随之改变。
>
> 5. 上述设置完成后，其它设置保持默认即可。最后点击**Finish**，即完成创建Module。
>
> 至此，IDEA当中的项目管理结构你基本已经知道了，可以开始写代码了！

## Project和Module的区别

> `>(green)`
>
> 通过在IDEA种创建两种结构Project和Module，不难发现实际上两者没有本质区别。
>
> 总得来说：**Project是概念上的顶层结构，Module是IDEA的基本单元。** 
>
> 1. IDEA中的Project不是一个独立的概念，新建一个Project的实质是创建了一个独立的Module，并且这个module的名字和project名字一样。
> 2. 一个Project可以有多个module
> 3. 主流的大型项目结构基本都是多Module的，这类项目一般是按功能划分模块，模块之间彼此可以相互依赖。
>
> <font color=red>**两个注意事项：**</font>
>
> 1. <span style=color:red;background:yellow>**在JavaSE阶段，没有必要创建多Module的Project。即便创建了，多个module之间实际也不会相互依赖。**</span>
> 2. 每个Project用独立的窗口打开，而不是堆在一个窗口中。

###### THE END
