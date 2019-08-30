package cn.haohaoli.book.core.base.chapter6.interfaces;

import java.util.Arrays;

/**
 * TODO Cloneable 接口
 *  Cloneable接口是Java提供的一组标记接口 (tagging interface)之一
 * @see Cloneable
 * @author liWenHao
 * @date 2019/1/5 15:56
 */
public class ObjectCloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Employee employee = new Employee("c", 75000);
        employee.setHireDay(2019, 12, 1);
        System.out.println("原对象：employee =" + employee);
        //克隆对象
        Employee copyObj = employee.clone();
        employee.setName("x");
        employee.raiseSalary(100d);
        employee.setHireDay(2018, 1, 1);
        System.out.println("改变后对象：employee =" + employee);
        System.out.println("克隆对象：copyObj =" + copyObj);

        int[] array = {1, 2, 3, 4, 5};
        int[] cloneArrays = array.clone();
        System.out.println("原数组 ： " + Arrays.toString(array));
        cloneArrays[0] = 5;
        System.out.println("克隆数组 ： " + Arrays.toString(cloneArrays));
    }
}
