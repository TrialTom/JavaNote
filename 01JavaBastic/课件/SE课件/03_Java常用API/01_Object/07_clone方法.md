###### <sub><font color = orange>JAVASE基础教程</font></sub><br />——<br /><sup><font color=white>卷1</font></sup><font color=white>Object类</font><br/><sup><sub><font color=cyan>节6</font></sub><font color=cyan>clone方法</font></sup><br/><br/>	``#最新版本|V1.0#(purple) ``<br/>**长风**<br/>*COPYRIGHT ⓒ 2021. 王道版权所有*

[TOC]

# 方法的声明

> `>(green)`
>
> clone方法的方法声明为：
>
> ``` java
> protected native Object clone() throws CloneNotSupportedException;
> ```
>
> 需要注意的地方：
>
> 1. 首先注意访问权限，它的访问权限是protected。在不同包下只能在子类当中，创建子类自身对象才能够访问。当然访问权限在子类中可以被重写。
> 2. 它是一个本地native方法，没有方法体。
> 3. 返回值类型是Object，同样这个返回值类型也是被重写修改的。
> 4. `throws CloneNotSupportedException`仍然是方法抛出异常的声明，这里我们先不管，后面异常的章节会讲解。

# 方法的作用

> `>(green)`
>
> 这个方法的名字，其实已经很明显的告诉了你它的作用：
>
> > 克隆，生物学意义上的克隆，是用生物的体细胞，经过无性繁殖，得到相同基因型后代的过程。
>
> Java当中的克隆方法`clone()`有类似的作用，当你在程序中有这种需求：
>
> 1. 希望得到一个完全独立的对象。
> 2. 成员仍和原先对象一致。
>
> 普通的作用就是再new一个一模一样的，但学习clone方法后，你就可以用它来做这个是事情。 <span style=color:red;background:yellow>**所以Object类当中的clone方法默认实现，就是得到一个独立的，和原先对象成员一致的新对象。**</span>

# 方法使用步骤

> `>(green)`
>
> 现在，我们已经知道clone方法的作用了，那么怎么使用这个方法呢？直接调用的话，肯定是有些问题的，这里我们就来研究一下clone方法的使用步骤：
>
> 1. 第一步，首先就要解决的就是访问权限的问题。在默认的情况下，只能在子类中自己"克隆"自己，这种需求还是很少见的。所以为了能够在类的外部调用该类的clone方法，<font color=red>**就需要在该类中重写clone方法的访问权限。**</font>
>
> 2. 第二步，可以选择重写方法的返回值类型，从Object改为自身类型。很显然Object当中的克隆方法的默认实现，只会得到一个一模一样且独立的对象，肯定不可能改变对象的类型。**这一步不是必须的，但推荐做一下。**
>
>    注：方法体在多数情况下，我们使用Object默认实现就够了，不要重写它。
>
> 3. 第三步，一个类想要做克隆操作，必须要先实现一个接口`java.lang.Cloneable`，表示该类允许进行克隆。如果一个类没有实现接口`java.lang.Cloneable`，又要强行进行克隆操作，就会抛出异常`CloneNotSupportedException`。 <span style=color:red;background:yellow>**接口`java.lang.Cloneable`是一个类能够调用clone方法的标志。**</span>
>
> 4. 完成以上三步，就可以在需要的地方去完成一个对象的克隆了。

# 克隆使用中的细节问题

> `>(green)`
>
> 只要按照上述步骤执行，一定是可以完成一个对象的克隆的。当然实际使用下来，还有一些细节的问题。

## Cloneable接口的问题

> `>(green)`
>
> 一般情况下，因为接口中往往有抽象方法，某个普通类实现一个接口往往会报错，会要求该类必须实现抽象方法。但是某个类实现接口`java.lang.Cloneable`却不会报错，这是因为接口并没有抽象方法，甚至查看源码就会发现：
>
> <span style=color:red;background:yellow>**Cloneable接口是一个空接口，里面没有任何内容。**</span>
>
> 那么让类去实现一个空接口，有什么意义呢?
>
> 实现空接口虽然没有得到任何成员，但这个类的数据类型就发生了一些变化。让这个类从原先不是这个接口的子类，变成了接口的子类，一旦成为接口的子类，就可以使用`instanceof`关键字进行类型的判断，判断到底是否该接口。从而就可以根据不同的情况，做出不同的处理。比如下列代码，Cloneable接口的底层也是这么判断的：
>
> ###### 空接口的作用
>
> ```Java
> public class Demo {
>     public static void main(String[] args) {
>         judgeInstanceImplEmptyInter(new A());
>     }
>     //用于判断传入的对象是否是接口的子类对象
>     public static void judgeInstanceImplEmptyInter(Object o) {
>         if (o instanceof EmptyInterface) {
>             System.out.println("实现了空接口,可以做一些操作");
>             return;
>         }
>         System.out.println("没有实现空接口,抛出异常");
>     }
> }
> interface EmptyInterface {
> }
> class A implements EmptyInterface {
> }
> ```
>
> 上述代码中，由于A已经实现了接口，所以`instanceof`的结果就是true。程序会输出`实现了空接口,可以做一些操作`。
>
> <font color=red>**像Cloneable这种没有任何成员，一个空接口，它其实就起到一个标记的作用，称之为"标记接口"。被Cloneable标记的类是允许做克隆操作的，反之不允许。**</font>JDK中的标记接口，我们在后面还会见到。

