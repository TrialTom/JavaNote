[TOC]

# 一、 Redis简介

## 1. 介绍

Redis是一个NoSQL数据库，<span style="background:yellow;color:red;font-weight:bold">是一个完全开源免费、高性能、遵循BSD协议的、Key-Value数据库,是一个基于网络，可基于内存亦可持久化的日志型的key-value数据库，并且提供多种语言的API</span> 

> 非关系型数据库一般都是对关系型数据库的一个补充，
>
> 因为关系型数据库都是把数据存储到硬盘上，磁盘的读写速度比较慢，不能应付一些要求读写速度很快的场景，所以就有了NoSQL
>
> NoSQL一般都是把数据存储到内存上，所以NoSQL也有时被称为缓存中间件 

# 二. 安装

> 补充：Reids也是一个C/S架构的应用

- 启动服务端：`redis-server.exe redis.windows.conf` 

- 启动客户端：`redis-cli.exe -h localhost -p 6379` 

  > -h：指定ip地址
  >
  > -p：指定端口号

# 三. 核心配置文件(redis.conf)

## 1. 常规配置

```ini
# daemonize :表示Redis是不是以后台的方式运行的
# windeows 不支持此配置，windows只能以前台的方式运行
daemonize yes

# 当客户端闲置多长时间后关闭，单位是秒，0表示不关闭
timeout 0

# 端口号
port 6379

# 绑定的主机地址，其实就是指定那些ip地址可以访问这个Redis服务器
bind 127.0.0.1

# 指定日记级别设置，默认支持4中级别
logfile notice

# 默认数据库的数量
database 16

# 设置连接密码,默认是关闭的，即不设置密码 auth
requirepass 123456
```



## 2. 持久化配置（恢复数据）

> 持久化：Redis默认是把数据保存在内存上，Redis的持久化是指Redis可以把内存中的数据存储到磁盘上的某个文件中，在Redis意外关闭之后，重启时可以恢复数据。

- **RDB**：

  1. **概述**：Redis默认的持久化方法，RDB这种持久化是通过**内存快照**的形式来持久化的。

     > **内存快照**：保存某一时刻内存中所有的数据
     
  2. **配置**：

     ```ini
     # 持久化保存的文件的路径
     dir ./
     
     # 持久化保存的文件的名字
     dbfilename dump.rdb
     
     # 持久化触发策略
     # 如果九百秒之内有一次改变，就触发持久化
     save 900 1
     ```

- **AOF**：

  1. **概述**：append only file，这种持久化机制是通过追加写入的命令到日志文件中来保存数据的，在恢复数据的时候通过执行日志文件中的命令来重建整个数据库

  2. **配置**：

     ```ini
     # 保存文件的位置
     dir
     
     # 保存文件的名字
     appenfilename "appendonly.aof"
     
     # 总开光(默认关)
     # AOF 和 RDB 可以同时开启，当同时开启的时候，Redis启动时会优先从AOF文件中恢复数据
     appendonly no
     
     # AOF的策略
     # appendfsync always ：总是持久化，保存每一条写入的命令
     # appendfsync everysec : 每秒保存一次
     # appendfsync no : 不保存
     ```

  3. **总结**：

     - AOF这种持久化机制在使用everysec这种策略的时候，可能导致数据的丢失

     - 在实际应用中一般采用everysec这种持久化策略

       > - 若配置always，会导致redis在写的效率上和MySQL的效率持平，违背了我们使用Redis的初衷
       > - Redis中存储的数据：要求访问速度很快并且用户不敏感
       > - MySQL中存储的数据：用户敏感但是访问速度的要求不高

     - 若要求数据安全，并且访问速度快，可使用搜素引擎

# 四. 内存淘汰策略

1. **概述**：当Redis的内存不足的时候，Redis会根据配置的内存淘汰策略来淘汰部分的key-value，空闲出内存空间，存储新加入的key-value.
2. **八种淘汰策略**：
   - **volatile-lru**：LRU-least recently used，最近最少使用。从已设置过期时间的数据集中，选择最近最少使用的数据进行淘汰
   - **volatile-lfu**：Redis会从已设置过期时间的数据集中，删除一段时间的内使用次数最少的key
   - **volatile-random**：从已设置过期时间的数据集中，随机进行淘汰
   - **volatile-ttl**：从已设置过期时间的数据集中，挑选出最近将要过期的数据进行淘汰
   - **allkeys-lru**：从所有数据集中，选择最近最少使用的数据进行淘汰
   - **allkeys-lfu**：从所有数据中，选择一段时间内使用次数最少的key进行淘汰
   - **allkeys-random**：从所有数据中，随机选择数据进行淘汰
   - **no-evication**：禁止驱逐，不淘汰，存入数据直接报错

# 五、数据结构

