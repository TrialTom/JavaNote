[TOC]



# 一、Mybatis

## 1. 介绍

1. 1概述：

   Mybatis是一个ORM（Object relationship mapping）框架，对象关系映射框架，<span style="background:yellow;color:red;font-weight:bold">可以把Java对象映射为关系型数据库表中的记录，也可以把关系型数据库表中的记录映射为对象</span>。可以帮助我们在java代码中去执行sql语句的一个框架。

1. 2优势：

- 传统的JDBC：

  > - SQL语句和代码耦合在一起，SQL语句直接写在代码中，存在硬编码问题
  > - 流程复杂，在应对大型程序，或者是复杂的场景的时性能有限
  > - 功能单一，需要用户自己手动去解析结果集

- DBUtils：

  > - SQL语句依然在代码中，存在硬编码问题
  > - 功能不够只能，不能自动选择ResultSetHandler
  > - 不能处理多表查询的场景

## 2. 基础入门

2.1 导包

```xml
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.47</version>
</dependency>
```



2.2 配置

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value=""/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <!-- 将sql配置文件引入 -->
        <mapper resource="org/mybatis/example/BlogMapper.xml"/>
    </mappers>
</configuration>
```



2.3示例代码：

```java
class MybatisMain{
    public static viod main(String[] args){
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 读取配置文件
        InputStream inputStream = MybatisMain.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        // 每一个基于mybatis的应用都是一个以SqlSessionFactory的实例为核心的
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        // 获取SqlSession对象
        // 默认创建的SQLSesion不会自动提交事务，需要手动提交
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 发送SQL，获取结果，封装结果到user对象中
        User object = sqlSession.selectionOne("space.aaa",20);
        
        // 关闭
        sqlSession.close();
    }
}
```

- sql语句配置（Mapper配置文件）

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
  <!-- namespace：命名空间 -->
  <!-- namespace.id = SQL语句的坐标 -->
  <mapper namespace="space">
      
      <!-- id:在当前xml文件中唯一 -->
      <!-- resultType：写返回类型的全限定名称 -->
      <select id="aaa" resultType="com.cs.bean.User">
          select * from user where id = #{id}
      </select>
      
  </mapper>
  ```

  

## 3. 动态代理

3.1 **使用要求**：

- 需要定义一个接口（如：UserMapper）

- <span style="background:yellow;color:red;font-weight:bold">需要创建一个和接口同名的xml文件，并且要求其编译后在同一级目录下</span> 

- xml配置文件mapper的namespace的值必须是接口的全限定名称

  > 接口中定义的方法，其方法名是sql语句对应标签的id，方法的返回值是sql语句对应标签的resultType

3.2 **使用**：

```java
// 获取接口的代理对象(MapperProxy)
UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
// 执行的是代理对象中的方法
User user = userMapper.selectUserById(2);
```

## 4. 配置文件

**4.1 两类配置文件**：

- mybatis-config.xml：核心配置文件
- mapper.xml：SQL语句得配置文件

**4.2 核心配置文件中得的元素**：

- properties（属性）：可以允许我们引入外部的properties文件

  ```xml
  <!-- 引入外部文件 -->
  <properties resource="jdbc.properties"></properties>
      <environments default="development">
          <environment id="development">
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="${driver}"/>
                  <property name="url" value="${url}"/>
                  <property name="username" value="${username}"/>
                  <property name="password" value="${password}"/>
              </dataSource>
          </environment>
      </environments>
  ```

