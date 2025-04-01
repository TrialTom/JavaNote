[TOC]



# 1. 介绍

**1.1 三大组件**：

- Servlet：开发动态的Web资源
- Listener：监听器。监听ServletContext对象的创建和销毁
- Filter：过滤器，拦截

# 2. Listener

**2.1 概述**：

> web中的监听器
>
> 被监听的对象：ServletContext对象
>
> 监听者：我们编写的监听器对象
>
> 监听事件：servletContext对象的创建和销毁
>
> 触发事件：调用我们编写的监听器中相应 的方法

**2.2 使用**：

```java
// 也可使用web.xml配置
// <listener><listener-class></listener-class> </listerner>
@WebListener
public class MyservletContextLinstener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent){
        System.out.println("context被创建了");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent servletnContextEvent){
        System.out.printLn("context被销毁了");
    }
}
```

# 3. Filter

**3.1 Filter简介**：

- 过滤器是Servlet规范的高级特性，三大web组件之一
- 过滤器从servlet2.3规范开始引入的，过滤器是一种 web应用程序组件，可以部署在web应用程序中。
- 过滤器由servlet容器调用，用来拦截以及处理请求和相应。过滤器本事不能生成请求和响应对象，但是可以对请求和响应对象进行检查和修改。

**3.2 原理**：

- 过滤器介于客户端域servlet/jsp等相关的资源之间，对于与过滤器关联的servlet来说，过滤器可以在servlet被调用之前检查并且修改request对象。在servlet调用之后检查并修改response。

**3.3 示例代码**：

```java
// 1. 编写一个类实现Filer
@WebFilter("/filter")
public class MyFilter implements Filter {
    // 应用被加载成功，init方法就会执行
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter init");
    }
	// 类似于servlet的service方法，每一次经过filter，都会执行一次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	// 对请求放行
        filterChain.doFilter(servletRequest, servletResponse);
    }
	// 应用卸载、服务器关闭
    @Override
    public void destroy() {

    }
}
```

**3.4 Filter关联Servlet**：

- 将servlet的url-pattern直接赋给filter，默认情况下执行拦截操作。

  ```java
  // 对请求放行
  // 采用一种类似递归的方式调用下一次组件
  filterChain.doFilter(servletRequest, servletResponse);
  ```

**3.4 Filter url-pattern**：

1. servlet的url-pattern直接赋给filter不会报错

2. filter很有必要设置为/*，为了拦截更多的请求

3. 多个filter可以设置相同的url-pattern

   > servlet不可设置相同的url-pattern主要的原因在于servler需要去开发动态web资源，需要做出响应的，只需要一个组件去响应接即可。
   >
   > 但是filter主要职责不是去响应请求，而是对请求以及响应进行修改等，允许有多个filter同时处理一个请求。

**3.5 Filter链地执行先后顺序**：

1. 如果是注解，那么按照类名首字母ASCLL先后顺序
2. 如果是web.xml，那么按照filter-mapping声明的先后顺序

**3.6 引入Filter之后的请求处理流程**：

1. 域名解析，TCP握手，建立TCP连接，发送HTTP请求报文
2. HTTP请求报文到达服务器之后，被监听80端口号的Connector接收到，将其解析成request对象，提供一个response对象
3. Connector将这两个对象穿给engine
4. Engine进一步去挑选Host
5. Host根据请求的地址，尝试去找/server的应用，如果找到，则将这两个对象进行进一步传递。
6. 应用拿到有效路径部分，先查找有没有filter可以处理该请求。如果有，且有多个，则把多个filter同时加入到请求的处理中来，按照filter执行先后顺序形成一个链表。
7. 再次去找有没有servlet可以处理该请求，如果找到了，则将该servlet加入到链表中，如果没有找到，则将缺省Servlet加入到链表中
8. 依次去调用链表中每个组件对应的方法，filter.doFilter方法，servlet.service()。

