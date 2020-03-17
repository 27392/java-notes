# Java 核心技术 卷I - 第九章

## Java集合框架

![](https://github.com/27392/java-notes/blob/master/src/main/resources/uml/collection.png)

![](https://github.com/27392/java-notes/blob/master/src/main/resources/uml/map.png)

### 主要接口

在上面可以看到两个接口`Collection`和`Map`

#### Collection

`Collection`接口它包含了集合的基本操作和属性

主要有三个分支

   - `List`(**有序集合**)
   - `Set`(**不包含重复元素的集合**)
   - `Queue`(**队列**)

#### Map

而`Map`接口则是映射(映射用来存放键/值对)

#### 抽象类 (Abstract...)

- AbstractCollection
    
    实现了`Collection`接口的部分功能
    
- AbstractList
    
    实现了`List`接口的部分功能
    
- AbstractSequentialList
    
    个人理解是链表的超类,存在这个类是为了和数组集合区分开,因为数组集合支持随机访问`RandomAccess`
    
- AbstractQueue
    
    实现了`Queue`接口的部分功能
    
- AbstractSet
    
    实现了`Set`接口的部分功能
    
- AbstractMap
    
    实现了`Map`接口的部分功能

> 正如之前在第五章所说的抽象类一样,我们可以将一些通用的方法及与放在超类中
>
> `AbstractList`中实现了`List`中部分方法,直接继承`AbstractList`拓展起来更方便
>
> 如果我们创建自己的`List`那么就不用从`List`接口开始并实现其中的全部方法,只需从`AbstractList`继承,然后执行一些创建新类必须的工作

#### Iterable(标识迭代器接口)

迭代器是用来遍历集合的工具,它也是一种设计模式

通过`Iterable`接口可以获取`Iterator`对象通过他来对象集合进行遍历

而`Collection`继承了`Iterator`接口,也就是说所有实现`Collection`的类都可以使用`Iterator`遍历器去遍历

#### 主要实现(归类)

|   集合类型     |描 述|
|    ----       | ----|
|ArrayList      | 一种可以动态增长和缩减的索引序列|
|LinkedList     | 一种可以在任何位置进行高效地插人和删除操作的有序序列|
|ArrayDeque     | 一种用循环数组实现的双端队列    |
|HashSet        | 一种没有重复元素的无序集合     |
|TreeSet        | 一种有序集                     |
|EnumSet        | 一种包含枚举类型值的集           |
|LinkedHashSet  | 一种可以记住元素插人次序的集    |
|PriorityQueue  | 一种允许高效删除最小元素的集合   |
|HashMap        | 一种存储键 / 值关联的数据结构  |
|TreeMap        | 一种键值有序排列的映射表          |
|EnumMap        | 一种键值属于枚举类型的映射表      |
|LinkedHashMap  | 一种可以记住键 / 值项添加次序的映射表|
|WeakHashMap    | 一种其值无用武之地后可以被垃圾回收器回收的映射表|
|IdentityHashMap| 一种用`==`而不是用`equals`比较键值的映射表|

### Collection接口

> **在Java类库中,集合类的根接口是`Collection`接口**

### 迭代器

`Iterator`接口包含四个方法

```java
public interface Iterator<E> {

    // 如果存在可访问的元素,返回true
    boolean hasNext();
    
    // 返回将要访问的下一个对象.如果已经到达了集合的尾部,将抛出一个 NoSuchElementException
    E next();
    
    // 删除上次访问的对象.这个方法必须紧跟在访问一个元素之后执行.如果上次访问之后,集合已经发生了变化,这个方法将抛出一个 IllegalStateException
    default void remove();
    
    // jdk8 遍历
    default void forEachRemaining(Consumer<? super E> action);
}
```

通过反复调用`next`方法可以逐个访问集合中的每个元素.

但是到达了集合的末尾,`next`方法将抛出一个`NoSuchElementException`异常

因此需要在调用`next`之前调用`hasNext`方法.如果迭代器对象还有多个供访问的元素,这个方法就返回`true`

如果想查看集合中的所有元素,就请求一个迭代器,并在`hasNext`返回`true`时反复的调用`next`方法

```java
while (iterator.hasNext()){
    System.out.println(iterator.next());
}
```

用`for each`循环可以更加简练的表示同样的循环操作

```java
for (Integer integer : collection) {
    System.out.println(integer);
}
```

**编译器简单地将`for each`循环翻译为带有迭代器的循环**

**`for each`循环可以与任何实现了`Iterable`接口的对象一起工作,这个接口只包含一个方法**

```java
public interface Iterable<T> {
    Iterator<T> iterator();
}
```

**`Collection`接口扩展了`Iterable`接口.因此对于标准库中的任何集合都可以使用`for each`循环**

> **也就是说`for each`只是一个语法糖,最后还是会翻译成迭代器的写法,同时扩展了`Iterable`接口的类都可以使用`for each`循环**

在JDK8中,可以调用`forEachRemaining`方法来完成遍历

```java
iterator.forEachRemaining(System.out::println);
```

`forEachRemaining`方法内部也是同样的在`next`前调用`hasNext`

```java
default void forEachRemaining(Consumer<? super E> action) {
    Objects.requireNonNull(action);
    while (hasNext())
        action.accept(next());
}
```

注意: **元素被访问的顺序取决于集合的类型**

> 如果对`ArrayList`进行迭代,迭代器将从索引`0`开始,每迭代一次,索引值加`1`
>
> 然而,如果是访问`HashSet`中的元素,每个元素将会按照某种随机的次序出现.
> 
> 虽然可以确定在迭代过程中能够遍历到集合中的所有元素,但却无法预知元素被访问的次序
> 
> 这对计算总和或统计符合某个条件的元素个数这类与顺序无关的操作来说并不是什么问题

`remove`方法则是删除上次调用`next`方法时返回的元素

```java
while (iterator.hasNext()){
    iterator.next();
    iterator.remove();    // 如果在调用remove之前没有调用next则会抛出异常,且不能多次删除
}
```

**`next`方法和`remove`方法的调用具有互相依赖性**

**如果调用`remove`之前没有调用`next`是不合法的,如果这样做将会抛出一个`IllegalStateException`异常**

**如果想要删除相邻的元素,不能多次调用`remove`方法**

> 可以将`next`方法理解为是一个指针,初始化时指向一个不存在的位置,每当调用一次则移动指针到下一个位置
> 
> 书上说,可以将`next`方法与`Inputstream.read`方法看做等效
>
> 从数据流中读取一个字节,就会自动的"消耗掉"这个字节,下一次调用`read`将会消耗并返回输入的下一个字节
>
> 用同样的方法,反复调用`next`方法就可以读取集合中所有的元素

#### ListIterator接口

> `ListIterator`接口继承了`Iterator`接口,提供了专门操作`List`的方法,也就是说`ListIterator`是为`List`所特有的

`ListIterator`接口在`Iterator`接口的基础上增加如下方法

```java
public interface ListIterator<E> extends Iterator<E> {

    // 当反向迭代列表时,还有可供访问的元素,返回true
    boolean hasPrevious();
    
    // 获取之前的元素
    E previous();
    
    // 返回下一次调用 next 方法时将返回的元素索引
    int nextIndex();
    
    // 返回下一次调用 previous 方法时将返回的元素索引
    int previousIndex();
    
    // 用新元素取代 next 或 previous 上次访问的元素.如果在 next 或 previous 上次调用之后列表结构被修改了,将抛出一个 IllegalStateException
    void set(E e);
    
    // 在当前位置前添加一个元素
    void add(E e);
}
```

`ListIterator`接口增加了添加元素,设置元素,反向遍历,获取当前位置等功能

而在`List`接口中提供了两种获取`ListIterator`的方法

```java
public interface List<E> extends Collection<E> {

    // 返回一个列表迭代器,以便用来访问列表中的元素
    ListIterator<E> listIterator();
    
    // 返回一个列表迭代器,以便用来访问列表中的元素,这个元素是第一次调用 next 返回的给定索引的元素
    ListIterator<E> listIterator(int index);
    
    ...
}
```

## 具体的实现

### List

**`List`: 有序集合(也称为序列)**.`List`接口在`Collection`接口的基础上添加了大量的方法

使得可以精确地控制每个元素在列表中的插入位置. 用户可以通过其整数索引(在列表中的位置)访问元素,并搜索列表中的元素

`List`接口下有两种类型的实现

- 基于[数组](https://baike.baidu.com/item/%E6%95%B0%E7%BB%84/3794097?fr=aladdin)的`ArrayList`、`Vector`
    
    `ArrayList`其底层维护一个数组.在操作删除或者添加操作时,具有自动调节数组容量的功能
    
    `Vector`与`ArrayList`同理,它们的区别是
    
    |      | 是否线程安全 | 扩容大小 |
    |  ----   |   ----      |  ----   |
    |ArrayList|  线程不安全  | `1.5`倍 |
    |Vector   |  线程安全    | `2`倍   |
    
    **在Java老版本中,使用`Vector`实现动态数组.不过`ArrayList`更有效,所以一般还是使用`ArrayList`**
    
    > 数组在内存中是顺序存储,因此可以很好地实现逻辑上的顺序表
    >    
    > 既不能打乱元素的储存顺序,也不能跳过某个储存单元进行储存
    
- 基于[链表](https://baike.baidu.com/item/%E9%93%BE%E8%A1%A8/9794473?fr=aladdin)的`linkedList`

    **尽管数组在连续的存储位置上存放对象引用,但是链表却将每个对象放在独立的节点中,每个节点还存放着序列中下一个节点的引用**
    
    **在Java语言中所有的链表实际上都是双向链表**,(即每个节点还存放在指向前驱节点的引用)
    
    **所以`linkedList`也是双向链表**
 
    > 数组在内存中顺序存储,而链表在内存中则是随机存储,链表将每一个节点分布在内存不同的位置,依靠`next`指针关联
    > 
    > 这样可以灵活的利用零散的碎片空间

---

数组VS链表

**数组和链表都属于线性数据结构**

|       | 查询    | 更新  | 插入 | 删除 |
|  ---- |   ---- | ---- | ---- | ----|
| 数组  |  O(1)  | O(1) | O(n) | O(n) |
| 链表  |  O(n)  | O(1) | O(1) | O(1) |

从表格中可以看出,**数组的优势在于能够快速定位元素,对于读操作多、写操作少的场景来说,用数组更合适**

**相反地,链表的优势在于能够灵活的进行插入和删除操作,如果需要在尾部频繁插入、删除元素,用链表更合适**

### Set

**`Set`: 储存无序的、不可重复的数据**.`Set`接口并没有添加新的方法,所有的方法都继承自`Collection`接口

1. **无序性**:
    
    不等于随机性,储存的数据在底层数组中并非按照数组索引添加,而是根据数据的**(散列码)哈希值**决定的
    
    > 其实底层使用的时`HashMap`,`HashMap`底层是数组.关于这部分知识可以查看后面的`HashMap`
    
2. **不可重复性**:

    保证添加的元素按照`equals()`判断时,不能返回`true`,即.相同的元素只能添加一个
    
---

`Set`接口下主要有三个主要的实现类

- `HashSet`

   一种没有重复元素的无序集合

- `LinkedHashSet`

   它是`HashSet`的子类,并且它是有序的**可以记住添加元素时的顺序**
   
   > 它并没有添加额外的方法,只是在构造时,使用`LinkedHashMap`.关于这部分知识可以查看后面的`LinkedHashMap`
    
- `TreeSet`

   它与散列集十分类似,不过,它比散列集有所改进.
   
   **树集是一个有序集合可以以任意顺序将元素插入到集合中.在对集合进行遍历时,每个值将自动地按照排序后的顺序呈现**
   
   **它与散列集不同,它并不是使用`equals()`来判断两个元素是否相同,而是使用[`Comparable`](https://github.com/27392/java-notes/tree/master/src/main/java/cn/haohaoli/book/core/base/chapter6#611-%E6%8E%A5%E5%8F%A3%E6%A6%82%E5%BF%B5)、[`Comparator`](https://github.com/27392/java-notes/tree/master/src/main/java/cn/haohaoli/book/core/base/chapter6#622-comparator%E6%8E%A5%E5%8F%A3)来判断是否相同**
   
   **所以要使用它,必须能够比较元素.这些元素必须实现`Comparable`接口或者构造集时必须提供一个`Comparator`**
   
   > 需要注意一点: **当`Comparable`,`Comparator`同时存在时,使用给定的`Comparator`规则**
    
#### 总结

`HashSet`在使用它之前需要重写`equals`和`hashCode`方法,否则他还是会储存重复的元素,一般情况下还是用它

`LinkedHashSet`它则可以记住添加元素的顺序.如果需要想添加时没有重复的元素而且记住添加时元素的顺序,那么可以使用它

`TreeSet`在使用它之前则需要有一个排序的规则`Comparable`、`Comparator`都可以.它会帮你在添加的时候自动排序

### Queue

### Map

## 视图 

## 算法





