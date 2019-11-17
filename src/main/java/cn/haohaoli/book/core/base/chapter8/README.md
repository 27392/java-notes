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

要解决这个问题,编译器会在`StringPair`类中生成一个**桥方法**:

```java
public void setSecond(Object second) {
   setSecond((String)second);
}
```

这样就能覆盖父类的`setSecond`,并且在桥方法中调用的是`setSecond(String second)`方法,这样一来对多态就没有问题了

但是,假设我们`StringPair`也覆盖了`getSecond`方法.在`StringPair`就会有两个方法

  1. `Object getSecond();`
  2. `String getSecond();`

**在Java中是不允许这样的(具有相同参数类型的两个方法是不合法的),他们都没有参数.在虚拟机中,用参数类型和返回类型确定一个方法.**

**因此,编译器可能生成两个仅返回类型不同的方法字节码,虚拟机自己能够正确地处理这一情况**

> **桥方法不仅用于泛型类型,在一个方法覆盖另一个方法时可以指定一个更严格的返回类型**

```java
public class Employee implements Cloneable {
    
    public Employee clone() throws CloneNotSupportedException{
        return (Employee) super.clone();
    }
}
```

`Object.clone`和`Employee.clone`方法被说成具有**协变的返回类型**

实际上`Employee`类有两个克隆方法

```java
public Employee clone()
public Object clone()
```

合成的桥方法调用了新定义的方法

