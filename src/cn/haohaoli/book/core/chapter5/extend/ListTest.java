package cn.haohaoli.book.core.chapter5.extend;

import org.omg.CORBA.IntHolder;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        /**
         * size 0
         * capacity 100 容量
         */
        List<Employee> staff = new ArrayList<>(100);
        staff.add(new Employee("c", 75000, 1987, 12, 15));
        staff.add(new Employee("h", 50000, 1989, 10, 1));
        staff.add(new Employee("t", 40000, 1990, 3, 15));
        staff.set(2, new Employee("x", 75000, 1987, 12, 15));
        System.out.println(1);

        for (Employee e : staff) {
            System.out.println(e);
        }

        /**
         * 因为Java里面对处在在-128~127之间的Integer值，用的是原生数据类型int，会在内存里供重用，
         * 也就是说这之间的Integer值进行==比较时只是进行int原生数据类型的数值比较，
         * 而超出-128~127的范围，进行==比较时是进行地址及数值比较。
         */
        Integer a = 100;
        Integer b = 100;
        Integer c = 128;
        Integer d = 128;
        /**
         * 第三段旨在说明：==和equals的区别，==是进行地址及值比较，无法对==操作符进行重载，
         * 而对于equals方法，
         * Integer里面的equals方法重写了Object的equals方法，查看Integer源码可以看出equals方法进行的是数值比较。
         *      return value == ((Integer)obj).intValue();
         */
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(c == d);
        System.out.println(c.equals(d));

        Integer i = 128;
        change(i);
        IntHolder intHolder = new IntHolder(i);
        change(intHolder);
        System.out.println(i);
        System.out.println(intHolder.value);

    }

    public static void change (Integer i) {
        i *= 3;
    }

    public static void change (IntHolder i) {
        i.value  = i.value *3;
    }

}
