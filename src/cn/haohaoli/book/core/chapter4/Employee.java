package cn.haohaoli.book.core.chapter4;

import java.util.Random;

public class Employee {

    private static int nextId = 1;

    private int id;

    private String name;

    private Double salary;

    static {
        nextId = new Random().nextInt(1000);
        System.out.println("执行静态代码块1");
    }

    static {
        nextId = (int) (Math.random() * 1000);
        System.out.println("执行静态代码块2");
    }


    {
        nextId++;
        System.out.println("执行代码块1");
    }

    {
        nextId++;
        System.out.println("执行代码块2");
    }





    public Employee() {
    }

    public Employee(String name) {
        this(name, null);
    }

    public Employee(String name, Double salary) {
        System.out.println("执行构造器");
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Employee.nextId = nextId;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = nextId;
        nextId++;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(1);
    }

}
class test {
    public static void main(String[] args) {
        System.out.println(2);
    }
}
