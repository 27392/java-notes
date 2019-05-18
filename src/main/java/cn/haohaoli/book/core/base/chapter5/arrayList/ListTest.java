package cn.haohaoli.book.core.base.chapter5.arrayList;

import cn.haohaoli.book.core.base.chapter5.inheritance.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型数组列表
 * @author LiWenHao
 * @date 2019-01-24 10:27
 */
public class ListTest {

    /**
     * TODO 泛型数组列表 (ArrayList)
     *  ArrayList 是一个采用类型参数（ type parameter ) 的泛型类（ generic class)。为了指定数
     *  组列表保存的元素对象类型，需要用一对尖括号将类名括起来加在后面
     *  数组列表管理着对象引用的一个内部数组。最终， 数组的全部空间有可能被用尽。
     *  这就显现出数组列表的操作魅力： 如果调用 add 且内部数组已经满了，数组列表就将自动地创建
     *  一个更大的数组，并将所有的对象从较小的数组中拷贝到较大的数组中。
     */
    public static void main(String[] args) {

        /**
         * TODO ArrayList (部分) API:
         *     • ArrayList<E> ()
         *          构造一个空数组列表。
         *     • ArrayList<E> (int initialCapacity)
         *          用指定容量构造一个空数组列表。
         *          参数：initialCapacity 数组列表的最初容量
         *     • boolean add(E obj)
         *          在数组列表的尾端添加一个元素。 永远返回 true。
         *          参数：obj 添加的元素
         *     • int size()
         *          返回存储在数组列表中的当前元素数量。（这个值将小于或等于数组列表的容量。)
         *     • void ensureCapacity(int capacity)
         *          确保数组列表在不重新分配存储空间的情况下就能够保存给定数量的元素。
         *          参数：capacity 需要的存储容量
         *     • void trimToSize()
         *          将数组列表的存储容量削减到当前尺寸
         *     • void set(int index E obj)
         *          设置数组列表指定位置的元素值， 这个操作将覆盖这个位置的原有内容。
         *          参数： index 位置（必须介于 0 ~ size()-1 之间）
         *                 obj 新的值
         *     • E get(int index)
         *          获得指定位置的元素值。
         *          参数：index 获得的元素位置（必须介于 0 ~ size()-1 之间）
         *     • void add(int index,E obj)
         *          向后移动元素，以便插入元素。
         *          参数：index 插入位置（必须介于 0 ~ size()-1 之间）
         *                obj 新元素
         *     • E removed (int index)
         *          删除一个元素，并将后面的元素向前移动。被删除的元素由返回值返回。
         *          参数：index 被删除的元素位置（必须介于 0 ~ size()-1 之间）
         */
        /**
         * size 0
         * capacity 100 容量
         */
        List<Employee> staff = new ArrayList<>(100);
        staff.add(new Employee("c", 75000, 1987, 12, 15));
        staff.add(new Employee("h", 50000, 1989, 10, 1));
        staff.add(new Employee("t", 40000, 1990, 3, 15));
        staff.add(0, new Employee("a", 20000, 1991, 8, 15));
        staff.set(2, new Employee("x", 75000, 1987, 12, 15));
        Employee remove = staff.remove(0);
        for (Employee e : staff) {
            e.raiseSalary(5d);
            System.out.println("name:" + e.getName() + ".salary:" + e.getSalary() + ", hi reDay=" + e.getHireDay());
        }
        for (int i = 0; i < staff.size(); i++) {
            //使用 staff.get(i) 替代 staff[i] 访问元素。
            System.out.println(staff.get(i));
        }
        System.out.println("ArrayList size= " + staff.size());
    }

}
