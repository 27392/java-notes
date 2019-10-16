# Java 核心技术 卷I - 第八章

## 8.1 泛型程序设计

**泛型程序设计意味着编写的代码可以被很多不同类型的对象所重用**

## 8.2 - 泛型类

**泛型类就是具有一个或多个`类型变量`的类**

```java
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pair<T> {
    private T first;
    private T second;
}
```

`Pair`类引入了一个类型变量`T`,用尖括号`<>`括起来,并放在类名的后面

**泛型类可不是只可以定义一个类型变量,也可以有多个类型变量,用逗号隔开**.例如:

```java
public class Pair<T, U> {
}

public class Pair<T, U, S> {
}
```

**类定义中的类型变量(也就是`T`)可以指定方法的返回类型以及域和方法参数的类型**

> 类型变量使用大写形式,且比较短

用具体的类型替换类型变量就可以实例化泛型类型,例如:

```java
// Pair<String> pair = new Pair<String>();

// jdk7 以后可以省略构造函数中的泛型类型
Pair<String> pair = new Pair<>();
```

实例化`Pair`类并将类型参数设置为`String`后,可以理解为`Pair`类中所有的类型变量(也就是`T`),将会被替换成`String`.

换句话说,泛型类可看作普通类的工厂

```java
// 构造方法
Pair<String>();
Pair<String>(String first, String second);
// 方法
String getFirst();
String getSecond();
void setFirst(String first);
void setSecond(String second);
```

当然我们也可以不使用泛型构造对象

```java
Pair pair = new Pair();
```

这样默认参数类型就是`Object`类型

## 8.3 - 泛型方法

**泛型不但可以作用在类上还可以作用在方法上**

```java
public class ArrayAlg {

    public static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }
}
```

这个方法时在普通类中定义,而不是在泛型类中定义.

> 注意.**类型变量放在修饰符(也就是`public static`)的后面,返回类型的前面**

**泛型方法可以定义在普通类中,也可以定义在泛型类中.**

**当调用一个泛型方法时,在方法名前的尖括号中放入具体的类型:**

```java
String middle = ArrayAlg.<String>getMiddle("John", "Q.", "Public");
```

在这种情况(实际也是大多数情况)下,方法调用中可以省略`<String>`类型参数.编译器有足够的信息能够推断出泛型参数类型.

在该例子中我们的参数皆为`String`即`String[]`于泛型类`T[]`进行匹配并推断出`T`一定是`String`

> 可变参可以把它想象成一个数组`T...`其实和`T[]`是一样的,具体参考第五章-参数数量可变的方法

```java
String middle = ArrayAlg.getMiddle("John", "Q.", "Public");
```

### 注意

但是少数情况下,编译器也会提示错误!如下实例:

```java
double middle = ArrayAlg.getMiddle(3.14, 1729, 0);
```

编译器的错误信息会议晦涩的方法指出.反正我是看不懂~.

简单的说就是编译器将会自动打包参数为(1个Double)和(2个Integer)对象,然后寻找这些类的共同超类

事实上可以找到两个这样的超类,`Number`和`Comparable`,但是我们返回值是`double`对不上所以出错了!

在这种情况下可以将参数都改成`double`类型

```java
double middle = ArrayAlg.getMiddle(3.14, 1729.0, 0.0);
```

> **当出现不同类型时,编译器将会寻找这些类的共同超类**

## 8.4 - 类型变量的限定

有时,类或者方法需要对类型变量加以约束.例如:我们需要计算数组中的最小元素

```java
public static <T> T min (T[] ts) {
    if (null == ts || ts.length == 0) {
        return null;
    }
    T smallest = ts[0];
    for (T t : ts) {
        // 错误! 不能保证smallest类有compareTo方法
        if (smallest.compareTo(t) > 0) {
            smallest = t;
        }
    }
    return smallest;
}
```

但是这有一个问题, 变量`smallest`类型为`T`,这以为这它可以是任何一个类的对象.这就不能保证`T`所属的类有`compareTo`方法

解决这个问题的方法就是将`T`限制为实现了`Comparable`接口的类,可以通过对类型变量`T`设置限定实现这一点

**使用泛型限定的格式为`<T extends BundingType>`**

```java
public static <T extends Comparable> T min (T[] ts) {
    if (null == ts || ts.length == 0) {
        return null;
    }
    T smallest = ts[0];
    for (T t : ts) {
        if (smallest.compareTo(t) > 0) {
            smallest = t;
        }
    }
    return smallest;
}
```

现在,这个方法只能被实现了`Comparable`接口的类(`String、LocalDate`等)的数组调用

### 为什么使用extends而不是implements

但是这里为什么使用关键`extends`而不是使用`implements`?毕竟`Comparable`是一个接口

`<T extends BundingType>`,`T`应该是绑定类型的子类型,`T`和绑定类型可以是类,也可以是接口

**选择字关键`extends`的原因是更接近子类的概念**

### 多个限定

**一个类型变量,可以有多个限定.用`&`分隔,而逗号用来分隔类型变量**

```java
<T extends Comparable & Serializable, U>
```

> Java中类只支持单继承,但是可以实现多个接口
>
> 所以限定中最多只能有一个类,如果用一个类做限定,它必须是限定列表的第一个

## 8.5 - 泛型代码和虚拟机

虚拟机没有泛型对象,所有对象都属于普通类

### 8.5.1 - 泛型擦除

**无论何时定义一个泛型类型,都自动提供了一个相对应的原始类.原始类型的名字就是删除类型变量后的泛型类型名**

