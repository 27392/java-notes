# Java 核心技术 卷I - 第五章

## 5.1 - 类,超类和子类

假设你在某个公司工作,这个公司中的经理的待遇与普通雇员的待遇存在一些差异

不过他们之间也存在着很多相同的地方,例如,他们都领薪水

只是普通雇员在完成本职任务之后仅领取薪水,而经理在完成了预期的业绩之后还能得到奖金.

这种情况就需要继承,这是因为需要为经理定义一个新类`Manager`,以便增加一些新的功能.

但可以重用`Emmployee`类中以编写的部分代码,将其中的所有域保留下来.

从理论上讲,在`Manager`与`Employee`之间存在着明显的**is-a**(是)关系,每一个经理都是一名雇员: `is-a`关系是继承的一个明显特征

### 5.1.1 - 定义子类

下面是由继承`Employee`类来定义`Manager`类的格式,关键字`extends`表示继承

```java
public class Manager extends Employee {
} 
```
> 关键字`extends`表明正在构造的新类派生于一个已存在的类
>
> 已存在的类称为`超类`(superclass)、`基类`(base class)或`父类`(parent class)
> 
> 新类称为`子类`(subclass)、`派生类`(derived class)或`孩子类`(child class)

尽管`Employee`类是一个超类,但并不是因为它优于子类或者拥有比子类更多的功能.实际上恰恰相反,子类比超类拥有的功能更加丰富
 
现在我们在`Manager`类中增加用于储存奖金信息(`bonus`)的域,以及一个用于设置这个域的新方法

```java
public class Manager extends Employee {
    
    private double bonus;
    
    public void setBonus (double bonus){
        this.bonus = bonus;
    }
} 
```
这里定义的方法和域并没有什么特别之处. 如果有一个`Manager`对象,就可以使用`setBouns`方法

当然,由于`setBonus`方法不是在`Employee`类中定义的,所以属于`Employee`类的对象不能使用他.

然而,尽管在`Manager`类中没有显式的定义`getName`和`getHireDay`等方法,

但属于`Manager`类的对象却可以使用他们, 这是因为`Manager`类自动的继承了超类`Employee`中的这些方法

同样,从超类中还继承了`name`、`salary`、`hireDay`这3个域. 

这样一来,每个Manager类对象就包含了4个域 : `name`、`salary`、`hireDay`和`bonus`

**在通过拓展超类定义子类的时候,一个将通用的方法放在超类中,而将具有特殊用途的方法放在子类中.**

> **注意不能继承父类的`private`修饰的域与方法**

### 5.1.2 - 覆盖方法

然而,超类中的有些方法对子类`Manager`并不一定适用.

具体来说,`Manager`类中的`getSalary`方法应该返回薪水和奖金的总和. 为此需要提供一个新的方法来覆盖超类中的这个方法

似乎看起来很简单,只需要返回salary和bonus域的总和就可以了

```java
public double getSalary() {
    return salary + bonus;
}
```

但是,这个方法好像并不能运行.这是因为`Manager`类不能够直接访问超类的私有域

也就是说,尽管每个`Manager`对象都拥有一个名为`salary`的域,但在`Manager`类不能够直接访问`salary`域

只有`Employee`类才能够访问私有部分

**如果`Manager`类的方法一定要访问私有域,就必须借助于公有的接口,`Employee`类中的公有方法`getSalary`正是这样一个方法**

现在我们知道原因,再来试下

```java
public double getSalary() {
    double baseSalary = getSalary();
    return baseSalary + bonus;
}
```

但是这样修改仍然不能运行,问题出现在调用`getSalary`的语句上

这是因为`Manager`类也有一个`getSalary`方法(就是正在实现的这个方法),所以这条语句将会导致无限次的调用自己,知道整个程序崩溃为止

这里我们应该希望调用超类`Employee`的`getSalary`方法,而不是当前类的这个方法,为此使用特定的关键字`super`解决这个问题:

```java
public double getSalary() {
    double baseSalary = super.getSalary();
    return baseSalary + bonus;
}
```
> 有些人认为`super`与`this`引用是类似的概念,实际上,这样比较并不太恰当.
>
> 这是因为`super`不是一个对象的引用,不能讲`super`赋给另一个对象变量,它只是一个指示编译器调用超类方法的特殊关键字

### 5.1.3 - 子类构造器

让我们在子类中提供一个构造器,如下:

```java
public Manager(String name, double salary, int year, int month, int day) {
    super(name, salary, year, month, day);
}
```
这里的关键字`super`具有不同的含义

```java
super(name, salary, year, month, day);
```
这里是"调用超类`Employee`中含有`name, salary, year, month, day`等参数的构造器"的简写

由于`Manager`类的构造器不能访问Employee类的私有域,所以必须利用`Employee`类的构造器对这部分私有域进行初始化

我们可以通过`super`实现对超类构造器的调用,**使用`super`调用构造器的语句必须是子类构造器的第一条语句**

**如果子类的构造器没有显式的低啊用超类的构造器,则将自动的调用超类默认(没有参数)的构造器**

**如果超类没有不带参数的构造器,并且在子类的构造器中有没有显式的调用超类的其他构造器,代码则会编译错误**

> 关键字`this`有两个用途,一是引用隐式参数,二是调用该类其他的构造器;
>
> 同样`super`关键字也有两个用途,一是调用超类的方法,二是调用超类的构造器
>
> 在调用构造器的时候,这两个关键字的使用方式很相似. **调用构造器的语句只能作为另一个构造器的第一条语句出现**
>
> 构造参数即可以传递给本类(this)的其他构造器,也可以传递给超类(super)的构造器

### 5.1.4 - 继承层次

继承并不仅限于一个层次. 

例如: 可以由`Manager`类派生`Executive`类.有一个公共超类派生出来的所有类的集合被称为"继承层次"

在继承层次中,从某个特定的类到其祖先的路径被称为该类的继承链

    // 继承层次
    
          Employee
       ┌-----↑-----┐
    Manager    Secretary
       ↑
    Executive
    
