[TOC]



# 一、Maven

## 1. 介绍

Maven是一个开源的（Apache）项目管理工具。主要两个功能：

- 项目构建

  > 项目构建指把一个项目编译，打包（.jar），测试，运行，部署等。

- 依赖管理

  > Maven可以帮我们去管理项目中引入的jar包

## 2. Maven工作流程

1. 扩充：

   > 传统的导包方式会让用户的电脑上，对于同一jar包，出现多次，占用磁盘空间

2. 流程：

   ![maven](E:\javaEE program\05DataBase\_05DataBase笔记\maven.png)

   > 本地仓库：本地电脑上的一个路径，这个路径专门用来存放jar
   >
   > 中央仓库：是Maven官方为我们提供的一个jar包的下载地址
   >
   > 镜像仓库：是国内的一个公司或者是组织对中央仓库的定期同步，一般使用阿里的镜像仓库。

## 3. 安装

### 下载：

> 说明：Maven由java开发，使用的Maven需要和本地的JDK版本配套，[下载地址](https://maven.apache.org/download.cgi) 

### 本地配置：

- 环境变量

  1. 配置MAVEN_HOME
  2. 在path中添加 `%MAVEN_HOME%\bin` 

- 配置本地仓库

  1. 在config路径下setting.xml中更改本地仓库的位置

     `<localRepository>D:\d2</localRepository>`

- 阿里镜像仓库

  ```xml
  <mirror>
      <id>nexus-aliyun</id>
      <mirrorOf>central</mirrorOf>
      <name>Nexus aliyun</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public</url>
  </mirror>
  ```

- JDK编译配置

### IDEA中配置

## 4. Maven工程

### 4.1 补充：

> - Project是IDEA的最顶级的结构单元，project只是起到了一个项目定义的功能，类似于工作空间的概念，这个工作空间可以去配置你的IDEA的一些公共配置，例如SDK，代码库等，
>
> - module是project的一部分，可用来单独编译运行等，每一个module项目运行的时候都是一个单独的Java进程。IDEA创建项目的时候会默认创建一个同名的module，实际上我们开发写代码都是在开发这个module。

### 4.2 坐标三要素：

- groupId：Maven项目的公司名或者组织名，一般是域名反转
- artifactId：项目名字
- version：版本号

### 4.3 maven工程的结构

> module-name（maven工程根路径）
>
> - src
>   - main
>     - java（源代码路径）
>     - resources（资源文件路径：所有配置文件都放到这个路径下）
>   - test
>     - java（测试代码的源代码路径，后续的测试类和测试方法都必须在这个路径下）
>     - resources（测试用例需要的配置文件都可以放到这个路径下）
>   - target（编译之后的class文件）
>   
> - pom.xml（配置文件，可以导入jar包）
>

## 5. 项目管理

- **clean**：删除编译生成的target文件夹

- validate：校验项目中文件的合法性

- **compile**：编译整个项目，可以把项目中的.java文件编译成class文件，输出到target文件夹

  > 扩充：默认情况下，maven工程编译的时候，不会把src/main/java 路径下的配置文件放到classes文件夹中

- **test**：运行 src/test/java 路径下的测试类（需要配合Junit一起使用）

- **package**：把项目（module）打包为一个.jar文件或者.war文件

  > - 文件名字= artifactId + version
  > - 需要打包为.war文件则只需在pom.xml中加入 `<packaging>war</packaging>` 

- verify：验证通过package打包生成的文件

- **install**：将打包生成的.jar文件上传到本地仓库

- site和depoly：这两个命令用来部署项目（一般不用，一般用Jenkins）

## 6. 依赖管理

### 6.1 导包

- 找到对应包的坐标：

  [mvn仓库](https://mvnrepository.com/) 

- 在pom.xml文件中加入需要的jar包坐标，加入 `<depenencies></dependencies>`这个标签里面

```xml
<-- 导包的管理标签 -->
    <dependencies>
        <!-- https://mvnrepository.com/artifact/com.mchange/c3p0 -->
        <dependency>
            <groupId>com.mchange</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.10.0</version>
        </dependency>
    </dependencies>
```

### 6.3 Scope（包的作用域）

- complie：默认作用域，可以省略，包在编译，测试，运行时都生效
- test：表示这个包只有在src/test/java路径下才生效
- provided：对于src/main/java和src/test/java 路径下的java文件，此包只有在编译的时候生效，但是在运行的时候不生效
- runtime：运行时生效，编译的时候失效（最典型的就是数据库驱动包，mysql-connection-java）

### 6.4 依赖冲突

1. 概述：同一项目中，导入了同一jar包的不同版本，就会存在依赖冲突

2. 解决方案：

   - **声明优先原则**：谁先声明，以谁为准（在pom.xml文件中，谁写在上面，就以谁的版本为准）

   - **就近原则**：优先级比声明优先级更高，哪个版本的jar包传递次数少，就以谁为准。

   - **手动排除**：可以手动配置，排除jar包的传递依赖

     ```xml
     <dependency>
         <!-- 手动排除 -->
         <exclusions>
             <exclusion>
                 <groupId></groupId>
                 <artifactId></artifactId>
             </exclusion>
         </exclusions>
     </dependency>
     ```


### 6.5 提取常量

```xml
<properties>
    <spring.version>4.3.3.RELEASE</spring.version>
</properties>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>${srping.version}</version>
</dependency>
```

> 1. 概述：将依赖的版本提取到外部的properties标签中
>
> 2. 优点：
>    - 可以统一项目中相关内容的jar包的版本，防止出现依赖冲突的问题
>    - 便于开发者去管理这个项目中jar包的版本

## 7. 创建Maven项目

```java
try{
    Properties properties = new Properties();
    
    // 在maven项目中，我们需要通过类加载器来找claaes路径下文件
    ClassLoade classLoader = DBCPUtils.class.getClassLoader();
    // URL url = classLoader.getResource("dbcp.properties");
    // String path = url.getPath();
    // FileInputStream fileInputStream = new FileInputStream(path);
    
    InputStream inputStream = classLoader.getResourceAsStream("dbcp.properties");
    
    properties.load(inputStream);
}
```

