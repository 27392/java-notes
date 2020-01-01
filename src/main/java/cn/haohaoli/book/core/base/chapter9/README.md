# Java 核心技术 卷I - 第九章

## 9.1 Java集合框架

### 9.1.1 将集合的接口与实现分离

### 9.1.2 Collection接口

> **在Java类库中,集合类的根接口是`Collection`接口**

这个接口有两个基本方法

```java
boolean add(E e);
Iterator<E> iterator();
```

当然除了这两个方法外,该接口还有其他方法.具体用法在`CollectionTest`类中

`add`方法用于向集合中添加元素,如果添加元素确实改变了集合就返回`true`,如果集合没有发生变化就返回`false`

例如,如果实体向集合中添加一个对象,而这个对象已经在集合中存在,这个添加请求就没有实效,因为集合中不允许有重复的元素

> 例如后面说的`Set`

而`iterator`方法用于返回一个实现了`Iterator`接口的对象

可以使用这个迭代器对象依次访问集合中的元素

### 9.1.3 迭代器

`Iterator`接口包含四个方法

```java
public interface Iterator<E> {
    boolean hasNext();
    E next();
    default void remove();
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

### 9.1.4 泛型实用方法

### 9.1.5 集合框架中的接口

## 9.2 具体的集合

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

以下是他们的UML类图

![](https://github.com/27392/java-notes/blob/master/src/main/resources/uml/collection.png)

![](https://github.com/27392/java-notes/blob/master/src/main/resources/uml/map.png)


