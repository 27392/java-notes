package cn.haohaoli.book.core.base.chapter4;

import java.time.LocalDate;

/**
 * @author liWenHao
 * @date 2019/1/23 05:05
 */
public class EmployeeTest {

    public static void main(String[] args) {
        //在这个程序中， 构造了一个 Employee 数组， 并填人了三个雇员对象:
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("张三",10000d, 1987, 10, 24);
        staff[1] = new Employee("王五",10300d, 1983, 6, 30);
        staff[2] = new Employee("赵四",4050d, 1981, 4, 4);
        for (Employee employee : staff) {
            //利用 Employee 类的 raiseSalary 方法将每个雇员的薪水提高 2%
            employee.raiseSalary(2d);
        }

        for (Employee employee : staff) {
            System.out.println("name=" + employee.getName() +
                    ",salary=" + employee.getSalary() +
                    ",hireDay=" + employee.getHireDay());
        }
    }
}

class Employee {

    //实例域定义为 final 构建对象时必须初始化这样的域
    private final String name;

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

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double v = this.salary * byPercent / 100;
        this.salary += v;
    }

}