通常,一个祖先类可以拥有多个子孙继承链.

例如,可以由`Employee`类派生出子类`Secretary`它与`Manager`类没任何关系. 必要的话可以将这个过程一直延迟下去

> **注意! Java 中不支持多继承**

### 5.1.5 - 多态

**有一个用来判断是否应该设计为继承关系的简单规则,这就是"is-a"规则,它表明子类的每个对象也是超类的对象**

例如,每个经理都是雇员,因此将Manager类设计为Employee类的子类是显而易见的, 反之不然,不是每个雇员都是经理

"is-a"规则的另一中表述法是置换法则. 他表示程序中出现超类对象的任何地方都可以用子类对象置换

例如,可以将一个子类的对象赋给超类变量

```java
Employee e;
e = new Employee();
e = new Manager();
```

在Java中对象变量是多态的.

一个`Employee`变量既可以引用一个`Employee`类对象,也可以引用一个`Employee`类的任何一个子类的对象(`Manager`,`Executive`等)

看一个例子:

```java
Manager boss = new Manager();
Employee[] staff = new Employee[3];
staff[0] = boss;
```

在变量`staff[0]`与`boss`引用同一个对象. 但编译器将`staff[0]`看成`Employee`对象

这就意味着,可以这样调用

```java
boss.setBonus(5000);
```

但是不能这样调用

```java
staff[0].setBonus(5000);
```

这是因为`staff[0]`声明的类型是`Employee`,而`setBonus`不是`Employee`类的方法

然而,不能将一个超类的引用赋给子类变量.例如下面的赋值就是非法的

```java
Manager m = staff[0];
```

原因很清楚: 不是所有的雇员都是经理.

如果赋值成功,`m`有可能引用了一个不是经理的`Employee`对象,当后面调用`setBonus`时就有可能发生运行时错误

### 5.1.6 - 理解方法调用

弄清楚如何在对象上应用方法调用非常重要. 

下面假设要调用`x.f(args)`,隐式参数`x`声明为类C的一个对象.下面是调用过程的详细描述

```java
public class C{
    public f(int i) {}
    public f(String s) {}
}

C x = new C();
x.f(args);
```
1)  
    编译器查看对象的声明类型和方法名. 假设嗲用`x.f(param)`,且隐式参数`x`声明为`C`的类对象

    需要注意的是: 有可能存在多个名字为`f`,但是参数类型不一样的方法.

    例如,可能存在方法`f(int)`和方法`f(String)`. 

    编译器将会一一例举所有`C`类中名为`f`的方法 和其超类中访问属性为`public`且方法名为`f`的方法(超类的私有方法不可访问)

    至此,编译器已获得所有可能被调用的候选方法
   
2)  
    接下来,编译器将查看调用方法时提供的参数类型.
    
    如果在所有名为`f`的方法中存在一个与提供的参数类型完全匹配,就选择这个方法.这个过程被称为**重载解析**
    
    例如, 对于调用`x.f("hello")`来说,编译器会挑选`f(String)`,而不是`f(int)`
    
    由于允许类型转换(int可以转换成double,Manager可以转换成Employee,等等),所以这个过程可能很复杂
    
    如果编译器没有找到与参数类型匹配的方法,或者发现经过类型转换后有多个方法与之匹配,就会报告一个错误
    
    至此,编译器已获得需要调用的方法名字和参数类型
    
    > 之前说过,**方法的名字和参数列表称为方法的签名.** _(第4章重载有介绍)_
    > 
    > 例如, `f(int)`和`f(String)`是两个具有县共同名字,不用签名的方法.
    > 
    > 如果在子类中定义了一个与超类签名相同的方法,那么子类中的这个方法就覆盖了超类中的这个相同签名的方法
    > 
    > **不过返回类型不是签名的一部分**,因此,在覆盖方法时,一定要保证返回类型的兼容性
    >
    > 允许子类将覆盖方法的返回类型定义为原返回类型的子类型,
    >
    > 例如,假设Employee类有
    > ```java
    > public Emplyee getBuddy(){}
    > ```
    > 但是经理不会想找到非经理的员工,为反映这一点,在后面的子类`Manager`中可以按照如下所示的方式覆盖整个方法
    > ```java
    > public Manager getBuddy(){}
    > ```
    > 我们说,这两个getBuddy方法具有可协变的返回类型
3)
    
    可以具体参考: https://www.cnblogs.com/ygj0930/p/6554103.html
    
    如果是`private`方法、`static`方法、`final`方法或者构造器
    
    那么编译器将可以准确的知道应该调用哪个方法,我们将这种调用方式成为静态绑定
    
    于此对应的是,调用的方法依赖于隐式参数的实际类型,并且在运行时实现动态绑定

