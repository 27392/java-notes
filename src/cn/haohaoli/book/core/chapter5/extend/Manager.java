package cn.haohaoli.book.core.chapter5.extend;

import java.util.Objects;

/**
 * 经理类
 */
public class Manager extends Employee implements Comparable<Employee> {

    private double bonus;

    public Manager(String name, double salary, int year, int month, int day) {
        /**
         * 调用超类Employee构造器
         * 调用超类构造器必须在第一句,如果子类没有显式的调用超类的构造器会默认调用超类的无参构造器
         */
        super(name, salary, year, month, day);
    }

    @Override
    public Manager getBuddy() {
        return null;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        if (!super.equals(o)) return false;
        return Double.compare(manager.bonus, bonus) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bonus);
    }

    @Override
    public int compareTo(Employee o) {
        if (getClass() != o.getClass()) {
            throw new ClassCastException();
        }
        return super.compareTo(o);
    }
}
