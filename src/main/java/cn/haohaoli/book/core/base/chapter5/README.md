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

尽管`Employee`类是一个超类,但并不是因为它优于子类或者拥有比子类更多的功能

实际上恰恰相反,子类比超类拥有的功能更加丰富
 
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
    };
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
> 在子类中定义`equals`方法时,首先调用超类的equals, 如果超类中的域都相等,就需要比较子类的中实例域
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

## 5.4 - 对象包装器与自动装箱
## 5.5 - 参数数量可变的方法
## 5.6 - 枚举
## 5.7 - 反射
## 5.8 - 继承的设计技巧
