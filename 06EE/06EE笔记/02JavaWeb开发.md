[TOC]



# 1. 服务器开发

## 1.1 名词术语

- 静态web资源：一成不变
- 动态web资源：变化性、交互性，背后有程序在运行。
- 服务器：第一层含义表示的是硬件，一台性能比较高的计算机主机（云服务器）；**第二层表示的是软件**，该程序用来接收网络中用户的请求，以及对网络请求作出响应。
- B/S架构：浏览器/服务器架构。比如PC端
- C/S架构：客户端/服务器架构。更多出现在移动端

# 2. 简易静态资源服务器

```java
// 服务器的功能：需要持续不断的去监听某一端口号，如果有一个客户端向当前端口号发送数据，会将数据传送给服务器，服务器需要解析出客户端的意图，然后做出响应

public static void main(String[] args){
    ServerSocket serverSocket = new ServerSocket(8080);
    // 其中client就是连接进来的一个TCP连接
        while(true){
        	// 该行代码默认情况下是阻塞的
             Socket client = serverSocket.accept();
            Request request = new Request(Client);
            // 接收客户端传递过来的请求信息
            InputStream inputStream = client.getInputStream();
            byte[] bytes = new bytes[1024];
            // 此处inputStrea的类型是SocketInputStream,
            // 该处如果读取不到数据，不会立即返回-1，而是会阻塞一段时间
            int read = inputStream.read(bytes);
            String request = new String(bytes,0,read);
            // 希望给客户端返回信息
            OutputStream outStream = client.getOutputStream();
            // 下标从1开始，原因在于/开头的路径会被解析认为是一个绝对路径
            File file = new File(requestUrl.substring(1));
            StringBuffer buffer = new StringBuffer();
            if(file.exists() && file.isFile()){
                // 确保文件存在，且文件不是目录
                // 根据HTTP协议 200
                buffer.append("HTTP/1.1 200 OK\r\n");
                buffer.append("Content-Type:text/html\r\n");
                buffer.append("\r\n");
                outputStream.write(buffer.toString().getBytes("utf-8"));
                int length = 0;
                FileInputStream fileInputStream = new FileInputStream(file);
                while((lengh = fileINputStream.read(bytes))!=-1){
                    outputStream.write(bytes,0,length);
                }
                return;
            }
            // 404
            buffer.append("HTTP/1.1 404 NOt Found\r\n");
            bufferappend("COntent-Type:text/html\r\n");
            buffer.append("\r\n");
            buffer.append("<div style='color:red'>File Not Found</div>");
            outputStream.write(buffer.toString().getBytes("utf-8"));
    }    
}

class Request{
    private String method;
    
    private String requestUrl;
    
    private String protocol;
    public Request(Socket client){
        InputStream inputStream = client.getInputStream();
        byte[] bytes = new byte[1024];
        // 此处inputStrea的类型是SocketInputStream,
        // 该处如果读取不到数据，不会立即返回-1，而是会阻塞一段时间
        int read = inputStream.read(bytes);
        String request = new String(bytes,0,read);
    }
    // 将请求报文进行解析，拆分去取出请求行部分
    private void parseRequestLine(){
        int index = request.indexOf("\r\n");
        String line = request.substring(0, index);
        String[] parts = line.spilt(" ");
        
        method = parts[0];
        requestUrl = parts[1];
        protocol = parts[2];
        // 请求资源后面可能附着请求参数，还需要将请求参数从请求资源中剔除
        int hasParameter = requestUrl.indexOf("?");
        if(hasParameter != -1){
            String[] parts01 = requestUrl.substring(0,hasParameter);
            requestUrl = requestUrl.spilt(0,hasParmeter);
        }        
    }
    
}
```

# 3. 常见Web服务器

- WebLogic是BEA公司的产品，是目前应用最广泛的Web服务器，支持JavaEE规范
- TomCat

# 4. Tomcat

**4.1 目录组成**：

- bin：二进制文件存放目录
- conf：配置文件存放目录
- logs：日志文件存放目录
- webapps：部署资源位置

**4.2 启动**：

1. starup.bat启动
2. bin目录唤出cmd，指定startup

**4.3 停止**：

	1. shutdown.bat

