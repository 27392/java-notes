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
Pair<String>(String first,String second);
// 方法
String getFirst();
String getSecond();
void setFirst(String first);
void setSecond(String second);
```

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

## 8.5 - 泛型代码和虚拟机

## 8.6 - 约束与局限性

## 8.7 - 泛型类型的继承规则

## 8.8 - 通配符类型

## 8.9 - 反射与泛型
