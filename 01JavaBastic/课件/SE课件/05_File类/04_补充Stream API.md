# Stream API

> **Stream API是Java8中被认为最成功的新特性之一**，它在实际开发中经常被使用，我们有必要进行了解和学习。严格来说它不是一个初级的、简单的、易于理解的语法，而是较为复杂，涵盖知识点极深的Java高级特性，要学习Stream API请至少拥有以下知识技能：

1. 熟练掌握面向对象概念和语法
2. 熟悉lambda表达式，知道功能接口，方法引用等概念
3. 一定的泛型和集合知识
4. 较强的逻辑思维能力、（相对于初学者）较深的代码功力

Stream API并不需要特别着急很快学会，那是不现实的，需要反复琢磨。本文仅提供Stream最最基本的入门级别使用，**重要的是记忆并多加练习**，更深入的源码、原理（即为什么）不是我们现阶段应该思考的问题。（推荐大家工作后，熟练使用Stream后，再进行自我学习）

[TOC]

## 概述

> Stream API是什么？别管，高级就行了，人上人的玩法就行了。

举例：

```Java
list.
    stream().
	filter(stu -> stu.getAge() >= 18).
	map(Student::getScore).
	forEach(System.out::println);
```

### 总体描述

> 老规矩，首先要对Stream API有一个大概的认识，知道这些东西是做什么的

1. Stream API指的是java.util.stream包下的Stream接口及其一系列抽象方法的使用，**使用Stream API进行编程可以实现函数式编程风格**，极大得提升了Java程序员的生产力，让Java代码也可以写得简洁、优雅且高效。
2. Stream是 Java8 中对**集合容器操作的关键抽象概念**，包括对集合的查找、筛选、排序、映射等等操作都可以使用它来实现。和常规做法相比Stream API更简洁高效。

总之，Stream API是用来实现对集合数据操作的函数式编程的函数工具。

---



> 函数式编程是一种常见的编程范式，与之相对应的是命令式编程，面向过程/对象编程都是命令式编程。
>
> 浅谈一下函数式编程与命令式编程：

1. **命令式编程关注解决问题的步骤（即如何解决问题），你必须用代码精确指示程序应该怎么实现功能。**

   > 比如你想要遍历输出一个集合，就首先要定义一个集合，然后再去做遍历操作输出每个元素。

   所以命令式编程就衍生出了一系列概念：变量、赋值、表达式、语句、跳转控制等等，对于程序员而言，已经习惯了通过代码指示程序完成功能，命令式编程是日常开发中使用最多的编程方式。

2. **函数式编程不关心怎么解决问题，而是默认程序自己就知道怎么完成一个功能。**程序员想做任何事情，只需要告诉程序我想做什么就行了。**所以函数式编程关注做什么，只要知道目的就能达成功能。**至于功能到底

   ```
   深入一点说，函数式编程的函数不是C当中的函数，更不是指Java当中的方法，而就是指数学意义上的“函数”，即“映射”。函数式编程本质上是对数据做映射操作。
   ```

以上是从概念角度分析函数式编程，接下来我们来看看Java的Stream是怎么实现的。



### 作用

> 集合的操作使用普通的方法调用也能完成，为何非要使用Stream？

1. 这里首先要谈的就是函数式编程与命令式编程的区别了：

   ```
   在很多情况下，命令式编程是很好用的。当我们写业务逻辑时，有些命令式代码是必须要写的，而有些操作则是可以抽取分离出来单独作为函数工具的，这些函数工具就是函数式编程的基础。
   ```

   在正常不分离的时候，程序员必须要学习、思考和设计这些工具的实现，这就给程序员带来了额外的负担，并且能力不同的程序员在做实现时工具的好坏可谓云泥之别。

   而抽取分离工具后，语言的开发者为我们设计这些实现，开发者团队可以从语言的全局出发，设计出最优解，保证了工具的高效可用。同时程序员也就无需再思考这些工具的实现，只需要知道怎么用语法就足够了。所以对于程序员而言，函数式编程让我们写更出简洁的代码、更优雅的代码、更高效的代码，同时还让我们从实现功能的泥沼里抽出身子，站在更高的上帝视角，鸟瞰整个程序，把更多精力投向程序设计本身。

   ```
   Java并不是完全的函数式编程语言，Stream API中对集合元素操作的方法可以认为就是函数工具，但是集合元素的定义、赋值等还是依赖于常规的命令式代码。
   ```

