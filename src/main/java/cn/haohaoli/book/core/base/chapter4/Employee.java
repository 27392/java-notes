package cn.haohaoli.book.core.base.chapter4;

import java.time.LocalDate;

/**
 * Employee类
 * @author liWenHao
 * @date 2019/1/23 02:47
 */
public class Employee {

    /**
     * TODO 静态域 static
     *  如果将域定义为 static, 每个类中只有一个这样的域。
     *  而每一个对象对于所有的实例域,却都有自己的一份拷贝
     */
    private static int nextId = 1;

    private int id;

    /**
     * TODO final 实例域
     *  可以将实例域定义为 final 构建对象时必须初始化这样的域。
     *  也就是说,必须确保在每一个构造器执行之后,这个域的值被设置，
     *  并且在后面的操作中， 不能够再对它进行修改
     */
    private final String name;

    private Double salary;

    private LocalDate hireDay;

    /**
     * TODO 构造器
     *  构造器与类同名.在构造 Employee 类的对象时， 构造器会运行， 以便将实例域 初始化为所希望的状态。
     *  构造器与其他的方法有一个重要的不同。 构造器总是伴随着 new 操作符的执行被调用，
     *  而不能对一个已经存在的对象调用构造器来达到重新设置实例域的目的
     *    注意:
     *      •构造器与类同名
     *      •每个类可以有一个以上的构造器
     *      •构造器可以有 0 个、1 个或多个参数
     *      •构造器没有返回值
     *      •构造器总是伴随着 new 操作一起调用
     *  如果在编写一个类时没有编写构造器， 那么系统就会提供一个无参数构造器。 这个构造器将所有的实例域设置为默认值。
     *    实例域中的数值型数据设置为 0、
     *    布尔型数据设置为 false、
     *    所有对象变量将设置为 null
     *  如果类中提供了至少一个构造器， 但是没有提供无参数的构造器，则在构造对象时如果 没有提供参数就会被视为不合法
     */

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, Double salary) {
        this(name);
        this.salary = salary;
    }

    public Employee(String name, Double salary, int year, int month, int day) {
        this(name);
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    /**
     * TODO 域访问器
     *  getName 方法、 getSalary 方法和 getHireDay 方法。这些都是典型的访问器方法。由于它们只返回实例域值， 因此又称为域访问器
     *  需要获得或设置实例域的值。
     *    应该提供下面三项内容:
     *      •一 私有的数据域;
     *      •一 公有的域访问器方法;
     *      •一个公有的域更改器方法
     */
    public String getName() {
        return name;
    }

    /**
     * TODO 静态方法
     *  静态方法是一种不能向对象实施操作的方法
     *  可以认为静态方法是没有this参数的方法（在一个非静态方法中,this参数表示这个方法的隐式参数）
     */
    public static int getNextId(){
        return nextId;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = nextId;
        nextId++;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void setHireDay(LocalDate hireDay) {
        this.hireDay = hireDay;
    }

    public void raiseSalary(double byPercent) {
        double v = salary * byPercent / 100;
        salary += v;
    }

}

