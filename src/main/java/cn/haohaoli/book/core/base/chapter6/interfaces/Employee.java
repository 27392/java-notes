package cn.haohaoli.book.core.base.chapter6.interfaces;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * TODO 实现一个接口, 通常需要下面两个步骤 关键字(implement)
 *  1） 将类声明为实现给定的接口
 *  2） 对接口中的所有方法进行定义
 * 雇员类
 * @author liWenHao
 * @date 2019/1/31 00:15
 */
public class Employee implements Comparable<Employee>, Cloneable {

    private String name;
    private double salary;
    private Date hireDay;

    public Employee() {
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        hireDay = new Date();
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

    public Date getHireDay() {
        return hireDay;
    }

    public void setHireDay(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month -1, day);
        hireDay = calendar.getTime();
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 10;
        salary += raise;
    }

    /**
     * TODO Comparable接口中的compareTo 方法
     *  重写Comparable接口中的compareTo方法
     *  接口中还有一个没有明确说明的附加要求: 在调用 x.compareTo(y) 的时候，
     *  这个 compareTo 方法必须确实比较两个对象的内容， 并返回比较的结果。
     *      当x小于y时，返回-1;
     *      当x等于y时，返回0; 否则返回一个正数。
     */
    @Override
    public int compareTo(Employee o) {
        /**
         * 将薪水进行比对 可以直接使用Double.compare静态方法
         * 也可以直接写 (其实Double.compare方法中实现的就是下面这总 具体可以看源码)
         *  salary == o.salary ? 0 : (salary > o.salary ? 1 : -1);
         */
        return Double.compare(salary, o.salary);
    }

    /**
     * TODO 对象克隆
     *  如果单独使用 Employee clone = (Employee) super.clone();
     *  默认的克隆为 `浅拷贝` 并没有克隆对象中引用的其他对象。可能对象中存在对其他类的引用
     *   浅拷贝：
     *    如果原对象和浅克隆对象共享的子对象是不可变的，
     *    那么这种共享就是安全的。如果子对象属于一个不可变的类， 如String,
     *    就是这种 情况。 或者在对象的生命期中，子对象一直包含不变的常量， 没有更改器方法会改变它，
     *    也没有方法会生成它的引用， 这种情况下同样是安全的。
     *    通常子对象都是可变的， 必须重新定义 clone 方法来建立一个深拷贝， 同时克隆所有子对象
     * @throws CloneNotSupportedException
     */
    @Override
    protected Employee clone() throws CloneNotSupportedException {
        //深拷贝例子
        Employee clone = (Employee) super.clone();
        clone.hireDay = (Date) hireDay.clone();
        return clone;
    }

    @Override
    public String toString() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + f.format(hireDay) +
                '}';
    }
}
