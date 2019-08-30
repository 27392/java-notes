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

### 6.1.3 - 接口与抽象类

说到抽象类与接口的话就有可能会很多疑问,比如说: 为什么Java要引进接口概念?

为什么不将我们上面介绍的`Comparable`接口设计成抽象类?

```java
//当然这里也可以完全和Comparable接口一样加泛型, 为了方便就最简单的来
public abstract class Comparable{
    
    public int compareTo(Object o);
}
```

但是,如果使用抽象类的话就会存在一个问题: 每个类只能推展一个类.

假如`Employee`类已经拓展了一个类,例如`Person`,它就不能在拓展第二个类了例如: 

```java
class Employee extends Person, Comparable // 错误的!
```

但每个类可以实现多个接口

```java
class Employee extends Person implements Comparable
```

### 6.1.4 静态方法

在JDK8中,允许在接口中增加静态方法,理论上说,没有任何理由认为这是不合法的,只是这样有违于将接口作为抽象规范的初衷

在此之前,通常的做法都是讲静态方法放在伴随类中.

例如在标准库中,你会看到成对出现的接口和使用实用工具类,如`Collection/Collecionts`或`Path/Paths`

下面来看看`Paths`类,其中只包含两个`工厂方法 - 静态工厂方法`

可以由一个字符串序列构造一个文件或目录的路径,如`Paths.get("C","Program Files","jdk1.8.0_192")`

在JDK8中,可以为`Path`接口增加以下方法

```java
public interface Path {
    
    static Path get(String first, String... more) {
        return FileSystems.getDefault().getPath(first, more);
    }
}
```

这样一来,`Paths`类就不再是必要的了

不过整个Java库都以这种方法重构也是不太可能的,但是实现你自己的接口时,不在需要为实用工具方法另外提供一个伴随类

**接口不会继承`static`修饰的方法,像下面这样定义并不会有什么问题**

```java
public interface Paths extends Path {
    
    static Path get(String first, String... more) {
        return FileSystems.getDefault().getPath(first, more);
    }
}
```

### 6.1.5 默认方法

默认方法可以为接口提供一个默认实现. 但是必须用`default`修饰表示这样一个方法

```java
public interface Comparable<T>{
    
    default int compareTo(T o) {
        return 0;
    }
}
```

当然,这并没有太大的用处,因为`Comparable`的每一个实现类都要覆盖这个方法

不过有些情况下默认方法可能很有用.例如,`Collecion`接口中可以定义一个便利方法:

```java
public interface Collection{
    
    int size();
    
    default boolean isEmpty(){
        return size() == 0;
    }
}
```

这样实现`Collecion`接口后就不用操作实现`isEmpty`方法了

> 在JavaAPI中,会看到很多接口都有相应的伴随类,这个伴随类中实现了相应接口的部分或所有方法
>
> 如 Collection/AbstractCollection 或 MouseListener/MouseAdapter
>
> 在JDK8中,这个技术已经过时.现在可以直接在接口中实现方法

#### 接口的演化

默认方法的一个重要用法是"接口演化"

以Collection接口为例,这个接口作为Java的一部分已经有很多年了,假设很久以前你写了这样一个类

```java
public class Bag implements Collection
```

后来在JDK8中,又为这个接口增加了一个`stream`方法

假设`stream`方法不是一个默认方法. 那么`Bag`类将不能编译,因为它没有实现这个新方法

为接口增加一个非默认方法不能保证**源代码兼容**. 不过,假设不重新编译这个类,而只是使用原先的一个包含这个类的Jar文件. 

这个类仍能正常加载,尽管没有这个新方法. 程序仍然可以正常构造`Bag`实例,不会有意外发生

不过,如果程序在一个`Bag`实例上调用`stream`方法,将会出现一个`AbstractMethodError`异常

将方法实现为一个默认方法就可以解决这两个问题. Bag类又能正常编译

另外如果没有重新编译而直接加载这个类,并在一个Bag实例上调用`stream`方法,将调用`Collection.stream`方法

**在JDK8后`com.java.util.function`包中大量类使用了静态方法包括默认方法**

### 6.1.6 解决默认方法冲突

如果先在一个接口中将一个方法定义为默认方法,然后又在超类或另一个接口中定义了同样的方法,会发生什么情况?

规则如下:

   1. **`超类优先`.如果超类提供了一个具体方法,同名而且有相同参数类型的默认方法就会被忽略**
   
   2. **`接口冲突`.如果一个超接口提供了一个默认方法,另一个接口提供了一个同名而且参数类型相同的方法,必须覆盖这个方法类解决冲突**

下面来看第二个规则

