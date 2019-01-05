package cn.haohaoli.book.core.chapter5.interfaces;

import cn.haohaoli.book.core.chapter5.extend.Employee;

import java.nio.file.Paths;
import java.util.Arrays;

public class EmployeeSortTest {

    public static void main(String[] args) {
        Employee[] staff = new Employee[3];
        staff[0] = (new Employee("c", 75000, 1987, 12, 15));
        staff[1] = (new Employee("h", 50000, 1989, 10, 1));
        staff[2] = (new Employee("t", 40000, 1990, 3, 15));
        Arrays.sort(staff);
        System.out.println(Arrays.toString(staff));

        Paths.get("jdk1.8.0", "jre", "bin");
    }
}
