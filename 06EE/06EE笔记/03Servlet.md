[TOC]



# 1. 简介

Servlet是Sund公司提供的一门用于开发动态web资源的技术,[Servlet (Java(TM) EE 7 Specification APIs) (oracle.com)](https://docs.oracle.com/javaee/7/api/javax/servlet/Servlet.html#service-javax.servlet.ServletRequest-javax.servlet.ServletResponse-)

**A servlet is a small Java program that runs within a Web server**. Servlets receive and respond to requests from Web clients, usually across HTTP,the HyperText Transfer Protocol.

To implement this interface, you can write a generic servlet that extends `javax.servlet.GenericServlet` or an HTTP servlet that extends `javax.servlet.http.HttpServlet`.

# 2. 开发servlet

## 2.1 方式一与方式二

**2.1.1 编写servlet**：

```java
import javax.servlet.*;

// 方式一
public class FirstServlet extends GenericServlet{
    public void service(ServletRequest req, ServletResponse res)throws ServletException{
        System.out.println("hellow ")
    }
}

// 方式二：继承HttpServlet
public class MyServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletExcption{
        super.doGet(req, resp);
    }
}

```

- **问题一**：为什么在继承GenericServlet的servlet中执行的是service方法，而在继承HttpServlet的情形下却没有了service方法，变成了doGet或者doPost方法

  > service方法是在Servlet接口中的，HttpServlet中也有service方法
  >
  > <span style="background:yellow;color:red;font-weight:bold">servlet的程序执行入口永远是service方法</span> 

## 2.3 方式三

```java
// 可简化为: @WebServlet("/servlet3")
@WebServlet(name = "third", urlPatterns = "/servlet3")
public class ThirdServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException{
        Systme.out.println("DoGet3");
    }
}
```

## 2.4 IDEA和Tomcat的关联方式

**补充：输出窗口的介绍**：

- Server：输出窗口
- Tomcat Localhost Log：一般情况下，可以打印错误日志
- Tomcat Catalina Log：启动信息

**CATALINA_BASE**：从此目录可查到config等一系列部署文件，可以理解为idea会复制tomcat文件，利用这些配置文件重新开启一个新的tomcat示例。

**结论**：应用部署的时候，应用根目录（out/artifacts/xxx_war_exploded）和开发时的目录（web）不是一回事，web目录下的文件会同步到应用根目录，单向的

> 若开发目录中的文件未同步到应用根目录中，则可以使用以下方法：
>
> 1. 尝试先使用redeploy重新部署来试试
> 2. 执行rebuild project，然后再次执行1
> 3. 直接将out目录删除在重现执行2和1.



# 3. 执行过程

**3.1 编译**：

> 知识回顾：
>
> 类加载的过程：
>
> JDK中的类库位于硬盘上面的，但可以编译运行代码时，类库会加载到内存中，这个过程由类加载器来完成。
>
> - BootStrap类加载器：负责加载JDK中的核心类库
> - Extension类加载器：负责加载JDK/ext目录下的类库
> - System类加载器：可以主动通过设置一个参数 -classpath xxx.jar 将第三方位置下的jar包加载到内存(`javac -classpath xxx.servlet.jar xx.java`)

**3.2 运行**：需要将java文件部署在服务器中

**3.3 部署**：

把class文件部署到服务器之后，如果和静态资源文件一样直接访问，此时是下载，而不是运行。并且存在隐患（服务器的源代码可以被用户下载到，服务器的任何安全漏洞别人知道的一清二楚）

> 针对此隐患，我们需要设置一个WEB-INF目录，存放在该目录下的任何文件都不可以直接访问到。要想访问该目录下的文件，可以通过映射访问
>
> ```xml
> <!-- web.xml -->
> 
> <servlet>
>     <servlet-name>first</servlet-name>
>     <servlet-class>FirstServlet</servlet-class>
> </servlet>
> 
> <servlet-mapping>
>     <servlet-name>first</servlet-name>
>     <url-pattern>/servlet1</url-pattern>
> </servlet-mapping>
> ```
>
> 还需要将class文件存放classes目录下

## 3.4 执行过程的总结  

1. 域名解析（以http://localhost/server/servlet1为例）
2. TCP连接建立
3. 发起HTTP请求，到达服务器之后
4. 被监听80端口的Connector接收到，将其解析成为request对象，同时提供一个response对象
5. Connector将这两个对象传递给Engine，Engine进一步传递给Host，Host查找/server应用，
6. 找到之后，则将这个两个对象进一步传递给Context应用。应用拿到的路径是/servlet1
7. 根据web.xml文件中配置的映射关系/servlet1 -- FirstServlet，利用反射去运行当前servlet的service方法
8. service方法通过反射被调用，之前一直传递过来的request、response对象作为参数来运行
9. service方法在运行时，可以读取request对象，也可以设置response数据，最终Connector会读取response里面的数据，并生成HTTP响应报文

# 4. Servlet生命周期

1. 概述：

   This interface defines methods to initialize a servlet, to service requests, and to remove a servlet from the server. These are known as life-cycle methods and are called in the following sequence:

   1. The sevlet is constructed, then initialized with the `init` method.
   2. Any calls from clients to the service method are handled.
   3. The servlet is taken out of service, then destroyed with the `destroy` method, then garbage collected and finalized.

2. **init()**：

   - 通过调用init方法来完成一些实例化操作，会在第一次访问之前加载一次
   - init方法只会被加载一次，一个servlet在tomcat服务器中只有一个对象
   - 默认情况下，init方法是在客户端第一次访问某个servlet之前执行，可以设置一个参数：load-on-starup=非负数，会随着应用的启动而加载，可以将init方法的执行时机提前。`@WebsServlet(value="/life",loadOnStartup=1)` 

3. **destroy()**：

# 5. Url-Patten细节

**问题一**：一个servlet可以不可以设置多个url-pattern?

> ```java
> @WebServlet(value = {"/life", "/life1"}, loadOnStartup = 1)
> ```

**问题二**：多个servlet可不可以设置同一个url-pattern?

> 不允许

**问题三**：url-pattern的写法有哪些？

> 只有两种合法的写法：/xxxx和*.xxxx

**问题四**：url-pttern的优先级问题

> /xxx的优先级高于*.xxx，都是/xxx，谁的匹配程度越高，优先调用谁

# 6. 缺省Servlet

**6.1 概述**：<span style="background:yellow;color:red;font-weight:bold">Tomcat中处理请求，无论任何一个请求，最终都要求去由一个servlet处理</span>，如果当前应用中没有配置servle，Tomcat会给我们提供一个servlet，这就是缺省servlet。专门用来处理那些没有任何servlet可以处理的请求。

**6.2 功能**：该servle的主要业务就是识别出请求资源，然后利用docBase进行拼接，进行磁盘io，查找文件是否存在。

**6.3 实质**：缺省Servlet的url-pattern是`/`，如果你在你的应用中重新实现了一个新的缺省Servlet，那么Tomcat提供的缺省Servlet就不会再给你使用了。

**6.4 实现**：

```java
// 获取文件的名称
String servletPath = request.getServletPath();
// 获取应用名称
ServletContext servletContext = getServletContext();
// 获取请求文件的绝对路径
String realPath = servletContext.getRealPath(servletPath);
File file = new File(realPaht);
// 判断文件是否存在
if(file.exists() && file.isFile()){
    FileInputStream fileInputStream = new FileInpuStream(file);
    ServletOutPutStream outputStream = response.getOutpuStream();
    int length = 0;
    byte[] bytes = new byte[1024];
    while((length = fileInpuStream.read(bytes)) != -1){
        outputStream.write(bytes, 0, length);
    }
    fileInpuStream.close();
    outputStream.close;
    return;
}
response.setStatus(404);

```



# 6. ServletConfig（了解）

**6.1 概述**：该对象可以获取servlet的一些初始化参数

```xml
<servlet>
	<servlet-name>config</servlet-name>
    <servlet-class>ConfigServlet</servlet-class>
    <init-parm>
    	<param-name>key</param-name>
        <param-value>abc</param-value>
    </init-parm>
</servlet>

// 通过一下获取key值
ServletConfig servletConfig = getServletConfig();
String key = servletConfig.getInitParameter("key");
```



# 7. ServletContext

**7.1 特点**：

- 该对象的生命周期基本上和应用的生命周期保持一致，随着应用的启动而创建，随着应用的销毁而销毁。
- 在每个应用中有且只有一个。

**7.2 context域**：一块用来共享数据的内存空间

**7.3 示例代码**：

```java
// 拿到servletContext对象的引用
ServletContext servletContext = getServletContext();
// 存入数据
servletContext.setAttribute("name","abc");

// 另一servlet取出数据
Object name = servletContext.getAttribute("name");

// 加锁版
ServletContext servletContext = getServletContext();
synchronized(servleConetext.getClass()){
    Integer count = (Integer)servletContext.getAttribute("count");
    if(count == null){
        count = 0;
    }
    count++;
    servletContext.setAttribute("count", count);
    response.getWriter().println(count);
}
```

**7.4 获取绝对路径**：

1. Tomcat中部署的应用使用getAbsolutePath获取的路径：返回的是相对tomcat的bin目录路径。

   > 首先此方法返回的相对路径是相对于用户的工作目录，即JVM，tomcat在bin目录下开启了jvm，而tomcat本生是一个java程序，会调用jvm，所以就相当于在一个大的main方法下调用了getAbsolutePath方法。

2. **示例代码**：

   ```java
   // 应用的根目录其实就是Context的doBase
   ServletContext servletContext = getServletContext();
   String doBase = servletContext.getRealPath("");
   println("doBase");
   
   // 方式二
   // getRealPath中传入文件相对路径，即可得到绝对路径
   String path = servletContext.getRealPath("abc.txt");
   ```

   