[TOC]

# 1. Java常用框架

Spring框架（SpringFramework）

- IOC、DI
- AOP
- 整合MyBatis、Spring事务Transation

SpringMVC框架（核心：去servlet）

- 核心流程 -->Handler方法
- 使用@RequestMapping
- 参数和返回值
- Filter和HandlerInterceptor
- 静态资源处理
- 异常处理

SpringBoot（Spring框架，约定大于配置）

# 2. 回顾

**反射（reflect）**：

**动态代理（Proxy）**：创建一个代理对象，调用方法，调用InvocationHandler的invoke方法

- JDK动态代理
- cglib动态代理

# 3. Spring FrameWork

**3.1 概述**：轻量级的开源框架，用起来比较简单

- Core technologies：dependency injection，events，resources，i18n，validation，databinding，type conversion，SpEL，AOP

  > **依赖注入**，事件，资源，i18n国际化，校验，类型转换，Spring Expression Language（表达语言），**面向切面编程** 

# 4. IOC

**4.1 概述**：ioc控制反转（Inverse of Control）

> 控制：控制实例的生成
>
> 反转：控制的反转，实例的生成的反转。原先是应用程序来控制实例的生成，把这个过程反转给Spring，Spring框架来控制实例的生成。
>
> 容器：Spring 容器，IOC容器，管理的实例
>
> 组件：Component，Spring容器管理的实例
>
> 注册：提供一些信息给Spring 容器，管理这些信息所对应的实例，这些实例就成为了Spring容器中的组件。

# 5. DI

**5.1 概述**：dependency injection依赖注入，经过了控制反转，Spring容器管理了应用程序运行过程中的一些实例，程序的正常运行，需要使用这些实例提供的方法。<span style="background:yellow;color:red;font-weight:bold">我们要从Spring容器中把这些实例去取出来，这个过程即依赖注入</span>。

# 6. 实例

搭建Spring应用

- 创建Maven，引入Spirng
- 引入Spring 的配置文件
  - 做配置
  - 注册组件，管理实例
- 初始化Spring容器
- 从容器中取出组件（取出实例）

> **引入依赖**：5+1依赖（5个核心包+日志包）
>
> - 5个核心包：context，core，aop，beans，expression
> - 日志包：jcl
> - 在pom.xml中需要引入spring-context就可以了，Maven的特性：引入依赖会引入它关联的一些依赖
>
> ```xml
> <dependencies>
> 	<dependency>
>     	<groupId>org.springframewordk</groupId>
>         <artifactId>spring-context</artifactId>
>         <version>5.2.15.RELEASE</version>
>     </dependency>
> </dependencies>
> ```
>
> 
>
> **配置文件**：
>
> ```xml
> <?xml version="1.0" encoding="UTF-8"?>
> <beans xmlns="http://www.springframework.org/schema/beans"
>        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
> 
>     <!--配置bean-->
>     <bean id="bookDao" class="com.zs.dao.impl.BookDaoImpl">
>     	<property/>
>     </bean>
>     <bean id="bookService" class="com.zs.service.impl.BookServiceImpl"></bean>
> </beans>
> ```
>
> > **bean标签**：
> >
> > - <span style="background:yellow;color:red;font-weight:bold">class属性：写全限定类名，写的是实现类的</span> 
> > - id属性：组件在容器中的唯一标识
> > - name属性：省略不写，使用id属性作为默认的name属性
> >
> > **property标签**：
> >
> > - 作用：给父标签对应的组件的成员变量赋值
> > - name属性：name属性值写的是set方法名
> > - value属性：set方法的形参，提供的是值类型：基本数据类型，包装类，字符串
> > - ref属性：set方法的形参，提供的是引用类型Object对象
> >
> > **constructor-arg**:
> >
> > - 使用有参构造方法
> > - name属性：
> > - value属性：
>
> **初始化容器**：
>
> ApplicationContext类型的对象，ApplicationContext是接口，它的实现类就是容器的实现类。
>
> ClassPathXmlApplicationContext→该实现能够加载xml的Spring配置文件，完成容器的初始化。
>
> ```java
> // 初始化容器
> // classpath -> target/classes,来源src/main和src/main/resources
> ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
> ```
>
> 
>
> **取出组件**：使用容器提供的方法，ApplicationContext提供的方法来获得组件
>
> ```java
> // 通过bean 的id取出组件
> classPathXmlApplicationContext.getBean("...");
> 
> // getBean(Class),按照类型从容器中取出组件，该类型的组件在容器中只有一个。
> // 建议使用接口的方式来取出，和后面的AOP有关系
> applicationContext.getBean(UserMapper.xml);
> 
> // getBean(String,Class) ，按照组件id和类型取出，(NoUniqueHBeanDefinitionException)
> ```
>
> 

# 7. Bean的实例化

**7.1 xml中的注册**：

- 默认按照无参构造方法来完成实例化
- 通过constructor-arg子标签来使用有参构造方法。
  - name属性：有参构造方法的形参名
  - value属性或ref：赋值
- 通过bean标签注册的组件类型：factory-method对应的方法的返回值类型。

```xml
<!-- 静态工厂 -->
<bean id="userFromStaticFactory" class="com.cs.factory.StaticFactory" factory-method="createUser"/>

<!-- 实例工厂 -->
<bean id="factory" class="com.cs.factory.InstanceFactory"/>
<bean id="userFromInstanceFactory" factory-bean="factory" factory-method="createUser"/>
```

## 7.2 FactoryBean

1. 概述：

   - FactoryBean接口，是将工厂设计模式的工厂方法，工厂提供接口，通过不同的工厂实现类，获得不同的实例
   - 通过FactoryBean组测的组件类型，和它提供的生产方法的返回值相关，返回值就是向容器注册的组件。FactoryBean提供的生产方法：**getObject()** 

2. 注册：

   ```xml
   <bean id="userFromFactoryBean" class="com.cs.factory.UserFactoryBean"/>
   ```

3. BeanFactory与FactoryBean：

   - BeanFactory是一个接口，是ApplicationContext的父接口，这个接口也可以称之为容器接口，生产实例、管理实例的方法是BeanFactory提供的
   - BeanFactory所有的实例都是通过BeanFactory注册，而FactoryBean是特定的实例

# 8. 作用域

**8.1 扩展**：

> singleton 单例：容器中的组件以单例的形式维护，默认是单例。实例化执行一次
>
> prototype 原型：容器中的组件以原型的实行维护，每一取出的组件都是不同外的组件，都是新的组件。取出几次就实例化几次
>
> <span style="background:yellow;color:red;font-weight:bold">单例组件在容器初始化的时候就已经完成实例化了</span> 