4)
    当程序运行,并且采用动态绑定方法时,虚拟机一定调用与x所引用对象的实际类型最合适的那个类的方法
    
    假设x的实际类型是D,他是C类的子类,如果D类定义了一个方法`f(String)`,就直接调用它
    
    ```java
    public class D extends C {
       public void f (String s) {};
    }   
    C x = new C();
    x.f(agrs);
    // 此时调用的是D类中的f(String)
    ```
    
    否则,将在D类的超类中寻找`f(String)`,以此类推
    
    每次调用方法都要进行搜索,事件开销相当大.
    
    因此,虚拟机预先为每个类创建了一个**方法表**,其中列出了所有方法的签名和实际调用的方法
    
    这样一来,在真正调用方法的时候,虚拟机仅查找这个表就行.
    
    在前面的例子中,虚拟机搜索D类的方法表,以便寻找与调用`f(String)`想匹配的方法
    
    这个方法既有可能是`D.f(String)`,也有可能是`C.f(String)`,这里的C是D的超类
    
    > 如果调用super.f(String),编译器将对隐式参数超类的方法表进行搜索
    
    举个例子:
    ```java
    Employee e = new Manager();
    e.getSalary();
    ```
    这里e声明为Employee类型
    
    Employee类只有一个名叫`getSalary`的方法,这个方法没有参数,因此不必担心重载解析的问题
    
    由于`getSalary`不是`private`方法、`static`方法或final方法,所以讲采用动态绑定
    
    虚拟机为Employee和Manager两个类生成方法表
    
    在Employee的方法表中,列出了这个类定义的所有方法
      
    ```    
    Employee
        getName()           -> Emplyee.getName()
        getSalary()         -> Emplyee.getSalary()
        getHireDay()        -> Emplyee.getHireDay()
        raiseSalary(double) -> Emplyee.raiseSalary(double)
    ```
    实际上,上面列出的方法并不完整,我们只看我们定义的方法
    
    Manager方法表则稍微有些不同,其中有三个方法是继承而来,一个方法时重新定义的,还有一个方法是新增加的
    
    ```    
    Manager
        getName()           -> Emplyee.getName()
        getSalary()         -> Emplyee.getSalary()
        getHireDay()        -> Emplyee.getHireDay()
        raiseSalary(double) -> Emplyee.raiseSalary(double)
        setBonus(double)    -> Emplyee.setBonus(double)
    ```
    在运行时,调用`e.getSalary`的解析过程为:
    
     1) 首先,虚拟机提取`e`的实际类型的方法表.即可能是`Employee、Manager`的方法表,也有可能是其他子类的方法表
        
     2) 接下来,虚拟机搜索定义`getSalary`签名的类. 此时,虚拟机已经知道应该调用哪个方法
        
     3) 最后虚拟机调用方法
    
    动态绑定有一个非常重要的特性:无需对现存的代码进行修改,就可以对程序进行拓展
    
    假设增加一个新类`Executive`类,并且变量`e`有可能引用这个类的对象,我们不需要对包含调用`e.getSalary()`的代码进行重新编译
    
    如果`e`恰好引用一个`Executive`类的对象,就会自动的调用`Executive.getSalary()`方法
    
> 注意 :
>
> 在覆盖一个方法的时候,子类方法不能低于超类方法的可见性.
>
> 特别是,如果超类方法时`public`,子类方法一定要声明为`public` 
    
### 5.1.7 - final类和方法

有的时候希望阻止人们利用某个类定义子类.不允许拓展的类被称为`final`类

如果在定义类的时候使用了`final`修饰符就表明这个类是`final`类.

例如, 假设希望组织人们定义`Executive`的子类,就可以在定义个类的时候.使用final修饰符声明. 声明格式如下:

```java
public final class Executive extends Manager{
    
}
```
类中特定方法也可以被声明为`final`,如果这样做子类就不能覆盖这个方法

> **`final`类中的所有方法自动的成为`final`方法**
>
> 在第四章**final 实例域**中说过,域也可以别声明为final
>
> 对于final域来说,构造对象之后就不允许改变他们的值了
>
> **不过如果将一个类声明为final,只有其中的方法自动的成为final,而(不包含域)**

### 5.1.8 - 强制类型转换

一个类强制转换成另一个类型的过程被称为类型转换

Java提供了一种专门用于类型转换的表示法. 例如:

```java
double x = 3.405;
int nx = (int)x;
```
将表达式x的值转换成整数类型,舍弃小数部分

正想有时需要将浮点类转换成整型数值一样,有时候也可能需要将某个类的对象引用转换成另一个类的对象引用

对象引用的转换语法与数值表达式的类型转换类似,仅需要用一堆圆括号将目标类名括起来,并放置在需要转换的对象引用之前就可以了. 例如:

```java
Manager m = new Manager();
m.setBonus(5000);
Employee[] staff = new Employee[3];
staff[0] = m;
staff[1] = new Employee();

Manager boss = (Manager) staff[0];
```

**进行类型转换的唯一原因是: 在暂时忽视对象的实际类型之后,使用对象的全部功能.**

由于某些项是普通职员,所以`staff`数组必须是`Employee`对象的数组

我们需要将数组中引用经理的元素复原成`Manage`r类,以便能够访问新增加的所有变量

**在Java中,每个对象变量都属于一个类型,类型描述了这个变量所引用的以及能够引用的对象类型**

例如, `staff[i]`引用一个`Employee`对象(因此他还可以引用`Manager`对象)

将一个值存入变量是,编译器将检查是否允许该操作.

**将一个子类的引用赋给超类变量,编译器是允许的.但将一个超类的引用赋给一个子类对象,必须进行类型转换,这样才能通过运行时检查**

如果试图在继承链上进行向下的类型转换,并且"谎报"有关对象包含的内容,会发生什么情况呢?

```java
Manager m = (Manager)staff[1];
```
在运行时系统报告这个错误,并产生一个`ClassCastException异常`. 如果没有捕获这个异常,那么程序就会终止

**因此,应该养成这样一个良好的程序设计习惯,在进行类型转换之前,先查看一下是否能够成功的转换**

这个过程简单的使用`instanceof`操作符就可以实现. 例如:

```java
if(staff[1] instanceof Manager) {
    m = (Manager)staff[1];
}
```
最后,如果这个类型转换不可能成功,编译器就不会进行这个转换. 例如:下面这个类型转换

```java
String s = (String)staff[1];
```
将会产生编译错误,这是因为String不是Employee的子类

实际上,通过类型准转换调整对象的类型并不是一种好的做法.

在我们列举的示例中,大多数情况并不需要将Employee对象转换成Manager对象,

两个类的兑现都能够正确的调用`getSalary`方法,这是因为实现多态性的动态绑定机制能够自动的找到相应的方法

只有在使用Manager中特有的方法时才需要进行类型转换

例如, `setBonus`方法. 如果鉴于某种原因,发现需要通过`Employee`对象调用`setBonus`方法,那么就一个检查一下超类的设计是否合理

重新设计超类,并添加`setBonus`方法才是正确的选择

请记住,只要没有捕获`ClassCastException`异常,程序就会终止执行.在一般青空下,尽量少用类型转换和`instanceof`运算符

