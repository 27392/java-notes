# Java 核心技术 卷I - 第六章

## 6.1 - 接口

接口(`interface`) 这种技术主要用来描述类具有什么功能,而并不给出每个功能的具体实现
    
**一个类可以实现一个或多个接口**,并在需要接口的地方,随时使用实现了对应接口的对象

### 6.1.1 接口概念
    
在Java程序设计语言中,接口不是类,而是对类的一组需求描述,这些类要遵从接口描述的统一格式进行定义

我们经常听到服务提供商这样说,"如果类遵从某个特定接口,那么就履行这项服务"

比如: `Arrays`类的`sort`方法可以对对象数组对对象进行排序,但要求满足下列前提,对象所属的类必须实现了`Comparable`接口

`Comparable`接口具体代码:

```java
public interface Comparable {
    int compareTo(Object o);
}
```

这就是说,任何实现了`Comparable`接口的类都需要包含`compareTo`方法,并且这个方法的参数是一个`Object`对象,返回一个整型数值

> 在JDK5中,`Comparable`接口已经改进为泛型类型
>
> ```java
>  public interface Comparable<T> {
>      int compareTo(T o);
>  }
>  ```
> 
> 例如,在实现`Comparable<Employee>`接口的类中,必须提供下列方法 
>
> ```java
> int compareTo(Employee o)
> ```
> 
> 还可以使用不带类型参数的"原始"`Comparable`类型.
>
> 这样一来,`compareTo`方法就有一个`Object`类型的参数,必须将`compareTo`方法的这个参数强制转换为所希望的类型
> 
> 在调用`x.compareTo(y)`的时候这个`compareTo`方法必须确实比较两个对象的内容,并返回比较的结果.
> 
> **当x小于y时,返回一个负数,当x等于y时,返回0; 否则返回一个正数**

**接口中的所有方法自动的属于`public`**,因此,在接口中声明方法时,不必提供关键字`public`

**接口没有实例,所以提供实例域和方法实现的任务应该由实现接口的那个类来完成.因此可以将接口看成是没有实例域的抽象类**

但是这两个概念还是有一定区别的

假设现在希望使用`Arrays`类的`sort`方法对`Employee`对象数组进行排序,`Employee`类就必须实现`Comparable`接口

为了让类实现一个接口,通常需要下面两个步骤: 
    
   1. 将类声明为实现给定的接口
    
   2. 对接口中的所有方法进行定义

而将类声明为某个接口,需要使用关键字`implements`

```java
class Employee implements Comparator
```

当然,这里的Employee类需要提供`compareTo`方法.假设希望根据雇员的薪水进行比较.

```java
public int compareTo(Object o){
    Employee other = (Employee) o;
    return Double.compare(salary, other.salary);
}
```
> 静态`Double.compare`方法
> 
> ```java
> Double.compare(x, y)
> ```
> 
> 如果第一个参数小于第二个参数,它会返回一个负值;如果二者相等则返回0;否则返回一个正值
>  
> `Double`类中的`compareTo`方法实际也是调用该方法
> 
> ```java
> //Double 类
> public int compareTo(Double anotherDouble) {
>   return Double.compare(value, anotherDouble.value);
> }
> ```

现在,我们已经看到,要让一个类使用排序服务就必须让它实现`Comparable`类中`compateTo`方法

这是理所当然的,因为要向`sort`方法提供对象的比较方法.

但是为什么不能再`Employee`类直接提供一个`compareTo`方法,而必须实现`Comparable`接口呢?

主要原因是在于Java是一种强类型语言,在调用方法的时候,编译器将会检查这个方法是否存在

在`sort`方法中可能存在下面这样的语句:

```java
//假设存在这样的语句
if(a[i].compareTo(a[j]) > 0){
    return a[i];
}
```

为此,编译器必须确认`a[i]`一定有`compareTo`方法.

如果`a`是一个`Comparable`对象的数组,就可以确保拥有`compareTo`方法,因为每个实现`Comparable`接口的类都必须实现这个方法的定义

> 有人认为,将`Arrays`类中的`sort`方法定义为接收一个`Comparable[]`数组
> 
> 然后使用元素类型没有实现`Comparable`接口的数值作为参数调用`sort`方法时,由编译器给出错误报告
> 
> 但事实并非如此,在这种情况下,`sort`方法可以接口一个`Object[]`数组,并对其进行笨拙的类型转换
>
> 如果数组中元素不属于`Comparable`接口的类,那么虚拟机就会抛出一个异常

**对于任意的`x`和`y`,实现必须能够保证`x.compareTo(y) == -(y.compareTo(x))`**

**也就是说,如果`y.compareTo(x)`抛出一个异常,那么`x.compare(y)`也应该抛出一个异常**

#### 注意: 
    
在接口声明中,没有将`compare`方法声明为`public`,这是因为在接口中的所有方法都是自动是`public`

不过,在实现接口时,必须把方法声明为`public`

### 6.1.2 - 接口的特性

**接口不是类,尤其不能使用`new`运算符实例化一个接口**

```java
Comparable x = new Comparable();   //错误的!
```

然后,尽管不能构造接口对象,却能声明接口变量:

```java
Comparable x;
```

**接口变量必须引用实现了接口的类对象**

```java
class Employee extends Comparable<Employee>
Comparable x = new Employee();
```

如同使用`instanceof`检查一个对象是否属于某个特定类一样,也可以使用`instanceof`检查一个对象是否实现了某个特定的接口:

```java
if(x instanceof Comparable) {
}
```

**与可以建立类的继承关系一样,接口也可以被拓展**

这里允许存在多条从具有较高通用性的接口道较高专用性的接口的链. 例如,假设有一个称为`Moveable`的接口

```java
public interface Moveable{
    void move (double x, double y);
}
```

然后,可以以它为基础推展一个叫`Powered`的接口:

```java
public interface Powered extends Moveable{
    double milesPerGallon();
} 
```

虽然在接口中不包含实例域或静态方法,但却可以包含常量. 例如:

```java
public interface Powered extends Moveable{
    double milesPerGallon();
    
    double SPEED_LIMIT = 95;
} 
```

**与接口中的方法都自动的设置为`public`一样,接口中的域将被自己动的设为`public static final`**

#### 注意

有些接口中定义了常量,而没有定义方法.例如,

在JDK标准库中有一个`SwingConstants`就是这样的一个接口,其中只包含`NORTH`、`SOUTH`和`HORIZONTAL`等常量

任何实现`SwingConstants`接口的类都自动的继承了这些常量,并可以在方法中直接的引用`NORTH`,而不必采用`SwingConstants.NORTH`这样的形式

然而,这样应用接口似乎有点偏离了接口概念的初衷,最好不要这样使用它


## 6.2 - 接口示例
