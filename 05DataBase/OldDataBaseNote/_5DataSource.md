[TOC]



# 一、数据连接池

> DataSource，数据源，是JDBC中定义的一个接口，这个接口就是表示数据库连接池

## 1. 介绍

1. **问题一**：为什么要使用数据库连接池？

   > - 使用JDBC的流程
   >
   >   ```java
   >   // 1. 注册驱动
   >   Class.forName("com.mysql.jdbc.driver");
   >   // 2. 建立JDBC与MySQL服务器的连接
   >   Connection connection = DriverManager.getConnection(String url,String username, String password);
   >   // 3. 获取Statement
   >   Statement sta = connection.createStatement();
   >   // 4. 封装，发送sql语句
   >   ResultSet = res = sta.excutedQuery(String sql);
   >   // 5. 解析结果
   >   while(res.next()){
   >       String name = res.getString(String name);
   >   }
   >   // 6 关闭资源
   >   res.close();
   >   sta.close();
   >   connection.close();
   >   ```
   >
   > - 为了重复使用connection，统一管理网络连接

2. **概述**：

   > - 数据库连接池其实就是利用池化的思想，在一个池子（容器）中维护多个连接。
   > - 当我们使用JDBC时，就从池子里面拿一个连接（Connection对象），然受去使用，当我们JDBC业务执行完了之后，就将Connection对象返还到池子中。
   > - 这样就避免反复的创建数据库连接和销毁数据库连接，有助于提高JDBC的效率

## 2. 手动实现

1. **思路**：

   > - 具备的功能：
   >   - 获取连接
   >   - 返回连接
   > - 使用的数据结构：
   >   - List
   >   - 数组

2. 示例代码：

   ```java
   class MyConnectionPool{
       // 连接池存放的容器
       static LinkedList<Connection> connectionPool;
      
       static{
            // 初始化数据库连接池
           connectionPool = new LinkedList<>();
           
           // 放入Connection对象
           Connection connection = JDBCUtils.getConnection();
           connectionPool.addFirst(connection);
       }
       // 获取连接
       public static Connection getConnection(){
           Connection connection = connectionPool.removeLast();
           return connection
       }
       // 返回连接
       public static void returnConnection(Connection connection){
           connectionPool.addFirst();
       }
   }
   ```

   

## 3. 使用开源的数据库连接池

### DBCP

1. 概述：DBCP是Apache开源组织下面的一个开源项目

2. 使用：

   - 导包

     - commons-dbcp1.4
     - commons-pool1.6

   - 配置

     - 使用DBCP，需要配置

       ```properties
       # 常规配置
       driverClassName=com.mysql.jdbc.Driver
       url=jdbc:mysql://localhot:3306/test
       username=root
       password=1234
       
       # 初始化大小
       initialSize=10
       # 最大值
       maxActive=50
       # 最大闲置值
       maxIdle=20
       # 最小闲置值
       minIdle=5
       # 等待时间
       maxWait=60000
       
       connectionProperties=useSSL=false;characterEncoding=utf8
       
       # 连接自动提交
       defaultAutoCommit=true
       ```

       

   - 使用

     ```java
     class DBCPUtils{
         // 数据库连接池
         static DataSource dataSource;
         
         static{
             Properties properties = new Properties();
             FileInputStream in = new FileInputStream("dbcp.propertise");
             propertise.load(in);
             dataSource = BasicDataSourceFactory.createDataSource(propertise)
         }
         
         public static Connection getConnection(){
             Connection connection = null;
             try{
                 connection = dataSource.getConncetion();
             }catch(SQLException e){
                 e.printStackTrace();
             }
             return connection
         }
     }
     ```

     

### C3p0

1. 导包

2. 配置

   - 注意：C3p0要求在项目源代码路径（scr目录下）下配置一个c3p0-config.xml的文件

     > **xml**：类似html，早期开发用来传输数据（目前传输数据的功能被JSON取代），目前xml文件最常见的作用是用来做配置文件

   - 配置文件：

     ```xml
     <?xml version="1.0" encoding="UTF-8">
     <!-- 文件头部 -->
     <!-- 在xml中，一些特殊字符需要写成转义字符
     	>	&gt;
     	<	&lt;
     	>=	&gt;=
     	<=	&lt;=
     	&	&amp;
     -->
     <c3p0-config>
     	
     </c3p0-config>
     ```

     

3. 示例代码：

   ```java
   class C3p0{
       static DataSource dataSource;
       
       // 初始化
       static{
           dataSource = new ComboPooledDataSource();
       }
       
       // 获取连接
       public static Connection getConnection(){
           Connection connection = null;
           try{
               connection = dataSource.getConnection;
           }catch(SQLException e){
               e.printStackTrace();
           }
           return connection;
       }
   }
   ```

   

### Druid

> 相关：Druid是阿里巴巴开发的一个数据库连接池，现在交给Apache来维护了。OceanBase

1. 导包

2. 配置：需要配置一个properties文件

   ```properties
   driverClassName=com.mysql.jdbc.Driver
   url=jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false
   username=root
   password=123456
   ```

   

3. 示例代码：

   ```java
   class DruidUtils{
       // 数据库连接池
       static DataSource dataSource;
       
       static{
           try{
               Properties properties = new Properties();
               FileInputStream fileInputStream = new FileInputStream("druid.properties");
               properties.load(fileInputStream);
               
               // 初始化数据连接池
               dataSource = DruidDataSourceFactory.createDataSource(properties);
           }catch(Exception e){
               e.printStackTrace();
           }
       }
       
       // 获取连接
       public static Connection getConnection(){
           Connection connection = null;
           try{
               connection = dataSource.getConnection();
           }catch(SQLException e){
               e.printStackTrace();
           }
       }
   }
   ```

   