> 注意点
>
> 1. **虚拟中没有泛型,只有普通的类和方法**
>
> 2. **所有的类型参数都有它们的限定类型替换**
>
> 3. **桥方法被合成来保持多态**
>
> 4. **为保持类型安全性,必要时插入强制类型转换**
>
> [参考资料](https://blog.csdn.net/PacosonSWJTU/article/details/50374131)

## 8.6 - 约束与局限性

### 8.6.1 - 不能使用基本类型实例化类型参数

**不能用类型参数代替基本类型**

因此,没有`SimpleGeneric<double>`,只有`SimpleGeneric<Double>`

**这其中的原因是类型擦除,在擦除之后,`SimpleGeneric`类含有`Object`类型的域,而`Object`不能存储`double`值**

### 8.6.2 - 运行时类型查询只适用于原始类型

**虚拟机中的对象总有一个特定的非泛型类型**(这个在泛型擦除的时候已经说过了)

**因此所有的类型查询只产生原始类型**

例如:测试`a`是否是任意类型的一个`Pair`,或强制类型转换

```java
if (a instanceof SimpleGeneric<String>) {}  //错误

if (a instanceof SimpleGeneric<T>) {}       //错误

// 强制类型转换
SimpleGeneric<String> s = (SimpleGeneric<String>)a
```

**为提醒这一风险,试图查询一个对象是否属于某个泛型类型时,倘若使用`instanceof`会得到一个编译器错误,如果使用强制类型转换会得到一个警告**

**同样的道理,`getClass()`方法总是返回原始类型(在泛型擦除中已经有验证)**

### 8.6.3 不能创建参数化类型的数组

> 也就说不能创建泛型类数组~

在开始之前我们需要知道

**在Java中,`Object[]`数组可以是任何数组的父类,或者说,任何一个数组都可以向上转型成它在定义时指定元素类型的父类的数组.**

**这个时候如果我们往里面放不同于原始数据类型,但是满足后来使用的父类类型的话,编译不会有问题**

**但是在运行时会检查加入数组的对象的类型,如果试图存储其他类型的元素,就会抛出一个`ArrayStoreException`异常**

> **也就是说,数组会记住它的元素类型,如果试图存储其他类型的元素,就会抛出一个`ArrayStoreException`异常**

```java
String[] strArray = new String[20];
Object[] objArray = strArray;
objArray[0]       = new Integer(1);   // throws ArrayStoreException
```

如果说Java允许我们使用类似如下的代码:

```java
SimpleGeneric<String> [] table = new SimpleGeneric<String>[10]
```

在擦除之后,`table`的类型是`Pair[]`.可以将它转换成`Object[]`,然后放入`Pair<String>`泛型实例

```java
Object[] objarray = table;
objarray[0]       =  new SimpleGeneric<String>();   //ok
```

这样能通过数组存储检查,它能知道我们往里放了`Pair`实例,至于我们定义的`<String>`在这个时候已经擦除掉了

对它而言,只要是`Pair`实例都是合法的,而我们本来定义的是转`SimpleGeneric<String>`的数组,结果我们却可以放任何`SimpleGeneric`对象

如果说Java允许我们这样定义的话,我们在取值时将无法保证数据的正确性,所以Java是不允许创建参数化类型的数组

> 尽管不允许创建这些数组,但是声明类型为`SimpleGeneric<String> []`的变量仍是合法的

### 8.6.4 Varargs警告(泛型可变参警告)

到现在我们已经知道了Java不支持泛型类型的数组,而现在我们来考虑一个问题

像参数个数可变的方法传递一个泛型类型的实例,例如:

```java
public static <T> void print(T... ts) {
    for (T t : ts) {
        System.out.println(t.getClass().getSimpleName());
    }
}
```

实际上,参数`ts`就是一个数组,我们之前说过在Java中可变参其实就是一个数组

如果我们现在这么去调用这个方法,

```java
print(new SimpleGeneric<>(), new SimpleGeneric<>());
```

那么,为了调用这个方法,Java虚拟机必须建立一个`SimpleGeneric<String>`数组,这就违反了*不能创建泛型数组*这个规则

不过,对于这种情况,规则有所放松,你只会得到一个警告,而不是错误!

可以采用两种方法来抑制这个警告

 - **在调用泛型可变参方法的调用方上上增加注解`@SuppressWarnings("unchecked")`**
 
 - **在泛型可变参方法上增加注解`@SafeVarargs`,在JdK1.7中可以使用**

### 8.6.5 不能实例化类型变量

**不能直接使用像`new T()`,`new T[n]`,`T.class`等这样的的表达式中的类型变量**

例如:下面`SimpleGeneric<T>`类的构造器就是非法的

```java
public SimpleGeneric() {
    // this.field = new T();    //错误
}
```

类型擦除将`T`改变成`Object`,而我们本意并不希望调用的是`new Object()`

比较传统的解决方法是通过反射调用`Class.newInstance`方法类构造泛型对象:

```java
T t = T.class.newInstance();
```

但是这个表达式`T.class`是不合法的,因为它会擦除为`Object.class`,到头来还是`Object`对象并不是我们想要的

必须像下面这样设计便可以得到一个`Class`对象

```java
static <T> SimpleGeneric<T> make(Class<T> clazz) throws IllegalAccessException, InstantiationException {
    return new SimpleGeneric<>(clazz.newInstance());
}
```

但是在Java8之后,最好的解决方法是提供一个构造器表达式.例如

```java
SimpleGeneric<String> supplierPair = SimpleGeneric.make(String::new);
```

`makePair`方法接受一个`Supplier<T>`这样一个函数式接口,表示一个无参数而且返回类型是`T`的函数

```java
static <T> SimpleGeneric<T> make(Supplier<T> supplier) {
    return new SimpleGeneric<>(supplier.get());
}
```

### 8.6.6 不能构造泛型数组

**就像不能实例化一个泛型实例一样,也不能实例化泛型数组**

例如: 下面`SimpleGeneric<T>`类的构造器就是非法的

```java
public SimpleGeneric() {
    // fields = new T[10](); //错误
}
```

如构造泛型实例的解决方式一样,也可以使用反射和函数式接口来实现

具体代码在`GenericRestrainTest`类中,这里我们关注主要的代码

与构造泛型实例的发射方式不同,这里使用`Array`类中的`newInstance()`方法传入类型和长度实例化

```java
private static <T extends Comparable> T[] minmax(Class<T> clazz, T... a) {
    // ...其他操作
    T[] ts = (T[]) Array.newInstance(clazz, 2);
    // ...其他操作
    return ts;
}
```

而这里使用`IntFunction`,当然使用`Supplier`也是一样的.使用`Supplier`我们可以在方法外面定义长度

```java
private static <T extends Comparable> T[] minmax(IntFunction<T[]> function, T... a) {
    //..其他
    T[] ts = function.apply(2);
    // ..
    return ts;
}
```

### 8.6.7 不能在泛型类中定义静态类型变量

```java
public static class SimpleGeneric<T> {
     private static T staticField;  //错误
}
```

**因为静态域会在对象间共享,所以无法确定需要使用的类型**

### 8.6.8 不能抛出或捕获泛型类的实例

也就说不能抛出也不能捕获**泛型类对象**

`catch`子句中不能使用类型变量.例如,以下方法将不能通过编译

```java
public static <X extends Throwable> void tryEx(String str , Class<X> x){
    try {
        
    } catch (X x){  //不能catch类型变量,错误

    }
}
```

捕获异常的原则是,子类在前,父类在后.而泛型在擦除后`X`会变成`Throwable`,`Throwable`是所有异常的父类

这就说明我们就不能再捕获其他异常,而这违背了异常捕获的原则

实际上,甚至**泛型类**拓展`Throwable`(这里包含它的子类等等)都是不合法的

```java
public class Ex<T> extends Throwable{
}
```

为什么泛型类不能拓展`Throwable`呢?

因为异常都是在运行时捕获和抛出的,而在编译的时候,泛型则会被擦除.

假设上面的可以编译通过

```java
try {  

} catch(Ex<Integer> e1){

} catch(Ex<Number> e2){
  
} 
```

在泛型擦除后,在两个`catch`块中的类是一样的,而`catch`两个一样的异常会编译不通过

```java
try {  

} catch(Ex e1){

} catch(Ex e2){ //不能捕获两个一样的异常
  
} 
```

不过,在异常规范中使用类型变量是允许的

```java
public static <X extends Throwable> void isEmpty(String str, X x) throws X {
    if (str == null || "".equals(str)){
        throw x;
    }
}
```

### 8.6.9 可以消除对受查异常的检查

感觉意义不是很大,后面有具体的例子在说明

### 8.6.10 注意擦除后的冲突

编译器会检查是否存在冲突,这个不做详细的解释

## 8.7 - 泛型类型的继承规则

先来看两个类`Manager`和`Employee`,`Manager`类继承`Employee`类

那么`Pair<Manager>`是`Pair<Employee>`的子类吗?

下面我们将一个`Pair<Manager>`实例赋值给`Pair<Employee>`来看看会怎么样

```java
Pair<Manager>  managerPair   = new Pair<>();
Pair<Employee> employeePairs = managerPair;  // 错误 Pair<Manager>并不是Pair<Employee>的子类
```

编译错误,很显然`Pair<Manager>`并不是`Pair<Employee>`的子类

**所以无论`S`和`T`有什么关系,`Pair<S>`与`Pair<S>`都没有什么联系**

**泛型类也可以实现或者继承其他泛型类,这一点与普通类没有什么区别**

> 注意泛型与Java数组之间的区别,可以将一个`Manager[]数组`赋给一个类型为`Employee[]`的变量

## 8.8 - 通配符类型

### 8.8.1 通配符的概念(子类限定通配符)

**在通配符类型中,运行类型参数变化**

例如通配符类型 

```java
Pair<? extends Employee>
```

它表示任何泛型`Pair`类型,它的类型参数是`Employee`的子类,如`Pair<Manager>`等

假设我们要编写一个打印雇员的方法:

```java
private static void printBuddies(Pair<Employee> pair) {
    Optional.ofNullable(pair).ifPresent(System.out::println);
}
```

就像在[泛型继承](#87---泛型类型的继承规则)中说的,不能将`Pair<Managee>`传递给这个方法,解决的方法很简单:使用通配符类型

```java
private static void printBuddies(Pair<? extends Employee> pair) {
    Optional.ofNullable(pair).ifPresent(System.out::println);
}
```

类型`Pair<Manager>`是`Pair<? extends Employee>`的子类型(如下)

        Pair<? extends Employee>
        ┌──────────┴───────────┐
    Pair<Manager>        Pair<Employee>

但是将`Pair<Manager>`,传递给`Pair<? extends Employee>`会有什么问题吗?

```java
Pair<Manager>            managerBuddies  = new Pair<>();
Pair<? extends Employee> wildcardBuddies = managerBuddies;  //OK
wildcardBuddies.setFirst(new Employee());                   //错误
```

这好像并没有什么问题,但是对`setFirst`方法的调用有一个类型错误

让我们仔细看一下类型`Pair<? extends Employee>`.它的方法似乎是这样的

```java
? extends Employee getFirst();
void setFirst(? extends Employee);
```

**这样将不可能调用`setFirst`方法,编译器只知道某个`Employee`的子类型,但不知道具体是什么类型.它拒绝传递任何特定的类型.毕竟`?`不能用来匹配**

**使用`getFirst`就不存在这个问题:将`getFirst`的返回值赋给一个`Employee`的引用完全合法**

> 子类型限定的通配符可以从泛型对象读取 

### 8.8.2 通配符的超类限定

通配符限定与类型变量限定十分类似,但是,还有一个附加的能力,即可以指定一个超类型限定.如下所示

```java
? super Manger
```

这个通配符限制为`Manager`的所有超类型(如下)

          Pair<? super Manager>
        ┌──────────┴───────────┐
    Pair<Employee>        Pair<Object>

为什么要这样做呢? 带有超类型限定的通配符的行为与**子类限定通配符**(`? extends Employee`)恰恰相反

它可以为方法提供参数,但不能使用返回值,例如`Pair<? super Manager>`

```java
? super Manager getFirst()
void setFirst(? super Manager)
```

我们可以把它的方法看成以上内容,这并不是Java的语言,但是可以让我们知道编译器知道什么

编译器无法知道`setFirst`方法的具体类型,因此调用这个方法时不能接受类型为`Employee`或`Object`等参数,只能传递`Manager`类型的对象,或者它的子类型.

另外,如果调用`getFirst`方法,不能保证返回对象的类型.只能把它赋值给一个`Object`

> **直观的将,带有超类限定的通配符可以向泛型对象写入,带有子类型限定的通配符可以从泛型对象读取**
> 
> **在方法参数中的区别就是一个是限制了必须是我的子类,而另了一个限制必须是我的父类(这个通过两个简单的图就能看出)**
>
> **而在调用时区别就是上面说,一个能读一个能写(比较难理解,可以多看看实例代码加深理解)**

### 8.8.3 无限定通配符

### 8.8.4 通配符捕获

## 8.9 - 反射与泛型
