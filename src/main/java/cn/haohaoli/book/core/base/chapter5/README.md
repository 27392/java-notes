# Java 核心技术 卷I - 第五章

## 类,超类和子类

假设你在某个公司工作,这个公司中的经理的待遇与普通雇员的待遇存在一些差异

不过他们之间也存在着很多相同的地方,例如,他们都领薪水

只是普通雇员在完成本职任务之后仅领取薪水,而经理在完成了预期的业绩之后还能得到奖金.

这种情况就需要继承,这是因为需要为经理定义一个新类`Manager`,以便增加一些新的功能.

但可以重用`Emmployee`类中以编写的部分代码,将其中的所有域保留下来.

从理论上讲,在`Manager`与`Employee`之间存在着明显的**is-a**(是)关系,每一个经理都是一名雇员: `is-a`关系是继承的一个明显特征

### 定义子类

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

### 覆盖方法

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
> 有些人认为super与this引用是类似的概念,实际上,这样比较并不太恰当.
>
> 这是因为super不是一个对象的引用,不能讲super赋给另一个对象变量,它只是一个指示编译器调用超类方法的特殊关键字

### 子类构造器

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


## Object类
## 对象包装器与自动装箱
## 参数数量可变的方法
## 枚举
## 反射
## 继承的设计技巧