+ 总结:

    - 只能在继承层次内进行类型转换
    
    - 在将超类转换成子类之之前,应该使用`instanceof`进行检查

> 如果 x 为null,`x instanceof C` 不会产生异常,只是返回false. 
>
> 之所以这样是因为null没有引用任何对象,当然也不会引用C类型的对象

### 5.1.9 - 抽象类

如果自下而上在类的继承层次结构上移,位于上层的类更具有通用性,甚至可能更加抽象

从某种角度看,祖先类更加通用,人们只将它作为派生其他类的基类,而不作为想使用的特定的实例类

例如,考虑一下对Employee类层次的拓展. 一个雇员是一个人,一个学生也是一个人. 下面将类Person和Student添加到类的层次结构中

    // 层次图
    
           Person
       ┌-----↑-----┐
    Employee    Student
    
为什么要花费精力进行这样高层的抽象呢? 每个人都有一些诸如姓名这样的属性.

学生与雇员都有姓名属性,因此可以将getName方法放置在位于继承关系较高层次的通用超类中

现在,在增加一个getDescription方法,它可以返回对一个人的简短描述. 例如:
    
     年薪50,000.00美元的员工
     
     计算机科学专业的学生

在Employee类和Student类中实现这个方法很容易. 但是要提供什么功能呢?除了姓名之外,Person类一无所知. 

当然,可以让Person中的getDescription方法返回一个空字符串

然而还有一个更好的办法,就是使用`abstract`关键字,这样就不需要实现这个方法了. 例如:

```java
public abstract String getDescription();
```

为了提高程序的清晰度,**包含一个或多个抽象方法的类本身必须被声明为抽象的**

```java
public abstract class Person{
    public abstract String getDescription();
}
```
除了抽象的方法之外,抽象类还可以包含具体数据和具体的方法. 例如,Person类还保存着姓名和一个返回姓名的具体方法

```java
public abstract class Person{
    private String name;
    
    public Person (String name) {
        this.name = name;
    }
    
    public abstract String getDescription();
    
    public String getName () {
        return this.name;
    }
}
```

> 建议尽量将通用的域和方法(不管是不是抽象的)放在超类(不管是否是抽象类)中

**抽象方法充当着占位的角色,他们的具体实现在子类中**

拓展抽象类可以有两种选择一种是在抽象类中定义部分抽象类方法或不定义抽象类方法,

这样就必须将子类也标记为抽象类;另一种是定义全部的抽象方法,这样依赖子类就不是抽象的了

例如,通过拓展抽象Person类,并实现`getDescription`来定义`Student`类,由于Student类中不再包含有抽象方法,也可以将类声明为抽象类

**类即使不包含抽象方法,也可以将类声明为抽象类**

**抽象类不能被实例化.也就是说,如果将一个类声明为`abstract`,就不能创建这个类的对象**.例如,代码

```java
new Person();
```

是错误的,但可以创建一个具体子类的对象.

**需要注意,可以定义一个抽象类的对象变量,但是他只能引用非抽象子类的对象.** 例如,

```java
Person p = new Student("xx","desc");
```

这里的`p`是一个抽象类Person的变量,`Person`引用了一个非抽象子类`Student`的实例

下面定义一个拓展抽象类Person的具体子类Student

```java
public class Student extends Person {
    
    private Strint major;
    
    public Student (String name, String major){
        super(name);
        this.major = major;
    }
    
    public String getDescription() {
        return major + "专业学生";
    }
}
```

在`Student`类中定义了`getDescription`方法. 因此,在Student类中的全部方法都是非抽象的,这个类不再是抽象类

### 5.1.10 - 访问修饰符

大家都知道,最好将类中的域标记为`private`,而方法标 记为`public`.

任何声明为`private`的内容对其他类都是不可见的,这对于子类来说也完全适用,即**子类也不能访问超类的私有域**

然而,在有些时候,人们希望超类中某些方法允许被子类访问,或允许访问超类的某个域

为此,需要将这些方法或者域声明为`protected`

如果将超类`Employee`中的`hireDay`声明为`protected`,而不是私有,Manager中的方法就可以直接访问它

不过,**Manager类中的方法只能够访问Manager对象中的hireDay域,而不能访问其他Employee对象中的这个域**

这种限制有助于避免滥用受保护机制,使得子类只能获得访问受保护欲的权利

在实际应用中,要谨慎使用`protected`属性.

假设需要将设计的类提供给其他人使用,而在这个类中设置了一些受保护域,由于其他人可以由这个类在派生出新类,并访问其中的受保护域

在这种情况下,如果需要对这个类实现进行修改,就必须通知所有使用这个类的人,这违背了OOP提倡的数据封装原则

受保护的方法更具有实际意义. 如果需要限制某个方法的使用,就可以将它声明为`protected`

这表明子类得到信任,可以正确的使用这个方法,而其他类则不行

+ 总结:

  - 1.**仅对本类可见 - `private`**
  - 2.**对所有类可见 - `public`**
  - 3.**对本报和所有子类可见 - `protected`**
  - 4.**对本包可见 - 默认,不需要修饰符**

## 5.2 - Object类

Object类是Java中所有类的始祖,在Java中每个类都是有它拓展而来的.但是并不需要这样写

```java
public class Employee extends Object{
}
```
如果没有明确的指出超类,Object就被认为是这个类的超类. 

由于在Java中每个类都是由Object类拓展而来的,所以熟悉这个类供的所有服务十分重要

我们可以用使用Object类型的变量引用任何类型的对象

```java
Object obj = new Employee();
```

当然,Object类型的变量只能用于作为各种值得通用拥有者.

要想对其中的内容进行具体的操作,还需要清楚对象的原始类型,并进行相应的类型转换

```java
Object obj = new Employee();
Employee e = (Employee) obj;
```

在Java中只有基本类型不是对象,例如,数值、字符和布尔类型的值都不是对象

**所有的数值类型,不管是对象数值还是基本类型的数值都拓展了Object类**

