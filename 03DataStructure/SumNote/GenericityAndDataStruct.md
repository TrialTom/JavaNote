[TOC]



# 一、泛型

## 1. 概述：

> 参数化类型，相当于一个“占位符”，先假定一种不存在的类型来代指这个参数类型，当我们真正使用的时候再传入具体的类型。

```java
public class Holder<T>{
    private T data;
    
    public Holder(T data){
        this.data = data;
    }
    
    public T get(){
        return this.data;
    }
}

// 使用
Holder<AutoCar>	carHolder = new Holder<>(new AutoCar());
AutoCar autoCar = carHolder.get();
autoCar.run();
```

## 2. 分类

#### 2.1 泛型类

> 格式：class 类名<泛型类型1，泛型类型2···>{ }

#### 2.2 泛型接口

> 格式： ``public interface Player <T, R>{ R play(T data)}``

#### 2.3 泛型方法(了解)

> 格式：```<T> T get(T data){return data};```

#### 2.4 泛型通配（了解）

> 1. 泛型通配符<?>
>
>    任意类型，如果没有明确，那么就是Object以及任意的Java类了
>
> 2. ? extends E
>
>    向下限定，E及其子类
>
> 3. ? super E
>
>    向上限定，E及其父类

## 3. 注意：

#### 3.1 泛型类：

- 泛型类的使用

```java
// jdk1.5版本写法
User<String> user1 = new User<String>();

// jdk1.7版本写法
User<String> user1 = new User<>();

```

- <>中的泛型类要么全写，要么全不写

- <span style="color:red;background:yellow">泛型只能使用引用类型，不能使用基础类型</span>

- 泛型只在此类中会起作用，而继承此类的子类中，泛型是不起作用的

- 泛型继承

  > 1. 如果继承时，未指定父类泛型，则为默认类型Object
  >
  > 2. 如果继承时，指定了父类类型，则为指定类型，无论子类定义泛型与否
  >
  > 3. 如果继承时，传入了子类指定的泛型，则父类与子类变量类型一致
  >
  >    ```java
  >    class Son<E> extends Father<E>{}
  >    ```

#### 3.2 泛型接口

- 实现接口时，若未指定泛型类型，则泛型类型默认为Object

#### 3.3 泛型通配

- 泛型不允许协变，又想产生类似协变的效果，又不想引入协变带来的问题（类型不匹配问题）

  > 协变：允许接收该类，及该类的子类

#### 3.4 泛型擦除

- Java中的泛型并不是真的泛型，Java的泛型只存在于编译之前，当Java中的泛型编译后，会把泛型编译成Object以及类型强转