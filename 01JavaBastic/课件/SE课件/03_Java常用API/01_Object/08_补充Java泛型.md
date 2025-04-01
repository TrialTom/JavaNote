# Java泛型

> Java 泛型（generics）是 JDK 5 中引入的一个新特性。到如今看来，它已经成为Java日常开发中必须使用的、最常用的语法之一。关于泛型，王道后续的课程中，仍然会详细讲解它，如果你学有余力并迫不及待了解它，可以参考本文档~

[TOC]

## 泛型概述

首先我们要知道，泛型的出现离不开集合，换句话说正是为了更好的使用集合，Java才出现了泛型的语法。按照课程进度，我们还未学习过集合，所以为了讲解泛型，我们需要简单了解一个集合类：ArrayList 作为示范案例使用。

> ArrayList是最常见的集合类之一，它的底层实现是数组，某种程度上来讲它是数组的“包装类”，是一个可变长、自动扩容的“数组”。

但是需要注意的是，集合中只能存储对象，不能存储基本数据类型。由于包装类具有“自动拆装箱”的特性，直接存储基本数据类型在语法上看是没有任何问题的，但是需要知道**实际存储的是它的包装类对象而不是值本身**。

ArrayList的无参构造如下：

```Java
/**
* Constructs an empty list with an initial capacity of ten.
*/
public ArrayList() {
	this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
}
```

通过注释很容易知道：无参构造会初始化一个长度为10，但是没有任何元素的（空的）链表（数组）。这个数组的长度是可以扩容的，具体这里就不详谈了，感兴趣可以看看ArrayList源码或者自己查阅一下。

ArrayList存储删除查看元素都是通过成员方法实现的，这是它和数组很大的不同之处，常用的成员方法有：

```Java
 boolean add(E e) //将指定的元素添加到此列表的尾部。 
          
 void add(int index, E element) //将指定的元素插入此列表中的指定位置。
          
 E get(int index) //返回此列表中指定位置上的元素。 
          
 E remove(int index) //移除此列表中指定位置上的元素。
     
 Object[] toArray() //按适当顺序（从第一个到最后一个元素）返回包含此列表中所有元素的数组。 
     
 int size() //返回此列表中的元素数。 
     
 ...
```

其中index表示的是数组的下标，E实际上就是一个泛型，现在你可以将它看成是一个通配任何引用数据类型的类型。至于方法的作用，看方法名即可得出，注释上也写的很明白了。在往下学习之前，你可以先用无参构造创建一个ArrayList对象，然后用成员方法完成插入，删除，查找数组等操作。

---

### 背景

> 我们可以先来看一看在没有使用泛型时，集合的使用，直接来一段代码

```java
ArrayList als = new ArrayList();
als.add("java");
als.add(123); //存储的是int包装类Integer对象
als.add(new Demo());

for (int i = 0; i < als.size(); i++) {
    Object o = als.get(1);
    String s = (String) o;
    System.out.println(s);
}
```

很显然：

在没有使用泛型的集合中，编译器默认该集合的元素类型是Object，会将每一个元素当成Object对象存储。这固然使得集合的使用很灵活，但也带来了更多的麻烦：程序员必须清楚知道集合中每个元素的数据类型，稍有不慎就会出现问题。比如上述代码，就将报错：

```Java
Exception in thread "main" java.lang.ClassCastException:
									java.lang.Integer cannot be cast to java.lang.String
```

比起灵活，Java程序更看重的往往是安全性，这在很多地方都有所体现。所以为了限制集合中元素的数据类型，让集合能够更加安全得使用，Java引入了泛型。



### 概念

> 首先看一个泛型的官方定义

```
Java泛型（generics）是JDK5中引入的一个新特性，泛型提供了编译时类型安全监测机制，该机制允许我们在编译时检测到非法的类型数据结构。泛型的本质就是参数化类型，也就是所操作的数据类型被指定为一个参数。
```

直接看这个定义，肯定是没法理解泛型的，不要捉急慢慢往下学习。这段定义主要有以下两个关键点：

1. **编译时，泛型是编译时的概念**。编译后的Java代码中没有泛型，泛型不参与代码的运行过程。

   为了验证这一点，不妨查看以下代码：

   ```Java
   ArrayList<String> stringArrayList = new ArrayList<>();
   ArrayList<Integer> integerArrayList = new ArrayList<>();
   
   if (stringArrayList.getClass() == integerArrayList.getClass()) 
       System.out.println("它们的运行时对象是同一个对象，泛型不影响程序运行");
   ```

   > 程序输出：
   >
   > 它们的运行时对象是同一个对象，泛型不影响程序运行

   ```
   通过上面的例子可以证明，在编译之后程序会采取去泛型化的措施。也就是说Java中的泛型，只在编译阶段有效。
   在编译过程中，正确检验泛型结果后，会将泛型的相关信息擦除，称之为泛型“类型擦除”（后面会详细讲）也就是说，泛型信息不会进入到运行时阶段。
   
   对此总结成一句话：具有不同泛型的多个类型在逻辑上可以看成是多个不同的类型，实际上都是相同的类型。
   ```

