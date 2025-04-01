###### <sub><font color = orange>JAVASE基础教程</font></sub><br />——<br /><sup><font color=white>卷1</font></sup><font color=white>Object类</font><br/><sup><sub><font color=cyan>节3</font></sub><font color=cyan>toString方法</font></sup><br/><br/>	``#最新版本|V1.0#(purple) ``<br/>**长风**<br/>*COPYRIGHT ⓒ 2021. 王道版权所有*

[TOC]

# 方法的声明

> `>(green)`
>
> Object类当中`toString`方法的，方法的声明很普通：
>
> ``` java
> public String toString()
> ```
>
> 没有什么注意的地方，记住它有返回值，并且返回字符串String对象就可以了。

# API文档中解释

> `>(green)`
>
> 关于`toString`方法的作用，在JDK文档中，有详细的说明，主要说以下四点：
>
> 1. 返回该对象（调用toString方法的对象）的字符串表示。
> 2. 通常，`toString`方法会返回一个“以文本方式表示”此对象的字符串。
> 3. 结果应是一个简明但易于读懂的信息表达式。
> 4. 建议所有子类都重写此方法。
>
> 所以，`toString`方法的作用非常简单，说白了，把对象转换成字符串，就和它的方法名一样。而且这个字符串要简洁明了，就能够来描述这个对象，而且最后一句话，已经表明了：Java设计者自己都觉得Object类当中的，`toString`方法的默认实现不是一个最优解，子类可以根据自身情况选择重写它。
>
> 于是我们需要看一看，Object类当中它的默认实现，到底啥样。

# toString方法的默认实现

> `>(green)`
>
> `toString`方法在Object类当中的方法代码，非常简单，如下：
>
> ###### Object类当中的toString方法
>
> ``` java
> public String toString() {
>     return getClass().getName() + "@" + Integer.toHexString(hashCode());
> }
> ```
>
> 该方法会返回一个字符串，这个字符串的组成是：
>
> 1. 用当前对象调用`getClass`方法，获取运行时对象后，直接调用`getName`方法。这是获取这个类型的全限定类名。
> 2. `hashCode`方法返回当前对象的哈希值，在Object类当中，它的默认实现是通过计算对象的地址，转换成一个十进制的数字来实现的。然后`Integer.toHexString()`会再将这个十进制的地址值，转换成十六进制的地址值字符串。于是`Integer.toHexString(hashCode()`整体就表示某个对象的十六进制地址值。
>
> 好了，这个格式连起来就是：
>
> > 全限定类名 +  @  +  对象的十六进制地址值
>
> 这你再熟悉不过了，这就是我们在学习`对象与类`小节时，直接打印对象名的结果。

# 作用

> `>(red)`
>
> 看到这里，你应该已经明白`toString`方法的作用了。 <span style=color:red;background:yellow>**在Java中，如果直接打印一个对象名（引用名），默认就会调用该类的toString方法。**</span>如果类中没有重写该方法，就会去使用Object类的默认实现，在之前，我们看到的打印对象名出来的地址值，都是这么来的。
>
> 实际上，所有把对象名和字符串操作连接起来的地方，都会自动调用对象的`toString`方法。
>
> 比如：
>
> ###### toString自动调用
>
> ``` java
> // s是一个引用
> System.out.println(s);
> System.out.println(s + "hello");
> ```
>
> ---
>
> 上述案例都搞明白后，应该就能够明白为什么推荐子类重写该方法了吧？因为在多数时候，我们并不关心全限定类名和地址值，一个对象的字符串描述，我们更希望能看到对象的状态（成员变量的取值）。要想实现这种需求，就需要自己重写`toString`方法。重写没有固定的标准格式，一般拼字符串连接成员变量的取值，就可以了。比如下列代码：
>
> ###### toString方法的重写
>
> ``` java
> // Student类中
> @Override
> public String toString() {
>     return "Student{" +
>             "s=" + s +
>             ", id=" + id +
>             ", age=" + age +
>             ", score=" + score +
>             '}';
> }
> ```
>
> 这样，我们再去打印对象名，或者用对象名拼接字符串，看到的就不再是地址值了。而是我们重写后`toString`方法的调用结果。

# 注意事项

> `>(green)`
>
> toString方法的使用，总体很简单，但我们还是要讲以下几个细节：
>
> 1. toString方法就是<font color=red>**为了完成打印成员变量取值的工作的，不要在里面写一些奇怪的代码**</font>。IDEA的debug模式下，会自动调用类的toString方法，去在界面上展示对象。如果你在toString方法中写赋值或者其它语句，就会<span style=color:red;background:yellow>**导致debug模式运行下，代码运行结果不正确，但正常run模式启动，结果正常的奇怪情况。**</span>
>
> 2. toString方法可以快速自动生成，仍然用alt+insert。一般情况下，没有特殊需求，直接自动生成即可，没有必要手写。
>
> 3. 如果类中有（自定义）引用数据类型成员变量，也需要重写它的toString方法，不然就会打印地址值了。
>
> 4. 为了避免空指针异常，<font color=red>**打印对象名或对象名拼接字符串中**</font>的**隐含调用的toString方法**能不写出来就不要写出来，不要画蛇添足。
>
>    因为隐式调用不会空指针异常，但直接调用方法完全可能出现空指针异常。
>
> 

###### The End