## 4.4 部署资源

1. 服务器部署资源的本质：**把用户的一个网络路径加以解析转换成本地硬盘路径，在服务器本地硬盘上去搜索加载该文件，并响应**。

2. <span style="background:yellow;color:red;font-weight:bold">直接部署</span>：将资源文件直接放置在tomcat的webapps目录下

   > 若要在tomcat中部署资源，需遵守：tomcat中最小的单位是应用，资源文件必须放置在应用中才可以
   >
   > <span style="background:yellow;color:red;font-weight:bold">如何访问</span>：当输入http://localhost:8080时，可以理解为已经定位到了tomcat的webapps目录，只需要写出该文件相对于webapps的相对路径即可。
   >
   > **直接部署的另一种形式**：部署一个war包的形式，当tomcat启动时，会将war包自动展开形成目录

3. **虚拟映射**：资源文件不放在webaaps目录下

   - 方式一：conf/Catalina/localhost目录下，新建一个xml文件，文件名会被解析成为应用名，接下来tomcat中输入路径时需要输入应用名。

     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <Context docBase="D:\abc" />
     ```

   - 方式二：conf/server.xml中的Host节点配置

     ```ini
     <Context path="/abc" docBase="D:/abc" />
     ```

     

## 4.5 TomCat组件

1. **概述**：tomcat是由一系列可以配置的组件构成的。server.xml文件中定义了很多xml节点标签，tomcat启动时就会读取该配置文件，将每一个节点解析成一个组件（对象）
2. **TomCat请求处理流程**：(http://localhost:8080/abc/1.txt)
   - 域名解析，拿到域名对应的IP地址
   - TCP连接
   - 发送HTTP请求，TCP拆包，加上TCP头部，加上IP头部，网络中转传输，到达服务器主机，脱去IP头部，脱去TCP头部，重新组装
   - 被监听着8080端口的Connector程序接收到，将请求报文解析成为request对象，同时还会提供一个response对象。
   - Connector将这两个对象传递给engine，engine进一步将这两个对象传给Host
   - Host挑选一个合适的Context，然后进行下一步下发，尝试去搜索/abc应用Context
   - Host将这两个对象传递给Context，Context的职责是进一步去加载该文件，此时有效路径/1.txt，利用Context的docBase和/1.txt进行拼接，形成一个硬盘路径，查看该文件是否存在，加载该文件；如果找到，则将该文件数据写入到response中；若没有找到则写入404状态码到response
   - Connector读取response里面的数据，按照HTTP协议的要求，生形成HTTP协议请求，生成HTTP报文，再次经过TCP，IP链路层，网络中转到达客户端。

## 4.6 设置

1. **设置默认端口号**：

   > 访问网站时没有端口号，说明使用的是当前协议的默认端口号，对于http协议来说，默认端口号是80；对于https协议来说，默认端口号是443

   ```xml
   <!-- 若希望访问时不携带端口号，需要将port置为80 -->
   <Connector port="8080" protocol="HTTP/1.1"
              connectionTimeout="20000"
              redirectPort="8443"/>
   ```

2. **默认应用**：若查找某个应用没找到，则会将该请求交给一个默认应用，按照如下方式来解析（以/abc/1.txt为例）：

   - 在默认应用中查找abc的目录，在abc的目录下查找1.txt文件

   - 默认应用在webapps下目录名称是ROOT，但是TomCat访问ROOT应用下的资源时，不需要携带应用名，否则加载不到。

   - 即只要将文件放置到ROOT应用下，那么在TomCat中发访问时，是不需要加应用名的。

     > **设置默认应用的两种方法**：
     >
     > 1. 将webapps目录下的ROOT改名，自己新建一个ROOT目录
     > 2. conf/Catalina/localhost目录下新建一个ROOT.xml文件

3. 设置默认加载页面：

   > 访问TomCat时，指向的是一个目录而不是一个具体的文件，那么TomCat会加载默认界面

   - conf/web.xml文件中进行配置

     ```xml
     <welcome-file-list>
     	<welcome-file>index.html</welcome-file>
         <welcome-file>index.htm</welcom-file>
         <welcome-file>index.jsp</welcome-file>
     </welcome-file-list>
     ```

     