2. **泛型的本质是参数化类型，且仅表示引用数据类型，不能表示基本数据类型。**

这里就需要详细解释一下参数化类型：

> 一提到参数，我们最熟悉的就是定义方法时的形参，方法调用时需要传入的实参。假如方法需要传入一个String对象作为参数，那么就可以传入任意一个字符串。

**方法只有在真正调用时，才能确定它的参数是什么。**

> 类似于方法中的参数，在很多时候，我们都需要去指定类型，比如方法形参的类型。这时侯，把这个类型定义成为参数的形式（即类型形参），然后在使用/调用/需要时传入具体的类型（即类型实参）

**参数化类型（泛型）也只有在使用时，才能确定它的类型到底是什么。**由此看泛型也存在定义（类型形参）和具体使用（类型实参）两部分。

泛型（参数化类型）可以定义在类、接口和方法中，分别称之为**泛型类、泛型接口、泛型方法**。

---



以上只是概念，现在我们先不理会泛型的具体语法，来看一段代码加深理解。我们仍然使用ArrayList这个类，它是一个泛型类，可以参考它的类定义和部分成员方法的定义：

```java 
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable{
    		
    		public boolean add(E e) {
                //...
            }
        
    		public E get(int index) {
        		//...
    		}
    
    		//...
	}
```

> 其中 <E> 就表示一个泛型，我们可以在创建ArrayList对象时，指出它的具体类型，这就是所谓的参数化类型
>
> <E>还会一并传入方法中，限制add方法的形参类型，get方法的返回值类型等等

```Java
ArrayList<String> als = new ArrayList<>();
als.add("123");
//als.add(123) 报错
als.add("345");
als.add("hello");
for (int i = 0; i < als.size(); i++) {
    String s = als.get(i);
    System.out.println(s);
}
```

> 其中 <String> 表示该参数化类型的具体类型是String，那么集合中就只能存放String类型对象，一旦想存入别的数据类型对象就会编译报错。
>
> 并且由于编译器明确知道该集合中元素的类型，取元素的时候也不再是取出Object对象，而是具体的类型对象。

以上只是泛型类、泛型方法的基本使用，随后我们再学习它的语法。



### 优点

学完上面的知识点，你对泛型应该处在一知半解的状态了。那么不妨先来了解一下泛型的优点，毕竟先知道它为啥好用，学起来才有动力嘛。

1. **使用泛型能写出更加灵活通用的代码**

   ```
   即便是仅仅参考上面的代码，也不难发现使用泛型后，代码变得更加通用，更加灵活了。参数化类型（泛型）只需要在使用时给出具体类型，不同的类型参数（实参）就可以得到完全不同的效果，这其实就是泛型设计的初衷之一。
   ```

   > 想一想如果没有泛型，难道要针对每一种要存储的元素的类型设置一种集合嘛？这显然是不合理的

2. **泛型将代码安全性检查提前到编译期**

   ```
   没有泛型之前，编译器是不会检测集合容器中元素的数据类型的，因为它们全部都是Object。使用泛型后，能让编译器在编译的时候借助传入的类型参数（实参）检查对集合容器的插入，获取等操作是否合法。
   ```

   显然，这一系列的操作**将运行时异常（ClassCastException）提前到编译时期被检查出来**，大大提升了程序的安全性。使用泛型后，**集合操作就几乎不会再出现类型转换异常（ClassCastException）了！**

3. **消除了强制类型转换**

   ```
   类型从来都是对编译器而言的，既然（参数化类型）泛型已经明确指出类型，也就无需再做强转了。
   ```

接下来开始学习泛型语法。



## 泛型语法

> 使用泛型，必不可少的就是泛型标识符，我们先来看一下泛型标识符

### 标识符

所谓泛型标识符就是上面代码，定义泛型类或者泛型方法时，尖括号<>中的大写字母，比如\<E>中的E。严格来说，泛型标识符只要符合标识符的语法就可以随便写，比如写出\<a123>这样的泛型标识符，但是实际上泛型标识符也有一套约定俗成的规范：

1. 最好使用单个大写字母做为泛型标识符。

   ```
   小tips：
   	使用单词作为泛型标识符是极其罕见的方式，除非特别有必要，不然不要这么做！
   ```

2. 某些特殊字母有特殊含义，常用的如下

| 标识符 |    含义     |                        说明                        |
| :----: | :---------: | :------------------------------------------------: |
| **E**  | **Element** |          常用于集合，表示集合中元素的类型          |
| **T**  |  **Type**   | 表示任何Java类型，普通情况下最常用的泛型标识符之一 |
| **K**  |   **Key**   |                表示Map中的Key的类型                |
| **V**  |  **Value**  |               表示Map中的Value的类型               |
| **N**  | **Number**  |                      数值类型                      |
| **?**  |             |         任何不确定的Java类型，这个后面会讲         |

当然，如果你想要用A、B、C等作为泛型标识符也不是不行，建议优先考虑使用表格中的。



### 泛型类

#### 定义语法

> 泛型类是泛型语法最简单的使用，直接看泛型类的定义语法：

