[TOC]



# 一、Map

## 1. Map的特点

1. Map是Map集合体系的顶级接口

2. Map存储的是Key—Value数据（key-value数据具有自我描述性）

3. Map的一些子实现存储元素有序，另一些子实现存储元素无序（指key的特性）

4. **Map的所有子实现都不允许存储重复元素** （指key的特性）

   > map中不允许存储重复元素，当key重复的时候，新的value会覆盖旧的value，旧的value会作为返回值返回

5. Map的一些子实现允许存储null，另一些子实现不允许存储null（指key的特性）

   > 我们对map的学习重点在key上

## 2. API

1. **增删改查** 
   - `V put(K key, V value)`:添加键值对
   - `void putAll(Map<? extends k, ? extends V> m)`：添加所有
   - `V remove(Object key)`：移除键值对，返回value
   - `boolean containsKey(Object key)`：判断某个key是否存在
   - `boolean containsValue(Object value)`：判断某个value值是否存在
   - `V get(Object key)`：根据key获取value
2. **一般集合类中具有的** 
   - `int hashCode()`：
   - `boolean equals(Object o)`：
   - `int size()`：
   - `void clear()`：
3. **特殊方法** ：为了遍历
   - `Set<K> keySet()`：获得键集
   - `Collection<V> values()`：获得值集
   - `Set<Map.Entry<K, V>> entrySet()`：获得键值对集合

# 二、HashMap（非常重要）

## 1. 底层结构：数组+链表+红黑树

> 扩展：JDK1.8之前，HashMap的底层结构是单纯的：数组+链表

- Key-Value数据存储到HashMap上的流程
  1. 取出Key—Value值，计算的到hash值
  2. hash值和hashmap底层数组长度取模，得到下标
  3. 将key-value存储到此下标位置

## 2. HashMap的特点

1. HashMap是Map接口的一个子实现

2. HashMap表示的数据结构：hash表

3. HashMap的底层结构：数组+链表+红黑树

   1. HashMap默认初始容量：16，默认扩容机制：扩为2倍，默认的加载因子：0.75

   > ```java
   > class HashMap{
   >  // hashmap的底层数组
   >  Node<K,V>[] table;
   >  float loadFactor;// 加载因子，装载因子
   >  float DEFAULT_LOAD_FACTOR = 0.75f;
   >  
   >  public HashMap(){
   >      this.loadFactor = DEFAULT_LOAD_FACTOR;
   >  }
   >  public V put(K key, V value){
   > 	return putVal(hash(key), key, false, true);
   >  }
   >     final V putVal(int hash, K key, V value, boolean onlyIfAbsetn, boolean evict){
   >         Node<K, V>[] tab;
   >         NOde<k, V> p;
   >         int n, i;
   >         
   >         if((tab = table) == null || (n = tab.length) == 0){
   >             // resize(): 就是HashMap扩容方法	
   >             n = (tab = resize()).length;
   >         }
   >     }
   >     final NOde<K, V>[] resize(){
   >         // 关键代码:
   >         // oldTab = null
   >         Node<K,V> oldTable = table;
   >         // oldCap = 0；旧容量
   >         int oldCap = (oldTab == null) ? 0 oldTab.length;
   >         // oldThr = 0; 就阈值
   >         int oldThr = threshold;	
   >         // 新阈值，和新容量
   >         int newCap, newThr = 0;
   >         // 新容量等于16
   >         newCap = DEFAULT_INITIAL_CAPACITY;
   >         // newThr = 0.75 * 16 = 12;
   >         newThr = (int)(DEFAULT_LOAD_FACTOR * FRFAULT_INITIAL_CAPACITY);
   >     }
   > }
   > ```
   >
   > 补充：
   >
   > - 加载因子：
   >   - HashMap的默认加载因子：0.75；用来限定阈值
   >     - 阈值 = 加载因子 * 数组长度
   >   - 如果我们在数组中存储元素数量，超过阈值，引发数组扩容 

4. 存储元素是无序的

5. 不允许存储重复的key

6. 允许存储null作为key

7. 线程不安全


## 3. HashMap的扩容机制

```java
class HashMap{
    Nodep[] table;
    int size;
    
    public V put(K key, V value){
		return putVal(hash(key), key, value, false, true);
    }
    final V putVal(int hash, K key, V value, boolean onlyIfAbsetn, boolean evict){
        // 关键逻辑
        if(++size > threshold){ // 添加的数据使得键值对数量大于阈值
            resize();	// 扩容
            afterNodeInsertion(eviet);
            return null;
        }
    }
   	 final NOde<K, V>[] resize(){
         newThr = oldThr << 2;
     }
}
```



## 4. 给定数组长度

