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

## 9.2 具体的集合

![](https://github.com/27392/java-notes/blob/master/src/main/resources/uml/map.png)

![](https://github.com/27392/java-notes/blob/master/src/main/resources/uml/collection.png)















## 迭代器 


