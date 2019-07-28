package cn.haohaoli.book.core.base.chapter5.abstractClasses;

import java.time.LocalDate;

/**
 * 雇员类
 * @author LiWenHao
 * @date 2019-01-23 20:46
 */
public class Employee extends Person {

    private double salary;

    /**
     * TODO 受保护访问
     *  将hireDay声明为proteced, 而不是私有的，Manager中的方法就可以直接地访问它。
     *  不过， Manager 类中的方法只能够访问 Manager 对象中的 hireDay 域，
     *  而不能访问其他 Employee 对象中的这个域。 这种限制有助于避免滥用受保护机制， 使得子类只能获得访问受 保护域的权利。
     *    下面归纳一下 Java 用于控制可见性的 4 个访问修饰符:
     *      1 ) 仅对本类可见 private。
     *      2 ) 对所有类可见 public:
     *      3 ) 对本包和所有子类可见 protected。
     *      4 ) 对本包可见—默认(很遗憾) 不需要修饰符。
     */
    protected LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        super(name);
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    @Override
    public String getDescription() {
        return String.format("职员 工资为：%.2f", salary);
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

}
