[TOC]



# 一、集合类

## 1.  概述

> 1. 什么是数据结构？
>    - 数据结构是相互之间存在一种或多种特定关系的数据元素的集合
> 2. 数据结构在java上的表现
>    - 数据结构本身和java没有什么关系
>    - 只不过Java中有多种集合、数组、等集合性质的多对象存储结构
>    - 为了更加便捷的操作这些集合或者数组数据，我们根据数据结构的组织方式，构建了一些特殊的Java集合类，用于描述一些Java对象的底层数据的组成关系
> 3. 数据结构的种类和Java中的集合类别有那些？
>    - java的集合类：
>      - Collection
>      - Map
> 4. Collection和Map的区别：
>    - Collection存储单个数据
>    - Map存储键值对

1. 为什么需要集合类：
   - 对一组对象进行操作的时候，很可能事先并不知道到底有多少个对象。为了解决这个问题而引出了集合类（解决存储问题，扩容问题，内存空间浪费问题，数据查找问题，数据删除问题等等）
2. 集合类的特点：
   - 只能存储引用类型的数据（存在泛型）
   - 可以自动调整大小
3. 数组和集合类都是容器，有何区别
   - 数组可以存储基本数据类型的数据，集合不可以（从存储的数据来说）
   - 数组的长度是固定的，集合可以自动调整大小（从占用的存储空间来说）
   - 数组的效率高，相对来说集合效率比较低（从执行效率上来说）
   - 数组没有API，集合有丰富的API（从使用上来说）

# 二、 Collection

## 1. 特点

- Collection是Collection集合体系的顶级接口

- Collection定义了一个数据容器

- Collection的一些子实现的存储元素有序，一些子实现的存储元素无序

- 一些子实现允许存储重复元素，一些子实现不允许存储重复元素

- 一些子实现允许存储null，一些子实现不允许存储null

  > 回答顺序
  >
  > 1. 父子关系
  > 2. 数据结构表现
  > 3. 底层结构
  > 4. 有序/重复/存储null
  > 5. 线程安全

## 2. API

1. **添加**：

   - `boolean add(E e)`:添加
   - `boolean add(Collection<? extend E> c)`：添加集合

2. **查找**：

   -  `boolean contains(Object o)`：集合中是否包含此实例
   - `boolean contain(Collection<? extend E> c)`：查找集合是否都存在

3. **删除**：

   - `boolean remove(Object o)`：移除指定元素

   -  `boolean removeAll(Collection<?> c)`：移除指定集合中的所有实例

4. **结合类一般都具有的方法**：

   - `int size()` : 返回元素个数

   - `void clear()`：清空所有元素
   - `boolean equals(Object o)`：比较collection与指定对象是否相等
   - `int haseCode()`：返回哈希值
   - `boolean isEmpty()`：判空

5. **Collection的特殊方法(用来做遍历的)**：

   - `Obejct[] toArray()`：把collection中的所有元素放到数组中

   - `<T> T[] toArray(T[] a)`：

     > **对于泛型方法的toArray**，
     >
     > - 如果提供的数组（参数），足够长（能完整的容纳Collection中所有的元素），那么这个方法返回的数组和我们传入的数组是同一个数组，
     > - 如果提供的数组不够长，返回的数组将是一个新的数组，传入的参数数组仅仅起标识类型的作用
     >
     > **如果数组足够长**，Collection有n个元素，**数组下标0~（n-1）存储的Collection的n个元素**；并且把下标为n的位置置为null，n之后的内容不在处理。
     >
     > **虽然这是个泛型方法**（从泛型语法角度上传任何类型的数组都是可以的），但是实际运行的时候，只有这个数组类型传对，才能正常运行。
     
   - `Iterator<E> iterator()`：返回迭代器:循环遍历

     > **Iterator类型**：（一个接口）
     >
     > - hasNext：判断后面还有没有元素可以遍历
     > - next：向后遍历
     > - remove：删除刚刚遍历过的元素
     >
     > **注意一**：iterator本质
     >
     > - Collection提供了一个ToArray方法，把数**据转存到数组中，然后遍历**，但是效率不高，所以设计了iterator（原地遍历：不复制数据，仅维护标记）
     > - 我们通过Collection的iterator方法，获得Iterator对象，这个对象中存储了一些**指向源Collection数据的标记**。
     >
     > **注意二**：
     >
     > - iterator获得的对象仅是**维护了一个指向源数据的标记**，也就意味着，我们通过iterator删除数据，实际上删除的是源数据
     >
     > **注意三**：
     >
     > - iterator的删除操作，不能在未遍历之前删除，也不能连续的删除
     >
     > **注意三：Iterator的并发修改异常** 
     >
     > - 一些Collection 的子实现是线程不安全的（加锁或导致运行效率降低），在使用Iaterator遍历的时候，就会产生线程安全问题，但是又不希望通过锁来保证线程安全（加锁导致效率降低）；
     > - 所以一些**集合类就维护了一个标记**（标记修改次数），当源集合数据发生修改，标记回增加；在iterator对象中为了保证在遍历过程中源集合类数据没有被修改，会**在每次遍历之前检测自己保存修改次数记录和源集合类是否一样**，如果不一样，就认为源集合数据被别的线程修改，抛出并发修改异常
     > - 但是由于设计问题，导致单线程的情况下，在使用iterator遍历过程中，如果我们**直接调用源集合类修改结果的方法修改集合数据**，也会抛出并发修改异常。
     > - **解决方法**：在使用iterator遍历的过程中，不要使用源集合类修改结构的方法修改源集合数据。