```Java
[修饰符] class 类名<泛型标识符1,泛型标识符2...> {
	//类体
}
```

**注意事项：**

1. 建议类名后直接尖括号定义泛型，不要加空格（源码中都是这么写的）
2. 尖括号里可以写多个泛型标识符，用逗号隔开
3. 泛型标识符仅表示类型形参，具体类型实参要创建对象时才能决定
4. 泛型标识符所表示的类型形参可以在类体中定义变量、方法

> 参考如下定义泛型类的代码：

```Java
class Generics<T> {
    //定义成员变量
    private T type;

    public void test(){
        //T a = new T(); 类型形参不能用于创建对象，因为该类型完全可能没有这个构造器
        //局部变量也可以定义
        T t;
    }

    //构造器
    public Generics(T type) {
        this.type = type;
    }

    //get、set方法
    //定义类型形参作为返回值类型的方法
    public T getType() {
        return type;
    }
    //定义类型形参作为方法形参类型的方法
    public void setType(T type) {
        this.type = type;
    }
}

```



#### 创建对象

> 定义完类后，还需要创建它的对象来使用它，同样直接上语法：

```Java
类名<类型实参> 对象名 = new 类名<>();
```

**注意事项：**

1. 构造器、多态等该咋用咋用，比起之前的语法多了一套前后尖括号表示泛型

2. **类型实参不能是基本数据类型，必须是引用数据类型**。这一点我们会在后面详解原因

3. 在Java6和Java5中，前后尖括号都要写上类型实参，**Java7之后只需要写前面尖括号就行了**

   > 这一点感兴趣的话，可以调整IDEA Language Level 去感受一下

4. 创建对象时使用的类型实参具体类型，将会替换类中任何使用类型形参的位置。

5. 创建对象时不写尖括号泛型也是完全没问题的，默认类型实参是Object类型

   > 创建泛型类对象不加泛型，多数情况下会让代码变得不安全，失去了泛型的设计意义。所以除非有特殊用途，不然创建泛型类对象时，应该加上泛型类型实参。

参考如下代码：

```java
Generics<String> g1 = new Generics<>("abc");
//Generics<int> g2 = new Generics<>(123); 编译报错,类型实参不能是基本数据类型
//Generics<String> g3 = new Generics<>(123); 编译报错,类型实参是String
String t = g1.getType();
System.out.println(t);
g1.setType("xxx");
System.out.println(g1.getType());
System.out.println("------------------------------");
//如果不加泛型语法是没问题的,但是类型形参T默认为Object类型,这在多数情况下是没有意义的
//失去泛型限制,代码就会变得过于灵活,不安全,容易引发强制类型转换失败导致出现类型转换异常
Generics g4 = new Generics("abc");
Generics g5 = new Generics(123);
Generics g6 = new Generics(new Demo());
```

以上就是泛型类的定义和基本使用的语法。



#### 案例练习

> 学习完语法后，接下来做一个简单的使用练习：

**现在需要制作一台生产机器，这台机器总是同时生产同一种产品，生产出来的产品会进行随机售卖**

参考以下代码：

```java
//定义泛型类Producer,T是泛型标识符
public class Producer<T> {
    //产品,必须同时生产同类型产品,且需要进行随机售卖,所以不能任意被访问,需要私有化
    private T product;
    
    //产品仓库,产品生产出来需要存放,同样私有化
    //存放容器的类型可以选择数组,也可以选择集合,写法不同而已,这里我选择ArrayList,使用会方便一点
    private ArrayList<T> repository = new ArrayList<>();

    //随机售卖,需要随机数,该Random对象全局唯一就可以了
    private static final Random RANDOM = new Random();

    //生产方法
    public void produce(T product) {
        repository.add(product);
    }

    //销售方法
    public T sale() {
        //如果没有产品了,不能继续销售了
        if (repository.size() == 0) return null;
        //随机取产品
        T result = repository.get(RANDOM.nextInt(repository.size()));
        //从仓库中取出,然后返回
        repository.remove(result);
        return result;
    }

    //初始化生产机器,需要直接三个产品
    public Producer(T product1, T product2, T product3) {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);
    }
}
```

注：随机数的生成，需要自行学习一下，如果你还不了解的话。

接下来进行一下测试：

```java
Producer<String> stringProducer = new Producer<>("iPhone12", "小米11", "华为Mate40");
stringProducer.produce("三星S11");
//随机售卖十次
for (int i = 0; i < 10; i++) {
    System.out.println(stringProducer.sale());
}
System.out.println("----------------------------------");
Producer<Integer> integerProducer = new Producer<>(666, 777, 888);
integerProducer.produce(333);
//随机售卖十次
for (int i = 0; i < 10; i++) {
    System.out.println(integerProducer.sale());
}
```

没什么好说的了，自己看代码研究一下。

---



#### 泛型子类

> 泛型类当然是可以被继承的，但是语法上有一定限制。如下：

1. 如果子类也是泛型类，子类定义了类型形参，那么父类尖括号里：

   - 要么什么都不写，表示父类的类型实参是Object
   - 要么就必须父子类泛型标识符保持一致，表示父子类类型实参是一致的

   即：

   ```java
   class Son<T> extends Father{}
   //或
   class Son<T> extends Father<T>{}
   ```

