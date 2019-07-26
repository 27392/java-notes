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

然而,尽管在`Manager`类中没有显式的定义`getName`和`getHireDay`等方法,但属于Manager类的对象却可以使用他们, 这是因为`Manager`类自动的继承了超类`Employee`中的这些方法

同样,从超类中还继承了`name`、`salary`、`hireDay`这3个域. 

这样一来,每个Manager类对象就包含了4个域 : `name`、`salary`、`hireDay`和`bonus`

**在通过拓展超类定义子类的时候,一个将通用的方法放在超类中,而将具有特殊用途的方法放在子类中.**

### 覆盖方法

## Object类
## 对象包装器与自动装箱
## 参数数量可变的方法
## 枚举
## 反射
## 继承的设计技巧
