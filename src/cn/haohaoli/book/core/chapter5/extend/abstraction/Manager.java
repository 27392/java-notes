package cn.haohaoli.book.core.chapter5.extend.abstraction;

import cn.haohaoli.book.core.chapter5.extend.abstraction.Employee;

import java.time.LocalDate;

/**
 * 经理类
 * @author LiWenHao
 * @date 2019-01-23 20:34
 */
public class Manager extends Employee {

    private double bonus;

    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        //子类可以访问父类的hireDay域
        hireDay = LocalDate.now();
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
}