2. 如果子类不是泛型类，子类不定义类型形参，那么父类的尖括号里：

   - 要么什么都不写，表示父类的类型实参是Object
   - <font color = red>**要么直接写类型实参的类型，比如String、Integer之类的，而不是泛型标识符。**</font>

   即：

   ```Java
   class Son extends Father{}
   或
   class Son extends Father<类型实参>{}
   ```

泛型类的继承实际中用的可能不会很多，仅供了解不提供额外的案例，真要碰到了，自行摸索也很容易。



---



### 泛型接口

#### 定义语法

> 泛型接口与泛型类的定义及使用基本相同，直接看语法：

```java 
[修饰符] interface 接口名<泛型标识符1,泛型标识符2....> {
    //...
}
```

参考以下代码：

```Java
interface Generics<T> {
    T getKey();
}
```

泛型接口和普通接口无非就多了泛型标识符，接口中的方法仍然是抽象方法~



#### 实现类

> 和泛型类的子类差不多，也有两种情况：

1. 如果实现类也是泛型类，那么接口尖括号里都是类型参数，而不是类型实参
2. 如果实现类不是泛型类，那么接口要明确类型实参（如果不明确没法默认Object）

参考以下代码：

```java 
//接口的类型实参由实现类传递，所以必须保持接口和实现类的泛型标识符一致。当然实现类可以自己扩充更多的泛型
class GenericsImpl<T,N> implements Generics<T>{
    @Override
    public T getKey() {
        return null;
    }
}
//实现类没有泛型需要指出接口的类型实参，否则默认为Object
class GenericsImpl2 implements Generics<String>{
    @Override
    public String  getKey() {
        return null;
    }
}
```

具体的使用，也没有太多可啰嗦的，可以自己尝试一下，总体和泛型类差不多。



### 泛型方法

> 泛型类、接口总体上是比较简单易于理解的，但泛型方法就比较复杂了，迎接挑战~

```
在上面的代码中，我们在泛型类、泛型接口中已经使用泛型标识符替代了，方法的返回值和形参的数据类型位置，那么它们就是泛型方法吗？
```

<font color = red>显然不是</font>，上述**在泛型类中定义的带泛型标识符的成员方法，在创建对象的时候就已经明确了泛型的类型实参**

而泛型方法的定义是这样的：

1. 泛型类，是在实例化类的对象时指明泛型的类型实参的类。
2. **泛型方法，是在调用方法的时候指明泛型的类型实参的方法。**



#### 定义语法

> 首先直接看泛型方法的定义语法：

```Java
[修饰符] <泛型标识符1,泛型标识符2...> 返回值类型 方法名(形参列表) {
 	//方法体   
}
```

注意事项：

1. 泛型方法的定义需要尖括号比如\<T>这种，带尖括号声明的方法才是泛型方法

2. 泛型类中仅带有泛型标识符的成员方法不是泛型方法，可以认为是带泛型的成员方法

   比如上述代码中的：

   ```Java
   //生产方法
   public void produce(T product) {
       repository.add(product);
   }
   ```

3. 泛型方法中定义的泛型类型形参\<T>，可以在方法内部使用

---



先看一个基本案例，参考以下代码：

```Java
//定义一个泛型方法
public <A> void test(){
    A a;
}
```

很容易你就发现了，这样的一个泛型方法当中的\<A>是毫无意义的，做不了任何事情。所以我们**常见的泛型方法都是和泛型类一起使用的**，比如下面这样的：（结合上一个案例，该方法同样定义在一个泛型类Producer中）

```java 
public class Producer<T> {
	//定义一个泛型方法，该泛型方法和泛型类ArrayList一起使用
    public static <E> E getProduct(ArrayList<E> list) {
        //随机获取容器中的某个元素
        return list.get(RANDOM.nextInt(list.size()));
    }
}
```

该泛型方法需要在方法调用时，指出泛型类型形参E所指的具体类型，即类型实参，调用代码如下：

```Java
//方法调用
ArrayList<String> list = new ArrayList<>();
list.add("中国红");
list.add("iPhone33");
list.add("小米17");
String product = Producer.getProduct(list);
System.out.println(product);
System.out.println("----------------------------");
ArrayList<Integer> list2 = new ArrayList<>();
list2.add(123);
list2.add(666);
list2.add(8888);
Integer product1 = Producer.getProduct(list2);
System.out.println(product1);
```

注意事项：

1. **泛型方法中的类型形参和泛型类当中的类型形参是完全独立的，而带泛型的成员方法和类共享泛型形参**，这是一个非常重要的区别。

