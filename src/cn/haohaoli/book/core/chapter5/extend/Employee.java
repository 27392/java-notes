package cn.haohaoli.book.core.chapter5.extend;

import java.time.LocalDate;
import java.util.Objects;

/**
 * 雇员类
 */
public class Employee extends Person implements Comparable<Employee> {

    private double salary;

    protected LocalDate hireDay;

    public Employee() {
    }

    public Employee(String name, double salary, int year, int month, int day) {
        super(name);
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    public Employee getBuddy() {
        return null;
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

    @Override
    public String getDescription() {
        return "职员";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 &&
                Objects.equals(hireDay, employee.hireDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary, hireDay);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare(salary, o.salary);
    }
}
