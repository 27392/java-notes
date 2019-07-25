# Java 核心技术 卷I - 第四章

## 类和对象
    
   类是构造对象的模板.由类构造(construct)对象的过程称为创建类的实例(instance)
   
   + **对象的三个主要特性**
  
        - 对象的行为(behavior)
            - 可以对对象施加哪些操作,或可以对对象施加哪些方法?
        - 对象的状态(state)
            - 当施加那些方法时,对象如何响应?
        - 对象标识(state)
            - 如何辨别具有相同行为但状态不同的对象?
   
   + **类之间的关系**
   
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
   
   + **注意**
          
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

   + **结论**
   
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

定义一个`Employee`(雇员)类,在当前目录下`EmployeeTest`类中

   + 注意
    
       源文件名是`Employee.java`,这是因为文件名必须与`public`类的名字相匹配.
       
       **在一个源文件中,只能有一个共有类,但可以有任意数目的非公有类**

### Employee类解读
  
   + **方法**
   
       在`Employee`类中包含一个构造器和四个方法,该类的所有方法都被标记为`public`
       
       ```java
       public Employee(String name, Double salary, int year, int month, int day)
       public String getName()
       public Double getSalary()
       public LocalDate getHireDay() 
       public void raiseSalary(double byPercent)
       ```
       关键字`public`意味着任何类的任何方法都可以调用这个些方法
   
   + **实例域**
   
       接下来在`Employee`类的实例中还有三个实例域来存放将要操作的数据
       ```java
       private String name;
       private Double salary;
       private LocalDate hireDay;
       ```
       关键字`priave`确保只有`Employee`类的实例能够访问这些实例域,而其他类的方法不能够读写这些域
       
   + **构造器**
   
       在来看看`Employee`类的构造器
       ```java
       public Employee(String name, Double salary, int year, int month, int day) {
           this.name = name;
           this.salary = salary;
           this.hireDay = LocalDate.of(year, month, day);
       }
       ```
       可以看到构造器与类同名,在构造`Employee`类的对象是,构造器会运行,以便将实例域初始化为所希望的状态
       
       例如,使用下面代码创建`Employee`类实例时:
       ```
       new Employee("小明", 10000, 1990, 12, 2);
       ```
       将会把实例域设置为:
       ```
       name = "小明";
       salary = 10000;
       hireDay = LocalDate.of(1990, 12, 2);
       ```
       + **注意**
           - **构造器与类同名**
           - **每个类可以有一个以上的构造器**
           - **构造器可以有 0 个、1 个或多个参数**
           - **构造器没有返回值**
           - **构造器总是伴随着 `new`操作一起调用**
           
### 隐式参数与显式参数

方法用于操作对象以及存取它的实例域.例如,方法:
```java
public void raiseSalary(double byPercent) {
    double v = salary * byPercent / 100;
    salary += v;
}
```
将调用这个方法的对象的`salary`实例域设置为新值,看看下面的调用:
```java
Employee employee = new Employee("赵四",4000d, 1981, 4, 4);
employee.raiseSalary(2);
```
它的结果将 `employee`对象`salary`域的值增加了2%,具体说,这个条用将执行下列命令

```java
double v = employee.salary * 2 / 100;
employee.salary += v;
```

`raiseSalary`有两个参数.
    
   + 第一个参数称为隐式参数,是出现在方法名前的`Employee`对象
   
   + 第二个参数位于方法名后面括号中的数值,这是一个显示参数

**在每个方法中,关键字`this`表示隐式参数**

### 封装的好处
```java
public String getName()
public Double getSalary()
public LocalDate getHireDay() 
```
这些都是典型的访问器方法.由于他们只返回实例域值,因此又称为域访问器

如果将`name`,`salary`,`hireDay`实例域设置为`public`来取代独立的访问器方法会不会更容易些呢?

关键在于这些域是只读域,一旦在构造器中设置完毕,就没有任何一个办法可以对它进行修改,这样类确保不会受外界的破坏

如果获取或设置实例域的值,应该提供下面三项内容

+ **一个私有的实例域**
+ **一个共有的域访问器方法**
+ **一个共有的域更改器方法**

### final 实例域

可以将实例域定义为`final`.构建对象是必须初始化这样的域.

也就是说,必须保证在每一个构造器执行之后,这个域的值被设置,并且在后面的操作中,不能够在对他进行修改

**final修饰符大都应用于基本类型域,或不可变类型的域**