2. 既然泛型方法和泛型类的泛型类型形参完全独立，实际上它们完全可以使用相同的泛型标识符，如：

   ```Java
   public class Producer<T> {
   	//定义一个泛型方法
       public static <T> T getProduct(ArrayList<T> list) {
           //随机获取容器中的某个元素
           return list.get(RANDOM.nextInt(list.size()));
       }
   }
   ```

   上述定义是符合语法的，且方法能够正常调用。但这种做法终究是不太好的，idea会给我们一个警告信息：

   ```
   Type parameter 'T' hides type parameter 'T'.
   Inspection info: Reports type parameters being named identically to visible types in the current scope. Such a parameter name may be confusing.
   ```

   很明显，相同的泛型标识符会导致confusing（令人困惑）。所以如果你不是为了写出让自己，让别人都难受的代码，还是不要这么用。

   这里给出这个案例，是为了让你理解**泛型类和泛型方法中的两个泛型形参是完全不同的，相互独立的。**

3. 相信你已经注意到了，我在上述代码中给出的泛型方法是用static修饰的，是一个静态方法，可以无需创建对象就能够调用。但是**带有泛型的成员方法是不能使用static修饰的，不能是静态的**，其原因仍然是上述1、2导致的，独立和不独立的区别。（**泛型类必须创建对象指出泛型类型实参，所以不能static修饰**）

4. 泛型方法是可静态、可非静态的。参考以下非静态泛型方法代码：

   <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202110152219158.png" alt="image-20210926094354287" style="zoom:50%;" />

   方法调用：

   ![img](https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202110152219556.png)

   ```
   注：newInstance方法是利用反射创建Class运行时类，所对应的类的对象
   ```

5. 泛型方法也可以使用可变参数，仅供了解，参考以下代码：

   ```Java
   //具体的使用就把E...看成是泛型数组即可,类型根据泛型类型实参决定。
   public <E> void print(E... e){
   	for (E e1 : e) {
   		System.out.println(e);
   	}
   }
   ```
   
   

#### 作用

> 泛型方法和泛型类一样，也是为了提升代码的灵活性而产生的。

主要有以下作用与意义：

1. 泛型方法能够使方法独立于类产生变化，而不像泛型类中带泛型标识符的成员方法

   ```
   这里说一点小tips：
   	定义泛型类要从整个类的设计角度去考虑，而如果仅仅是为了某个方法的灵活性，那么建议定义泛型方法。
   ```

2. 静态方法如果想要使用泛型，就必须定义泛型方法

---



## 泛型通配符

> 提到通配符很容易想到的就是星号“*”，但是在泛型里通配符要使用问号“?”

### 基本语法

先不着急直接了解它的语法和作用，先来看一个案例（需求）。首先定义一个泛型类：

```Java
//定义泛型类
class Generics<T>{
    private T type;

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }
}
```

进行如下测试：

```Java
public class Demo {
    public static void main(String[] args) {
        Generics<Integer> integerGenerics = new Generics<>(123);
        show(integerGenerics);
        
        //报错
		//Generics<String > stringGenerics = new Generics<>("abc");
		//show(stringGenerics);
    }
    
    public static void show(Generics<Integer> generics) {
        Integer type = generics.getType();
        System.out.println(type);
    }
}
```

输出结果显然是：

> 123

```
以上代码大家都很熟练了，我们定义的show方法明确给出了类型实参是Integer，那么方法传入的Generics对象的类型实参必须是Integer，不能是别的任何类型，这是使用泛型增加了代码的安全性，这也是引入泛型的目的。
```

**但这种绝对的、不可传入别的任何类型实参泛型对象的方法也有些不够灵活**，在有些时候我们也需要一定的灵活性。比如下面需求：

> 这里先介绍一个类Number，它是Java当中数值包装类型的抽象父类，假如定义以下show方法：

```Java
public static void show(Generics<Number> generics) {
    Number type = generics.getType();
    System.out.println(type);
}
```

> 我们希望show方法能传入Number类所有子类的类型实参Generics对象，比如传入Generics\<Integer>类型实参对象，这是一个合理需求，但是做得到吗？很显然不行，因为泛型类型实参已经明确是Number类型，不可能是任何其它类型（不管子类非子类）。

为了解决以上问题，实现现实需求，我们提出了泛型通配符的概念：

1. 泛型通配符指的是用“?”问号**填入类型实参的位置、填入类型实参的位置、填入类型实参的位置。**

   既然"?"能够作为一种类型实参，那么下列代码也是允许的：

   ```Java
   //这时，表示ArrayList中能够存入Object对象
   ArrayList<?> list = new ArrayList<>();
   ```

2. 仅仅填入问号表示可以匹配任何类型，即相当于填入Object作为类型实参

参考以下代码：

```Java
public static void show(Generics<?> generics) {
        Object type = generics.getType();
        System.out.println(type);
}
```

测试代码：

```Java
Generics<Integer> integerGenerics = new Generics<>(123);
show(integerGenerics);

Generics<String > stringGenerics = new Generics<>("abc");
show(stringGenerics);
```

输出结果：

> 123
> abc

类型实参填入问号后，就可以匹配任何类型了。但这又违背了泛型的设计：因为它过于灵活了。所以**实际开发中几乎没有直接写问号的用法，而是要设置通配符的上下限！**



### 通配符上限

> 上限是针对继承来说的，限制上限即是限制类型实参的父类必须是某个类

语法:：

```
类/接口<? extends 类/接口名>
```

