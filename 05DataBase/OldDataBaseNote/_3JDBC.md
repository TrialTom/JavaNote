[TOC]



# 一、JDBC

## 1. 数据库的访问过程

> 步骤：
>
> 1. 建立网络连接，向MySQL服务器发送用户名和密码
> 2. MySQL服务器收到用户名密码并校验，
> 3. 向客户端返回连接成功
> 4. 客户端发送SQL语句
> 5. 服务端执行SQL语句，把执行的结果返回给客户端
> 6. 客户端解析结果，并显示

## 2. JDBC介绍

1. **概述**：Java Database connection。Java数据库连接，可以理解为数据库的Java客户端

   > JDBC是Oracle公司给我们指定的一套java语言中去连接数据库的规范，为了连接的关系型数据库，所以有了这个规范

2. **与各数据库的关系**：

   > java中只定义了JDBC接口，具体的JDBC的实现类需要由各数据库的开发者实现，称为数据库驱动

3. **使用**：

   - 下载MySQL驱动到本地，并导入到项目[下载地址](https://mvnrepository.com) 

   - ```java
     String url = "jdbc:mysql://localhost:3306/databaseName?useSSL=false&characterEnconding=utf8";
     String username = "root";
     String password = "123456";
     // 0. 注册驱动（加载驱动程序）
     
     // DriverManager:驱动管理器
     //  可以省略，自动注册（SPI）,不过一般不会忽略
     // DriverManager.registerDriver(new Driver()); 
     // 等价
     Class.forName("com.mysql.jdbc.Driver");
     
     // 1.建立网络连接 Connection对象表示网络连接
     Connection connection = DriverManager.getConnection(url,username,password);
     
     // 2.发送sql语句
     // statement对象主要是用来帮助我们向MySql服务器发送SQL语句的
     // 帮助我们把SQL语句封装到网络请求中
     Statement statement = connection.createStatement();
     
     // 发送增删改的语句
     int affectedRows = statement.executeUpdate(String sql);
     // 发送查询语句，查询出来的结果是一张临时表，Java中没有对应的数据结构
     // JDBC中，制定了一个接口来接收查询的数据 ResultSet
     ResultSet resultset = statement.executeQuery(String sql);
     
     // 3.获取sql语句的执行结果
     
     // 4.解析结果集
     // resultSet.next(),previous(),beforeFirst(),afterLast(),
     String name = resultSet.getString("columnName");
     // 5.断开连接
     statement.clone();
     connection.clone();
     ```

   - url详解：

     ![url](E:\javaEE program\05DataBase\_05DataBase笔记\url.png)

4. **优化**：

   - 注册驱动优化

     ```java
     // 注册驱动
     
     // 第一种注册方式
     DriverManager.registerDriver(new Driver());
     new Driver();
     class.forName("com.mysql.jdbc.Driver");
     
     // 优化，解耦
     Class.forName(driver); // 配置文件中配置driver
     ```

   - 配置文件：将代码中url, username, password, driverClassName写入配置文件

     ```java
     // 静态代码块，加载配置文件
     static {
         try{
             Properties properties = new Properties();
             FileInputStream fileInputStream = new FileInputStream("jdbc.properties");
             properties.load(fileInputStream);
             
             url = properties.getProperty("url");
             username = properties.getProperty("username");
         }catch(Exception e){
             System.out.println("配置文件读取有异");
         }
     }
     ```

   - 关闭资源：通过try...finally确定关闭顺序



## 3. 拓展

> [volatile](https://www.imooc.com/learn/352)：修饰在变量之前

# 二、 数据库注入问题

## 1. 概述：

> 由于传递给MySQL的SQL语句是通过字符串拼接而来，那么在拼接的时候，就可能拼入SQL中的关键字，从而改变SQL语句的结构，造成安全上的隐患。这就是数据库注入问题、

## 2. 解决（PreparedStatement）

```java
// 获取PrepareStatement
// 还没有赋值的参数使用 ？ 来占位
// 这句带代码会将SQL语句交给MySQL服务器来编译（预编译）
PrepareStatement prepareStatement = connection.prepareStatement("select * from table where id = ?");
// 对第一个问号赋值，1：代表第一个问号
preparedStatement.setString(1, idValue);
// 执行SQL语句，Java程序需要和MySQL服务器进行通信，目的是为了把输入的参数传递给MySQL服务器
ResultSet resultSet = prepareStatement.executeQuery();
```

## 3. 总结

> PreparedStatement解决数据库注入问题的原理：
>
> - 产生问题的原因主要原因是：Statement把SQL和用户输入的参数进行拼接，而用户输入的参数可能含有MySQL相关的关键字，这些关键字拼接到SQL语句之后，会影响SQL语句的整体结构，导致影响SQL语句的执行结果。
>
> - PreparedStatement解决数据库注问题的思路：先把SQL语句进行预编译，让MySQL服务器识别到SQL语句的整体结构，后续用户输入的参数只会被当成纯参数。

# 三、API

## DriverManager

> 1. 概述：数据库驱动管理器，主要用来管理数据库驱动程序
>
> 2. 示例代码：
>
>    ```java
>    // 注册驱动
>    DriverManager.registerDriver(new Driver());
>    
>    // 获取连接
>    Connection conn = DriverManger.getConnection(Sring url, String username, String password);
>    ```
>
>    

## Connection

1. 概述：JDBC中的接口，表示JDBC和数据库服务器之间的连接

2. 示例代码：

   ```java
   // 创建Statement对象
   Statement stat = connection.createStatement();
   
   // 创建PreparedStatement
   Preparedstatement pst = connection.preparedStatement(String sql);
   
   // 事务相关API
   connection.setAutoCommit(false);// 开启事务
   connection.commit(); // 提交事务
   connection.rollback(); // 回滚事务
   ```

## Statement

1. 概述：接口，主要是来帮我们去封装SQL语句，发送给MySQL服务器

2. 示例代码：

   ```java
   // 执行增删改语句，返回影响的行数
   // 也可以执行一些没有返回的SQL语句，如：use ，建表语句
   int affectedRow = statement.executeUpdate(String sql);
   
   // 执行查询语句，返回结果集
   ResultSet rs = statement.executeQuery(String sql);
   ```

   

## PreparedStatement

1. 概述：是一个Statement子接口，帮助我们预编译的Statement

2. 示例代码：

   ```java
   // 设置参数,
   // index:表示 ？ 的位置，从1开始
   preparedStatement.setString(int index, String value);
   ```

   

## ResultSet

1. 概述：存储SQL查询出的结果表（临时表）

# 五、批处理

> 默认情况下MySQL的批处理是一条一条执行的，在url后加入：<span style="background:yellow;color:red;font-weight:bold">rewriteBatchedStatements=true</span> 提升preparedStatement插入的效率

## 1. 代码示例

```java
try{
    Statement statement = connection.createStatement();
    
    for(int i = 0; i < 1000; i++){
        String sql = "insert into tableName values(null, 'statement', 200, 'statement')";
        // 添加sql语句
        statement.addBatch(sql);
    }
    // 批量执行
    statement.executeBatch();
}catch(Exception e){
    e.printStackTrace();
}
```

