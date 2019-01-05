package cn.haohaoli.book.core.chapter5.reflection;

import cn.haohaoli.book.core.chapter5.extend.Employee;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodTableTest {
    public static void main(String[] args) throws Exception {
        Employee employee1 = new Employee("xx", 50000, 198, 1, 1);

        Method getName = Employee.class.getMethod("getName");
        Object invoke = getName.invoke(employee1);
        System.out.println(invoke);

        /**
         * 方法签名，参数
         */
        Employee.class.getMethod("raiseSalary", double.class);

        Method square = MethodTableTest.class.getMethod("square", double.class);
        Method sqrt = Math.class.getMethod("sqrt", double.class);

        printTable(1, 10, 10, square);
        printTable(1, 10, 10, sqrt);
    }

    public static double square (double x) {
        return x * x;
    }

    public static void printTable(double from, double to, int n, Method f) throws InvocationTargetException, IllegalAccessException {
        System.out.println(f);
        double dx = (to - from) / (n - 1);
        for (double x = from; x <= to ; x+= dx) {
            Object invoke = f.invoke(null, x);
            System.out.printf("%10.4f | %10.4f%n", x, invoke);
        }
    }

}
