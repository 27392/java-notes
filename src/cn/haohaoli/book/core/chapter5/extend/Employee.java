package cn.haohaoli.book.core.chapter5.extend;

import java.time.LocalDate;
import java.util.Objects;

/**
 * 雇员类
 * @author LiWenHao
 * @date 2019-01-23 20:46
 */
public class Employee {

    private String name;

    private double salary;

    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void setHireDay(LocalDate hireDay) {
        this.hireDay = hireDay;
    }

    public void raiseSalary(double b) {
        double v = salary * b / 100;
        salary += v;
    }

    /**
     * TODO Java 语言规范要求 equals 方法具有下面的特性:
     *  1 ) 自反性: 对于任何非空引用 x, x.equals(x) 应该返回 true
     *  2 ) 对称性: 对于任何引用 x 和 y, 当且仅当 y.equals(x) 返回 true, x.equals(y) 也应该返 回 true。
     *  3 ) 传递性: 对于任何引用 x、 y 和 z, 如果 x.equals(y) 返回 true， y.equals(z) 返回 true, x.equals(z) 也应该返回 true。
     *  4 ) 一致性: 如果 x 和 y 引用的对象没有发生变化， 反复调用 x.equals(y) 应该返回同样 的结果。
     *  5 ) 对于任意非空引用 x, x.equals(null) 应该返回 false,
     *
     * TODO 编写一个完美的 equals 方法的建议:
     *  1 ) 显式参数命名为 otherObject, 稍后需要将它转换成另一个叫做 other 的变量。
     *  2 ) 检测 this 与 otherObject 是否引用同一个对象:
     *      if (this = otherObject) return true;
     *      这条语句只是一个优化。 实际上， 这是一种经常采用的形式。 因为计算这个等式要比一 个一个地比较类中的域所付出的代价小得多。
     *  3 ) 检测 otherObject 是否为 null , 如 果 为 null , 返 回 false。 这项检测是很必要的。
     *      if (otherObject = null) return false;
     *  4 ) 比较 this 与 otherObject 是否属于同一个类。如果 equals 的语义在每个子类中有所改 变， 就使用 getClass 检测:
     *      if (getClass() != otherObject.getCIassO) return false;
     *      如果所有的子类都拥有统一的语义， 就使用 instanceof 检测:
     *      if (!(otherObject instanceof ClassName)) return false;
     *  5 ) 将 otherObject 转换为相应的类类型变量: ClassName other = (ClassName) otherObject
     *  6 )现在开始对所有需要比较的域进行比较了。使用= 比较基本类型域，使用equals比 较对象域。如果所有的域都匹配，就返回true; 否则返回false。
     *      return fieldl == other.field
     *      && Objects.equa1s(fie1d2, other.field2)
     *      && ...
     *  如果在子类中重新定义equals, 就要在其中包含调用super.equals(other)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 &&
                Objects.equals(name, employee.name) &&
                Objects.equals(hireDay, employee.hireDay);
    }

    /**
     * TODO 散列码(hashcode)
     *  是由对象导出的一个整型值。散列码是没有规律的。如果x和y是 两个不同的对象， x.hashCode( ) 与 y.hashCode( ) 基本上不会相同。
     *  需要组合多个散列值时， 可以调用 Objects.hash 并提供多个参数。 这个方法会对各个参数调用 Objects.hashCode，
     *  Equals 与 hashCode 的定义必须一致: 如果 x.equals(y) 返回 true, 那么 x.hashCode( ) 就必 须与 y.hashCode( ) 具有相同的值
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, salary, hireDay);
    }

    /**
     * TODO toString
     *   toString方法， 它用于返回表示对象值的字符串
     *   随处可见 toString 方法的主要原因是: 只要对象与一个字符串通过操作符 "+" 连接起来， Java 编译就会自动地调用 toString 方法，
     *
     * @return
     */
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
