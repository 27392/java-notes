package cn.haohaoli.book.core.chapter6.interfaces;

import cn.haohaoli.book.core.chapter5.inheritance.Employee;

import java.util.Arrays;

public class EmployeeSortTest {

    public static void main(String[] args) {
        /**
         * 雇员类实现Comparable 接口 定义比较方法
         */
        Employee[] staff = new Employee[3];
        staff[0] = (new Employee("c", 75000, 1987, 12, 15));
        staff[1] = (new Employee("h", 50000, 1989, 10, 1));
        staff[2] = (new Employee("t", 40000, 1990, 3, 15));
        Arrays.sort(staff);
        System.out.println(Arrays.toString(staff));
    }
}