**(如果类中的每个方法都不会改变其对象,这种类就是不可变的类,例如`String`)**
## 静态域与静态方法

   + **静态域**
    
        如果将域定义为`static`,每个类中只有一个这样的域,而每一个对象对于所有的实例域却都有自己的一份拷贝.
        
        例如,假定需要给每一个职员赋予唯一标识码,这里新建`staff`类,添加一个实例域`id`和一个静态域`nextId`
        
        ```java
        private static int nextId = 1;
        private int id;
        ...
        ```
        现在,每一个职员都有一个自己的`id`域,但是这个类的所有实例共享一个`nextId`域
        
        换句话说,如果有1000个`Staff`类的对象,则有1000个实例域id.
        
        **但是,只有一个静态域`nextId`域.即使没有一个职员对象,静态域`nextId`域也存在.它属于类,而不属于任何独立的对象**
        
   + **静态常量**
   
        静态变量使用的比较少,但静态常量却使用的比较多.例如,在`Math`类中定义了一个静态常量:
        
        ```java
        public class Math {
             public static final double PI = 3.14159265358979323846;
        }
        ```
        在程序中可以采用`Math.PI`的形式获得这个常量
        
        如果关键字`static`被省略,`PI`就变成了`Math`类的一个实例域.并且每一个`Math`对象都有它自己的一份`PI`拷贝        
        
        **由于每个类都可以对公有域进行修改,所以,最好不要将域设计为`public`. 然而,共有变量(即`final`域)却没有问题**
   
   + **静态方法**
   
        **静态方法是一种不能向对象实施操作的方法**,例如,`Math`类的`pow`方法就是一个静态方法
        
        ```java
        Math.pow(x,y);
        ```
        在计算时,不使用任何`Math`对象. 换句话说,没有隐式参数
        
        **可以认为静态方法是没有`this`参数的方法**
        
        `Staff`类的静态方法不能访问Id实例域,因为它不能操作对象. 但是静态方法可以访问自身类中的静态域.
        
        ```java
        public static int getNextId() {
            return nextId;
        }
        ```
        
        可以通过类名调用这个方法
        
        ```java
        Staff.getNextId();
        ```
## 方法参数
在程序设计语言中有关将参数传递给方法(或函数)的一些专业术语.
    
   - 按值调用   表示方法接受的是调用者提供的值
   - 按引用调用 表示方法接受的是调用者提供的变量地址
    
**一个方法可以修改传递引用所对应的变量值,而不能修改传递值调用所对应的变量值**

**而在Java中总是采用值调用. 也就是说,方法得到的是所有参数值得一个拷贝**

例如调用下面代码, `tripleValue`方法将参数增加至三倍

```java
public void static tripleValue(double x) {
    x = 3 * x;
}
double percent = 10;
tripleValue(percent);
```

在调用之后,percent的值还是10. 看下具体的调用过程

   1. `x`被初始化为`percent`值得一个拷贝(也就是10)
   2. `x`被乘以3后等于30. 但是`percent`仍然是10
   3. 这个方法结束之后,参数变量`x`不再使用

> 然而,方法参数共有两种类型
>   - 基本数据类型(数字,布尔型)
>   - 对象引用

通过上面代码可以看到,**一个方法不可能修改一个基本数据类型的参数.** 

而对象引用作为参数就不用了,调用下面代码将一个雇员的薪水提高两倍

```java
public void static tripleValue(Emplyee x) {
    x.raiseSalary(200);
}
Employee employee = new Employee("小红", 1000d);
tripleValue(emplyee);
```

看下具体的调用过程
 1. `x`被初始化为`employee`值得拷贝,这里是一个对象的引用
 2. `raiseSalary`方法应用这个对象引用. `x`和`employee`同时引用的那个Employee对象的薪水提高了200%
 3. 方法结束后,参数变量`x`不再使用. 当然对象变量`employee`继续引用那个薪水增至三倍的雇员对象
 
**实现一个改变对象参数状态的方法并不是一件难事. 方法得到的是对象引用的拷贝,对象引用及其他的拷贝同时引用同一个对象**

### 注意
有许多人认为Java对对象采用的是引用传递,实际上这种理解是不对的, 通过一个交换两个雇员对象的方法.来试验这个问题

```java
public static void swap (Employee x, Employee y) {
    Employee temp = x;
    x = y;
    y = temp;
}
Employee x = new Employee("王五",10300d,1980,12,1);
Employee y = new Employee("赵四",4050d,1988,1,23);
swap(x, y);
```
如果java对对象是采用按引用调用,那么这个方法就应该能够实现交换数据的效果

但是,方法并没有改变储存在变量`a`和`b`中对象引用. `swap`方法的`x`和`y`被初始化为两个对象引用的拷贝,这个方法交换的是这两个拷贝