6. **实际上迭代用：foreach增强的for循环** 

   > **注意一**：
   >
   > - 在Java中foreach循环底层会编译成iterator迭代（数组除外），所以不要在循环中调用源集合类的方法，改变源集合数据
   >
   > **注意二**：
   >
   > - 数组也可以使用foreach循环，数组的foreach循环会编译成fori循环。

# 三、List

## 1. List 的特点

1. List是Collection的子接口
2. List在Collection的基础上，定义的容器是线性表（List代表线性表）
3. List的子实现都有序
4. List的子实现都可以存储重复元素
5. List的子实现都可以存储null

## 2. API

1. **添加**：
   
   - `boolean addAll(int index, Collection<? extend E> c)`:插入到指定位置
2. **查找**：
   
   - `E get(int index)`：返回列表中指定位置的元素
3. **删除**：
   - `remove(int index)`：移除列表中指定位置的元素
   - `int indexOf(Object o)`：根据内容找下标
4. **扩展**：
   - `ListIterator<E> listIterator(int index)`：从指定位置开始迭代

   - `List<E> subList(int fromIdex, int toIndex)`：切割方法，返回视图（左闭右开）

     > **视图**：通过数据源产生一个视图，这个试图并没有真正复制源数据的数据，而是维护一些标记，这些标记指向源数据
     >
     > **注意一**：视图使用之前，我们不希望直接调用源数据的修改结构的方法修改源数据，否则会报并发修改异常

# 四、ArrayList

## 1. 特点

1. ArrayList 是List接口的子实现
2. 表示的数据结构是线性表
3. 底层结构是一个数组
4. 默认的初始容量10，默认的扩容机制（1.5倍原大小）
5. 存储的元素有序，允许存储有序元素，允许存储null，线程不安全。

## 2. 构造方法

- `ArrayList()`:构造一个初始容量为10的空列表
- `ArrayLIst(Collection <? extend E> c)`：构造一个包含指定collection的元素列表
- `ArrayList(int initialCapacity)`：指定初始容量的列表

## 3. API

1. **常规**：

2. **一般集合类具有的方法**：

   - `void clear()`：移除此列表中所有元素
   - `boolean isEmpty`:
   - `int size()`：返回此列表中元素的个数

3. **独特**：

   - `void ensureCapacity(int minCapacity)`:

     > 增加容量，至少将容量扩大为minCapacity这么大

   - `void trimToSize()`：

     > 削减容量，将列表实例的容量调整为列表的当前大小

   - `Object clone()`：浅表副本

     > 浅表副本：即将原来存储的数组再次复制一遍，浅表的改变不会影响原数据。

## 4. ArrayList源码分析Iterator