表示**类型实参**必须是**类/接口名**或者**它的子类类型**，参考以下代码：

```Java
public static void main(String[] args) {
    Generics<Integer> integerGenerics = new Generics<>(123);
    show(integerGenerics);

    Generics<Double> doubleGenerics = new Generics<>(111.11);
    show(doubleGenerics);
}

public static void show(Generics<? extends Number> generics) {
        Number type = generics.getType();
        System.out.println(type);
}
```

测试结果是：

> 123
> 111.11

以上我们发现泛型通配符的使用让泛型变得更加灵活了，但是为了灵活也付出了一些代价：

1. **使用带上限通配符做类型实参的类是无法实例化的**，这一点和使用\<Object>作为类型实参是有区别的

   - 有通配符号的类实例化后，类型实参到底是父类还是子类呢？既然不太好搞清楚，那就从语法上禁止实例化

   例如下列语法是错误的：

   ```java
   //new Generics<? extends Number>();
   ```

2. 接上条，既然不能实例化，所以**使用带上限通配符做类型实参的类，大多用于作为方法形参的数据类型**，这里粘一个ArrayList源代码方法作为参考：

   ```Java
       @Override
       @SuppressWarnings("unchecked")
       public void sort(Comparator<? super E> c) {
           final int expectedModCount = modCount;
           Arrays.sort((E[]) elementData, 0, size, c);
           if (modCount != expectedModCount) {
               throw new ConcurrentModificationException();
           }
           modCount++;
       }
   //...
   ```

3. 再接上条，除了作为方法形参的数据类型，也可以**用于泛型类的通配父类**，参考以下代码：

   ```Java
   Generics<?> generics = new Generics<Number>(123);
   Generics<?> generics2 = new Generics<String>("123");
   Generics<?> generics3 = new Generics<Double>(123.3);
   ```

   这一条仅作了解，实际使用不会特别多，比如ArrayList中有以下源代码：

   ```Java
       public Object clone() {
           try {
               ArrayList<?> v = (ArrayList<?>) super.clone();
               v.elementData = Arrays.copyOf(elementData, size);
               v.modCount = 0;
               return v;
           } catch (CloneNotSupportedException e) {
               // this shouldn't happen, since we are Cloneable
               throw new InternalError(e);
           }
       }
   ```

4. 使用**带上限通配符**的类作为引用类型时，只能调用**获取、拿**之类的操作，不能做**存、赋值**之类的操作。参考以下代码：

   ```Java
   //定义泛型类
   class Generics<T> {
       private T type;
   
       public T getType() {
           return type;
       }
   
       public void setType(T type) {
           this.type = type;
       }
   
       public Generics(T type) {
           this.type = type;
       }
   }
   
    public static void show(Generics<? extends Number> generics) {
           Number number = new Integer(12);
           Integer integer = new Integer(12);
           //以下两行为编译报错代码
           //generics.setType(number);
           //generics.setType(integer);
   
           //以下正确代码
           Number type = generics.getType();
           System.out.println(type);
       }
   ```

   原因也不难理解，在做**取、拿**等操作时，可以用父类类型指向子类对象接收。但是**存、赋值**的时候又存入什么类型呢？既然不明确，那就语法不允许。

---

以上，就是带上限通配符的使用，最后再看一个案例代码：

```Java
//三层的继承层次
class Father{}
class Son extends Father{}
class Grandson extends Son{}

//ArrayList类部分源码
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    //...
    public boolean addAll(Collection<? extends E> c) {
            Object[] a = c.toArray();
            int numNew = a.length;
            ensureCapacityInternal(size + numNew);  // Increments modCount
            System.arraycopy(a, 0, elementData, size, numNew);
            size += numNew;
            return numNew != 0;
    }
    //...
}
```

做如下测试：

```Java
ArrayList<Father> fathers = new ArrayList<>();
fathers.addAll(new ArrayList<Father>());
fathers.addAll(new ArrayList<Son>());
fathers.addAll(new ArrayList<Grandson>());

ArrayList<Son> sons = new ArrayList<>();
//sons.addAll(new ArrayList<Father>()); 报错
sons.addAll(new ArrayList<Son>());
sons.addAll(new ArrayList<Grandson>()); 
```

仔细研究一下上述addAll()方法的调用，能够加深理解～



### 通配符下限

> 既然有上限，那么自然就有下限。上限是设置继承中的上限父类必须是某个类，所谓限限制下限即是限制类型实参的子类必须是某个类。

语法：

```Java
类/接口<? super 类/接口名>
```

表示**类型实参**必须是**类/接口名**或者它的父类类型，参考以下代码：

```Java
public class Demo {
    public static void main(String[] args) {
        ArrayList<InterfaceFather> interfaceFathers = new ArrayList<>();
        show(interfaceFathers);

        ArrayList<Father> fathers = new ArrayList<>();
        show2(fathers);

        ArrayList<Grandson> grandsons = new ArrayList<>();
        //show2(grandsons); 报错
    }

    public static void show(ArrayList<? super InterfaceSon> list) {
    }
    public static void show2(ArrayList<? super Son> list) {
    }
}

interface InterfaceFather {
}
interface InterfaceSon extends InterfaceFather {
}
class Father {
}
class Son extends Father {
}
class Grandson extends Son{
}
```

