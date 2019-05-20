package cn.haohaoli.book.core.base.chapter6.interfaces;

import java.util.Arrays;

/**
 * TODO Comparable 对一个对象数组进行排序 前提是实现了Comparable接口
 * @author liWenHao
 * @date 2019/1/30 22:05
 */
public class ComparableTest {

    //Employee实现了Comparable接口重写接口中的compareTo方法 对薪水进行对比排序
    public static void main(String[] args) {
        Employee[] staff = new Employee[3];
        staff[0] = (new Employee("小明", 75000));
        staff[1] = (new Employee("老白", 40000));
        staff[2] = (new Employee("红红", 50000));
        Arrays.sort(staff);
        System.out.println(Arrays.toString(staff));
    }
}