## 创建对象的方式

> `>(green)`
>
> clone方法是一种新的创建对象的方式，和new对象的方式是平行的关系，是独立的关系。
>
>  <span style=color:red;background:yellow>**调用clone方法得到对象的过程，是依赖于本地方法实现的，不会去调用构造器。**</span>

## 方法体的重写

> `>(green)`
>
> 上面我们已经说过了，在进行克隆操作时，正常情况下，我们使用Object类当中的默实现就足够了，不需要重写实现。但假如你真的有需求，对于某个对象的引用`x`，JDK文档中也规定了一些重写的原则：
>
> > 1. x.clone() != x 为 true
> > 2. x.clone().getClass() == x.getClass() 一般也为true
> > 3. x.clone().equals(x) 一般情况下也为true
>
> 上述规定告诉我们：
>
> 1. 克隆必须是一个新的独立的对象
> 2. 克隆最好不要改变数据类型，除非你真的有需要。
> 3. 克隆后的两个对象调用equals方法，应该返回true。前提是，必须按照成员变量的取值重写equals方法。

## 深度克隆

> `>(red)`
>
> 如果类中有引用数据类型的成员变量，那么clone方法的使用就要格外注意了：
>
> 1. Java当中，Object类的clone方法的默认实现是`完全直接拷贝`一份成员变量。
>    1. 对于基本数据类型的成员变量来说，没有任何问题，直接拷贝值。
>    2. 但对于引用数据类型而言，拷贝的是引用。这意味着克隆后的引用和原先的引用指向同一个对象。
> 2. 这样的话，使用任何一个引用去修改对象的状态，都会互相影响，**这样的两个对象就不是完全独立的了。**
>
> 像以上Object类当中的clone方法的实现，直接拷贝一份成员变量，不管引用数据类型成员变量引用，所指向的对象。我们称之为"浅克隆"。
>
>  <span style=color:red;background:yellow>**对应的，如果能够让引用数据类型成员变量之间也能相互独立，克隆后获取真正独立的两个对象。我们称之为"深度克隆"。**</span>
>
> 深度克隆怎么做呢？
>
> 其实非常简单，浅克隆之所以两个对象没有真正独立，是因为拷贝引用和原先的引用指向了同一个对象，现在只需要：
>
> 1. 将引用指向的对象，也克隆一份。
> 2. 然后让克隆后的引用指向它。
>
> 参考下图：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202201120159531.png?align=center" alt="深度克隆" style="zoom: 33%;" />
>
> 当然，这个过程中，需要在类中重写clone方法，参考代码如下：
>
> ``` java
>  //重写clone方法的访问权限
>  @Override
>  protected Student clone() throws CloneNotSupportedException {
>      //仍然选择调用父类默认实现
>      //深度克隆的步骤
>      //1.深度克隆是在浅克隆基础上玩的
>      Student cloneStu = (Student) super.clone();
>      //2.需要把Dog对象克隆一份
>      Dog cloneDog = cloneStu.d.clone();
>      //3.将拷贝引用指向拷贝对象
>      cloneStu.d = cloneDog;
>      return cloneStu;
>      //return ((Student) super.clone());
>  }
> ```
>
> 以上完毕！

# 小试牛刀

> `>(green)`
>
> 深度克隆练习：请完成类FirstLevel的深度克隆。
>
> >  现在有三个类 FirstLevel 、SecondLevel 、ThirdLevel 
>
> FirstLevel 类有三个属性：
>
> ###### FirstLevel 属性
>
> ``` java
> int firstIntValue;
> double firstDoubleValue;
> SecondLevel second;
> ```
>
> SecondLevel 类有三个属性：
>
> ###### SecondLevel 属性
>
> ``` java
> int secondIntValue;
> double secondDoubleValue;
> ThirdLevel third;
> ```
>
> ThirdLevel 类有两个属性：
>
> ###### ThirdLevel 属性
>
> ``` java
> int thirdIntValue;
> double thirdDoubleValue;
> ```
>
> 这个案例的对象内存图，画出来大概就是下面这个样子：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202201122342645.png?align=center" alt="双层深度克隆问题" style="zoom: 50%;" />
>
> 通过图来理解这个概念，应该会更好。如果还有问题，互相交流一下。

###### The End