2. 在开发中，最常见的数据源是关系型数据库，比如MySQL、Oracle等等。操作数据时普遍使用SQL语句，这时往往就不太需要集合去操作数据了。而对于一些非关系型的数据库，比如Redis、MongoDB等。它们没有SQL语句操作数据，这时候就非常有必要使用Java容器去处理数据了。

3. 某些时候使用数据库直接操作数据会有一些问题（比如效率等）时，可以考虑Stream API。



### 理解

> Stream到底是什么东西？

Stream这个单词，应该翻译成“流”的意思，Stream API将处理的数据源（集合或者数组等数据源）看做一种Stream流，并且在pipeline管道中传输和操作这些数据（称之为聚合操作或者中间操作），而到达终点就可以得到最终的处理结果（称之为终止操作）。

关键概念：

1. 元素：必须是对象，Stream流中的元素会形成一个队列等待进行处理。
2. **数据源：**Stream本身不存储数据，只是将数据源中的数据当成流处理，数据仍然存储在数据源中。
3. **数据源不可变：**Stream API操作数据流不改变数据源本身。（除非在操作中，调用了类似set的方法）
4. 聚合操作：在管道中对流进行的一系列运算
5. 终止操作：结束管道运算，获取最终结果流



### 执行过程

> 说了半天，到底咋用？直接看流程

![image-20210927015658935](https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202110180129620.png)

解释：

1. 首先想要使用Stream操作集合数据，必须有一个数据源。
2. 中间操作，比如查找、排序、筛选等等一系列操作
3. 终止操作，表示结束中间操作，生成结果

**注意事项：**

1. 中间操作有些场合也称之为聚合操作
2. **一个Stream对象的任何中间操作要想被执行，必须要有终止操作。**
3. **终止操作执行后该Stream对象就不能做任何中间操作了，需要重新创建Stream才能继续操作。**



## 使用

> 上面已经说过了，Stream的使用要三步走，这里同样按照三步走去讲解。

提供一个类Student及数据源，用于Stream操作（getter、setter方法、构造器之类的可自行给出）

```Java
class Student {
    private int stuId;
    private int age;
    private String name;
    private double score;
}
//数据
Student s1 = new Student(1, 18, "张三", 88);
Student s2 = new Student(3, 28, "武玄宇", 100);
Student s3 = new Student(5, 8, "李子恒", 77);
Student s4 = new Student(7, 19, "诸葛孔明", 66);
Student s5 = new Student(12, 20, "零", 44);
Student s6 = new Student(18, 33, "李一", 55);
Student s7 = new Student(2, 57, "隔壁老王", 13);
Student s8 = new Student(9, 10, "我大姨家的小姑凉", 35);
Student[] students = {s1, s2, s3, s4, s5, s6, s7, s8};
```

### 创建对象

> 创建Stream对象的方式很多，这里讲几个最常见的：

1. 通过集合创建，集合的祖先接口Collection中有以下默认实现方法：

   ```Java
   //生成串行流，顺序流
   default Stream<E> stream() {
           return StreamSupport.stream(spliterator(), false);
   }
   
   //生成并行流
   default Stream<E> parallelStream() {
           return StreamSupport.stream(spliterator(), true);
   }
   ```

   并行流使用多线程的方式操作数据，数据量较大时它的效率会更高。一般情况下，建议使用串行流。

   参考代码如下：

   ```Java
   List<Student> list = Arrays.asList(students);
   Stream<Student> stream = list.stream();
   Stream<Student> studentStream = list.parallelStream();
   ```

   

2. 通过数组创建，数组工具类Arrays中有以下静态方法：

   ```Java
   public static <T> Stream<T> stream(T[] array) {
       return stream(array, 0, array.length);
   }
   public static IntStream stream(int[] array) {
       return stream(array, 0, array.length);
   }
   //...不同数据类型数组作为形参的重载方法
   ```

   参考代码如下：

   ```Java
   Stream<Student> stream1 = Arrays.stream(students);
   IntStream stream2 = Arrays.stream(new int[]{1, 2, 3});
   ```

3. 通过Stream接口中本身的静态方法创建:

   ```Java
   public static<T> Stream<T> of(T t) {
           return StreamSupport.stream(new Streams.StreamBuilderImpl<>(t), false);
   }
   public static<T> Stream<T> of(T... values) {
           return Arrays.stream(values);
   }
   ```

   参考代码如下：

   ```Java
   Stream<Integer> integerStream = Stream.of(1);
   Stream<String> stringStream = Stream.of("abc", "123", "hello");
   ```