```java
Object obj = new Employee();
Employee[] staff = new Employee[10];
obj = staff;        // OK
obj = new int[10];  // OK
```
### 5.2.1 - equals方法

**Object类中的`equals`方法用于检测一个对象是否等于另一个对象.**

在Object类中,这个方法将判断两个对象是否具有相同的引用,如果两个对象具有相同的引用,他们一定是相等的.

对于多数的类来说,这种判断并没有什么意义. 例如:

采用这种方法比较两个`PrintStream`对象是否相等就完全没有意义

然而,经常需要检测两个对象状态的相等性,如果两个对象的状态相等,就认为这两个对象是相等的

例如: 如果两个雇员对象的姓名、薪水和雇佣日期都一样,就认为他们是相等的

```java
public class Employee {
    
    private String name;
    private double salary;
    private LocalDate hireDay;

    public boolean equals(Object o) {
        //检查引用是否一致
        if (this == o) return true;
        //如果为空返回false
        if (o == null) return false;
        //判断类型
        if (getClass() != o.getClass()) return false;
        Employee e = (Employee) o;
        //检查字段是否具有相同的值
        return name.equals(e.name) && salary == e.salary && hireDay.equals(o.hireDay);
    }
}
```

**`getClass`方法将返回一个对象所属的类,只有在两个对象属于同一个类时,才有可能相等**

> 为了防止`name`或`hireDay`可能为null的情况,需要使用`Objects.equals`方法
>
> 如果两个参数都为null,`Objects.equals(a,b)`调用将返回true;如果其中一个参数为null,则返回false
> 
> 否则,如果两个参数都不为null,则调用`a.equals(b)`,利用这个方法,Employee.equals方法的最后一条语句要改写为:
>
> ```java
> return Objects.equals(name, e.name) && salary == e.salary && Objects.equals(hireDay,e.hireDay);
> ```
> 
> **在子类中定义`equals`方法时,首先调用超类的`equals`, 如果超类中的域都相等,就需要比较子类的中实例域**
>
> ```java
> public class Manager extends Employee {
>    
>    private double bonus;
>    
>    public boolean equals (Object o) {
>        if (!super.equals(o)) return false;
>        Manager m = (Manager) o;
>        return bonus == m.bonus;
>    }
> }
> ```

### 5.2.2 相等测试与继承

如果隐式和显式的参数不属于同一个类,equals方法将如何处理呢? 这是一个很有争议的问题.

在前面的列子中,如果发现类不匹配,equals方法就返回false. 但是很多人却喜欢用`instanceof`进行检查

```java
if(!(o instanceof Employee)) rerurn false;
```

这样做不但没有解决`o`是子类的情况,并且还有可能会招致一些麻烦.这就是建议不要使用这种处理方式的原因所在.

Java语言规范要求`equals`方法具有下面的特性:

1. **自反性: 对于任何非空引用x, `x.equals(x)`应该返回true**
2. **对称性: 对于任何引用x和y,当且仅当`y.equals(x)`返回true,`x.equals(y)`也应该返回true**
3. **传递性: 对于任何引用x、y和z,如果x.equals(y)返回true,`y.equals(z)`返回true,`x.equals(z)`也应该返回true**
4. **一致性: 如果x和y引用的对象没有发生变化,反复调用`x.equals(z)`应该返回true**
5. **对于任意非空引用x,`x.equals(null)`应该返回false**

这些规则十分合乎情理,从而避免了类库实现者在数据结构中定位一个元素是还要考虑`x.equals(y)`,还是调用`y.equals(x)`的问题

然而,就`对称性`来说,当参数不属于同一个类的时候需要仔细的思考一下. 看下面这个调用

```java
e.quals(m)
```

这里的`e`是一个Employee对象,`m`是一个Manager对象,并且两个对象具有相同的姓名、薪水和雇佣日期

如果在`Employee.equals`中用instanceof进行检查,则返回true. 然而这意味着反着来调用:

```java
m.equals(e)
```

也需要返回true.`对称性`不允许这个方法调用返回false,或抛出异常

这就使得Manager类受到了束缚. 这个类的`equals`方法必须能够用自己与任何一个Employee对象进行比较, 而不考虑经理拥有的那部分特有信息! 猛然间会让人感觉`instanceof`检查并不是完美无瑕

有些人认为不应该利用`getClass`来检查,因为这样不符合`置换原理`(具体没仔细研究,暂时就不解释这个了)

下面可以从两个截然不同的情况看一下这个问题:

+ **如果子类能够拥有自己的相等概念,则`对称性`需求将采用`getClass`进行检查.**

+ **如果由超类决定相等的概念那么就可以使用`instanceof`进行检查,这样可以在不用的子类对象之间进行相等的比较**

在雇员和经理的例子中,只要对应的域相等,就认为两个对象相等.

如果两个Manager对象所对应的姓名、薪水和雇佣日期均相等,而奖金不同就认为他们是不相同的,因此可以使用`getClass`检查

但是,假设使用雇员的ID作为相等的检测标准,并且这个相等的概念适用于所有的子类,就可以使用`instanceof`进行检查,并应该将`Employee.equals`声明为`final`

> 编写一个完美的equals方法的建议
>
> 1. 显式参数命名`o`,稍后需要将它转换成另一个叫`other`的变量
> 
> 2. 检测`this`与`o`是否引用同一个对象
>
>   ```java
>    if (this == o) return true;
>   ```
>
>   这条语句只是一个优化.
>    
>   实际上,这是一种经常采用的形式. 因为计算这个等式要比一个一个地比较类中的域所付出的代价小得多
>
> 3. 检测 `o` 是否为null,如果为null,返回false,这项检测是很重要的
>
>   ```java
>   if (o == null) return false;
>   ```
>
> 4. 比较`this`与`o`是否属于同一个类. 如果`equals`的语义在每个子类中有所改变,就使用`getClass`检测
>
>   ```java
>   if (getClass() != o.getClass()) return false;
>   ``` 
> 
>   如果所有的子类都拥有统一的语义,就是用`instanceof`检测
>
>   ```java
>   if (o instanceof ClassName) return false;
>   ```
>   
> 5. 将`o`转换为相应的类类型变量
>
>   ```java
>   ClassName other = (ClassName) o;
>   ```
> 
> 6. 现在开始对所有需要比较的域进行比较了,使用`==`比较基本类型域,使用`equals`比较对象域
>
>   如果所有的域都匹配,就返回`true`;否者返回`false`
>
>   ```java
>   return field1 = other.field1 && Objects.equals(field2,other.field2);
>   ```
>
>   如果在子类中定义了`equals`,就要在其中包含调用`super.equals(other)`