新建两个接口有着相同的默认方法

```java
interface Named{
    default String getName(){
        return "Named";
    }
}

interface Person{
    default String getName(){
        return "Person";
    }
}
```

当同一个类同时实现这两个接口时,类会继承`Person`和`Named`接口提供的两个不一致的`getName`方法

并不是从中选择一个,编译器会报告一个错误,让我们自己来解决这个二义性.

这时,只需要在`Student`类中提供一个`getName`方法,这个方法可以选择两个冲突方法中的一个,如下:

```java
class Student implements Person, Named{
    @Override
    public String getName() {
        // 可以从两个默认接口中选择默认方法,也可以自己重新实现
        return Named.super.getName();
        //return Person.super.getName();
    }
}
```

假设`Named`接口没有为`getName`提供默认的实现

```java
interface Named{
    String getName();
}
interface Person{
    default String getName(){
        return "Person";
    }
}
```

那么`Student`类会从`Person`接口继承默认方法吗? 毕竟在`Person`类中有着默认实现,这样好像说得过去

不过java强调一致性.两个接口如何冲突并不重要.如果至少有一个接口提供了一个实现,编译器就会报告错误,而我们必须解决这个二义性


上面我们只看了两个接口命名冲突.现在来考虑另一种情况

一个类推展了一个超类,同时又实现了一个接口,并从超类和接口继承了相同的方法. 例如:

```java
interface Named{
    String getName();
}

public class Person{
    public String getName(){
        return "Person";
    }
}
```

继承超类,实现接口

```java
class Student extends Person implements Named{
}
```

在这种情况下,只会考虑超类方法,接口的所有默认方法都会被忽略掉

在例子中,`Student`从`Person`继承了`getName`方法,`Named`接口是否为`getName`提供默认实现并不会带来什么区别. 这正是**类优先**规则

>  资料：https://blog.csdn.net/shallowinggg/article/details/78039372

## 6.2 - 接口示例

### 6.2.1 接口回调

> 参考: https://blog.csdn.net/jiayi_yao/article/details/51046526

### 6.2.2 Comparator接口

在6.1.1节,我们已经了解了如何对一个对象数组排序,嵌套是这些对象是实现了`Comparable`接口的类的实例

例如: 可以对一个字符串数组排序,因为`String`类实现了`Comparable<String>`,而且`String.compareTo`方法可以按字典顺序比较字符串

现在假设我们希望按照长度递增的顺序对字符串进行排序,而不是按字典顺序进行排序

这样一来我们肯定不能让`String`类用两种不同的方法实现`compareTo()`方法,更何况`String`类也不应该由我们来修改

要处理这种情况,`Arrays.sort`方法还有第二个版本,有一个数组和一个比较器(Comparator)作为参数,比较器是实现了`Comparator`接口的类的实例

下面是`Comparator`接口:

```java
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```

要按长度比较字符串,那么久可以定义一个实现`Comparator<String>`的类

```java
public class LengthComparator implements Comparable<String> {
    
    public int compare(String o1, String o2){
        return o1.length() - o2.length();
    }
}
```

> 尽管`LengthComparator`对象没有状态,不过还是需要建立这个对象的一个实例. 我们需要这个实例来调用`compare`方法

```java
public class ComparatorTest {

    public static void main(String[] args) {
        String[] s = {"x", "xxxx", "xx"};
        Arrays.sort(s, new LengthComparator());
        System.out.println(Arrays.toString(s));
        // [x, xx, xxxx]
    }
}
```

### 6.2.3 对象克隆

当一个包含对象引用的变量建立副本时会发生什么? 例如:

```java
Employee original = new Employee();

//将original所引用的地址再负值给copy对象
Employee copy = original;
```
    priginal和copy指向同一对象 
   
        original   copy
            ↓       ↓
          new Emplyee()

此时任何一个变量改变都会影响另一个对象,例如:

```java
Employee original = new Employee();
Employee copy = original;
copy.raiseSalary(10);
```

如果希望`copy`是指向一个新的对象,它的初始状态与`original`相同,但是之后它们各自会有自己不同的状态,这种情况就可以使用`clone`方法

```java
Employee original = new Employee();
Employee copy = original.clone();
copy.raiseSalary(10);
```

但是并没有想象中的这么简单,`clone`方法是`Object`的一个`protected`方法,这说明你的代码不能直接调用这个方法

只有`Employee`类可以克隆`Employee`对象这个限制是有原因的,`Object`类对需要克隆的对象一无所知,所以只能逐个拷贝

如果对象中的所有数据域都是数值或其他基本类型,拷贝这些域没有任何问题

