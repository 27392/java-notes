package cn.haohaoli.book.core.chapter5.object;

import cn.haohaoli.book.core.chapter5.inheritance.Employee;

/**
 * TODO Object 类
 *  如果没有明确地指出超类， Object 就被认为是这个类的超类。由于在 Java 中， 每个类都是由 Object 类扩展而来的
 *  可以使用 Object 类型的变量引用任何类型的对象
 *  在 Java 中， 只有基本类型 ( primitive types ) 不是对象， 例如， 数值、 字符和布尔类型的 值都不是对象。
 *  所有的数组类型， 不管是对象数组还是基本类型的数组都扩展了 Object 类。
 * @author LiWenHao
 * @date 2019-01-23 22:00
 */
public class ObjectTest {

    public static void main(String[] args) {

        Object obj = new Employee("小倩", 35000,1996,10,1);
        //Object 类型的变量只能用于作为各种值的通用持有者。 要想对其中的内容进行具体的 操作， 还需要清楚对象的原始类型， 并进行相应的类型转换:

        Employee e = (Employee)obj;

        Employee[] staff = new Employee[10];
        obj = staff;        // OK
        obj = new int[10];  // OK
    }
}