- [settings]((https://mybatis.net.cn/configuration.html#properties))：

  ```xml
  <setings>
  	<!-- 日志设置 -->
      <setting name="logImpl" value="STDOUT_LOGGING"/>
  </setings>
  ```

- typeAliases（类型别名）：降低冗余的全限定类名的书写

  ```xml
  <!-- 别名配置 -->
      <typeAliases>
          <typeAlias type="com.cs.bean.Clazz" alias="Clazz"></typeAlias>
      </typeAliases>
  ```

  > 补充：Mybatis给我们的一些基本类型和包装类型设置了别名

- typeHandler（类型处理器）：将java的数据类型映射为JDBC中的数据类型

- ObjectFactory（对象工厂）：实例化对象

- plugs

- environments（环境配置）：

  > 概述：mybatis支持我们去连接多个数据库
  >
  > 事务管理器（transactionManager）：
  >
  > - type
  >   - JDBC：依赖于使用JDBC和数据库之间创建的连接来管理事务
  >   - MANAGED：此配置几乎没做什么，依赖于外部的容器来管理事务（如：Tomcat）
  >
  > 数据源（dataSource）：
  >
  > - type
  >   - UNPOOLED：不使用数据库连接池，每次请求的时候会新建立连接，执行完SQL语句会关闭连接
  >   - POOLED：使用数据库连接池
  >   - JNDI：可以使用外部配置的数据库连接池，DBCP，C3p0，Druid

- mappers：

  > 概述：映射器的配置，可以配置mapper映射文件的路径
  >
  > 配置的两种方式：
  >
  > - 配置单个文件
  >
  > - 配置包
  >
  >   ```xml
  >   <!-- 表示扫描这个包下的所有的xml文件 -->
  >   <mappers>
  >       
  >       <!-- 需要Mapper接口和Mapper.xml编译之后在同一个目录下 -->
  >   	<package name="com.cs"/>
  >   </mappers>
  >   ```
  >
  >   

## 4. 输入映射

**4.1概述：**Mybatis的传值方式

### **4.2一个简单参数** ：

- 简单参数：基本类型 + 包装类型 + 集合类型 + String

- mapper

  ```java
  User selectUserById(Integer id);
  ```

- mapper.xml

  ```xml
  <select id="selectUserById" resultType="user">
  	select * from user where id = #{id}
  </select>
  ```

- SQL语句中通过<span style="background:yellow;color:red;font-weight:bold">#{任意名称}</span>来取值

### **4.3多个简单参数**：

- mapper

  ```java
  // 多个参数
  int insertUser(@param("id") Integer id,
                @param("username") String name,
                @parm("birthday") Date birthday);
  ```

- mapper.xml

  ```xml
  <insert id="insertUser">
  	insert into user values (#{id}, #{usrname}, #{birthday})
  </insert>
  ```

- SQL语句通过在mapper接口中加入@Param("值")给SQL语句中的<span style="background:yellow;color:red;font-weight:bold">#{Param注解的值}</span>来取值

### **4.4 通过对象传值** 

- **不加注解**：

  - mapper

    ```java
    // 传递对象
    int insertUserWithUser(User user);
    ```

  - mapper.xml

    ```xml
    <insert id="insertUserWithUser">
    	insert into clazz value(#{id},#{name})
    </insert>
    ```

  - **注意**：需通过<span style="background:yellow;color:red;font-weight:bold">#{成员变量名}</span>来取值

- **加注解**：通过<span style="background:yellow;color:red;font-weight:bold">#{对象.成员变量名}</span>来取值

### **4.6 Map传值（不建议使用）** 

- **不加注解**：

  - mapper

    ```java
    // 通过map来传值
    List<User> selectUserByMap(Map<String, Object> map);
    ```

  - mapper.xml

    ```xml
    <select id="selectUserByMap" resultType="User">
    	select * from user where id = #{id} or name = #{name}
    </select>
    ```

- **加入注解**：类似传入对象，通过`#{注解值.key}`来传值

- **总结**：`#{参数}`中的参数必须要求是map中的key保持一致

### **4.7 按位置取值（不建议使用）** 

- mapper

  ```java
  // 按位置传值
  int insertUserUseIndex(Integer id, String name, Date birthday);
  ```

- mapper.xml

  ```xml
  <select id="insertUserUseIndex">
  	insert inot user values(#{arg0}, #{arg1}, #{arg2})
  </select>
  ```

###  4.8 注意事项

1. **#{}** 和 **${}** 的区别：

   > #{}采用Prepared Statement预编译（没有数据库注入问题），${}采用Statement字符串拼接

2. **${}** 的使用场景

   > - 当我们把<span style="background:yellow;color:red;font-weight:bold">表名作为参数传入到SQL语句中</span>的时候,需要使用${}来取值.
   >   
   >   - 原因:当一个SQL语句没有表名的时候,经过PreparedStatement进行预编译,而编译过程由分析器去完成.假如没有表名,那么分析器进行词法分析和语法分析的时候就会报错.
   >   
   >     > **扩展**：什么时候需要传入表名?
   >     >
   >     > - MySQL数据库的单表是有性能极限的,当数据量很大的时候,就需要将数据存入到不同的表中,这个时候就需要分表了.
   >   
   > - 当传入列名的时候也需要用到${} 
   >











##   5. 输出映射

**5.1 概述**：Mybatis可以把查询的结果映射为对象

**5.2 简单类型**：

- 单个简单类型

  - mapper

    ```java
    // 一个简单类型
    int selectUserCount();
    ```

  - mapper.xml

    ```xml
    <select id="selectUserCount" resultType="_integer">
    	select count(id) from user
    </select>
    ```

    

- 多个简单类型

  - mapper

    ```java
    // 返回多个值
    int[] selectUserIdArray();
    ```

  - mapper.xml

    ```xml
    <select id="selectUserIdArray" resultType="int">
    	select id from user
    </select>
    ```

**5.3 返回是对象**：

- 单个对象

  - mapper

    ```java
    // 单个对象
    User selectUserById(@param("id") Integer id);
    ```

  - mapper.xml

    ```xml
    <select id="selectUserById" resultType="User">
    	select *
        from user
        where id = #{id}
    </select>
    ```

- 多个对象

  - mapper

    ```java
    // 对象集合
    List<User> selectUserByMinId(@param{minId} Integer minId);
    ```

  - mapper.xml

    ```xml
    <select id="selectUserByMinId" resultType="User">
    	select * from user where id &gt; #{minId}
    </select>
    ```

- **注意**：<span style="background:yellow;color:red;font-weight:bold">当返回是对象的时候，需要让SQL的查询结果表中的列名和对象中的成员变量名保持一致</span> ,不是原表中的列名

  > 若不一致，可通过as起别名，或者， 可以通过resultMap来解决

**5.4 resultMap**：

- mapper.xml

  ```xml
  <!-- 解决表中列名和成员变量名不一致的问题 -->
  <resultMap id="user2Map" type="User2">
  	<id column="id" property="uid"/>
      <result column="name" property="username"/>
  </resultMap>

  <select id="selectUser2ByIdUserResultMap" resultMap="user2Map">
  	select id,name,birthday from user where id = #{id}
  </select>
  ```
  
  > - id标签映射表中的主键，id标签主要是在复杂映射中可以提高效率
  >
  > - result标签映射普通列
  > - column：列名
  > - property：成员变量名
  > - javaType：成员变量类型（可省略）
  > - jdbcType：数据库类型（可省略）

## 6. 动态SQL

**6.1 概述**：Mybatis提供了很多标签，通过这些标签，我们可以去根据传入的参数动态改变SQL语句

**6.2 where（重要）**：

- 功能：

  - SQL语句中自动拼接where关键字，
  - 拼接的时候可以自动去除与他相邻的and或者or关键字。
  - where标签中，通过判断不存在可以拼接的条件，where将不会被拼接进sql语句

- 代码示例：

  ```xml
  <select id="selectUserById" resultType="User">
  	select * from user
      <where>
      	id = #{id}
      </where>
  </select>
  ```

**6.3 if（重要）**：

- 功能：会根据不同的判断条件，来拼接不同的SQL片段

- 示例代码：

  ```xml
  <select id="methodName" resultType="User">
  	select * from user where
      <!-- 判断条件 -->
      <if test="id gt 10">
          <!-- 条件成立插入值 -->
      	id &gt; 10
      </if>
      <if test="id lte 10">
      	id &lt; = 10
      </if>
  </select>
  ```

  > 补充：if test="OGNL表达式"
  >
  > | 符号 | xml     | ognl |
  > | ---- | ------- | ---- |
  > | >    | `&gt;`  | gt   |
  > | <    | `&lt;`  | lt   |
  > | >=   | `&gt;=` | gte  |
  > | <=   | `&lt;=` | lte  |
  >
  > 

**6.4 choose when otherwise（重要）**：

- 功能：类似if~else

- 示例代码：

  ```xml
  <!-- List<User> selectUserListById(@parm(id) Integer id) -->
  
  <select id="selectUserListById" resultType="User">
  	select * from user where
      <choose>
      	<when test="id gt 20">
          	id &gt; 20
          </when>
          <otherwise>
          	id &lt;= 20
          </otherwise>
      </choose>
  </select>
  ```

  

**6.5 trim（重要）**：

- 功能：修剪，帮助我们动态的去修建SQL语句

- 示例代码：

  ```xml
  <updata id = "methodName">
      update user set
      <trim suffixOverrides="," prefixOverrides="" suffix="" prefix="">
          <if test="usre.name != null">
          	name = #{user.name},
          </if>
          <if test="user.class != null">
          	id = #{user.class},
          </if>
      </trim>
      where id = #{user.id}
  </updata>
  ```

  > 属性：
  >
  > - prefix：增加指定的前缀
  > - suffix：增加指定的后缀
  > - prefixOverrides：去除指定的前缀
  > - suffixOverride：去除指定的后缀

**6.6 set**：

- 功能：专门用来处理update这种场景，去除，添加set

  > set =  `<trim suffixOverrides="," prefixOverrides="" suffix="" prefix="set">` 

**6.7 sql-include**：

- **功能**：从mapper.xml配置文件中提取一些SQL片段，如同在java中提取工具类

- 示例代码：

  ```xml
  
  <updata id = "methodName">
      update user set
      <trim suffixOverrides="," prefixOverrides="" suffix="" prefix="">
         <include refid="updateUser"\>
      </trim>
      where id = #{user.id}
  </updata>
  
  <sql id="updateUser">
  	<if test="usre.name != null">
          	name = #{user.name},
          </if>
          <if test="user.class != null">
          	id = #{user.class},
          </if>
  </sql>
  ```

- 补充：这个标签会影响mapper.xml文件中sql语句的可读性，常常只会去提取列名

**6.8 foreach**：

- 功能：迭代传入的参数

- 属性：

  - collection：传进来参数的名字
  - close：循环结束的时候加一个指定的字符
  - open：给循环开始的时候加一个指定的字符
  - item：指循环中的元素for如同fori中的i
  - separator：循环中的元素以指定的字符间隔
  - index：循环中元素的下标

- 示例代码：

  ```xml
  <!-- 多选择 -->
  <select id="selectUserListByIdUserList" resultType="User">
  	select * from user where id in 
      (<foreach collection="ids" item="id" separator=",">
      	#{id}
      </foreach>)
  </select>
  
  <!-- 批量插入 -->
  <insert id="methodName">
  	insert into user values
      <foreach collection="userList" separator="," item="user">
      	(#{user.id}, #{user.name})
      </foreach>
  </insert>
  ```

**6.9 selectkey**：

- 功能：在目前的SQL之前或者之后执行一条SQL语句

- 属性：

  - order：AFTER/BEFORE 表示在目标SQL语句执行之前或者之后执行
  - resultType：表示执行目标SQl的返回类型
  - keyProperty：表示需要执行的结果映射到指定参数中

- 示例代码：

  - mapper

    ```java
    // 插入一个用户，并且获取自增的id值
    int insertUserReturnId(@param("User") User user);
    ```

  - mapper.xml

    ```xml
    <insert id="insertUserReturnId">
    	insert into user values (null, #{user.name},#{user.birthday})
        
        <selectKey order="AFTER" resultType="int" keyProperty="user.id">
        	select LAST_INSERT_ID()
        </selectKey>
    </insert>
    ```

    

**6.10 useGeneratedKeys**：

- 功能：获取自增主键值

- 示例代码：

  ```xml
  <insert id="insertUserReturnIdUSerGeneratedKeys" userGeneratedKeys="true" keyProperty="user.id">
  	insert into user values (null, #{user.name}, #{user.birthday})
  </insert>
  ```


## 7. 多表查询

### 7.1 一对一

- **分次查询**：

  - 示例代码：

    ```xml
    <sql id="all_column"></sql>
    <!-- 查询的入口 第一次查询 -->
    <select id="methodName" resultMap="userConutMap">
    	select <include refid="all_column"/>
        from user where id = #{id}
    </select>
    x
    <!-- 第一次查询关联的map -->
    <resultMap id="userCountMap" type="User">
    	<id column="id" property="id"/>
        <result column="username" property="username"/>
        
        <association 
                     property="detail"
                     javaType="com.cs.bean.UserDetail"
                     <!-- 关联第二次查询 -->
                     select="selectUserDetailById"
                     column="id"/>
    </resultMap>
    	<!-- 第二次查询 -->
        <select id="selectUserDetailById">
        	select * form user_detail where user_id = #{id}
        </select>
    ```

  - **association**：单个bean的关联

    > property：成员变量的名字
    >
    > javaType：成员变量的类型
    >
    > select：第二次查询的SQL语句的坐标，假如第二次查询的标签和当前resultMap标签在同一文件中，namespace可以省略
    >
    > column：传递给第二次查询的值的列名

- **连接查询**：

  - 主要思想：连表改别名

### 7.2 一对多

- **分次查询**：

  - **示例代码**：

    ```xml
    <!-- 第一次查询 查询出班级的信息 -->
    <select id="selectClazzByName" resultMap="clazzCountMap">
    	select * from clazz where name = #{name}
    </select>
    <!-- 关联 -->
    <resultMap id="clazzCountMap" type="com.cs.bean.Clazz">
    	<id column="id" propert="id"/>
        <result column="name" property="name"/>
        <!-- 关联studentList信息 -->
        <collection property="studentList" 
                    ofType="com.cs.bean.Student"
                    select="com.cs.mapper.StudentMapper.selectStudentByClazzId"
                    column="id"/>
    </resultMap>
        <select id = "selectStudentByClazzId" resultType="Student">
    	select * from clazz where id = #{id}
    </select>
    ```

  - collection：集合的映射

    > collection：集合的映射
    >
    > ofType：集合中单个元素的类型
    >
    > select：第二次查询sql 语句的坐标
    >
    > column：传递值的列名，即上次查表结果中的列名

- **连接查询**：

  - 示例代码：

    ```xml
    <!-- 连接查询 -->
    <select id="methodName">
    	select c.id as cid,c.name as cname,s.*
        from clazz as c left join student as s 
        on c.id = s.id
        where c.name = #{name}
    </select>
    <resultMap id="classCrossMap" type="Clazz">
    	<id column="cid" property="id"/>
        <result column="cname" property="name"/>
        
        <collection property="studentList" ofType="Student">
        	<id column="id" property="id"/>
            <result column="name" property="name"/>
            ~~~
        </collection>
    </resultMap>
    ```

    

### 7.3 多对多

- 思路：多对多即互为一对多，多对多的查询其实就是一对多的查询

## 8. 懒加载和缓存

**8.1 导读**：懒加载和缓存有助于提高mybatis的性能，是一个优化相关的内容

### 8.2 懒加载

1. **概述**：懒加载是指mybatis进行多表查询的分次查询的时候，若此时不需要用到第二次SQL语句查询的结果，那么可以暂时先不执行第二次查询，等程序需要用到第二次查询的结果时，mybatis再进行第二次查询。

2. **配置**：

   - 全局配置

     - 配置所有的分次查询，都开启或者关闭懒加载

       ```xml
       <settings>
       	<setting name="lazyLoadingEnabled" value="true"></setting>
       </settings>
       ```

   - 局部配置

     - 配置单个分次查询的懒加载，不受全局开关的影响，局部配置的优先级高于全局配置。

       ```xml
       <!-- 属性fetchType为lazy，表示懒加载；为eager，表示饥饿加载 -->
       <association fetchType="lazy"/>
       ```

### 8.3 缓存

- **一级缓存**：

  1. **概述**：
     - 每一个SQLSession都有自己的一级缓存空间，它们彼此之间是独立的，互不影响的。
     - Mybatis的一级缓存是默认开启的，也没有提供关闭的操作
  2. **功能**：缓存SQL语句的执行结果
  3. **失效**：
     - 当执行SqlSession.commit() 的时候失效
     - 执行SqlSession.close() 的时候失效

- **二级缓存**：

  1. **概述**：二级缓存是一个Namespace级别的缓存，意味着每一个mapper.xml配置文件都有一个自己的缓存空间

  2. **配置**：

     - 配置二级缓存总开关

       ```xml
       <setting name="cacheEnable" value="true"/>
       ```

     - 在对应的mapper.xml文件中加入标签，表示开启这个配文件的二级缓存区域

     - 涉及二级缓存的对象，需要实现序列化接口（Serializable）

     - 执行的方法之后commit，才能将SQL查询结果放入二级缓存，SqlSession.commit其实是把一级缓存区的内容转移到二级缓存区

### 8.4 总结

- 一级缓存用处不大，使用场景具有局限性；
- 二级缓存对于用户来说是不可控，无法查看二级缓存中的内容；二级缓存中可能存在脏数据，一般不会使用

## 9. 插件介绍	

- lombok：一个第三方项目，可以帮助我们在编译的时候，自动生成我们需要的getter，setter，toString等方法

  > - 导包
  >
  >   ```xml
  >   <dependency>
  >       <groupId>org.projectlombok</groupId>
  >       <artifactId>lombok</artifactId>
  >       <version>1.18.12</version>
  >   </dependency>
  >   ```
  >
  >   
  >
  > - 使用：
  >
  >   ```java
  >   // @Data可以生成一系列方法
  >   @Data
  >   class User{
  >       int id;
  >   }
  >   ```
  >
  > - 优点：可以方便我们在修改了JavaBean的成员变量之后，自动生成对应的方法

- mybatis

  > 在Idea的plugs中安装MyBatisCodeHelperPro