但是如果对象内包含子对象的引用,拷贝域就会得到相同子对象的另一个引用,这样一来,原对象和克隆的对象仍然会共享一些信息

为了更直观的说明这个问题使用下面的`Employee`类

```java
class Employee {
    String name;
    double salary;
    Date hireDay;
}
```

接着使用`Object`类的`clone`方法克隆这个样一个`Employee`对象

```java
Employee original = new Employee();
Employee copy = original.clone();
```

        original                   copy
     name salary hireDay       name salary hireDay
       │            │           │            │
       └────┬───────┼───────────┘            │
            │       └───────────┬────────────┘
            ↓                   ↓
         String                Date
    
可以看到,默认的克隆操作是**浅拷贝**,并没有克隆对象中引用的其他对象

浅拷贝会有什么影响吗?

   - 如果原对象和浅克隆对象共享的子对象是不可变的,那么这种共享就是安全的.如果子对象属于一个不可变的类,如`String`,就是这种情况

或者在对象的生命期中,子对象一直包含不变的常量,没有更改器方法会改变它,也没有方法会生成它的引用,这种情况下同样是安全的

不过,通常子对象都是可变的,必须重新定义`clone`方法来建立一个**深拷贝**,同时克隆所有子对象

在上面的例子中,`hireDay`域是一个`Date`这是可变的,所以它也需要克隆, 如果`hireDay`是不可变的`LocalDate`类的一个实例,就无需做处理

对于每个类,需要确定:
    
   1. 默认的`clone`方法是否满足要求
   
   2. 是否可以在可变的子对象上调用`clone`类修补默认的`clone`方法
   
   3. 是否不该使用`clone`

实际上第3个选项是默认选项.如果选择第1项或第2项,类必须:

   1. 实现`Cloneable`接口
   
   2. 查询定义`clone`方法,并指定`public`访问修饰符

> `Object`类中`clone`方法声明为`protected`,所以不能直接调用`clone`
> 
> 只能调用受保护的`clone`方法来克隆它自己的对象.必须重新定义`clone`为`public`才能允许所有方法克隆对象
>
> 但是`Cloneable`接口并没有指定`clone`方法,这个方法是从`Object`类继承的.
>
> 这个接口只是作为一个标记.如果一个对象请求克隆,但没有实现这个接口,就会生成一个受查异常
>
> `Cloneable`接口是Java提供的一组标记接口之一,`Comparable`等接口通常用途是确保一个类实现或一组特定方法
> 
>   - 标记接口不包含任何方法;它唯一的作用就是允许在类型查询中使用`instanceof`,建议不要在自己的程序中使用标记接口
> 
>  即使`clone`的默认(浅拷贝)实现能够满足要求,还是需要实现`Cloneable`接口,将`clone`重新定义为`public`再调用`super.clone()`
>
> ```java
> class Employee implements Cloneable {
>     
>     @Override
>     public Employee clone() {
>         return (Employee) super.clone();
>     }
> }
> ```

### 深拷贝

与`Object.clone`提供的浅拷贝相比,重写父类的`clone`方法并没有为他增加任何功能,这里只是让这个方法是公有的

如果要建立深拷贝,克隆对象中可变的实例域.下面是一个创建深拷贝的`clone`方法例子:

```java
class Employee implements Cloneable {
    
    @Override
    public Employee clone() {
        Employee cloned = (Employee) super.clone();
        cloned.hireDay = (Date)hireDay.clone();
        return cloned;
    }
}
```
 
如果在一个对象上调用`clone`,但这个对象的类并没有实现`Cloneable`接口,`Object`类的`clone`方法就会抛出`CloneNotSupportedException`异常

当然,`Employee`和`Date`类实现了`Cloneable`接口,所以不会抛出这个异常.不过,最好还是将异常声明出去,然后由调用方检查

### 注意

必须当心子类的克隆,例如,一旦为`Employee `类定义了`clone`方法,任何人都可以用它来克隆子类对象

如果子类中都是基本类型那就没有问题,但是如果子类中有需要深拷贝或者不可克隆的域.这时不能保证`clone`正常工作

出于这个原因父类中的`clone`方法声明为`peotected`,不过如果你希望直接调用`clone`,就不能这么做

那么要不要在自己的类中实现`clone`方法呢?
    
   - 如果你的需要需要建立深拷贝,可能需要这个方法,但是应该完全避免使用`clone`

> 所有数组类型都有一个`public`的`clone` 方法,而不是`protected`.可以用这个方法建立一个新数组,包含原数组所有元素的副本

## 6.3 lambda表达式

> 具体参考 java8 in active

## 6.4 内部类

## 代理