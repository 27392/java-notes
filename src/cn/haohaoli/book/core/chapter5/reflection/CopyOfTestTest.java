package cn.haohaoli.book.core.chapter5.reflection;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CopyOfTestTest {

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        a = (int[]) goodCopyOf(a, 10);
        System.out.println(Arrays.toString(a));

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

    public static Object[] badCopyOf(Object[] objects,int newLength) {
        Object[] newObjects = new Object[newLength];
        //将objects数组里从索引为0的元素开始, 复制到数组newObjects里的索引为0的位置, 复制的元素个数.
        System.arraycopy(objects, 0, newObjects, 0, Math.min(objects.length, newLength));
        return newObjects;
    }

    public static Object goodCopyOf(Object o,int newLength) {
        Class<?> aClass = o.getClass();
        if (!aClass.isArray()) {
            return null;
        }
        Class<?> componentType = aClass.getComponentType();
        //原始长度
        int oldLength = Array.getLength(o);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(o, 0, newArray, 0, Math.min(oldLength, newLength));
        return newArray;
    }
}