在方法结时参数变量`x`和`y`被丢弃了. 原来的变量`a`和`b`仍然应用这个方法调用之前所引用的对象

### 总结

通过我们的验证,说明**Java对对象采用的不是引用调用,实际上对象引用是按值传递的**

1. **一个方法不能修改一个基本数据类型的参数**
2. **一个方法可以改变一个对象参数的装填**
2. **一个方法不能让对象参数引用一个新的对象**

## 对象构造
### 重载
有些类有多个构造器,例如可以如下构造一个空的`StringBuilder`对象
```java
StringBuilder messages = new StringBuilder();
```
或者,可以指定一个初始字符串
```java
StringBuilder messages = new StringBuilder("hello");
```
**这种特征叫重载**

**如果多个方法(比如: `StringBuilder`构造器方法)有相同的名、不同的参数,便产生了重载**

编译器必须挑选出具体执行那个方法,他通过用各个方法给出的参数类型与特定方法调用所使用的值类型进行匹配来挑选出相应的方法

如果编译器找不到匹配的参数,就会产生编译时错误,因为根本不存在匹配,或者没有一个比其他的更好(这个过程被称为`重载解析`)

> **java允许重载任何方法,而不是只构造器方法. 因此,要完整的描述一个方法,需要指出方法名及参数类型,这叫做方法的签名**
>
> **返回类型不是方法签名的一部分. 也就是说,不能有两个名字相同、参数类型也想同却返回不同类型值的方法**

### 默认域初始化

如果在构造器中没有显式的给域赋予初值,那么就会被自动的赋为默认值

- 数值为`0`
- 布尔值为`false`
- 对象引用为`null`

### 无参数构造器

很多类都包含一个无参构造函数,对象由无参构造函数创建时,其状态会设置为适当的默认值,例如可以这样设置:

```java
public Object() {
    this.id = 0;
    this.salary = 0d;
    this.name = "";
}
```
如果在编写一个类时没有编写构造器,那么系统就会提供一个无参构造器,这个构造器黄所有的实例域设置为默认值

于是实例域中的数值型数据设置为`0`、布尔型数据设置为`false`、所有对象变量类型将设置为`null`

> 仅当类没有提供任何构造器的时候,系统才会提供一个默认的构造器.
>
> 如果当前给出了一个或多个有参构造器那么就不会有默认的构造器.
>
> 如果想让这个类的用户能够采用下列方式构造实例
> ```java
> Object x = new Object();
>```
> 就必须提供一个默认的构造器(及不带参数的构造器)

### 显式域初始化

通过重载构造器方法,可以采用多种形式设置类的实例域的初始化状态. 确保不管怎么调用构造器,每个实例域可以被设置为一个有意义的初值

也可以在类的定义中,直接将一个值赋给任何域. 例如:

```java
class Clerk {
    private int id;
    private String name = "";
}
```
在执行构造器之前,先执行赋值操作. 当一个类的所有构造器都希望把相同的值赋予某个特定的实例域时,这种方式特别有用

初始值不一定是常量值,在下面例子中调用方法对域进行初始化

```java
class Clerk {
    private static int nextId;
    private int id = assignId();

    private int assignId(){
        int r = nextId;
        nextId++;
        return r;
    }
}
```
### 调用另外一个构造器

关键字`this`引用方法的隐式参数.然而这个关键字还有另外一个含义
 
如果构造器的第一个语句形如`this(...)`,这个构造器将调用同一个类的另一个构造器. 例如:

```java
public Clerk(double salary) {
    this("", salary);
}
```
当调用`new Clerk(3000)`时,`Clerk(double)`构造器将调用`Clerk(String,double)`构造器

> 构造器必须要在首行

### 初始化块
上面说了两种初始化数据域的方法

1. 在构造器中设置
2. 在声明中赋值

实际上还有第三种机制,称为初始化块.在一个类的声明中可以包含多个代码块. 只要构造对象,这些块就会被执行. 例如:

```java
class Clerk {

    private String    name = "";
    private double    salary;
    private LocalDate birthday;

    {
        birthday = LocalDate.now();
    }
    
    public Clerk() {
        this.salary = 0d;
    }
        
    public Clerk(double salary) {
        this("", salary);
    }

    public Clerk(String name, double salary) {
        this.salary = salary;
        this.name = name;
    }
}
```
在示例中,无论使用哪个构造器构造对象,`birthday`域都在对象初始化块中被初始化.**首先运行初始化块,然后才运行构造器的主体部分**

> 即使在类的后面定义,仍然可以在初始化块设置域.

## 文档注释
## 类设计技巧