**同时擦除类型变量,并替换为限定类型(无限定的变量用`Object`)** 

例如,`Pair<T>`的原始类型如下:

```java
public class Pair {

    private Object first;
    private Object second;
    
    public Pair(Object first, Object second) {
        this.first  = first;
        this.second = second;
    }
    
    public Object getFirst() { return first; }
    public void setFirst(Object first) { this.first = first; }
    
    public Object getSecond() { return second; }
    public void setSecond(Object second) { this.second = second; }

}
```

因为`T`是一个无限定的变量,所以指定用`Object`替换,这就像是我们不使用泛型构造这个对象一样

结果就是一个普通的类,就好像Java在泛型引入之前那样

> 在程序中可以包含不同类型的`Pair`,例如,`Pair<String>`,`Pair<LocalDate>`.而擦除类型后变成原始的`Pair`类型了

#### 验证泛型擦除

我们定义两个不同泛型类型不同的`Pair`对象,将他们的`Class`进行对比,看看结果如何

```java
public static void main(String[] args) {

    Pair<String>  stringPair  = new Pair<>();
    Pair<Integer> integerPair = new Pair<>();
    System.out.println(stringPair.getClass() == integerPair.getClass());
}
```

结果是`true`.这就说明两个`class`文件是同一个(是擦除后的类型)

#### 限定类型替换

**原始类型用第一个限定的类型变量来替换,如果没有给限定就用`Object`替换**

**例如,类`Pair<T>`中的类型变量没有显式的限定,因此,原始类型用`Object`替换`T`**

假设声明了限定,例如:

```java
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Pair<T extends Comparable & Cloneable> {

    private T first;
    private T second;
}
```

原始类型`Pair`如下:

```java
public class Pair {

    private Comparable first;
    private Comparable second;
    
    public Pair(Comparable first, Comparable second) {
        this.first  = first;
        this.second = second;
    }
    
    public Comparable getFirst() { return first; }
    public void setFirst(Comparable first) { this.first = first; }
    
    public Comparable getSecond() { return second; }
    public void setSecond(Comparable second) { this.second = second; }

}
```

如果说我们将声明改为`Pair<T extends Cloneable & Comparable>`,这样做呢?

这样做的话还是一样会用`Cloneable`代替来替换,但是这样在调用`Comparable`接口的方式时会进行强制类型转换

**所以为了提供效率,应该将标记接口(没有方法的接口)放在限定边界的末尾**

### 8.5.2 - 翻译泛型表达式

当程序调用泛型方法时,如果擦除返回类型,编译器插入强制类型转换.例如:

```java
Pair<Integer> pair  = new Pair<>();
Integer       first = pair.getFirst();
```

擦除`getFirst`的返回类型后将返回`Object`类型.编译器自动插入`Integer`的强制类型转换

也就说,编译器把这个方法调用翻译为两条虚拟机指令
    
   - 对原始方法`Pair.getFirst`的调用
   
   - 将返回的`Object`类型强制转换为`Integer`类型
   
> **当存取一个泛型域时也要插入强制类型转换**

### 8.5.3 - 翻译泛型方法

类型的擦除也会出现在泛型方法中,看下面的例子

```java
private static <T extends Comparable> T min(T[] ts) {
    T min = ts[0];
    for (T t : ts) {
        if(t.compareTo(min) < 0) {
            min = t;
        }
    }
    return min;
}
```

在擦除类型后,会使用限定进行替换就会变成下面这样:

```java
private static Comparable min(Comparable[] ts) {
    Comparable min = ts[0];
    for (Comparable t : ts) {
        if(t.compareTo(min) < 0) {
            min = t;
        }
    }
    return min;
}
```

##### 方法擦除带来的问题

看下面的示例:

```java
private static class StringPair extends Pair<String> {

    @Override
    public void setSecond(String second) {
       super.setSecond(second);
    }
}
```

令人感到奇怪的是,我们从`Pair`继承的`setSecond`方法它在擦除后应该是`setSecond(Object second)`

那么`StringPair`中的`setSecond`肯定就无法覆盖父类的方法,这对多态来说确实是个不小的麻烦,那么编译器是怎么解决的呢?

要解决这个问题,编译器会在`StringPair`类中生成一个桥方法:

```java
public void setSecond(Object second) {
   setSecond((String)second);
}
```

这样就能覆盖父类的`setSecond`,并且在桥方法中调用的是`setSecond(String second)`方法,这样一来对多态就没有问题了

但是,假设我们`StringPair`也覆盖了`getSecond`方法.在`StringPair`就会有两个方法

  1. Object getSecond(); 
  2. String getSecond();

**在Java中是不允许这样的(具有相同参数类型的两个方法是不合法的),他们都没有参数.在虚拟机中,用参数类型和返回类型确定一个方法.**

**因此,编译器可能生成两个仅返回类型不同的方法字节码,虚拟机自己能够正确地处理这一情况**

> [参考资料](https://blog.csdn.net/PacosonSWJTU/article/details/50374131)

## 8.6 - 约束与局限性

### 8.6.1 - 不能使用基本类型实例化类型参数

**不能用类型参数代替基本类型**

因此,没有`Pair<double>`,只有`Pair<Double>`

**这其中的原因是类型擦除,在擦除之后,`Pair`类含有`Object`类型的域,而`Object`不能存储`double`值**


## 8.7 - 泛型类型的继承规则

## 8.8 - 通配符类型

## 8.9 - 反射与泛型