4. 上述三种方式创建的Stream流对象都是有限的数据，而Java还提供了创建无限流的方式。同样是通过Stream接口中本身的静态方法创建：

   ```Java
   public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f) {
           //...
   }
   public static<T> Stream<T> generate(Supplier<T> s) {
           //...
   }
   ```

   **iterate方法是通过“迭代”的方式创建无限流。**UnaryOperator中的抽象方法实现会返回**根据种子元素seed计算出相同数据类型的元素结果，然后拿结果继续进行迭代运算**。显然如果没有中间操作/终止操作结束，会无限制进行迭代计算，即创建了”无限流“，数据是无限的（当然实际使用肯定要截断）。

   参考以下代码（这个Stream流里数据会从2开始，包括全体正偶数）：

   ```Java
   Stream<Integer> iterate = Stream.iterate(2, num -> num + 2);
   ```

   **generate方法是通过”无限生成“的方式创建无限流的。**Supplier中的抽象方法实现无需参数直接返回一个元素，无限次的调用该方法从而达到无限个元素（同样需要中间操作/终止操作结束）

   参考以下代码（这个Stream流会无限生成[0,1)的随机数）

   ```Java
   Stream<Double> generate = Stream.generate(Math::random);
   ```

   无限流相对使用较少，仅供了解。

---



### 中间操作

> Stream对象创建完毕后，就可以开始进行中间操作了，这里其实就是各种方法的调用，很简单。

首先要明确三点：

1. **一次中间操作就是调用一次Stream接口的方法，任何中间操作方法的返回值仍然是Stream对象**。这意味着只要你愿意，Stream中间操作可以通过链式调用无限制的进行下去（当然这没必要）。

2. **中间操作要想执行，必须要有终止操作执行。**

   ```
   多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理。终止操作时会一次性全部处理中间操作，称为“惰性求值”。这是一种为了效率而出现设计
   ```

   这里为了演示中间操作的效果，先提前介绍一种常用于测试的终止操作---迭代并对每个元素进行操作，源代码：

   ```Java
   void forEach(Consumer<? super T> action);
   ```

   Consumer中的抽象方法需要一个元素作为参数，而没有返回值。这和打印输出方法特征是一致的，所以迭代遍历Stream中集合的元素并打印输出，应该这么写：

   ```Java
   (Stream对象).forEach(System.out::println)
   ```

3. **终止操作要么没有返回值，要么返回值是集合或其它对象**（反正不是Stream对象）

**接下来分类介绍常见的中间操作（方法），这是Stream API使用的核心所在。**

---

#### 过滤与选择

> 以下方法都涉及过滤筛选等特点，放在一起学习

1. filter
2. limit
3. skip
4. distinct



##### filter方法

正如它的名字一样，该方法表示对Stream流中的元素进行过滤，源码如下：

```Java
Stream<T> filter(Predicate<? super T> predicate);
```

该方法需要传入一个Predicate接口实现对象，而Predicate接口中的抽象方法如下：

```Java
boolean test(T t);
```

所以该方法传入的Predicate接口实现对象实际上是传入了一个过滤规则：流中的元素逐个调用test方法，方法返回true会留下，否则过滤掉。**至于这整个过滤咋实现的，程序员无需关心，这就是函数式编程。**

参考以下代码：

```Java
List<Student> list = Arrays.asList(students);
Stream<Student> studentStream = list.stream();
//过滤规则1：未成年不配玩游戏
studentStream.filter(stu -> stu.getAge() >= 18).forEach(System.out::println);
System.out.println("分隔符");
//过滤规则：学习，学个屁！考试不及格才是王道
//上面一个流已经终止关闭了，不能再次使用了，需要新建一个
Stream<Student> studentStream2 = list.stream();
studentStream2.filter(stu -> stu.getScore() < 60).forEach(System.out::println);
```

输出结果是：

> Student{stuId=1, age=18, name='张三', score=88.0}
> Student{stuId=3, age=28, name='武玄宇', score=100.0}
> Student{stuId=7, age=19, name='诸葛孔明', score=66.0}
> Student{stuId=12, age=20, name='零', score=44.0}
> Student{stuId=18, age=33, name='李一', score=55.0}
> Student{stuId=2, age=57, name='隔壁老王', score=13.0}
>
> 分隔符
>
> Student{stuId=12, age=20, name='零', score=44.0}
> Student{stuId=18, age=33, name='李一', score=55.0}
> Student{stuId=2, age=57, name='隔壁老王', score=13.0}
> Student{stuId=9, age=10, name='我大姨家的小姑凉', score=35.0}