构造HashMap时，传入指定长度参数，会把这个值变成大于或者等于这个值的最小的2的幂值，作为底层数组的长度，这就意味着<span style="background:yellow;color:red">HashMap底层数组的长度会一直保持为2的幂值</span>

## 5. HashMap详细添加的流程

1. Kay—Value数据添加到HashMap中

2. **计算Hash值** 

   > ```java
   > // 如果key值是null，它对应的hash值是0
   > // 如果key不是null，取key的hashCode 异或上 hsahCode向右移动16位
   > (key == null) ? 0 : (h = key.hashCode()) ^ (h >> 16);
   > ```
   >
   > 注：就整个java语言而言，hash值来源于一个对象的地址

3. 用计算来的hash值和数组长度取模

4. 根据上一步计算的下标，判断这个下标位置是否已经存储了内容；若没有存储结点，则直接创建一个结点存储到这个下标的位置。

   > **<span style="background:yellow;color:red">存储到HashMap底层数组中结点的类型</span>**：
   >
   > ```java
   > class Node<K, V> implements Map.Entry<K, V>{
   >     final int hash;
   >     final K key;
   >     V value;
   >     Node<K, V> next;
   > }
   > ```

5. 计算得到的下标可能已经存储了元素；先判断重复，重复则用**新的Value值替换旧的Value** 

   > **<span style="background:yellow;color:red">HashMap对重复值的判断标准</span>**：
   >
   > - Key的hash值是否一样，
   > - hash一样的基础上，Key是否直接相等
   >
   > `p.hash == has && ((k = p.key) == key || (key != null && key.equals(k)))` 
   >
   > **使用某些特殊的类型来充当Key，要根据上面这个条件判断是否需要重写hashCode和equal方法** 

6. 不重复，插入

   > ```java
   > // 主要逻辑
   > for(int binCount = 0; ; ++binCount){
   >     if((e = p.next) == null){
   >         p.next = newNode(hash, key, value, null);
   >         // int TREEIFY_THRESHOLD = 8;
   >         // 链表的长度超过8，转换为红黑树
   >         if(binCount >= TREEIFY_THRESHOLD - 1){
   >             treeifyBin(tab, hash);
   >         }
   >         break;
   >     }
   > }
   > ```
   >
   > **<span style="background:yellow;color:red">HashMap中链表转换为红黑树</span>**：添加多个数据在HashMap中散列到同一个下标位置，这些数据又不满足HashMap对重复的定义会构成链表（拉链法），当这个链表长度过程（效率过低），**即链表长度超过8达到9个数据的时候**，会转化为红黑树。
   >
   > **<span style="background:yellow;color:red">链表长度超过8达到9不一定会转换为红黑树</span>**：当底层数组长度小于64的时候，产生一个长度为9的链表，是选择扩容，而非转化为红黑树。
   >
   > **<span style="background:yellow;color:red">存储的Key—Value数据散列到红黑树上</span>**：会用Key的hash值来比较，确定位置

## 6. 删除操作造成红黑树转换为链表

- 当红黑树的<span style="background:yellow;color:red">**根结点，根结点的左右结点，根结点的左节点的左结点**</span>，这四个结点有一个是不存在的，就要红黑树转化为链表

## 7. 扩容操作造成红黑树转换为链表

- 扩容会把树拆成两部分，低位和高位，这两个位置的任何一个位置，**<span style="background:yellow;color:red">被拆分的数据量小于等于6</span>**，就要由红黑树转换为链表

  > 为什么链表转换为红黑树阈值为8，红黑树转化为链表阈值为6?
  >
  > 答：防止添加和删除可能导致红黑树和链表间的反复转化。

## 8. 如果我们在HashMap中添加了一份数据，建议不要通过Key的引用直接修改Key

- 如果Key是一个自定义类型，又重写了HashCode和equals方法，通过key值的引用直接修改key，有可能导致再也无法操作这个key-value数据。

# 三、HashMap构造方法

- `HashMap()`：构造一个具有默认初始容量16和加载因子为0.75的空的HashMap
- `HashMap(int initialCapacity)`：指定容量
- `HashMap(int intialCapacity, float loadFactor)`：指定容量和加载因子
- `HashMap(Map<? extends K, ? extends V> m)`：

# 补充

- hash算法：MD4，MD5，SHA1，SHA2，SHA3（是以不可逆为目的的）

  > MD5：可以把一个文件经过散列，得到一个128位的二进制数，
  >
  > - 已经被证明不具有强抗碰撞性（王小云）
  >
  > 加密算法：加密伴随着解密
  >
  > hash算法的特性：冲突避免，输入敏感，逆推困难，迭代迅速