# Java 核心技术 卷I - 第四章

## 类和对象
    
   类是构造对象的模板.由类构造(construct)对象的过程称为创建类的实例(instance)
   
   + ##对象的三个主要特性
  
        - 对象的行为(behavior)
            - 可以对对象施加哪些操作,或可以对对象施加哪些方法?
        - 对象的状态(state)
            - 当施加那些方法时,对象如何响应?
        - 对象标识(state)
            - 如何辨别具有相同行为但状态不同的对象?
   
   + ##类之间的关系
   
        - 依赖(uses-a)
            - 一个类的方法操作另一个类的对象,就表明一个类依赖于另一个类
        - 聚合(has-a)
            - 聚合关系意味着类A的对象包含类B的对象
        - 继承(is-a)
            - 如果类A拓展类B,类A不但包含从类B继承的方法,还会拥有一些额外的功能
            
## 对象与对象变量

   要使用对象,就必须首先构造对象,并指定其初始状态
   
   在Java中使用构造器构造新实例.构造器是一种特殊的方法,用来构造并初始化对象
   
   **构造器的名字应该与类名相同**.想要构造一个对象,需要在构造器前面加上`new`操作符, 例如:
   
   ```java
   new Date();
   ```
   
   这个表达式创建了一个新对象,并初始化为当前日期和时间,如果需要的话也可以将这个对象传递给一个方法
   
   通常我们希望构造的对象可以多次使用,因此需要将对象存放在一个变量中:
   
   ```java
   Date birthday = new Date();
   ```
   
   + ##注意
          
       ```java
       Date deadline;
       ```
       定义一个对象变量`deadline`,它可以引用`Date`类型对象.
       
       但是`deadline`不是一个对象,实际上也没有引用对象,此时不能调用`Date`类的任何方法
       
       必须首先初始化变量`deadline`,可以使用新构造的对象初始化这个变量
       
       ```java
       deadline = new Date();
       ```
       也可以让这个变量引用一个已存在的变量
       
       ```java
       deadline = birthday;
       ```
       **此时两个对象变量引用同一个对象**

   + ## 结论
   
        **一个对象变量并没有实际包含一个对象,而仅仅引用一个对象**
       
        **在Java中,任何对象变量都是对存储在另外一个地方的一个对象的引用**
        
        **`new`操作符返回值是一个引用**
        
        例如下列语句:
        
        ```java
        Date deadline = new Date();
        ```
        
        有两个部分.表达式`new Date()`构造了一个Date对象,
        
        并且它的值只是对新创建对象的引用.这个引用储存在变量`deadline`中
        
## 自定义类
  
在Java中最简单的类定义形式为:

```java
class className{
    field1;
    field2;
    
    constructor1;
    constructor2;
    
    method1;
    method2;
}
```