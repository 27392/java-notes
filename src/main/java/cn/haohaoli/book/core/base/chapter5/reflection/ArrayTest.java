package cn.haohaoli.book.core.base.chapter5.reflection;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 数组测试类
 * @author LiWenHao
 * @date 2019-01-24 11:42
 */
public class ArrayTest {

    /**
     * TODO Array类 Api (部分)
     *  • static Object get(Object array,int index)
     *  • static xxx get/xx(Object array,int index)
     *      ( xxx 是 boolean、byte、 char、 double、 float、 int、 long、 short 之中的一种基本类M。）
     *      这些方法将返回存储在给定位置上的给定数组的内容。
     *  • static void set(Object array,int index,Object newValue)
     *  • static setXxx(Object array,int index,xxx newValue)
     *      ( xxx 是 boolean、 byte、char、double、float、 int、 long、 short 之中的一种基本类型。）
     *      这些方法将一个新值存储到给定位置上的给定数组中。
     *  • static int getLength(Object array)
     *      返回数组的长度。
     *  • static Object newInstance(Class componentType,int length)
     *  • static Object newInstance(Class componentType,int[]lengths)
     *      返回一个具有给定类型、 给定维数的新数组。
     */

    public static void main(String[] args) {
        int[] ints = {1, 2, 3};
        ints = (int[]) goodCopyOf(ints, 10);
        System.out.println(Arrays.toString(ints));

        /**
         * 一个对象数组不能转换成雇员数组 如果这样做在运行时java将会产生ClassCastException异常
         *  java 会记住每个元素的类型,即创建数组时new表达式中使用的元素类型。
         *      将一个雇员数组临时转换成Object数组 然后再把他转换回来是可以的
         *      但一个开始就是Object的数组却永远不能转换成雇员数组
         */
        String[] b = {"Tom", "Dick", "Harry"};
        b = (String[]) goodCopyOf(b, 10);
        System.out.println("ClassCastException");
        b = (String[]) badCopyOf(b, 10);
    }

    public static Object[] badCopyOf(Object[] objects, int newLength) {
        Object[] newObjects = new Object[newLength];
        //将objects数组里从索引为0的元素开始, 复制到数组newObjects里的索引为0的位置, 复制的元素个数.
        System.arraycopy(objects, 0, newObjects, 0, Math.min(objects.length, newLength));
        return newObjects;
    }

    /**
     * TODO 忽略警告
     *  资料：https://www.cnblogs.com/jingzhenhua/p/5986689.html
     */
    @SuppressWarnings("all")
    public static Object goodCopyOf(Object o, int newLength) {
        Class aClass = o.getClass();
        if (!aClass.isArray()) {
            return null;
        }
        Class componentType = aClass.getComponentType();
        //原始长度
        int oldLength = Array.getLength(o);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(o, 0, newArray, 0, Math.min(oldLength, newLength));
        return newArray;
    }
}
