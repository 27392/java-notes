package cn.haohaoli.book.core.chapter5.reflection;

import cn.haohaoli.book.core.chapter5.inheritance.Employee;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射方法例子
 * @author LiWenHao
 * @date 2019-01-24 11:42
 */
public class MethodTableTest {

    /**
     * TODO invoke 方法
     *  在 Method 类中有一个 invoke 方法， 它允许调用包装在当前 Method 对象中
     *  的方法。invoke 方法的签名是：
     *      Object invoke(Object obj, Object... args)
     *      第一个参数是隐式参数， 其余的对象提供了显式参数（在 Java SE 5.0 以前的版本中，必须传递一个对象数组， 如果没有显式参数就传递一个 null )。
     *      对于静态方法，第一个参数可以被忽略， 即可以将它设置为 null
     */
    public static void main(String[] args) throws Exception {
        Employee employee1 = new Employee("xx", 50000, 198, 1, 1);

        Method getName = Employee.class.getMethod("getName");
        Object invoke = getName.invoke(employee1);
        System.out.println(invoke);

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
            //静态方法 隐式参数为 null
            Object invoke = f.invoke(null, x);
            System.out.printf("%10.4f | %10.4f%n", x, invoke);
        }
    }

}