和带上限的泛型通配符除了表达意义不同，实际使用上差不多，特点也差不多：

1. 使用通配符做类型实参的类是无法实例化的

2. 使用**带下限通配符**的类作为引用类型时，**获取、拿**之类的操作和**存、赋值**之类的操作都可以做。但是有一定的限制，参考以下代码：

   ```Java
       public static void show2(ArrayList<? super Son> list) {
           //存入时，可以存入Son或者Son子类对象
           Father father = new Father();
           Son son = new Son();
           Grandson grandson = new Grandson();
   
           //list.add(father); 报错
           list.add(son);
           list.add(grandson);
   
           //做遍历获取操作，全部当成Object处理
           for (Object o : list) {
               System.out.println(o);
           }
       }
   ```

   限制下限后，类型实参一定是Son或者Son的父类，所以**“存、赋值等操作时”**传入Son或者Son的子类一定是可以的。而限制上限的话，类型实参就不确定是什么子类了，这时候传入任何对象就不太合适了。而正是因为**“存、赋值等操作时”**，限制下限的通配符可以存入多种对象，那么遍历时也没有办法直接用某个确定的类型接收了，必须使用Object。而限制上限时，无论如何都是子类，所以可以直接接收。

   **当然，以上解释都可以不用看，你完全可以当成语法记忆即可。最次你还有编译器语法检测，也不用太死记，用多了总会明白这些细节问题。**

---

### 有上界类型形参

> 上述带通配符"?"的上下限实际上都是有界类型实参，它们都是作为类型实参而存在的。而类型形参也可以设置界限，请参考以下代码：

```java
//定义泛型类
class Generics<T extends IA & IB> {
    private T type;

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }

    public Generics(T type) {
        this.type = type;
    }
}

```

在上述代码中，出现了“**T extends Number①**”的代码，这在Java中是允许的，它是一种**有界的类型形参**，接下来比较一下它和“**? extends Number②**”的区别：

1. 本质区别：①出现在**泛型类型形参**的位置，也就是定义泛型类/方法/接口时出现，它表示在**传入类型实参时可以是Number或者它的子类**，传入对应类型实参后可以创建相应的对象。

   比如：

   ```Java
   //创建上面定义的Generics类的对象
    Generics<Number> numberGenerics = new Generics<>(123);
    Generics<Integer> integerGenerics = new Generics<>(123);
    Generics<Double> doubleGenerics = new Generics<>(1.23);
   ```

   而②出现在**泛型类型实参**的位置，但是该类型并不能直接创建对象，而是经常用来作为方法的返回值类型或者形参的数据类型，具体的使用上面已经讲过就不再啰嗦，包括一些细节也不再重复。

2. ①作为有界的类型形参可以设置多重限定，而②是不能多重限定的，参考以下代码：

   ```java
   //定义泛型类
   class Generics<T extends IA & IB> {
       private T type;
   
       public T getType() {
           return type;
       }
   
       public void setType(T type) {
           this.type = type;
       }
   
       public Generics(T type) {
           this.type = type;
       }
   }
   
   interface IA{}
   interface IB{}
   ```

   注意：

   - 使用“ & ”符号表示为类型形参T设置多重限定，不能使用“&&”。它表示类型形参T传入的**类型实参必须同时实现（或者继承）两个接口IA和IB**
   - 使用多重限定时，**extends关键字后面直接跟的可以是一个类或接口**，而"&"后面必须是接口~
   - 如有必要还可以继续使用"&"连接更多的接口

   具体的使用可以参考以下代码：

   ```JAVA 
   package com.cskaoyan.javase.stream2.test5;
   
   import com.sun.org.apache.bcel.internal.generic.NEW;
   import com.sun.xml.internal.ws.api.model.MEP;
   
   import java.util.ArrayList;
   
   /**
    * @description:
    * @author: wuguidong@cskaoyan.onaliyun.com
    **/
   
   public class Demo {
       public static void main(String[] args) {
           Generics<IC> icGenerics = new Generics<>(new IC() {});
           Generics<? extends IA> icGenerics2 = new Generics<>(new IC() {});
         	Generics<A> aGenerics = new Generics<>(new A());
         	//编译报错
           //Generics<IA> iaGenerics = new Generics<>(new IA() {});
           //Generics<IB> ibGenerics = new Generics<>(new IB() {});
       }
   }
   
   //定义泛型类
   class Generics<T extends IA & IB> {
       private T type;
   
       public T getType() {
           return type;
       }
   
       public void setType(T type) {
           this.type = type;
       }
   
       public Generics(T type) {
           this.type = type;
       }
   }
   
   interface IA {
   }
   
   interface IB {
   }
   
   interface IC extends IA, IB {
   }
   
   class A implements IA,IB{}
   ```

   **该特性很少使用，仅供了解~敲几下代码试一试即可！**

