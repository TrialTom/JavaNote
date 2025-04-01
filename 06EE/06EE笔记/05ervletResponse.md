[TOC]

# 1. 简介

Defines an object to assist a servlet in sending a response to the client. The servlet container creates a ServletResponse Object and passess（把） it as an argument(参数) to the servlet's service method.

# 2. 常用方法

```java
// 设置响应报文
response.setStatus(404);
// 设置响应头
response.setHeader("Server", "alibaba");
// 返回响应体
response.getWriter().println("<div>File not Found</div>");

// 设置响应体的编码格式
response.setCharacterEncoding("GBK");
// 将编码格式告诉浏览器（通过响应头的方式）
response.setHeader("Content-Type","text/html;charset=gbk");
response.setContentType("text/html;charset=utf-8");//等价于上面的
```

# 4. 输入字节数据

```java
// 访问当前servlet时，把WEB—INF目录下的1.jpeg响应给客户端
ServletContext servletContext = getContext();
String realPath = servletContext.getRealPath("WEB-INF/1.jpeg");
File file =- new File(realPat);
ServletOutPutStream outputStream = response.getOutpuStream();
int length = 0;
byte[] bytes = new byte[1024];
while((lenght = fileInputStream.read(bytes)) != -1){
    outputStream.write(bytes, 0, length);
}
fileInputStream.close();
```



# 5. 定时刷新

```java
// 方式一：设置一个数字，表示多少秒刷新一次
response.setHeader("refresh", "1");

// 方式二：设置为2，跳转页面
response.setHeader("refresh", "2;url="+request.getContextPath() + "1.html");
```



# 6. 重定向

**6.1 概述**：301，302，307状态码 + Location响应头

**6.2 示例代码**：

```java
// 访问当前servlet，直接重定向到1.html
response.setStatus(302);
// 网络路径
response.setHeadle("LOcation", request.getContextPath() + "/1.html");
// 等价以下写法
response.sendRedirect(request.getContextPath() + "/1.html");
```

# 7. 下载

**7.1 补充**：

针对浏览器来说，浏览器有一个行为，如果可以打开文件，默认会执行打开操作；无法打开的文件，会默认执行下载操作

可以打开的那部分文件，添加一个响应头之hi偶，就会执行下载操作

```java
// 内容处置方式
response.setHeader("Content-Disposition", "attachment;filename=1.jpeg");
```

**7.2 下载主体代码和查看的主体代码相同**：

 ```java
// <a herf="/server/pic/stream">查看图片</a>
// <a herf="/server/pic/down">下载</a>

@WebServlet("/pic/*")
doGet(){
    String requestURI = request.getRequesURI();
    String op = requestURI.replace(request.getContextPath() + "/pic/", "");
    if("stream".equals(op)){
        stream(request,response);
    }else if("down".equals(op)){
        down(requset,response);
    }
}
// 下载图片方法
private void down(...){
    response.setHeader("Content-Disposition", "attachment;filename=1.jpeg");
    stream(requset, response);
}
// 显示图片方法
 ```