##### limit方法

limit有限制的意思，在Stream流中该方法起着截取、截断的作用，源码定义如下：

```Java
Stream<T> limit(long maxSize);
```

该方法传入一个整数，表示截取流中（包含）前maxSize个元素，这个比较简单，参考以下代码：

```Java
Stream.iterate(0, num -> num + 2).
                limit(3).
                forEach(System.out::println);
```

输出结果：

> 0
> 2
> 4



##### skip方法

skip有跳过的意思，在流中起着跳过一部分元素的作用，源码定义如下：

```Java
Stream<T> skip(long n);
```

该方法传入一个整数，表示跳过流中（包含）前n个元素，取后面的元素，参考以下代码：

```java 
Stream.iterate(0, num -> num + 2).limit(3).skip(2).forEach(System.out::println);
```

这个实际上是获取了流中第三个元素，即输出：

> 4



##### distinct方法

distinct本身是不同，区别的含义，在流中它起着**筛选并去掉相同元素**的作用，源码定义如下：

```Java
Stream<T> distinct();
```

可以看到这个方法没有任何参数，那么它是怎么判断元素相同呢？distinct方法会根据hashCode和equals方法的结果来决定两个元素是否相同。使用idea自带快捷键生成Student类的hashCode和equals方法后，参考以下代码：

```Java
ArrayList<Student> list2 = new ArrayList<>();
list2.add(new Student(1, 1, "1", 1));
list2.add(new Student(2, 1, "1", 1));
list2.add(new Student(1, 1, "1", 1));
list2.stream().distinct().forEach(System.out::println);
```

输出结果：

> Student{stuId=1, age=1, name='1', score=1.0}



#### 映射

> 映射是聚合操作中最常用的操作之一，并且具有一定上手难度。

##### map方法

map就是映射的意思，这里指的是数学当中的映射，即通过自变量得到因变量的映射。源码定义如下：

```Java
<R> Stream<R> map(Function<? super T, ? extends R> mapper);
```

其中Function功能接口中的抽象方法是这样定义的：

```java 
@FunctionalInterface
public interface Function<T, R> {
	R apply(T t);
    //...
}
```

流中的元素T会逐个传入该方法，然后返回新的元素R组成新的流，参考以下代码：

```Java
List<Student> list = Arrays.asList(students);
Stream<Student> studentStream = list.stream();
//映射规则1：获取学生名单
studentStream.map(Student::getName).forEach(System.out::println);
//映射规则2：获取成年的学生成绩
Stream<Student> studentStream2 = list.stream();
studentStream2.
    filter(stu -> stu.getAge() >= 18).map(Student::getScore).forEach(System.out::println);
```

输出结果：

> 张三
> 武玄宇
> 李子恒
> 诸葛孔明
> 零
> 李一
> 隔壁老王
> 我大姨家的小姑凉
> 88.0
> 100.0
> 66.0
> 44.0
> 55.0
> 13.0



##### flatMap方法

映射操作最常用的就是map方法，除此之外还有像：

> **mapToDouble()**
>
> **mapToInt()**
>
> **mapToLong()**

这三个map方法，可以自行摸索学习一下比较简单。在这里我们看一下flatMap方法：

```Java
<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
```

它几乎和map作用一致，但是在泛型定义上有细微的区别，这也就直接导致了它们的区别，在写lambda表达式时：

1. 如果flatMap方法传入实现类对象时，指向的实现方法的返回值类型是Stream<R>类型。那么flatMap方法的返回值类型就是Stream<R>类型。
2. 如果map方法传入实现类对象时，指向的实现方法的返回值类型是Stream<R>类型。那么map方法的返回值类型就是Stream<Stream<R>>类型。

参考以下代码实现：

```Java
 public static void main(String[] args) {
    String[] arr = {"abc", "666", "你好中国!"};
    Stream<String> stringStream = Arrays.stream(arr);
    Stream<Stream<Character>> streamStream = stringStream.map(Demo::getCharacterStream);
    streamStream.forEach(steam -> {
        steam.forEach(System.out::println);
    });
    System.out.println("分隔符");
    Stream<String> stringStream2 = Arrays.stream(arr);
    Stream<Character> characterStream = stringStream2.flatMap(Demo::getCharacterStream);
    characterStream.forEach(System.out::println);
}

public static Stream<Character> getCharacterStream(String str) {
    ArrayList<Character> characters = new ArrayList<>();
    for (char c : str.toCharArray()) {
        characters.add(c);
    }
    return characters.stream();
}
```

