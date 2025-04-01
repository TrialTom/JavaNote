[TOC]



# 一、DBUtils

## 1. 介绍

DBUtils是一个帮助我们更加简单的去操作JDBC的一个工具类，可以理解为是一个操作数据库的框架

> 框架：前人开发好的一种半成品软件，这个半成品中有一些最基础的功能，我们后续在框架的基础上进行二次开发，可以去定制我们自己的功能，使用框架的目的是为了提高效率。

## 2. 作用

主要帮助我们简化--获取statement，发送sql语句，解析结果--这三步

## 3. 使用

1. 导包：

   1. 导入lib中

2. 配置：不需要配置

3. 使用（示例代码）：

   ```java
   // 无参构造
   QueryRunner queryRunner1 = new QueryRunner();
   // 有参构造
   QueryRunner queryRunner2 = new QUeryRunner(DruidUtils.getDateSource());
   // 获取Connection对象
   Connection connection = DruidUtils.getConnection();
   // 包装SQL语句发送给MySQL服务器
   int affectedRows = queryRunner2.update("insert into account values(?,?,?)",1,2,3);
   // 调用ResultSetHadler需要传入一个ResultSetHandler接口，自定义Handler方法
   Object rs = queryRunner.query("select * from table1 where id = ?", new MyResultSetHandler(), 1);
   ```

## 4. 具体类

### 1. DBUtils

- 方法：

  ```java
  public static void close(Connection conn)throws SQLException;
  
  public static void close(ResultSet rs)throws SQLException;
  
  public static void closeQuietly(Connection conn);
  
  public static void closeQuietly(Connection conn, Statement stmt, ResultSet rs);
  ```

  > DBUtils这个类主要就是对close方法、commit方法、rollback方法做一些简单的封装

### 2. QueryRunner

1. 作用：最主要的功能就是帮助我们去发送SQL语句，封装参数。

2. 构造方法：

   ```java
   // 无参构造
   public QueryRunner(){
       super();
   }
   // 有参构造
   public QueryRunner(DataSource ds){
       super(ds);
   }
   ```

3. 方法：

   ```java
   query(String sql, ResultSetHandler<T> rsh);
   query(Connection conn, String sql, ResultSetHander<T> rsh);
   
   update(String sql);
   ```

### 3. ResultSetHandler

1. 概述：结果集处理器，可以解析结果集（ResultSet）对象，是一个接口，需自己去实现

2. 方法：

   - BeanHandler：

     - 示例代码：

       ```java
       TableObject obj = queryRunner.query("select * from account where id = ?", new BeanHandler<>(TableObject.class), 1);
       ```

       

     - 注意：

       - 返回对象的时候，对应的变量名和表中的列名要保持一致
       - 对应的类型要保持一致

   - BeanListHandler：可以返回一个Bean对象集合

   - MapHandler：将结果集转换为Map（key：列名 value：值）

   - ColumListHandler：取单列的数据为一个list

   - ScalarHandler：获取单个值

3. 示例代码：

   ```java
   // 自定义ResultSetHandler接口的实现类
   class MyResultSetHandler implements ResultSetHandler<TableObject>{
       @Override
       public TableObject handle(Resultset rs)throws SQLException{
           TableObject obj = new TableObject();
           if(rs.next){
               int antInt = rs.getInt("id");
               String name = rs.getString("name");
               
               obj.setId(id);
               obj.setName(name);
           }
           return obj;
       }
   }
   ```

   