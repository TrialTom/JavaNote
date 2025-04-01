[TOC]



# 1. 配置文件存放位置

**1.1 配置文件存放位置的两种方式**：

- 放置在WEB-INF目录下，

  ```java
  // 思路：从WEB-INF/a.txt文件中读取数据，放入context域，filter中与context域取出数据进行一一比对。
  class ApplicationListener implements ServletContextListener{
      @Override
      public void contextInitialized(ServletContextEvent servletContextEvent){
          // 读取WEB-INF目录下的a.txt文件
          ServletContext servletContext =  servletContextEvent.getServletConttext();
          String realPaht = servletContext.getRealpath("WEB-INF/a.txt");
          BufferedReader bufferedReader new BufferedReader(new FileReader(new File(realPath)));
          String line = null;
          List<String> uris = new ArrayList<>();
          while((line = bufferedReader.readLine()) != null){
              // 用list保存
              uris.add(line);
          }
          servletContext.setAttribute("uris", uris);
      }
  }
  ```

- 路径存放在src目录下，也就是maven项目下的resources目录下

  > 拓展：类加载器既然可以加载类到内存中，那么肯定可以知道class文件所在文件的硬盘路径，类加载器提供一个方法，获取位于classpath目录文件的路径

# 2. Maven项目整合

**2.1 具体步骤**：

1. src\main\新建一个webapp目录

2. 设置facets，选择web

3. 在pom.xml中设置`<packaging>war</packagin>`，导入依赖

   ```xml
   <depedencies>
   	<dependency>
       	<groupId>javax.servlet</groupId>
           <artifactId>javax.servlet-api</artifactId>
           <version>3.1.0</version>
           <scope>provided</scope>
       </dependency>
   </depedencies>
   ```




# 3. JSON介绍

> JSON（JavaScript Object Notation）是一种轻量级的数据交换格式，它基于JavaScript的一子集，它利用了JavaScript的一些模式来表示结构化数据
>
> <span style="background:yellow;color:red;font-weight:bold">它是一种数据格式，而非编程语言</span> 

## **3.1 示例代码**：

```js
var person={name:"zhangsan",age:18}

alter(person.name);
```

## **3.2 语法**：

> **json语法中，看到{}，表示的是一个对象，看到的是[]，表示的是一个数组或者集合** 

## **3.3 优势**：

<span style="background:yellow;color:red;font-weight:bold">最主要的优势就是表示出具有层级结构的数据关系</span> 

## **3.4 json表述方式**：

属性（成员变量）用引号括起来，和属性不使用引号

> 前者表示json字符串，可以理解为调用了toString方法之后的打印结果，后者表示json对象
>
> 一般情况下，在前后端数据传输的时候采用json字符串的形式进行传输。

## 3.5 实操：

```java
// user ---> json字符串
// {"username":"admin","password":"admin1"}
// 可以用bejson.com校验json字符串是否正确
// 手动处理
String jsonStr = "{\"username\":\" + user.getUsername()}";

// 使用json解析工具,如：gson，fastjson，jackson（Spring框架内嵌）
Gson gson = new Gson();
String s = gson.toJson(user);

// 使用json处理含有泛型类型的对象，
JsonElement jsonElement = new JsonParser.parse(JsonStr);
JsonArray jsonArray = jsonElement.getAsJsonArray();
for(JsonElement element : jsonArray){
    User u= gson.fromJson(element, User.class);
}
```

导包：

```xml
<dependency>
	<groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.9.0</version>
</dependency>
```

# 4. MVC

**4.1 概述**：

Model-View-Controller，把一个应用的输入，处理，输出流程按照Model、View、Controller的方式进行分离，这样一个应该用被分成三个块，即模型层、视图层、控制层

> Model：数据模型和所有基于对这些数据模型的操作。User就属于数据模型，
>
> View：视图，也就是页面，视图中的数据一般情况下来自模型，比如用户的个人主页，
>
> Controller：控制器，主要作用就是将model和view的代码进行解耦分离。

**4.2 Dao层引入**：

在符合MVC设计模式的代码进行进一步的解耦分离，分出了三层：展示层、业务层、数据层

> 展示层：Controller + View充当，servlet此时从当的就是展示层
>
> 业务层：就是为了进一步的解耦
>
> 数据层：dao层，Data Access Object ,专门用来处理数据持久化相关操作的

# 5. 三层架构重构项目

controller：处理请求，做一些常规的校验工作，紧接着调用service的代码逻辑，返回结果，调用视图

service：当前功能具体的业务逻辑都应当主要写在service层，比如插入表，主要的业务代码应当写在service层，负责调用一个个的dao方法

dao：负责提供一些基础的增删改查等操作的方法，供service来调用

> 除了controller写的是servlet之外，service和dao写的全部都是接口和实现类