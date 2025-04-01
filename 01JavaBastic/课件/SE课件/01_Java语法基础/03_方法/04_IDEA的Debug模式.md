###### <sub><font color = orange>JAVASE基础教程</font></sub><br />——<br /><sup><font color=white>卷3</font></sup><font color=white>方法</font><br/><sup><sub><font color=cyan>节4</font></sub><font color=cyan>IDEA的Debug模式</font></sup><br/><br/>	``#最新版本|V3.0#(purple) ``<br/>**长风**<br/>*COPYRIGHT ⓒ 2021. 王道版权所有*

[TOC]

# 概述

> `>(green)`
>
> **为什么学习IDEA的Debug模式？**
>
> 开发中经常需要应对的场景：
>
> 1. 程序运行往往都会存在超出或者不符合程序员预期的情况
> 2. 这个时候如果光凭借人肉眼去观察程序，很难直接去断定程序的问题
>
> 于是IDEA给我们提供了Debug模式来追踪代码的运行流程。
>
> ---
>
> **Debug模式的作用：**
>
> 1. 启动Debug模式程序员可以控制程序的暂停与执行
> 2. **启用Debug模式可以查看程序运行过程中参数数据的变化**
> 3. 可以用来定位程序中问题发生的位置，方便程序员去修正这些问题
> 4. **更高端的**，可以启用Debug模式来跟踪代码的运行流程去学习源码，复杂的开源框架等等（需要一定水平）
>
> 本节中，都以下列代码为演示：
>
> ###### Debug代码
>
> ``` java
> public class Demo {
>     public static void main(String[] args) {
>         System.out.println("hello world");
>         int a =  10;
>         int b  = 20;
>         int c ;
>         c = a + b;
>         Scanner sc = new Scanner(System.in);
>         int num = sc.nextInt();
>         if (num > 0) {
>             System.out.println("你输入的数大于0");
>         } else if (num == 0) {
>             System.out.println("你输入的数等于0");
>         } else {
>             System.out.println("你输入的数小于0");
>         }
>         for (int i = 0; i < 5; i++) {
>             for (int j = 0; j < 3; j++) {
>                 System.out.print("#");
>             }
>             System.out.println();
>         }
>         method();
>     }
>     public static void method() {
>         System.out.println("test");
>         System.out.println("test");
>         System.out.println("test");
>         System.out.println("test");
>     }
> }
> ```

# Debug窗口介绍

> 以下截图是常见的IDEA的Debug模式截图（2018.3）

<img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202112290220865.png?align=center" alt="IDEA-Debug模式详解图" style="zoom: 33%;" />

> `>(green)`
>
> 逐个项目解释（标号对应图中的标号）：
>
> 1. 这个像**虫子一样的按钮**，是开启IDEA的Debug模式，表示用Debug模式执行程序
>
> 2. **断点**，在左边行数栏单击左键表示一个断点。当程序以Debug模式执行时，程序会在断点处暂停执行
>
> 3. **程序暂停的行数**，特别需要注意的是，该行表示程序即将执行的行数，**但是还未执行**
>
> 4. **Debug窗口**，当使用Debug模式执行程序，会自动激活该窗口。该窗口是使用Debug模式的核心界面
>
> 5. 左边的Debugger表示Debug模式窗口，右边的Console实际上就是Run模式下的控制台
>
> 6. **调试功能按钮**，该区域的按钮是使用Debug模式的核心功能按钮，逐一介绍如下（从左往右）
>    
>    - **Show Execution Point** （Alt + F10）：如果你的光标或者视角在其它行或其它页面，点击这个按钮可跳转到当前程序暂停的行数。了解即可
>    
>    -  <span style=color:red;background:yellow>**Step Over（F8）**</span>：下一步，执行当前暂停行，在下一行暂停。如果当前行上有方法不会进入方法，而是直接给出方法执行完毕的结果。**核心功能**
>    
>    -  <span style=color:red;background:yellow>**Step Into**</span>（F7）：下一步（进入），执行当前暂停行，在下一行暂停。如果当前行上有方法就会进入方法，从而可以看到方法的具体执行流程。**核心功能**
>    
>    - **Force Step Into**（Alt + Shift + F7）：强制下一步，能进入任何方法，通常一些官方类库的底层源码是无法通过**Step Into**直接进入查看的，这个时候需要强制进入。了解即可
>    
>    - **Step Out**（Shift + F8）：跳出（方法），从当前方法内部直接跳出，但是会直接执行完毕该方法，该功能按钮了解即可
>    
>    - **Drop Frame**：回退上一步（方法的执行），和Step Out类似。不同的是，该功能不会导致方法执行完毕，可以重头再来一次方法的执行。了解即可
>    
>    - **Run to Cursor**（Alt + F9）：Debug模式运行到光标处，你可以直接使用光标定位你要Debug查看的程序行数，代码会运行至光标行，而不需要打断点。了解即可
>    
>    - **Evaluate Expression**（Alt + F8）：计算表达式，在Debug程序的过程中，用程序内存中已存在的变量去重新定义表达式，计算表达式的取值。仅作了解
>    
> 7. **Debug执行相关功能按钮**，从上到下，逐一介绍
>    
>    - **Rerun 'xxx'**：重新以Debug模式运行程序，会关闭服务后重新启动程序。核心功能
>    -  <span style=color:red;background:yellow>**Resume Program**</span>（F9）：继续（恢复）程序，该按钮从实际作用上来看是用来跳过当前断点。如果后面有其他断点则程序在其他断点处暂停，否则程序会直接执行完毕
>    - Pause Program：暂停程序，无实际用途
>    - **Stop 'xxx'**（Ctrl + F2）：关闭Debug模式，需要注意的是程序仍然会执行结束，如果它可以的话
>    - **View Breakpoints** （Ctrl + Shift + F8）：查看所有断点，可以对工程所有断点做一系列操作
>    - **Mute Breakpoints**：使断点全部失效，了解有该功能即可
>    
> 8. **frames即方法调用栈桢**：这里显示的是方法调用的栈帧，处在最上层的方法栈帧是正在调用的方法。
>
>     <font color=red>**注：学完数组中的JVM内存模型小节，再看第8点会更清晰。**</font>
>
> 9. **Variables**：变量区，这是我们需要重点关注的区域，在这里可以查看当前程序运行中内存中存在的变量的取值。