最终会输出两次：

> a
> b
> c
> 6
> 6
> 6
> 你
> 好
> 中
> 国
> !

flatMap方法和map方法的区别大家可以多写几个案例，摸索一下，实际上非常简单，就是很绕。



#### 排序

> 排序也是聚合操作的一种，它依赖于实现Comparable接口的自然排序或者带比较规则的Comparator实现自然排序。在这里，有关于自然排序就不再赘述了，Stream API实现排序依赖于以下两个方法：

1. sorted()：依赖于元素对应类型自身实现Comparable接口的自然排序进行排序。方法源码如下

   ```Java
   Stream<T> sorted();
   ```

2. sorted(Comparator com)：传入Comparator实现类表示比较规则，进行自然排序。方法源码如下

   ```Java 
   Stream<T> sorted(Comparator<? super T> comparator);
   ```

参考以下代码：

```Java
List<Student> list = Arrays.asList(students);
//自然排序规则1：按成绩从高到低排序
Stream<Student> studentStream = list.stream();
studentStream.sorted((stu1,stu2) -> ((int) (stu2.getScore() - stu1.getScore()))).forEach(System.out::println);
System.out.println("分隔符");
//自然排序规则2：按照年龄从小到大排序
Stream<Student> studentStream2 = list.stream();
studentStream2.sorted((stu1,stu2) -> ((stu1.getAge() - stu2.getAge()))).forEach(System.out::println);

Stream<Integer> integerStream = Stream.of(1, 10, 9, -1, 2, 3);
integerStream.sorted().forEach(System.out::println);
```

代码运行结果比较长，可以自行运行并查看结果。自然排序不再赘述了

---

以上，常见的聚合操作就完毕了，先把这些最常见的搞明白会用，不常见的可以等有需要时自行学习一下。



### 终止操作

> 终止操作我们已经知道一个forEach方法了，现在来学习一些其它的终止操作

#### 查找与匹配

> 本节下所有的终止操作都与查找与匹配相关，有如下方法：
>
> allMatch、anyMatch、noneMatch、findFirst、findAny、count、max、forEach

注：下表中的方法指的是Stream API当中的终止操作方法，抽象方法指的是终止操作方法的形参功能接口中需要实现的抽象方法

|                        方法                        |         抽象方法         |            描述            |
| :------------------------------------------------: | :----------------------: | :------------------------: |
| boolean allMatch(Predicate<? super T> predicate);  |    boolean test(T t);    | 检查是否所有元素都匹配条件 |
| boolean anyMatch(Predicate<? super T> predicate);  |    boolean test(T t);    | 检查是否至少有一个匹配条件 |
| boolean noneMatch(Predicate<? super T> predicate); |    boolean test(T t);    |  检查是否没有一个匹配条件  |
|              Optional<T> findFirst();              |            无            |     返回流中第一个元素     |
|               Optional<T> findAny();               |            无            |    返回流中任何一个元素    |
|                   long count();                    |            无            |      返回流中元素个数      |
| Optional<T> max(Comparator<? super T> comparator); | int compare(T o1, T o2); |    返回自然排序的最大值    |
| Optional<T> min(Comparator<? super T> comparator); | int compare(T o1, T o2); |    返回自然排序的最小值    |
|     void forEach(Consumer<? super T> action);      |    void accept(T t);     |   内部迭代执行accept方法   |

这里出现了一个陌生的类：Optional类

Optional类也是Java8的新特性之一，实际上它就是伴随着Stream API而诞生的。在这里我们不详细展开它去讲解它了，我们简单理解：Optional类可以认为是装了一个对象的“容器”，目的是为了用来判null，避免空指针异常。这里我们只讲它的三个成员方法：

|                       方法名                        |        形参        |                        描述                        |
| :-------------------------------------------------: | :----------------: | :------------------------------------------------: |
|                   public T get()                    |         无         |        如果存储的对象不是null，则返回该对象        |
|             public boolean isPresent()              |         无         |         如果存储的对象不是null，则返回true         |
| public void ifPresent(Consumer<? super T> consumer) | Consumer实现类对象 | 如果存储的对象不是null，则执行Consumer的accept方法 |

Consumer接口：

```java 
@FunctionalInterface
public interface Consumer<T> {
	void accept(T t);
	//...
}
```

整体参考以下代码：

