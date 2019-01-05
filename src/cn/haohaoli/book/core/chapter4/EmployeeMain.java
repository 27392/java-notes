package cn.haohaoli.book.core.chapter4;

public class EmployeeMain {

    public static void main(String[] args) {
        final Employee employee = new Employee("1",1d);
        employee.setId();
        System.out.println(employee);
        employee.setSalary(222.0d);
        Employee employee1 = new Employee("2",1d);
        employee1.setId();
        System.out.println(employee1);
        Employee employee2 = new Employee("3",1d);
        employee2.setId();
        System.out.println(employee2);
    }
}
