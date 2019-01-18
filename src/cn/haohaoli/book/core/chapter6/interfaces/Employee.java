package cn.haohaoli.book.core.chapter6.interfaces;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 雇员类
 */
public class Employee implements Cloneable {

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

    public void setHireDay(int year,int month,int day) {
        Date time = new GregorianCalendar(year, month - 1, day).getTime();
        hireDay.setTime(time.getTime());
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 10;
        salary += raise;
    }


    /**
     *  单独使用 Employee clone = (Employee) super.clone();
     *      可能对象中存在对其他类的引用
     * @return
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
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
