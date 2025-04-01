[TOC]



# 1. 简介

public interface ServletRequest

Defines an object to provide client request information to servlet. The servlet container creates a ServletRequest object and passes it as an argument to the servlet's service method.

pulbic interface HttpServletRequest

Extends the ServletRequest interface to provide request information for HTTP servlets.

servletRequest是对用户发送过来的请求报文的封装，但是请求报文可能有很多种不同的协议。如果发送过来的是HTTP请求报文，那么它可以解析转换成HTTPServletRequest

# 2. 常用方法

> ServletRequest是对请求报文的封装，那么利用该对象的方法理应可以获取到请求报文的各个部分

- 获取HTTP请求：

  ```java
  // 获取请求行
  String method = req.getMethod();
  // URL - （协议，主机，端口号）= URI
  String requestURI = req.getRequestURI();
  StringBuffer requestURL = req.getRequestURL();
  String protocol = req.getProtocol();
  // 获取请求头
  Enumeration<String> headerNames = req.getHeaderNames();
  while(headerNames.hasMoreElements()){
      String element = headerNames.nextElement();
      String header = req.getHeader(element);
      System.out.println(element + ":" + header);
  }
  ```
- getInputStream：用来获取请求体

  ```java
// 获取服务器主机信息
  String localAddr = req.getLocalAddr();
  // 端口号
  int localPort = req.getLocalPort();
  // 获取客户端主机信息
  String remoteAddr = req.getRemoteAddr();
  // 获取客户机的端口号
  int remotePort = req.getRemotePort();
  ```

# 3. 获取请求参数

**3.1 概述**：

- 用户数据提会随着HTTP请求报文传输到服务器，服务器会将其解析封装到request对象中，于开发者而言，需要做的就是利用request提供的方法来获取请求参数。

**3.2 方法介绍**：

```java
// 接收用户提交过来的参数，通过传递进入input输入框的那么属性，来获取对应的值
String username = request.getParameter("usrename");
// 提交的checkbox类型的数据，
String[] courses = request.getParameterValues("course");
```

**获取请求参数的系列方法**：无论是get还是post请求，都可以获取到请求参数，但是该方法有一个限制条件，必须是key=value类型的数据。

**3.3 进阶**：

```java
// <form action="http://localhost/server" method="get">
// 获取请求参数
Enumeration<String> parameterNames = request.getParameterNames();
while(parameterNames.hasMoreElements()){
    String key = parameterNames.nextElement();
    String[] values = request.getParameterValues(key);
    System.out.println(key + ":" + Arrays.toString(values));
}
```

# 4. 参数封装

**4.1 通过反射**：

**4.2 通过BeaUtils**：

- 导包：commons-beanutils, commons-collections, commons-logging

- 示例代码：

  ```java
  // 使用工具类来封装
  User user = new User();
  BeanUtils.populate(user, request.getParameterMap());
  ```

- 注意：

  <span style="background:yellow;color:red;font-weight:bold">EE阶段，jar包的存放位置必须要严格存放，必须存放在WEB-INF/lib目录下</span>。

  > 原因：编写的ee程序位于硬盘，运行时位于内存中，需要有类加载器负责将类，jar包加载到内存中，这一类加载器是由tomcat提供的，而整个类加载器只会到WEB-INF/classes目录下去加载class文件以及到WEB-INF/lib目录下去加载jar包

**4.3 中文乱码**：

`request.setcharacterEncoding(String env)`：

> 要点：
>
> 1. 只针对请求体乱码有效
> 2. 必须要求在读取请求参数之前调用

# 5. 网络路径

**5.1 全路径**：访问协议，主机，端口号，后续路径，例如：http://localhost/server/abc.html

**5.2 相对路径**：提交的地址相对于当前页面的地址的一个相对路径。不过相对路径太过于依赖当前页面所在路径，不建议使用

**5.3 /应用名/资源路径（推荐使用）**：

比如当前servlet提交地址：/server/abc

# 6. 转发（了解）

1. 概述：一个组件处理了部分请求的逻辑之后，如果需要将请求转交给另一个组件来处理，那么此时就需要使用转发。如：访问某个servlet，需要在该servlet中调用另一个servlet。

   > 可以通过给tomcat服务器发送一个指令，让tomcat去调用它

2. 实例代码：

   ```java
   // 网络请求分发器需要传递一个字符串，该参数需要写入另外一个组件的网络路径
   request.getRequestDispatcher("/servlet2").forward(request,response);
   ```

# 7. request域

1. 概述：request内部相当于有一个map，有一可以用来进行数据共享的场所，只要可以拿到同一request对象的引用，那么就可以共享request域中的数据。

   > 频繁的刷新同一个请求地址，对于tomcat服务器来说会创建多个，只有转发才会使用