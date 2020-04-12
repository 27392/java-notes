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

### 散列表

[散列表](https://zh.wikipedia.org/wiki/%E5%93%88%E5%B8%8C%E8%A1%A8)(`Hash table`,也叫哈希表),**是根据键(`Key`)而直接访问在内存储存位置的数据结构**

也就是说,**它通过计算一个关于键值的函数,将所需查询的数据映射到表中一个位置来访问记录,这加快了查找速度.这个映射函数称做散列函数,存放记录的数组称做散列表**

#### 举例说明

为了查找电话簿中某人的号码,可以创建一个按照人名首字母顺序排列的表(即建立人名`x`到首字母`F(x)`的一个函数关系),在首字母为`W`的表中查找“王”姓的电话号码,显然比直接查找就要快得多

这里使用人名作为关键字,“取首字母”是这个例子中散列函数的函数法则`F()`,存放首字母的表对应散列表.关键字和函数法则理论上可以任意确定

#### 散列函数(哈希函数)

通过上面例子,我们可以把散列函数比喻成一个中转站,它可以帮助我们将`key`转化为数组的下标

而哈希表本质上就是一个数组,通过散列函数,我们可以极其快速的查询到`key`值所对应的信息

#### 哈希冲突

1. 开放寻址法

    开发寻址法就是但我们遇到了哈希冲突,我们就重新探索一个空闲位置,然后插入

2. 链地址法

　　采用数组和链表相结合的办法,将`Hash`地址相同的记录存储在一张线性表中,而每张表的表头的序号即为计算得到的`Hash`地址   

[相关资料](https://zh.wikipedia.org/wiki/%E5%93%88%E5%B8%8C%E8%A1%A8#%E5%A4%84%E7%90%86%E5%86%B2%E7%AA%81)

#### Java中散列表

**在Java中,散列表使用链表数组实现(也就是链地址法)**,而每个列表被称为桶(`bucket`)

**想要查询表中对像的位置,就要先计算它的散列码(`hash code`),然后于桶的总数取余,所得到的结果就是保存这个元素桶的索引**

> `hashCode()`方法是定义在Object中,所以所有的对象都有此方法

##### 举例说明

例如,某个对象的散列码(调用对象的`hashCode`方法的返回值)为`76268`,并且有`128`个桶,那么对象就应该保持在第`108`号桶中(`76268`除以`128`余`108`)

或许会很幸运,在这个桶中没有其他元素,此时将元素直接插入到桶中就可以了

当然,有时候会遇到桶被占满的情况,这也是不可避免的.这种情况叫**散列冲突**

那么这时,就需要用新对象与桶中的所有对象逐个进行对比(调用对象的`equals()`),查看这个对象是否存在

> 重写equals就必须重写hashCode,[具体查看hashCode笔记](https://github.com/27392/java-notes/tree/master/src/main/java/cn/haohaoli/book/core/base/chapter5#523---hashcode)

如果散列码是合理且随机分布,同时桶的数目也够大,需要比较的次数就会很少

##### 扩容

**标准库使用的桶数是`2的幂`,默认值为`16`(为表大小提供任何职都将被自动地转换为`2的下一个幂`)**

如果散列表太满,就需要再散列(`rehashed`).如果要对散列表在散列,就需要创建一个桶数更多的表,并将所有的元素插入到这个新表中,然后丢弃原来的表

**装填因子(load factor)决定合适对散列表进行再散列**

例如.**如果装填因子为`0.75`(默认值),而表中超过`75%`的位置已经填入元素,这个表就会用双碑的桶数自动的进行再散列**

**对大多数应用程序来说,装填因子`0.75`是比较合理的**

##### 注意点

**想要更多地控制散列表的运行性能,就要指定一个初始的桶数**

**桶数是指用于收集有相同散列值的数目.如果要插入到散列表中的元素太多,就会增加冲突的可能性,降低运行性能**

**如果大致知道最终会有多少个元素要插入到散列表中,就可以设置桶数.通常将桶数设置为预计元素个数的`75% ~ 150%`**

**存在散列表中的对象,必须要重写`hashCode`和`equals`方法**

> **在Java8中,桶中元素大于8并且桶大于等于64时,会从链表变成平衡二叉树(红黑树)**

##### 优缺点

散列表的**缺点是无法控制元素出现的次序**,优点是**能高效的查找,时间复杂度接近`O(1)`**

### Set (集)

**`Set`: 储存无序的、不可重复的数据**.`Set`接口并没有添加新的方法,所有的方法都继承自`Collection`接口

1. **无序性**:
   
    不等于随机性,储存的数据在底层数组中并非按照数组索引添加,而是根据数据的**(散列码)哈希值**决定的
    
    > 其实底层使用的时`HashMap`,`HashMap`底层是数组.关于这部分知识可以查看后面的`HashMap`
    
2. **不可重复性**:

    保证添加的元素按照`equals()`判断时,不能返回`true`,即.相同的元素只能添加一个
    
---

`Set`接口下主要有四个主要的实现类

- `HashSet`(常用)

   为快速查找而设计的`Set`**它是实现了散列表的集**.存入`HashSet`的元素必须定义`hashCode()`
   
   > 内部使用`HashMap`

- `LinkedHashSet`

   它是`HashSet`的子类,具有`HashSet`的查询速度,同时内部使用链表维护元素的顺序(插入的顺序).元素也必须定义`hashCode()`
   
   > 它并没有添加额外的方法,只是在构造时,使用`LinkedHashMap`.关于这部分知识可以查看后面的`LinkedHashMap`
   
- `TreeSet`

   它与散列集十分类似,不过,它比散列集有所改进.
   
   **树集是一个有序集合可以以任意顺序将元素插入到集合中.在对集合进行遍历时,每个值将自动地按照排序后的顺序呈现**
   
   **它与散列集不同,它并不是使用`equals()`来判断两个元素是否相同,而是使用[`Comparable`](https://github.com/27392/java-notes/tree/master/src/main/java/cn/haohaoli/book/core/base/chapter6#611-%E6%8E%A5%E5%8F%A3%E6%A6%82%E5%BF%B5)、[`Comparator`](https://github.com/27392/java-notes/tree/master/src/main/java/cn/haohaoli/book/core/base/chapter6#622-comparator%E6%8E%A5%E5%8F%A3)来判断是否相同**
   
   **所以要使用它,必须能够比较元素.这些元素必须实现`Comparable`接口或者构造集时必须提供一个`Comparator`**
   
   > 需要注意一点: **当`Comparable`,`Comparator`同时存在时,使用给定的`Comparator`规则**
   >
   > 同样`TreeSet`内部也同样使用`TreeMap`

- `EnumSet`
    
   **它是一个枚举类型元素集的高效实现**
   
   > 使用的不多,这里不做介绍.需要了解的话[参考](https://www.cnblogs.com/swiftma/p/6044718.html)

#### 总结

`HashSet`在使用它之前需要重写`equals`和`hashCode`方法,否则他还是会储存重复的元素,一般情况下还是用它

`LinkedHashSet`它则可以记住添加元素的顺序.如果需要想添加时没有重复的元素而且记住添加时元素的顺序,那么可以使用它

`TreeSet`在使用它之前则需要有一个排序的规则`Comparable`、`Comparator`都可以.它会帮你在添加的时候自动排序

### Queue (队列)

**队列是一种线性数据结构,队列中的元素只能先入先出(First In First Out,简称`FIFO`)**

队列可以让人们有效地在尾部添加一个元素,在头部删除一个元素

下面是在`Queue`的接口的方法:

```java
public interface Queue<E> extends Collection<E> {
    
    /**
     * 如果可以在不违反容量限制的情况下立即将指定的元素插入到此队列中，
     * 如果成功则返回`true`,如果当前没有可用空间则抛出`IllegalStateException`
     */
    boolean add(E e);

    /**
     * 如果可以在不违反容量限制的情况下立即将指定的元素插入到此队列中
     * 当使用容量受限的队列时,此方法通常比`add`更可取,因为`add`可能通过抛出异常而无法仅插入元素
     * 如果元素被添加到此队列返回`true`否则返回`false`
     */
    boolean offer(E e);
    
    /**
     * 检索并删除此队列的头.这个方法与`poll`的不同之处在于,它只在这个队列为空时抛出一个异常。
     * 如果这个队列是空的,则抛出`NoSuchElementException`
     */
    E remove();
    
    /**
     * 检索并删除此队列的头,如果此队列为空,则或返回null
     */
    E poll();

    /**
     * 检索但不删除此队列的头.此方法与`peek`唯一的区别在于,如果此队列为空,它将抛出一个异常。
     * 如果这个队列是空的,则抛出`NoSuchElementException`
     */
    E element();

     /**
      * 检索此队列的头,但不删除它,如果此队列为空,则或返回null
      */
    E peek();
}
```

> 队列最要在并发容器中使用这里不多做介绍

### Map(映射)

`Map`: **存储键、键值对组合的集合,提供了`key`到`Value`的映射**.`Map`并没有继承`Collection`而是与他一样都是根接口

在`Map`中它保证了`key`与`value`之间的一一对应关系.也就是说一个`key`对应一个`value`,所以它不能存在相同的`key`值,当然`value`值可以相同

- `HashMap` (常用)
    
    **`HashMap`是基于哈希表的实现,也就是说它既有`Map`的键值对特点,也有哈希表的特点**
    
    `HashSet`底层是`HashMap`,所以`HashSet`底层只要使用它就可以完成所需要的功能
    
    > **`HashMap`是线程不安全的.`key`可以为`null`,`value`也可以为`null`**

- `Hashtable`
    
    `Hashtable`是JDK1.0提供的一个类,现在不推荐使用
    
    > **`Hashtable`是线程安全的.`key`不可以为`null`,`value`也不可以为`null`**

- `TreeMap`
    
    同样它与`TreeSet`一样,不过`TreeSet`内部使用的是`TreeMap`.它是对`key`进行排序
    
    **同理要使用它,必须能够比较元素.这些元素必须实现`Comparable`接口或者构造集时必须提供一个`Comparator`**

- `LinkedHashMap`

    **`LinkedHashSet`和`LinkedHashMap`类一样,它们都可以记住插入元素的顺序**
   
    **同样的`LinkedHashMap`也是`HashMap`的子类**
   
    它在`Node`类内部增加两个字段`before, after`来记住上一个元素和下一个元素,所以`LinkedHashSet`内部使用来它达到记住元素顺序的目的
   
- `WeakHashMap`

    **`WeakHashMap`使用`WeakReference`类型来保存键.如果垃圾回收器发现某个特定对象已经没人他人引用了,就将其回收**

    [相关资料](http://www.justdojava.com/2019/10/11/java-collection-8/)

- `EnumMap`
    
    **它是一个键类型为枚举类型的映射,它可以直接且高效地用一个值数组实现**
    
    > 在使用时,需要在构造器中指定键类型
    >
    > 使用的也很少,需要了解的话[参考](https://www.cnblogs.com/swiftma/p/6044672.html)

- `IdentityHashMap`
    
    **它与`HashMap`不同它使用`==`来判断对象是否相同**
    
    [相关资料](http://www.justdojava.com/2019/10/11/java-collection-7/)

## 视图与包装器

**视图是一个轻量级的对象,它实现了`Collection`或者`Map`接口,但从传统意义上来说并不是真正的集合**

**视图根本不包含其自己的数据.它的所有操作都是根据对另一个对象的操作实现**

例如: `Map`类中的`keySet()`方法就是一个这样的实例

看起来给人的感觉就是这个方法创建了一个`Set`,并将`Map`中的所有键都添加进去,然后返回.但是,实际情况并不是如此

取而代之的是,`keySet()`返回一个实现`Set`接口的类型对象.这个类的方法对原`Map`进行操作

<details>
    <summary>具体调用代码</summary>
   
```java
public Set<K> keySet() {
    Set<K> ks = keySet;
    if (ks == null) {
        ks = new KeySet();
        keySet = ks;
    }
    return ks;
}
```

返回`KeySet`对象,这个对象继承了`AbstractSet`接口,同时`iterator()`方法返回一个`KeyIterator`类

```java
final class KeySet extends AbstractSet<K> {
    public final int size()                 { return size; }
    public final void clear()               { HashMap.this.clear(); }
    public final Iterator<K> iterator()     { return new KeyIterator(); }
    public final boolean contains(Object o) { return containsKey(o); }
    public final boolean remove(Object key) {
        return removeNode(hash(key), key, null, false, true) != null;
    }
    public final Spliterator<K> spliterator() {
        return new KeySpliterator<>(HashMap.this, 0, -1, 0, 0);
    }
    public final void forEach(Consumer<? super K> action) {
        Node<K,V>[] tab;
        if (action == null)
            throw new NullPointerException();
        if (size > 0 && (tab = table) != null) {
            int mc = modCount;
            for (int i = 0; i < tab.length; ++i) {
                for (Node<K,V> e = tab[i]; e != null; e = e.next)
                    action.accept(e.key);
            }
            if (modCount != mc)
                throw new ConcurrentModificationException();
        }
    }
}
```

可以看到这里是直接调用拿`Map`中`Node`对象的`key`遍历

```java
final class KeyIterator extends HashIterator
    implements Iterator<K> {
    public final K next() { return nextNode().key; }
}
```

</details>

[StackOverflow上关于视图的问题](https://stackoverflow.com/questions/18902484/what-is-a-view-of-a-collection)

[CSDN相关文章](https://blog.csdn.net/sinat_19968265/article/details/80469185)

### 轻量级集合包装器

`Arrays`类的静态方法`asList`将返回一个包装了普通Java数组的`List`包装器

这个方法可以将数组传递给一个期望得到列表或集合参数的方法

```java
String[]     strArray = new String[]{"a", "b", "c"};
List<String> list     = Arrays.asList(strArray);
```

**返回的对象不是`ArrayList`(虽然它也叫`ArrayList`)**

**它是一个视图对象,带有访问底层数组的`get`和`set`方法**

> 特别注意: **改变数组大小的所有方法(例如,迭代器相关的`add`和`remove`方法)都会抛出一个`UnsupportedOperationException`异常**

另外该方法还可以接受可变长参数

```java
List<String> list     = Arrays.asList("a", "b", "c");
```

<details>
    <summary>asList方法具体代码</summary>
    
```java
public static <T> List<T> asList(T... a) {
    return new ArrayList<>(a);
}
```

这里的`ArrayList`并不是`java.util.ArrayList`.它只是`Arrays`类中的一个内部类

```java
private static class ArrayList<E> extends AbstractList<E>
        implements RandomAccess, java.io.Serializable
    {
        private static final long serialVersionUID = -2764017481108945198L;
        private final E[] a;

        ArrayList(E[] array) {
            a = Objects.requireNonNull(array);
        }

        @Override
        public int size() {
            return a.length;
        }

        @Override
        public Object[] toArray() {
            return a.clone();
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T[] toArray(T[] a) {
            int size = size();
            if (a.length < size)
                return Arrays.copyOf(this.a, size,
                                     (Class<? extends T[]>) a.getClass());
            System.arraycopy(this.a, 0, a, 0, size);
            if (a.length > size)
                a[size] = null;
            return a;
        }

        @Override
        public E get(int index) {
            return a[index];
        }

        @Override
        public E set(int index, E element) {
            E oldValue = a[index];
            a[index] = element;
            return oldValue;
        }

        @Override
        public int indexOf(Object o) {
            E[] a = this.a;
            if (o == null) {
                for (int i = 0; i < a.length; i++)
                    if (a[i] == null)
                        return i;
            } else {
                for (int i = 0; i < a.length; i++)
                    if (o.equals(a[i]))
                        return i;
            }
            return -1;
        }

        @Override
        public boolean contains(Object o) {
            return indexOf(o) != -1;
        }

        @Override
        public Spliterator<E> spliterator() {
            return Spliterators.spliterator(a, Spliterator.ORDERED);
        }

        @Override
        public void forEach(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            for (E e : a) {
                action.accept(e);
            }
        }

        @Override
        public void replaceAll(UnaryOperator<E> operator) {
            Objects.requireNonNull(operator);
            E[] a = this.a;
            for (int i = 0; i < a.length; i++) {
                a[i] = operator.apply(a[i]);
            }
        }

        @Override
        public void sort(Comparator<? super E> c) {
            Arrays.sort(a, c);
        }
    }
```

</details>

---

`Collections.nCopies(n, anObject)`方法将返回一个实现了`List`接口的不可修改的对象,并给人一种包含N个元素的,每个元素都像是一个`anObject`的错觉

例如,创建一个包含100个字符串的`List`,每个元素都设置为`"DEFAULT"`

```java
Collections.nCopies(100, "DEFAULT");
```

> 其实内部只有一个元素,而`size`是`100`,所以存储的代价很小


<details>
    <summary>查看具体代码</summary>
    
```java
 public static <T> List<T> nCopies(int n, T o) {
    if (n < 0)
        throw new IllegalArgumentException("List length = " + n);
    return new CopiesList<>(n, o);
}
```

```java
private static class CopiesList<E>
        extends AbstractList<E>
        implements RandomAccess, Serializable
    {
        private static final long serialVersionUID = 2739099268398711800L;

        final int n;
        final E element;

        CopiesList(int n, E e) {
            assert n >= 0;
            this.n = n;
            element = e;
        }

        public int size() {
            return n;
        }

        public boolean contains(Object obj) {
            return n != 0 && eq(obj, element);
        }

        public int indexOf(Object o) {
            return contains(o) ? 0 : -1;
        }

        public int lastIndexOf(Object o) {
            return contains(o) ? n - 1 : -1;
        }

        public E get(int index) {
            if (index < 0 || index >= n)
                throw new IndexOutOfBoundsException("Index: "+index+
                                                    ", Size: "+n);
            return element;
        }

        public Object[] toArray() {
            final Object[] a = new Object[n];
            if (element != null)
                Arrays.fill(a, 0, n, element);
            return a;
        }

        @SuppressWarnings("unchecked")
        public <T> T[] toArray(T[] a) {
            final int n = this.n;
            if (a.length < n) {
                a = (T[])java.lang.reflect.Array
                    .newInstance(a.getClass().getComponentType(), n);
                if (element != null)
                    Arrays.fill(a, 0, n, element);
            } else {
                Arrays.fill(a, 0, n, element);
                if (a.length > n)
                    a[n] = null;
            }
            return a;
        }

        public List<E> subList(int fromIndex, int toIndex) {
            if (fromIndex < 0)
                throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
            if (toIndex > n)
                throw new IndexOutOfBoundsException("toIndex = " + toIndex);
            if (fromIndex > toIndex)
                throw new IllegalArgumentException("fromIndex(" + fromIndex +
                                                   ") > toIndex(" + toIndex + ")");
            return new CopiesList<>(toIndex - fromIndex, element);
        }

        // 省略 equals hashcode

        // Override default methods in Collection
        @Override
        public Stream<E> stream() {
            return IntStream.range(0, n).mapToObj(i -> element);
        }

        @Override
        public Stream<E> parallelStream() {
            return IntStream.range(0, n).parallel().mapToObj(i -> element);
        }

        @Override
        public Spliterator<E> spliterator() {
            return stream().spliterator();
        }

        private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
            ois.defaultReadObject();
            SharedSecrets.getJavaOISAccess().checkArray(ois, Object[].class, n);
        }
    }
```

</details>

然而在`Collections`类中还有类似的几个方法

`Collection.singleton(anObject)`

> **这个方法返回一个实现了`Set`接口视图对象,并且是一个不可修改的单元素`Set`,这样的花就不需要付出建立数据结构的开销**

同样的还有`singletonList`,`singletonMap`方法都是一样的

<details>
 <summary>singleton方法具体代码</summary>

```java
private static class SingletonSet<E>
        extends AbstractSet<E>
        implements Serializable
    {
        private static final long serialVersionUID = 3193687207550431679L;

        private final E element;

        SingletonSet(E e) {element = e;}

        public Iterator<E> iterator() {
            return singletonIterator(element);
        }

        public int size() {return 1;}

        public boolean contains(Object o) {return eq(o, element);}

        // Override default methods for Collection
        @Override
        public void forEach(Consumer<? super E> action) {
            action.accept(element);
        }
        @Override
        public Spliterator<E> spliterator() {
            return singletonSpliterator(element);
        }
        @Override
        public boolean removeIf(Predicate<? super E> filter) {
            throw new UnsupportedOperationException();
        }
    }
```
</details>

`Collection.emptyMap(anObject)`

> 这个方法返回一个实现了`Map`接口视图对象,它的内部没有元素

同样的还有`emptySet`,`emptyList`方法都是一样的

### 子范围视图

视图不仅仅全部都是操作所有的数据,它还可以操作部分数据

例如,我们想获取第1~第3个元素.就可以使用`subList`方法来获得一个列表的子范围视图

```java
List<String> stringList = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
List<String> subList    = stringList.subList(1, 4);
```

> **第一个索引包含在内,第二个索引则不包含在内.这与`String`类的`substring`操作中的参数情况相同**

**可以将任何操作应用于子范围,并且能够自动地反映整个列表的情况**例如:

```java
List<String> stringList = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
List<String> subList    = stringList.subList(1, 4);
subList.clear();                // 清除视图同时其实删除原对象内的元素
System.out.println(stringList);
```

将子范围视图清空后(`subList.clear()`),相对应的元素自动从`stringList`中删除,并且`subList`为空

<details>
<summary>ArrayList中subList源码</summary>

```java
private class SubList extends AbstractList<E> implements RandomAccess {
    private final AbstractList<E> parent;
    private final int parentOffset;
    private final int offset;
    int size;

    SubList(AbstractList<E> parent,
            int offset, int fromIndex, int toIndex) {
        this.parent = parent;
        this.parentOffset = fromIndex;
        this.offset = offset + fromIndex;
        this.size = toIndex - fromIndex;
        this.modCount = ArrayList.this.modCount;
    }

    public E set(int index, E e) {
        rangeCheck(index);
        checkForComodification();
        E oldValue = ArrayList.this.elementData(offset + index);
        ArrayList.this.elementData[offset + index] = e;
        return oldValue;
    }

    public E get(int index) {
        rangeCheck(index);
        checkForComodification();
        return ArrayList.this.elementData(offset + index);
    }

    public int size() {
        checkForComodification();
        return this.size;
    }

    public void add(int index, E e) {
        rangeCheckForAdd(index);
        checkForComodification();
        parent.add(parentOffset + index, e);
        this.modCount = parent.modCount;
        this.size++;
    }

    public E remove(int index) {
        rangeCheck(index);
        checkForComodification();
        E result = parent.remove(parentOffset + index);
        this.modCount = parent.modCount;
        this.size--;
        return result;
    }

    protected void removeRange(int fromIndex, int toIndex) {
        checkForComodification();
        parent.removeRange(parentOffset + fromIndex,
                           parentOffset + toIndex);
        this.modCount = parent.modCount;
        this.size -= toIndex - fromIndex;
    }

    public boolean addAll(Collection<? extends E> c) {
        return addAll(this.size, c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);
        int cSize = c.size();
        if (cSize==0)
            return false;

        checkForComodification();
        parent.addAll(parentOffset + index, c);
        this.modCount = parent.modCount;
        this.size += cSize;
        return true;
    }

    public Iterator<E> iterator() {
        return listIterator();
    }

    public ListIterator<E> listIterator(final int index) {
        checkForComodification();
        rangeCheckForAdd(index);
        final int offset = this.offset;

        return new ListIterator<E>() {
            int cursor = index;
            int lastRet = -1;
            int expectedModCount = ArrayList.this.modCount;

            public boolean hasNext() {
                return cursor != SubList.this.size;
            }

            @SuppressWarnings("unchecked")
            public E next() {
                checkForComodification();
                int i = cursor;
                if (i >= SubList.this.size)
                    throw new NoSuchElementException();
                Object[] elementData = ArrayList.this.elementData;
                if (offset + i >= elementData.length)
                    throw new ConcurrentModificationException();
                cursor = i + 1;
                return (E) elementData[offset + (lastRet = i)];
            }

            public boolean hasPrevious() {
                return cursor != 0;
            }

            @SuppressWarnings("unchecked")
            public E previous() {
                checkForComodification();
                int i = cursor - 1;
                if (i < 0)
                    throw new NoSuchElementException();
                Object[] elementData = ArrayList.this.elementData;
                if (offset + i >= elementData.length)
                    throw new ConcurrentModificationException();
                cursor = i;
                return (E) elementData[offset + (lastRet = i)];
            }

            @SuppressWarnings("unchecked")
            public void forEachRemaining(Consumer<? super E> consumer) {
                Objects.requireNonNull(consumer);
                final int size = SubList.this.size;
                int i = cursor;
                if (i >= size) {
                    return;
                }
                final Object[] elementData = ArrayList.this.elementData;
                if (offset + i >= elementData.length) {
                    throw new ConcurrentModificationException();
                }
                while (i != size && modCount == expectedModCount) {
                    consumer.accept((E) elementData[offset + (i++)]);
                }
                // update once at end of iteration to reduce heap write traffic
                lastRet = cursor = i;
                checkForComodification();
            }

            public int nextIndex() {
                return cursor;
            }

            public int previousIndex() {
                return cursor - 1;
            }

            public void remove() {
                if (lastRet < 0)
                    throw new IllegalStateException();
                checkForComodification();

                try {
                    SubList.this.remove(lastRet);
                    cursor = lastRet;
                    lastRet = -1;
                    expectedModCount = ArrayList.this.modCount;
                } catch (IndexOutOfBoundsException ex) {
                    throw new ConcurrentModificationException();
                }
            }

            public void set(E e) {
                if (lastRet < 0)
                    throw new IllegalStateException();
                checkForComodification();

                try {
                    ArrayList.this.set(offset + lastRet, e);
                } catch (IndexOutOfBoundsException ex) {
                    throw new ConcurrentModificationException();
                }
            }

            public void add(E e) {
                checkForComodification();

                try {
                    int i = cursor;
                    SubList.this.add(i, e);
                    cursor = i + 1;
                    lastRet = -1;
                    expectedModCount = ArrayList.this.modCount;
                } catch (IndexOutOfBoundsException ex) {
                    throw new ConcurrentModificationException();
                }
            }

            final void checkForComodification() {
                if (expectedModCount != ArrayList.this.modCount)
                    throw new ConcurrentModificationException();
            }
        };
    }

    public List<E> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size);
        return new SubList(this, offset, fromIndex, toIndex);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > this.size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+this.size;
    }

    private void checkForComodification() {
        if (ArrayList.this.modCount != this.modCount)
            throw new ConcurrentModificationException();
    }

    public Spliterator<E> spliterator() {
        checkForComodification();
        return new ArrayListSpliterator<E>(ArrayList.this, offset,
                                           offset + this.size, this.modCount);
    }
}
```

可以看到它是通过偏移来实现,所有的操作还是使用`parent`父列表

</details>

> `subList`方法在`List`接口中,而`Set`、`Map`接口中并没有这个功能
>
> 不过对于有序集和映射,可以使用排序顺序而不是元素位置建立子范围

`SoredSet`接口声明了三个方法

```java
SortedSet<E> subSet(E from, E to)
SortedSet<E> headSet(E to)
SortedSet<E> tailSet(E from)
```
这些方法将返回大于等于`from`且小于`to`的所有元素子集.有序映射也有类似的方法

```java
SortedMap<K, V> subMap(K from, K to)
SortedMap<K, V> headMap(K to)
SortedMap<K, V> tailMap(K from)
```

> 在JDK1.6引入了`NavigableSet`和`NavigableMap`接口赋予子范围操作更多的控制能力.可以指定是否包含边界

<details>
    <summary>NavigableSet、NavigableMap接口详情</summary>
    
`NavigableSet`接口   
  
```java
NavigableSet<E> subSet (E from, boolean fromlnclusive, E to, boolean tolnclusive)
NavigableSet<E> headSet(E to, boolean tolnclusive)
Navigab1eSet<E> tailSet(E from, boolean fromlnclusive)
```

`NavigableMap`接口

```java
NavigableMap<K,V> subMap (K from, boolean fromlnclusive, K to, boolean tolnclusive)
NavigableMap<K,V> headMap(K to, boolean tolnclusive)
Navigab1eMap<K,V> tailMap(K from, boolean fromlnclusive)
```

> `NavigableSet`实现了`SortedSet`接口,`NavigableMap`接口也是一样实现了`SortedMap`接口

</details>

[参考](https://twodam.net/view-in-java-collection-framework)

### 不可修改视图

`Collections`还有几个方法用来产生集合的不可修改视图

这些视图对现有集合增加了一个运行时的检查.如果发现试图对集合进行修改,就抛出异常,同时这个集合将保持未修改的状态

> 主要用途是: 可以查看某个集合内容,但是又能避免这个集合被修改,相当于只读

可以使用过下面8种方法获得不可修改视图

+ unmodifiableCollection
+ unmodifiableSet
+ unmodifiableSortedSet
+ unmodifiableNavigableSet
+ unmodifiableList
+ unmodifiableMap
+ unmodifiableSortedMap
+ unmodifiableNavigableMap

每个方法都定义与一个接口.例如`Collections.unmodifiableList`定义的是`List`接口.可以与`ArrayList`、`LinkedList`或者任何实现了`List`接口的其他类一起协同工作

例如:

```java
List<String> list             = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
List<String> unmodifiableList = Collections.unmodifiableList(list);
// unmodifiableList.add("f");  // 错误,抛出UnsupportedOperationException异常
```

`Collections.unmodifiableList`方法将返回一个实现`List`接口的类对象(视图对象)

可以从`unmodifiableList`这个视图对象中获取元素,但是不能修改.

**因为所有的修改类型的方法以及被重新定义为一个抛出`UnsupportedOperationException`的异常,而不是将调用传递到底层集合(在这里指`list`)**

<details>
<summary>查看UnmodifiableList源码</summary>

```java
static class UnmodifiableList<E> extends UnmodifiableCollection<E>
                                  implements List<E> {
        private static final long serialVersionUID = -283967356065247728L;

        final List<? extends E> list;

        UnmodifiableList(List<? extends E> list) {
            super(list);
            this.list = list;
        }

        public boolean equals(Object o) {return o == this || list.equals(o);}
        public int hashCode()           {return list.hashCode();}

        public E get(int index) {return list.get(index);}
        public E set(int index, E element) {
            throw new UnsupportedOperationException();
        }
        public void add(int index, E element) {
            throw new UnsupportedOperationException();
        }
        public E remove(int index) {
            throw new UnsupportedOperationException();
        }
        public int indexOf(Object o)            {return list.indexOf(o);}
        public int lastIndexOf(Object o)        {return list.lastIndexOf(o);}
        public boolean addAll(int index, Collection<? extends E> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void replaceAll(UnaryOperator<E> operator) {
            throw new UnsupportedOperationException();
        }
        @Override
        public void sort(Comparator<? super E> c) {
            throw new UnsupportedOperationException();
        }

        public ListIterator<E> listIterator()   {return listIterator(0);}

        public ListIterator<E> listIterator(final int index) {
            return new ListIterator<E>() {
                private final ListIterator<? extends E> i
                    = list.listIterator(index);

                public boolean hasNext()     {return i.hasNext();}
                public E next()              {return i.next();}
                public boolean hasPrevious() {return i.hasPrevious();}
                public E previous()          {return i.previous();}
                public int nextIndex()       {return i.nextIndex();}
                public int previousIndex()   {return i.previousIndex();}

                public void remove() {
                    throw new UnsupportedOperationException();
                }
                public void set(E e) {
                    throw new UnsupportedOperationException();
                }
                public void add(E e) {
                    throw new UnsupportedOperationException();
                }

                @Override
                public void forEachRemaining(Consumer<? super E> action) {
                    i.forEachRemaining(action);
                }
            };
        }

        public List<E> subList(int fromIndex, int toIndex) {
            return new UnmodifiableList<>(list.subList(fromIndex, toIndex));
        }

        private Object readResolve() {
            return (list instanceof RandomAccess
                    ? new UnmodifiableRandomAccessList<>(list)
                    : this);
        }
    }
```

</details>

> 不可修改视图并不是集合本身不可修改.仍然可以通过集合原始的引用(在这里值`list`)对集合进行修改.同时,原来引用集合的元素发生变化,那么视图对象的元素也会随之变化

[参考](https://www.jianshu.com/p/462a56f7e349)

### 同步视图

如果由多个线程访问集合,就必须确保集合不会被意外的破坏

例如:如果一个线程试图将元素添加到`HashMap`中,同时另外一个线程正在对`HashMap`进行扩容,那么结果将是灾难性的

我们可以使用视图机制来确保常规集合的线程安全,而不是实现线程安全的集合类

例如,`Collections`类的静态`synchronizedMap`方法可以将任何一个映射表转换成具有同步访问方法的`Map`

```java
Map<String, String> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
```

现在,就可以由多线程访问`map`对象了.像`get`和`put`这类方法都是同步操作的,即在另一个线程调用另一个方法之前,刚才的方法调用必须彻底完成

<details>
<summary>查看SynchronizedMap源码</summary>

```java
private static class SynchronizedMap<K,V>
        implements Map<K,V>, Serializable {
        private static final long serialVersionUID = 1978198479659022715L;

        private final Map<K,V> m;     // Backing Map
        final Object      mutex;        // Object on which to synchronize

        SynchronizedMap(Map<K,V> m) {
            this.m = Objects.requireNonNull(m);
            mutex = this;
        }

        SynchronizedMap(Map<K,V> m, Object mutex) {
            this.m = m;
            this.mutex = mutex;
        }

        public int size() {
            synchronized (mutex) {return m.size();}
        }
        public boolean isEmpty() {
            synchronized (mutex) {return m.isEmpty();}
        }
        public boolean containsKey(Object key) {
            synchronized (mutex) {return m.containsKey(key);}
        }
        public boolean containsValue(Object value) {
            synchronized (mutex) {return m.containsValue(value);}
        }
        public V get(Object key) {
            synchronized (mutex) {return m.get(key);}
        }

        public V put(K key, V value) {
            synchronized (mutex) {return m.put(key, value);}
        }
        public V remove(Object key) {
            synchronized (mutex) {return m.remove(key);}
        }
        public void putAll(Map<? extends K, ? extends V> map) {
            synchronized (mutex) {m.putAll(map);}
        }
        public void clear() {
            synchronized (mutex) {m.clear();}
        }

        private transient Set<K> keySet;
        private transient Set<Map.Entry<K,V>> entrySet;
        private transient Collection<V> values;

        public Set<K> keySet() {
            synchronized (mutex) {
                if (keySet==null)
                    keySet = new SynchronizedSet<>(m.keySet(), mutex);
                return keySet;
            }
        }

        public Set<Map.Entry<K,V>> entrySet() {
            synchronized (mutex) {
                if (entrySet==null)
                    entrySet = new SynchronizedSet<>(m.entrySet(), mutex);
                return entrySet;
            }
        }

        public Collection<V> values() {
            synchronized (mutex) {
                if (values==null)
                    values = new SynchronizedCollection<>(m.values(), mutex);
                return values;
            }
        }

        public boolean equals(Object o) {
            if (this == o)
                return true;
            synchronized (mutex) {return m.equals(o);}
        }
        public int hashCode() {
            synchronized (mutex) {return m.hashCode();}
        }
        public String toString() {
            synchronized (mutex) {return m.toString();}
        }

        // Override default methods in Map
        @Override
        public V getOrDefault(Object k, V defaultValue) {
            synchronized (mutex) {return m.getOrDefault(k, defaultValue);}
        }
        @Override
        public void forEach(BiConsumer<? super K, ? super V> action) {
            synchronized (mutex) {m.forEach(action);}
        }
        @Override
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
            synchronized (mutex) {m.replaceAll(function);}
        }
        @Override
        public V putIfAbsent(K key, V value) {
            synchronized (mutex) {return m.putIfAbsent(key, value);}
        }
        @Override
        public boolean remove(Object key, Object value) {
            synchronized (mutex) {return m.remove(key, value);}
        }
        @Override
        public boolean replace(K key, V oldValue, V newValue) {
            synchronized (mutex) {return m.replace(key, oldValue, newValue);}
        }
        @Override
        public V replace(K key, V value) {
            synchronized (mutex) {return m.replace(key, value);}
        }
        @Override
        public V computeIfAbsent(K key,
                Function<? super K, ? extends V> mappingFunction) {
            synchronized (mutex) {return m.computeIfAbsent(key, mappingFunction);}
        }
        @Override
        public V computeIfPresent(K key,
                BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
            synchronized (mutex) {return m.computeIfPresent(key, remappingFunction);}
        }
        @Override
        public V compute(K key,
                BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
            synchronized (mutex) {return m.compute(key, remappingFunction);}
        }
        @Override
        public V merge(K key, V value,
                BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
            synchronized (mutex) {return m.merge(key, value, remappingFunction);}
        }

        private void writeObject(ObjectOutputStream s) throws IOException {
            synchronized (mutex) {s.defaultWriteObject();}
        }
    }
```

> 底层的方法都加上了`synchronized`.实际方法还是调用底层对象`m`

</details>

同样获得同步视图也是8种方法

+ synchronizedCollection
+ synchronizedSet
+ synchronizedSortedSet
+ synchronizedNavigableSet
+ synchronizedList
+ synchronizedMap
+ synchronizedSortedMap
+ synchronizedNavigableMap

### 检查视图

**"受査"视图用来对泛型类型发生问题时提供调试支持**

```java
LinkedList<String> list = new LinkedList<>();
List rawList = list;
rawList.add(LocalDate.now());
list.getFirst();    // 抛出异常 java.lang.ClassCastException
```

这个错误的`add`,在运行时检测不到.相反,只要调用`get`方法,将结果转化为`String`时,就会抛出`ClassCastException`异常

而受检视图可以探测到这类问题

```java
List<String> stringList = Collections.checkedList(new LinkedList<>(), String.class);
rawList = stringList;
rawList.add(LocalDate.now());   //在添加时即可探测类型问题,而不是等到获取时
```

视图的`add`方法将检测插人的对象是否属于给定的类.如果不属于给定的类,就立即抛出一个 `ClassCastException`.这样做的好处是错误可以在正确的位置得以报高

<details>
<summary>查看解析</summary>

视图对象`CheckedList`再添加时调用`typeCheck`方法来对类型做检查

```java
public void add(int index, E element) {
    list.add(index, typeCheck(element));
}
```

具体类型检查代码如下:

```java
E typeCheck(Object o) {
    if (o != null && !type.isInstance(o))
        throw new ClassCastException(badElementMsg(o));
    return (E) o;
}
```

</details>

有9个方法可以得到受查视图

+ checkedCollection
+ checkedQueue
+ checkedSet
+ checkedSortedSet
+ checkedNavigableSet
+ checkedList
+ checkedMap
+ checkedSortedMap
+ checkedNavigableMap

> 受查视图受限于虚拟机可以运行的运行时检查.例如,对于`ArrayList<Pair<String>`,由于虚拟机有一个单独的"原始"Pair类,所以,无法阻止插入`Pair<Date>`

### 不可变视图、同步视图、检查视图注意点

> `unmodifiableCollection`、`synchronizedCollection`、`checkedCollection`这几个方法返回都是视图,但是它们的`equals`方法不调用底层集合的`equals`的方法
>
> 相反,它继承了`Object`类的`equals`方法,这个方法只是检测两个对象是否是同一个对象(也就是直接对比引用的地址,使用`==`)
> 
> 然而`unmodifiableSet`和`unmodifiableList`却使用底层集合的`equals`方法和`hashCode`

## 算法

泛型集合接口有一个很大的优点,即算法只需要实现一次,例如

### 排序与混排

### 二分查找

要想在数组中查找一个对象,通常要一次访问数组中的每个元素,知道找到匹配的元素为止

如果数组是有序的,就可以直接查看位于数组中间的元素,看一看是否大于要查看的元素

如果是,用同样的方法在数组的前半部分继续查找;否则,同样的方法在数组的后半部分继续查找.这样就可以将查找范围缩减一半

> **要使用二分查找,首先数组必须是有序的(自然顺序,不可使用倒序),否则算法将返回错误的答案**

例如,如果数组有1024个元素

- 二分查找
 
 可以在10次比较后定位所匹配的元素(或者可以确定在数组中不存在这样的元素)

- 线性查找

    如果元素存在,平均需要512次比较;如果元素不存在需要1024次比较才可以确定

`Collections`类的`binarySearch`方法实现了这个算法.(注意: 集合必须是要排好序的)

**要想查找某个元素,必须提供一个实现`List`接口的集合,以及要查找的元素**

**如果集合中的元素没有实现`Comparable`接口,就还要提供一个比较器对象(`Comparator`)**

```java
int binarySearch(List<? extends Comparable<? super T>> list, T key)
int binarySearch(List<? extends T> list, T key, Comparator<? super T> c)
```

如果`binarySearch`方法**返回的数值大于等于0,则表示匹配对象的索引**

**如果返回负值,则表示没有匹配的元素.但是,可以利用返回值计算应该将`key`插入到集合的哪个位置,以保持集合的有序性**

**插人的位置是`-i - 1`,调用`c.add(-i - 1, key)`将元素插入到正确位置**

```java
List<Integer> arrayList = new ArrayList<>(Arrays.asList(4, 5677, 888, 33123, 44,44, 32, 45, 1, 4666));
// 排序,自然顺序
arrayList.sort(Comparator.naturalOrder());
int i = Collections.binarySearch(arrayList, 55);
// 没有找到元素
if(i < 0) {
    // 将元素添加的list中
    arrayList.add(-i - 1, 55);
}
```

#### 注意

**集合必须使用自然排序,使用倒序的话查找数据将是错误的**

**只有采用随机访问(实现了`RandomAccess`接口的类),二分查找才有意义.**

**如果必须利用迭代方式一次次地遍历链表的一般元素来找到中间位置的元素,二分查找就完全失去了优势**

**因此,如果为`binarySearch`算法提供一个链表,那么它将自动的变为线性查找**

### 简单算法


### 批操作

很多操作会"成批"复制或删除元素

```java
// 将从list1中删除list2中出现的所有元素 - 叉集
List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
List<Integer> list2 = new ArrayList<>(Arrays.asList(3, 4, 5));
list1.removeAll(list2);
System.out.println(list1);
```

```java
// 将从list1中保留list2中出现的所有元素 - 交集
list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
list2 = new ArrayList<>(Arrays.asList(3, 4, 5, 6));
list1.retainAll(list2);
System.out.println(list1);
```

### 集合与数组的转换

由于Java平台的大部分内容都是在集合框架创建之前设计的,所以,有时候需要在传统数组和比较现代的集合之间进行转换

如果需要将一个数组转换为集合,`Arrays.asList`包装器可以达到这个目的

```java
HashSet<String> hashSet = new HashSet<>(Arrays.asList("1","2","3"));
```

而从集合得到数组会困难一些,当然可以使用`toArray`方法

```java
HashSet<String> hashSet = new HashSet<>(Arrays.asList("1","2","3"));
Object[]  objects       = hashSet.toArray();                // 结果是一个对象数组,不能改变它的类型
// String[]  objects       = (String[])hashSet.toArray();   // 错误,不能做强制类型转换
```

`toArray`方法返回的数组是一个`Object[]`数组,不能改变它的类型.

实际上,*必须使用`toArray`方法的一个重载方法,提供一个所需类型而且长度为0的数组**,这样一来,返回的数组就会创建为相同的数组类型

```java
HashSet<String> hashSet = new HashSet<>(Arrays.asList("1","2","3"));
String[]  stringArray   = hashSet.toArray(new String[0]);
System.out.println(Arrays.toString(stringArray));
```

**如果愿意,还可以构造一个指定大小的数组**

> 为什么不直接用一个`Class`对象(例如`String.class`)传递到`toArray`方法. 
>
> 原因是这个方法有"双重职责",不仅要填充一个已有的数组(如果它足够长),还要创建一个新数组
>
> 简单点说就是如果传入的数组长度够用(可以传入指定大小的数组)就不用创建,否则就需要创建新的数