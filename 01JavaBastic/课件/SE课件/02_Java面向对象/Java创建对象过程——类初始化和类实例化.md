###### <sub><font color = orange>JAVASE基础教程</font></sub><br />——<br /><sup><font color=white>卷6</font></sup><font color=white>深入理解面向对象</font><br/><sup><sub><font color=cyan>节1</font></sub><font color=cyan>Java创建对象过程</font></sup><br/><br/>	``#最新版本|V1.0#(purple) ``<br/>**长风**<br/>*COPYRIGHT ⓒ 2021. 王道版权所有*

[TOC]

# 概述

> `>(green)`
>
> 在Java程序中，一个对象的创建往往包括两个步骤：
>
> 1. 类初始化（也就是类加载，创建对象的准备工作）
> 2. 类实例化（即具体创建对象的过程）
>
> 本文主要参考了《Java编程思想（Thinking in Java）》和《深入理解Java虚拟机》这两本书，以最常见地Java HotSpot虚拟机为例，深入刨析这两个步骤。当然，如果有错误，欢迎大家给出指导建议。

# 类加载概述

> `>(green)`
>
> 相信看到本文的同学，大致对类加载都要以下认知：
>
> > 一个`.java`文件中的所有class类，都会在编译后生成相应的一个或者多个`.class`文件（若一个类中含有内部类，则编译后会产生多个`.class`文件）。当JVM需要使用某个类时，就需要将该类的`.class`字节码文件读取进JVM内存中，从而JVM能够从`.class`文件中获取这个类的类型信息，最终形成可以被虚拟机直接使用的Java引用数据类型的过程，就是Java的类加载机制。
>
> 以上理解当然是没问题的，但是有些过于简略的，下面我们详细详解一下类加载。

## 类加载的详细步骤

> `>(green)`
>
> 在Java程序中，类加载都是在程序的运行期间完成的，这样会导致类加载的过程会增加一些程序性能开销，但这样动态的类加载，也为Java程序提供了很大的灵活性。比如，创建一个接口的引用，可以等到运行时期再指向其具体的实现对象，Java语言的多态性就是这么实现的。
>
> 但类加载的性能开销仍不能忽略，为了提升程序的性能，<span style=color:red;background:yellow>**Java中的类加载是懒加载的**</span>，也即只有到了必须使用某个类型时，才会"迫不得已"进行类加载！
>
> ---
>
> 首先，一个类从被加载到JVM内存中开始，到卸载出内存为止，**一个类的生命周期**包括：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202203131434667.png?align=center" alt="类生命周期" style="zoom: 28%;" />
>
> 一共是：**加载**（Loading）、**验证**（Verification）、**准备**(Preparation)、**解析**(Resolution)、**初始化**(Initialization)、**使用**(Using) 和**卸载**(Unloading)七个阶段。其中，验证、准备和解析可以统称为<font color=red>**连接**</font>(Linking)。
>
> 现在，我们先不着急了解这每个步骤所做的事情，这会在后文中再详细说明。只需要注意类加载的两个原则：
>
> 1. 类加载的过程必须"按部就班"的<font color=red>**开始**</font>，比如必须先加载才能连接，想要初始化必须先加载和连接。但类加载的过程不是按部就班的<font color=red>**完成**</font>的，**可能会在一个阶段执行的过程中调用或激活另外一个阶段。**
> 2. 解析这个步骤很特殊，它在某些情况下可以在初始化阶段之后再开始进行，这是为了支持Java语言的动态绑定。
>
> 

> 