[TOC]



# 一、Junit

## 1. 介绍

Junit是一个广泛使用的测试工具，可以测试代码中的方法或者是片段

目前推荐版本：4.12

## 2. 使用

- 导包

  ```xml
  <!-- https://mvnrepository.com/artifact/junit/junit -->
  <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
  </dependency>
  ```

  

- 使用

  ```java
  @Test
  public class test01(){
      // 方法体
  }
  
  // 在测试方法运行之后运行
  @After
  // 在测试方法运行之前运行
  @Before
  // 在测试类被销毁之前运行
  @AfterClass
  // 在测试类加载的时候运行
  @BeforeClass
  ```

## 3. 使用规范

1. 所有的测试类都需要写在 src/test/java 路径下
2. 所有的测试类的命名必须叫做XxxTest
3. 所有的@Test注解修饰的方法要满足：
   - 方法必须叫做testXxx()
   - 方法的返回值是void
   - 方法没有参数
   - 方法必须是public的
4. @BeforeClass和@AfterClass这两个注解修饰的方法必须是静态的。