> **对于数值类型域,可以使用静态的`Arrays.equals`方法检测相应的数组元素是否相等**

### 5.2.3 - hashCode

**散列码(hash code)是由对象导出的一个整数值,散列码是没有规律的**

如果`x`和`y`是两个不同的对象,`x.hashCode()`与`y.hashCode()`基本不会相同

**由于`hashCode`方法定义在`Object`类中,因此每个对象都有一个默认的散列值,其值为对象的储存地址**

举个例子:

```java
String s = "Ok";
StringBuilder sb = new StringBuilder(s);
String t = new String("Ok");
StringBuilder tb = new StringBuilder(t);

//结果
// s = 2556 ; sb = 1146848448
// t = 2556 ; tb = 1638215613
```

请注意,字符串变量`s`,与`t`拥有相同的散列码,这是因为字符串的散列码是由内容导出的,而`String`类中重新定义了`hashCode`

`String`类中定义的`hashCode`方法:

```java
public int hashCode() {
    int h = hash;
    if (h == 0 && value.length > 0) {
        char val[] = value;
        for (int i = 0; i < value.length; i++) {
            h = 31 * h + val[i];
        }
        hash = h;
    }
    return h;
}
```

然而字符串缓冲类型变量`sb`与`tb`却有着不同的散列码,这是因为在`StringBuilder`类中没有定义`hashCode`方法,所以它的散列码是有Object类的默认`hashCode`方法导出的对象储存地址

如果重新定义`equals`方法,就必须重新定义`hashCode`,以便用户可以将对象插入散列表中(例如HashMap等)

`hashCode`方法应该返回一个整型数值(也可以是负数),并合理的组合实例域的散列码以便能够让各个不同的对象产生的散列码更加均匀

例如,我们在`Employee`类中定义`hashCode`方法

```java
public int hashCode(){
    return 7 * name.hashCode()
        + 11 * new Double(salary).hashCode()
        + 13 * hireDay.hashCode();     
}
```

不过,还可以做得更好. 首先,最后使用null安全的`Objects.hashCode`如果其参数为null,这个方法会返回0,否则返回对参数调用hashCode的结果

另外,使用静态方法`Double.hashCode`来避免创建Double对象

```java
public int hashCode(){
    return 7 * name.hashCode()
        + 11 * Double.hashCode(salary)
        + 13 * hireDay.hashCode();  
}
````

还有更好的做法,需要组合多个散列值时,可以调用`Objects.hash`并提供多个参数.

这个方法会对各个参数调用`Objects.hashCode`,并组合这些散列值.

```java
public int hashCode(){
    return Objects.hash(name,salary,hireDay);
}
```

`equals`与`hashCode`的定义必须一致: 如果`x.equals(y)`返回true,那么`x.hashCode()`就必须与`y.hashCode`具有相同的值

假如,用定义的`Employee.equals`比较雇员的ID,那么`hashCode`方法就需要散列ID,而不是雇员的姓名或存储地址等信息

> 如果存在数组类型的域,那么可以使用静态的`Arrays.hashCode`方法计算一个散列码,这个散列码由数组元素的散列码组成

### 5.2.4 - toString

在`Object`类中还有一个重要的方法,就是`toString`方法,它用于返回表示对象值的字符串.

例如`Point`类的`toString`方法将返回下面这样的字符串

```java
System.out.print(new Point());
//java.awt.Point[x=0,y=0]
```

绝大多数(但不是全部)的`toString`方法都遵循这样的格式: 类的名字,随后是一对括号括起来的域值

下面是`Employee`类中的`toString`方法实现

```java
public String toString() {
    return "Employee[" + "name='" + name +
            ", salary=" + salary +
            ", hireDay=" + hireDay +
            ']';
}
```

实际上,还可以设计的更好一些,最好通过调用`getClass().getName()`获得类名的字符串,而不要将类名硬加到`toString`方法中

```java
public String toString() {
    return getClass().getName() + "[" + "name='" + name +
            ", salary=" + salary +
            ", hireDay=" + hireDay +
            ']';
}
```

`toString`方法也可以供子类调用.

当然,设计子类也应该定义自己的`toString`方法,并将子类域的描述添加进去

如果超类使用了`getClass().getName()`,那么子类只要调用`super.toString()`就可以了,例如下面是`Manager`类中的`toString`方法

```java
public class Manager extends Employee {
    