# 变量查看

> `>(green)`
>
> 在Debug过程中，跟踪查看变量的变化是非常必要的。这里单独拎出来，IDEA中可以查看变量的几个地方。

## 代码中直接查看

> `>(green)`
>
> 如下图所示，参数所在行后面会显示当前变量的值：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202112290221742.png?align=center" alt="代码中直接查看变量值" style="zoom: 50%;" />

## 鼠标悬停变量查看

> `>(green)`
>
> 如下图所示，鼠标光标悬停到参数上，显示当前变量信息：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202112290222290.png?align=center" alt="鼠标悬停变量查看图" style="zoom:50%;" />

## Variables窗口查看

> `>(green)`
>
> 以上两种方式实际中很少使用，更经常的我们在Variables窗口中查看变量的取值，如下图：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202112290223497.png?align=center" alt="Variables窗口查看变量值" style="zoom: 50%;" />

# 额外的使用技巧

> `>(red)`
>
> > 以上三点学会已经可以应付绝大多数Debug场景，现在学习一些额外的技巧提升生活质量

## 智能进入方法

> `>(red)`
>
> 如果一行代码中调用了多个方法（这在后面会很常见）常规的Step Into和强制进入都是从左到右按方法执行顺序进入方法内部
>
> 如果想要直接进入查看后面的方法，就做不到了。这时就需要智能步入（Smart Step Into），该功能位于功能栏中的run之下，比如对于下列代码：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202112290225783.png?align=center" alt="一行定义多个方法" style="zoom: 67%;" />
>
> 点击智能步入：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202112290225276.png?align=center" alt="智能步入按钮图" style="zoom: 50%;" />
>
> 会出现以下效果：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202112290226600.png?align=center" alt="智能步入效果" style="zoom: 67%;" />
>
> 这样你就可以自由选择想要进入查看的方法了，而不再是只能从左往右查看方法。

## 断点条件设置

> `>(red)`
>
> 程序中经常会有循环，if语句等带有条件的结构，如果循环的次数很多，不太容易达到我们想要看的条件。这个时候就需要给断点设置条件，这比一步步的Debug进行下去要省事很多。
>
> 比如以下代码：
>
> ###### 断点条件设置案例
>
> ``` java
> public static void main(String[] args) {
>  for (int i = 0; i < 100; i++) {
>      if (i == 50) {
>          System.out.println("hello world!");
>          continue;
>      }
>      System.out.println("666");
>  }
> }
> ```
>
> 如果我们就只想看i = 50时的执行效果，难到就点击step into让循环执行50次，未免太笨了。
>
> 那么如何设置断点条件呢？很简单，直接在断点上右键就可以设置当前断点的条件了，如下图:
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202112290229174.png?align=center" alt="断点条件设置" style="zoom:50%;" />
>
> 这样断点就会自动停在i = 50 的位置了，方便省事。

> `>(green)`
>
> 其余技巧，可以自行总结积累。

###### The End