```java
class ArrayList{
    Object[] elementData;
    int size;
    int modCount = 0; // 标记底层的结构性修改
    
    public Iterator<E> iterator{
        return new Itr();
    }
    
    private class Itr implements Iterator<E> {
        // 标记下一次遍历的元素
        int cursor;
        // 标记刚刚遍历的元素
        int lastRet = -1;
        int expectedModCount = modCount;
        
        public boolean hasNext(){
            return cursor != size;
        }
        
        public void remove(){
            if(lastRet < 0){
                throw new IlleglStateException();
            }
             checkForComodification();
            try{
                // 删除，只能进行一次删除
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            }catch (IndexOutOfBoundsException ex){
                throw new ConcurretModificationException();
            }
        }
    }
}
```



## 5. ArrayList的subList方法

```java
class ArrayList{
    Object[] elementData;
    int size;
    int modCound;
    
    public List<E> subList(int fromIndex, int toIndex){
        subListRangeCheck(formIndex, toIndex, size);
        
        return new SubList(this, 0, fromIndex, toIndex);
    }
    
    private class SubList extends AbstractList<E> implements RandomAccess{
        // 当前源数据的ArrayList
        private final AbstractList<E> parent;
        // 拥有源数据的起始标记
        private final int parentOffset;
        // 
        private final int offset;
        // 从其实标记开始，有几个数据
        int size;
        
        SubList(AbstractList<E> parent, int offset, int formIndex, int toIndex){
            this.parent = parent;
            this.parentOffset = formIndex;
            this.offset = offset + fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = arrayList.this.modCount;
        }
        
        public E remove(int index){
            // 检查合法性
            rangeCheck(index);
            // 检查并发修改异常
            checkForComodification();
            
            E result = parent.remove(parentOffset + index);
            // 同步修改次数
            this.modCount = parent.modCount;
            this.size--;
            return result;
        }
    }
}
```



# 五、Vector（向量）

## 1. 特点

1. Vector是LIst接口的子实现

2. Vector数据结构是线性表

3. Vector底层结构是数据

4. Vector默认初始容量10，扩容机制：

   > 扩容机制：如果存在增量（增量大于0）优先扩大增量个，如果增量小于等于0，默认扩为原来的2倍

5. Vector存储元素有序，允许存储null，允许重复

6. Vector是线程安全的

   > Vector是jdk1.0出现的（ArrayList以及Collection下大多数子实现是1.2出现的）

# 六、Stack

## 1. 特点

1. Stack作为Vector的子类，是为了复用Vector底层结构/变量/方法

2. Stack表示一个栈

   > Stack是Vedctor子类，必然可以复用add/addAll/remove...，但是建议使用push/pop/peek）

3. Deque(dai,ke)接口及其实现提供了LIFO堆栈操作的更完整和更一致的set，应该优先使用此set，而非Stack。

4. 有序，允许重复，允许null

5. 线程安全

   > jdk1.0是出现

# 七、LinkedList

## 1. 特点

1. LinedList是List接口的子实现，也是Deque接口的子实现
2. LinkList数据结构：线性表，队列，双端队列，栈
3. LinkedList底层结构是一个双向链表
4. LinkedList存储元素有序，允许存储重复元素，允许存储null
5. LinkedList线程不安全

## 2. LinkedList的构造方法

- `LinkedList()`：构造一个空列表
- `LinkedList(Collection<? extends E> c)`：构造的空列表包含指定collection两种的元素列表

## 3. API

1. **作为线性表的增删改查**：

   - `boolean add(E e)` 
   - `boolean contain(Object o)` 
   - `E get(int index)` 
   - `E remove(Object o)` 
   - `E get(int index)` 
   - `E set(int index, E element)` 

2. **作为队列的方法**：

   - `boolean offer(E e)`：入队
   - `E poll()`:出队
   - `E peek()`：查看队头

3. **作为双端队列的方法**：
   - `boolean offerFirst(E e)` ：在此列表开头插入指定元素
   - `E peekFirst()`:获取第一个元素
   - `E pollFirst()`:移除并删除第一个元素
   
   - `E element()`：查看队头元素
   
   - `boolean removeFirstOccurrence(Object o)`:移除第一次出现的指定元素
   
4. **作为栈的方法**：

5. **特殊**：

# 补充：

## 1. 可选操作

当接口的子实现不适合实现接口的某个方法，可在子实现的此方法中抛出UnsupportedOperationException()异常（不支持的操作）。