> Redis支持物种数据类型：String，hash，list，set（集合），zset（有序集合）等

## 1. String

**1.1 描述**：其实就是key对应一个value，这个value可以是字符串或者是数字

**1.2 示例代码**：

```ini
# 设置一个键值对
set key value

# 获取值
get key

# 批量设置
mset key1 value1 key2 value2···
# 批量获取
mget key1 key2···

# 给value加1
incur key

# 给value加上指定步长（加100）
incrby key 100

# 减一
decr key

# 减去指定的步长
decrby key decrement

# 设置值，设置过期时间
setex key seconds value

# 设置值，若key存在不会覆盖值
setnx name value
```

## 2. List（队列）

**2.1 描述**：Redis中的类似一个队列，允许用户从队列两端推入或者弹出元素（双端队列）

**2.2 操作**：

```ini
# 从左/右端推入
lpush/rpop listName value1，value2，value3···

# list存在插入，不存在不插入
lpushx listName value

# 从左/右端弹出
lpop/rpop listName

# 查询链表中的元素
lrange key start end

# 查询长度
llen key

# 查询栈顶的元素（0表示最左边的元素）
lindex key beforeindex overIndex 

# 在pivot 后面插入一个元素value
linsert key before/after pivot value

# 删除值，删除前count个值为value的数
lrem listName value count
# 设置指定位置元素的值
lset listName index value
```

## 3. hash

**3.1 描述**：类似于二维表（可存储42.9亿个）

**3.2 示例代码**：

```ini
# 设置一个hash表的一个键值对
hset key field1 value1

# 设置多个键值对
hmset key field1 value1 field2 value2...

# 获取一个键值对的值
hget key field

# 获取多个键值对的值
hmget key field1 field2...

# 获取所有的键值对
hgetall key

# 获取所有的键
hkeys key

# 获取所有的值
hvals key

# 查看hash表中，给定的field是否存在
hexists key fiedld

# 获取hash表的长度(长度等于键值对的数量)
hlen key

# 给指定field的值加上指定的步长
hincrby key field increment

# 设置field的值，不覆盖原来的值
hsetnx key field value
```

## 4. set（无序集合）

**4.1 描述**：和list对应，元素有两个特点：无序，不可重复

**4.2 示例代码**：

```ini
# 向集合中加入元素
sadd setName value1,value2...

# 查看集合中元素
smembers myset

# 判断指定元素是否存在
sismember setName value

# 返回元素个数
scard myset

# 取出count个元素,并删除
spop setName [count]

# 随机取出一个元素的值，不删除
srandmember setName [count]

# 交集
sinter setName1 setName2 
# 保存结果到newSet
sinterstore newSet setName1 setName2
# 并集
sunion setName1 setName2
sunionstore newSet setName1 setName2
# 差集
sdiff setName1 setName2
sdiffstore newSet setName1 setName2

# 移动元素
smove sourceSet destSet value
# 删除元素
srem setName value1 value2
```

## 5. sortedset( 有序集合)

**5.1 描述**：类似hash这种数据结构，有序集合最大的特点就是有序，可以根据分数区间排名取值

5.2 示例代码：

```ini
# 往无序集合中添加元素
zadd setName score1 member1

# 返回有序集合中key的数量
zcard setName

# 获取指定成员的分数
zscore setName member

# 查询指定区间内成员的数量
zscore setName min max

# 给成员加上score指定的分数
zincrby setName score member

# 查询指定(默认升序)
zrandge setName start stop [withscore]

# 查询指定排名区间内的成员，默认是降序
zreverange setName start stop 

# 查询指定分数区间内的成员(默认升序)
zrangebyscore setName min max
zreverangebyscore key max min

# 查询指定成员的排名，升序，从0开始
zrank setName member
# 查询指定成员的排名 降序
zreverange setName member

# 删除相关
zrem setName member1,member2...
# 根据排名区间来删除
zremrangebyrank setName start stop
# 根据分数区间来删除
zremrangebyscore setName start stop
```

# 六、客户端

1. **三种客户端**：
   
   - 命令行（mysql -urrot -p | redis-cil.exe）
   - 图形化界面（Navicat | Annoterh Redis Desktop Manager）
   - Java客户端（JDBC| Jedis）
   
2. **使用Jedis**：

   - 导包

     ```xml
     <dependencies>
         <!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
         <dependency>
             <groupId>redis.clients</groupId>
             <artifactId>jedis</artifactId>
             <version>2.9.0</version>
         </dependency>
     </dependencies>
     ```

   - 示例代码：

     ```java
     public static void main(String[] args){
         Jedis jedis = new Jedis("127.0.0.1", 6379);
         
         // jedis的使用，基本上的API和命令行是保持一致的
         jedis.set("name","value");
         
         jedis.get("name");
         
         jedis.close();
     }
     ```

     