3. ②通配符”?“的界限有上限和下限两种，**但是①的界限只有上限，没有下限。**也就是说：

   类型参数 T 只具有一种类型限定方式：

   - T extends A

   通配符 ? 可以进行两种限定：

   - ? extends A
   - ? super A

   代码可行测试一下，总体上，类型形参T如果存在界限都是非常简单的使用，了解和熟悉语法即可~

---



至此，泛型的常见使用我们都已经见过了，坦白来说，学到这里你应该已经一团浆糊了。但是没有关系，先知道，再熟悉，最后随着使用掌握各种细节和技巧，这本来就是学习的必经之路，而泛型我相信你不会缺乏熟悉它的机会。最后我们再了解一下泛型的类型擦除，就可以为泛型的学习画一个句号~



## 类型擦除

### 概念

> 实际上，类型擦除的概念早在文章的开头就提出来了：

```
在编译之后程序会采取去泛型化的措施。也就是说Java中的泛型，只在编译阶段有效。
在编译过程中，正确检验泛型结果后，会将泛型的相关信息擦除，称之为泛型“类型擦除”。也就是说，泛型信息不会进入到运行时阶段。

对此总结成一句话：具有不同泛型的多个类型在逻辑上可以看成是多个不同的类型，实际上都是相同的类型。
```

这里稍稍解释一下类型擦除的设计原因：

> Java语言的泛型采用的是**类型擦除法**实现的"**伪泛型**"，泛型信息（类型实参、参数化类型）编译之后通通被除掉了。这样的好处就是实现简单，运行时期也能节省一点类型所占内存空间，当然更重要的是：Java在设计之初是不存在泛型的，为了既实现泛型的效果又能够兼容之前的代码，Java不得不使用擦除法这种折中的手段。当然这种实现的坏处也是显而易见的，它不如"真泛型"强大和灵活，对比于C++的泛型，Java的泛型可以用非常弱鸡形容，当然这也是迫不得已的。

理解类型擦除对于用好泛型是很有帮助的，总体上类型擦除的原则是：

1. 消除类型参数的声明，即删除\<>及其包围的部分。
2. 根据类型参数的上下界推断并替换所有的类型参数为原生态类型：
   - 如果类型参数是无限制通配符或没有上下界限定则替换为Object
   - 如果存在上下界限定则根据子类替换原则取类型参数的最左边限定类型（即父类）。
3. 为了保证类型安全，必要时插入强制类型转换代码。
4. 自动产生“**桥接方法**”以保证擦除类型后的代码仍然具有泛型的“多态性”。

当然以上原则光看是不可能明白的，接下来看一些具体的案例。

---



### 擦除类定义中的类型形参

#### 无限制类型形参

> 注：图来源于网络，懒得自己画了

当类定义中的类型形参没有任何限制时，在类型擦除中直接被替换为Object，即任何形如\<T>和<?>的类型形参都将被替换为Object，图示如下：

<img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202110152219054.png" alt="image-20211014213600770" style="zoom:50%;" />

这个是最简单的类型擦除，应该还比较好理解

#### 带限制类型形参

当类定义中的类型形参存在限制（上下界）时，在类型擦除中替换为类型参数的上界或者下界（具体根据情况而定），比如形如\<T extends Number>、\<T extends Number &...>和<? extends Number>的类型形参被替换为Number，如<? super Number>被替换为Object，图示如下：

<img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202110152219097.png" alt="image-20211014213625346" style="zoom:50%;" />

---



### 擦除方法定义中的类型形参

擦除方法定义中的类型形参原则和擦除类定义中的类型形参是一样的，这里仅以擦除方法定义中的有限制类型形参为例，如下图：

<img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202110152219951.png" alt="image-20211014213742964" style="zoom:50%;" />

### 桥接方法

> 看到这个标题：桥接方法，我相信你可能是一脸懵逼的，但是不妨我们先看以下代码：

```Java
interface Info<T> {
    T info(T var);
}

class InfoImpl implements Info<Integer> {
    @Override
    public Integer info(Integer var) {
        return var;
    }
}
```

这就是一个泛型接口的代码，非常简单，按照之前类型擦除的经验，你可能认为会得到下面代码：

```Java
interface Info {
    Object info(Object var);
}

class InfoImpl implements Info {
    @Override
    public Integer info(Integer var) {
        return var;
    }
}
```

这种擦除的结果乍一看是没问题的，但实际上却是不能通过语法编译的，因为抽象方法的子类实现必须和父类方法保持相同的形参列表。这个时候，为了解决这个矛盾，Java编译器会自动增加一个所谓的“桥接方法”，用来满足Java语法的要求，具体来说就是会生成下列代码：

```Java
interface Info {
    Object info(Object var);
}

class InfoImpl implements Info {
    public Integer info(Integer var) {
        return var;
    }

  	//该方法就是一个桥接方法，搭起了父类和子类的桥梁
    @Override
    public Object info(Object var){
        return info(((Integer) var));
    }
}
```

如果画图描述这个过程就是：

<img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202110152219734.png" alt="image-20211014220325414" style="zoom: 33%;" />

以上关于类型擦除，就基本了解完了。至此，泛型的常见语法就学习完毕了~

