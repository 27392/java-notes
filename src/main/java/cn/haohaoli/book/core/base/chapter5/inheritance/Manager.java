package cn.haohaoli.book.core.base.chapter5.inheritance;

/**
 * TODO 定义子类（关键字 extends）
 *  关键字 extends 表明正在构造的新类派生于一个已存在的类。 已存在的类称为超类 ( superclass)、 基类(base class) 或父类(parent class);
 *  新类称为子类(subclass、) 派生类 (derivedclass) 或孩子类(childclass)。
 *  超类和子类是Java程序员最常用的两个术语，而了解 其他语言的程序员可能更加偏爱使用父类和孩子类， 这些都是继承时使用的术语
 * 经理类
 * @author LiWenHao
 * @date 2019-01-23 20:34
 */
public class Manager extends Employee {

    private double bonus;

    /**
     * TODO 子类构造器
     *  由于 Manager 类的构造器不能访问 Employee 类的私有域， 所以必须利用 Employee 类 的构造器对这部分私有域进行初始化，
     *  我们可以通过 super 实现对超类构造器的调用。 使用super 调用构造器的语句必须是子类构造器的第一条语句。
     *  如果子类的构造器没有显式地调用超类的构造器， 则将自动地调用超类默认(没有参数 )
     *  的构造器。 如果超类没有不带参数的构造器， 并且在子类的构造器中又没有显式地调用超类 的其他构造器’ 则 Java 编译器将报告错误。
     * TODO super
     *  关键字 this 有两个用途 一是引用隐式参数 二是调用该类其他的构造器，同样。
     *  super 关键字也有两个用途: 一是调用超类的方法， 二是调用超类的构造器。 在调用构造器的时候，
     *  这两个关键字的使用方式很相似。调用构造器的语句只能作为另一个构造器的第一条语句出现。构造参数既可以传递给本类(this) 的其他构造器，也可以传递给超类(super) 的构造器
     */
    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    /**
     * TODO 覆盖 ( override )
     *  然而， 超类中的有些方法对子类 Manager 并不一定适用。 具体来说， Manager 类中的 getSalary 方法应该返回薪水和奖金的总和。
     *  为此， 需要提供一个新的方法来覆盖 ( override ) 超类中的这个方法:
     */
    @Override
    public double getSalary() {
        /**
         * salary       //无法访问超类的私有域    错误！
         * getSalary()  //递归 错误！
         */
        //调用超类方法
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }
}