    public String toString(){
        return super.toString() + 
            "[bonus= " + bonus + 
            "]";
    }
}
```

现在,`Manager`对象将打印输出如下所示内容:

    Manager[name=...,salary=...,hireDay=...][bonus=...]

随处可见`toString`方法主要原因是: 只要对象与一个字符串通过操作符`"+"`连接起来,java编译就会自动的调用`toString`方法

> 在调用`x.toString()`的地方可以用`""+x`代替.
>
> 这条语句将一个空串与x的字符串表示相连接,这里的x就是toString(), 与toString不同的是,如果x是基本类型,这条语句照样能执行

如果x是任意一个对象,并调用

```java
System.out.println(x);
```

`println`方法就会直接的调用`x.toString()`,并打印输出得到的字符串

Object类定义了`toString`方法,用于打印输出对象所属的类名和散列码. 

```java
public String toString() {
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
}
```

例如,调用:

```java
System.out.println(System.out);
```

将输出下列内容

    java.io.PrintStream@445b84c0

之所以得到这样的结果是因为`PrintStream`类没有覆盖`Object`类的`toString`方法

> 注意:
>
> 数组继承了Object类的toString方法,数组类型将调用Object类中的toString方法打印
>
> 例如:
> 
> ```java
> int[] arrays = {1,2,3,4,5};
> String s = "" + arrays;
> // [I@135fbaa4
> ```
>
> 打印结果`[I@135fbaa4`,(前缀[I表明是一个整型数组). 修正的方式是调用静态方法`Arrays.toString`. 代码:
>
> ```java
> String s = Arrays.toString(arrays);
> ``` 
> 
> 将生成字符串`[1,2,3,4,5]`.
>
> 想要打印多维数组则需要调用`Arrays.deepToString`方法

## 5.4 - 对象包装器与自动装箱

有时,需要将int这样的基本类型转换为对象,所有的基本类型都有一个与之对应的类.

例如,Integer类对应基本类型`int`. 通常这类称为包装器(wrapper).

这些对象包装器类拥有很明显的名字: `Integer`、`Long`、`Float`、`Double`、`Byte`、`Short`、`Character`、`Void`和`Boolean`

**`Integer`、`Long`、`Float`、`Double`、`Byte`、`Short`这6个类都派生域公共的超类`Number`** 

**对象包装器类是不可变的,即一旦构造了包装器,就不允许更改包装在其中的值.同时,对象包装器还是`final`因此不能定义他们的子类**

例如:

```java
Integer total = 99;
int i = total;
```

### 自动装箱

当调用

```java
Integer total = 99;
```

将自动的变换成

```java
Integer total = Integer.valueOf(99);
```

这种变换被称为自动装箱(autoboxing)

### 自动拆箱

相反地,当将一个`Integer`对象赋给一个`int`值时,将会自动的拆箱.也就是说,编译器将下列语句:

```java
int i = total;
```

翻译成

```java
int i = total.intValue();
// Integer.intValue
```

> 注意: 
>
> 在大多数情况下,容易有一种假象,即基本类型域他们的对象包装器是一样的,只是它们的相等性不同
> 
> 大家知道, `==`运算符也可以应用于对象包装器对象,只不过检测的是对象是否指向同一个存储区域,因此下面的比较通常不会成立
>
> ```java
> Integer x = 1000;
> Integer y = 1000;
> System.out.println(x == y); //false
> ```
> 
> 然而,Java实现却有可能让它成立.如果将经常出现的值包装到同一个对象中,这种比较就有可能成立
>
> 这种不确定的结果并不是我们所希望的.解决这个问题的办法是在两个包装器对象比较时调用`equals`方法
>
> 包装器中的`equals`将自动调用包装类的基本类型的值进行对比
> 
> **自动装箱规范要求 `byte、char <= 127`,介于`-128 ~ 127`之间的`short`和`int`被包装到固定的对象中**
> 
> **例如,如果在前面的例子中将`x`和`y`初始化为100,对他们进行比较的结果一定成立. 因为被缓冲了**

## 5.5 - 参数数量可变的方法

在JDK5之前的版本中,每个Java方法都有固定数量的参数.然而,现在的版本提供了可以用可变的参数数量调用的方法

前面已经看到过这样的方法: `printf`. 例如下面的方法调用

```java
System.out.printf("%d", n);
System.out.printf("%d %s", n, "hello");
```

在上面两条语句中,尽管一个调用包含两个参数,另一个调用包含三个参数,但他们调用的都是用一个方法.

在`printf`方法是这样定义的:

```java
public PrintStream printf(String format, Object ... args) {
    return format(format, args);
}
```

这里的省略号`...`是Java代码的一部分,它表明这个方法可以接受任意数量的对象(除`format`参数之外)

实际上,`printf`方法接受两个参数,一个是格式字符串,另一个是`Object[]`数组,其中保存着所有的参数 (如果调用者提供的是整型数组或者其他其他基本类型的值,自动装箱功能将他们转换成对象)

现在将扫描`format`字符串,并将第`i`个格式说明符域`arg[i]`的值匹配起来

换句话说,对于`print`的实现着来说,`Object...`参数类型与`Object[]`完全一样

编译器需要对`print`的每次调用进行转换,以便将参数绑定到数组上,并在必要的时候进行自动装箱

```java
System.out.printf("%d %s", new Object[]{new Integer(n), "hello"});
```

用户自己也可以定义可变参数的方法,并将参数指定为任意类型,甚至是基本类型. 

下面是一个简单的实例: 其功能为计算若干个数值的最大值

```java
public static double max (double... values) {
    double largest = Double.NEGATIVE_INFINITY;
    for(double v : values) {
        if (v > largest) {
            largest = v;
        }
    }
}
```

可以像下面这样调用这个方法:

```java
double n = max(3.1, 40.4, -5);
```

编译器将`new double[] {3.1, 40.4, -5}`传递给`max`方法

> 允许将一个数组传递给可变参数方法的最后一个参数. 例如:
> 
> ```java
> System.out.printf("%d %s", new Object[]{new Integer(1), "hello"});
> ```
> 
> 因此,可以将依据存在且最后一个参数是数组的方法重新定义为可变参数的方法,而不会破坏任何已经存在的代码

## 5.6 - 枚举

下面是定义一个枚举类型的经典例子:

```java
public enum Size {
    SMALL, MEDIUM, LARGE, EXTRA_LARGE;
}
```

实际上,这个声明定义的类型是一个类,它刚好有4个实例,在此尽量不要构造新对象

因此,在比较两个枚举类型的值时,永远不需要调用`equals`,而直接使用`==`就可以了

如果需要的话,可以在枚举类型中添加一些构造器、方法和域. 当然构造器只是在构造枚举常量的时候被调用. 例如:

```java
public enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");
    
    private String abbreviation;
    
    public Size (String abbreviation){
        this.abbreviation = abbreviation;
    }
    
    public String getAbbreviation(){
        return this.abbreviation;
    }
}
```

**所有的枚举类型都是`Enum`类的子类,他们继承了这个类的许多方法.**

其中最有用的一个是`toString`,这个方法能够返回枚举常量名. 例如: `Size.SMALL.toString()`将返回字符串"SMALL"

`toString`的逆方法是静态方法`valueOf`. 例如,语句

```java
Size s = Enum.valueOf(Size.class, "SMALL");
```

将s设置成`Size.SMALL`.

每个枚举类型都有一个静态的`values`方法,它将返回一个包含全部枚举值的数组. 例如,如下调用

```java
Size[] values = Size.values();
```

返回包含元素 `Size.SMALL`, `Size.MEDIUM`, `Size.LARGE`, `Size.EXTRA_LARGE` 的数组

`ordinal`方法返回`enum`声明中枚举常量的位置,位置从0开始计数.例如: `Size.MEDIUM.ordinal()` 返回1

> 如同`Class`类一样,鉴于简化的考虑,`Enum`类省略了一个类型参数
> 
> 例如,实际上,应该将枚举类型`Size`拓展为`Enum<Size>`

## 5.7 - 反射
## 5.8 - 继承的设计技巧

1. 
    将公共操作和域放在超类
    
    这就是为什么将姓名域放在`Person`类中,而没有将它放在`Employee`和`Student`类中的原因

2. 
    不要使用受保护的域
    
    有些人认为,将大多数的实例域定义为`protected`是一个不错的主意,只有这样,子类才能够在需要的时候直接访问它们
    
    然而,`protected`机制并不能够带来更好的保护,其原因主要有两点
        
      1. 子类集合是无限制的,任何一个人都能够由某个类派生一个子类，并编写代码以直接访问`protected`的实例域,从而破坏了封装性。

      2. 在Java程序设计语言中,在同一个包中的所有类都可以访问`protected`域,而不管它是否为这个类的子类
        
         不过,`protected`方法对于指示那些不提供一般用途而应在子类中重新定义的方法很有用

3. 
    使用继承实现 "is-a" 关系

    使用继承很容易达到节省代码的目的,但有时候也被人们滥用了.例如,假设需要定义一个钟点工类.
    
    钟点工的信息包含姓名和雇佣日期,但是没有薪水.他们按小时计薪,并且不会因为拖延时间而获得加薪. 
    
    这似乎在诱导人们由`Employee`派生出子类`Contractor`, 然后再增加一个`hourlyWage`域

    ```java
    public class Contractor extends Employee {
       private double hourlyWage; 
    }
    ```
    
    这并不是一个好主意. 因为这样一来,每个钟点工对象中都包含了薪水和计时工资这两个域.
    
    在实现打印支票或税单方法的时候,会带来无尽的麻烦,并且与不采用继承,会多写很多很多代码
    
    钟点工与雇员之间不属于"is-a" 关系.钟点工不是特殊的雇员

4. 
    除非所有继承的方法都有意义,否则不要使用继承
    
    假设想编写一个`Holiday`(假期) 类. 毫无疑问,每个假日也是一日,并且一日可以用`GregorianCalendar`类的实例表示,因此可以使用继承
    
    ```java
    class Holiday extends CregorianCalendar {...}
    ```
    
    很遗憾,在继承的操作中,假日集不是封闭的. 在`GregorianCalendar`中有一个公有方法`add`,可以将假日转换成非假日:
    
    ```java
    Holiday christmas;
    christmas.add(Calendar.DAY_OF_MONTH , 12);
    ```

    因此,继承对于这个例子来说并不太适宜
    
    需要指出,如果扩展`LocalDate`就不会出现这个问题.由于这个类是不可变的,所以没有任何方法会把假日变成非假日

5. 
    在覆盖方法时,不要改变预期的行为

    置换原则不仅应用于语法,而且也可以应用于行为,这似乎更加重要. 在覆盖一个方法的时候,不应该毫无原由地改变行为的内涵. 
    
    就这一点而言,编译器不会提供任何帮助,即编译器不会检查重新定义的方法是否有意义. 
    
    例如,可以重定义`Holiday`类中`add`方法"修正" 原方法的问题,或什么也不做,或抛出一个异常,或继续到下一个假日

    然而这些都违反了置换原则. 语句序列
    
    ```java
    int dl = x.get(Calendar.DAY_OF_MONTH);
    x.add(Calendar.DAY_OF_MONTH , 1);
    int d2 = x.get(Calendar.DAY_OF_HONTH);
    System.out.println(d2 - dl);
    ```
    
    不管`x`属于`GregorianCalendar`类,还是属于`Holiday`类,执行上述语句后都应该得到预期的行为
    
    当然,这样可能会引起某些争议. 人们可能就预期行为的含义争论不休.
    
    例如,有些人争论说,置换原则要求`Manager.equals`不处理`bonus`域,因为`Employee.equals`没有它
    
    实际上,凭空讨论这些问题毫无意义.关键在于,在覆盖子类中的方法时,不要偏离最初的设计想法

6. 
    使用多态,而非类型信息

    无论什么时候，对于下面这种形式的代码
    
    ```
    if (x is of type1)
        action1(x)
    else if (x is of type2)
        action2(x)
    ```

    都应该考虑使用多态性

    `action1`与`action2`表示的是相同的概念吗? 如果是相同的概念,就应该为这个概念定义一个方法,
    
    并将其放置在两个类的超类或接口中,然后,就可以调用
    
    ```
    x.action();
    ```
    
    以便使用多态性提供的动态分派机制执行相应的动作

    使用多态方法或接口编写的代码比使用对多种类型进行检测的代码更加易于维护和扩展

7. 
    不要过多地使用反射

    反射机制使得人们可以通过在运行时查看域和方法,让人们编写出更具有通用性的程序

    这种功能对于编写系统程序来说极其实用,但是通常不适于编写应用程序. 
    
    反射是很脆弱的,即编译器很难帮助人们发现程序中的错误, 因此只有在运行时才发现错误并导致异常