```Java
Stream<Integer> integerStream = Stream.of(1, 10, 9, -1, 2, 3);
boolean b = integerStream.noneMatch(num -> num < -1);
System.out.println(b);

List<Student> list = Arrays.asList(students);
Stream<Student> studentStream = list.stream();
Optional<Student> min = studentStream.min((stu1, stu2) -> ((int) (stu2.getScore() - stu1.getScore())));
min.ifPresent(System.out::println);

Stream<Student> studentStream2 = list.stream();
Optional<Student> first = studentStream2.findFirst();
first.ifPresent(System.out::println);
```

输出结果如下：

> true
> Student{stuId=3, age=28, name='武玄宇', score=100.0}
> Student{stuId=1, age=18, name='张三', score=88.0}



#### 规约

> 规约指的就是reduce方法，reduce有减少的意思，在Stream中指的是用流中的所有元素、通过迭代的方式，共同完成一个结果。说白了，把集合中很多元素变成一个元素，这就是规约。
>
> 常用的有以下两个方法：

|                         方法                         |      抽象方法      |
| :--------------------------------------------------: | :----------------: |
| T reduce(T identity, BinaryOperator<T> accumulator); | R apply(T t, U u); |
|  Optional<T> reduce(BinaryOperator<T> accumulator);  | R apply(T t, U u); |

注意事项：

1. 抽象方法并不是BinaryOperator接口中的方法，而是它的父接口BiFunction中的抽象方法，它们的继承是这么声明的：

   ```java 
   @FunctionalInterface
   public interface BinaryOperator<T> extends BiFunction<T,T,T> {
   	//...
   }
   ```

   所以十分重要的一点是：**抽象方法中的泛型T、R、U必须都是同种数据类型，都是Stream流对象的泛型类型**

2. **reduce方法会从第一个元素和第二个元素开始执行apply方法，然后用结果和第三个元素继续执行，反复迭代直到最后一个元素，执行结束。**

   参考以下代码：

   ```Java
   Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
           integerStream.reduce((num1, num2) -> num1 - num2).ifPresent(System.out::println);
   ```

   该程序会执行1-2=-1，然后用-1继续减去3，迭代重复，直至最后一个元素，最终结果是：

   > -13

   上面程序把num1 - num2改成num1 + num2 结果就会变成累加，即15

3. 返回值为T的reduce方法，相当于指定出了第一个元素，给出了起始值

   参考以下代码：

   ```Java
   Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
   System.out.println(integerStream.reduce(15, ((num1, num2) -> num1 - num2)));
   ```

   其执行结果是：

   > 0

---



#### 收集

> 我们已经知道了，Stream API是为了操作集合中的元素并且不会改变数据源。那么Stream流操作后，怎么再变为集合呢？收集就是将Stream流重新变成集合对象的终止操作，即collect方法

最常用的就是下面这个方法：

```Java
<R, A> R collect(Collector<? super T, A, R> collector);
```

注意事项：

1. Collector只是一个普通接口，不能用lambda表达式给出它的实现类对象

2. Collector实现类对象的获取方式，JDK中早就已经定义好了，叫做CollectorImpl。它是类Collectors的一个内部类，我们可以Collectors的静态方法来获取CollectorImpl对象，这是典型的使用内部类的设计。

3. Collectors的常用静态方法如下：

   |     **方法**     |        返回值类型        |                      作用                       |
   | :--------------: | :----------------------: | :---------------------------------------------: |
   |    **toList**    | Collector<T, ?, List<T>> |      该Collector实现对象可以将流转换为List      |
   |    **toSet**     | Collector<T, ?, Set<T>>  |      该Collector实现对象可以将流转换为Set       |
   | **toCollection** |    Collector<T, ?, C>    | 该Collector实现对象可以将流转换为自行创建的集合 |

   注意：toCollection方法需要传入Supplier实现类对象，而其中抽象方法表示创建收集转换的集合对象

   参考以下代码：

   ```Java
   Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
   List<Integer> list = integerStream.collect(Collectors.toList());
   for (Integer integer : list) {
       System.out.println(integer);
   }
   Stream<Integer> integerStream2 = Stream.of(1, 2, 3, 4, 5);
   ArrayList<Integer> list2 = integerStream2.collect(Collectors.toCollection(ArrayList::new));
   for (Integer integer : list2) {
       System.out.println(integer);
   }
   ```

   输出结果：

   > 1
   > 2
   > 3
   > 666
   > 888

---

至此，Stream API的学习就可以告一段落了，虽然上述讲解的使用还比较浅显，但是我认为对于大家是足够的。等到你能够熟练使用它们了，再深入学习不迟，祝好~

