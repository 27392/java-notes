package cn.haohaoli.book.core.base.chapter9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * List操作
 * @author LiWenHao
 */
public class ArrayListTest {

    public static void main(String[] args) {

        List<Integer> listA = new ArrayList<>();
        listA.add(1);
        listA.add(5);

        List<Integer> listB = new ArrayList<>();
        listB.add(2);
        listB.add(3);
        listB.add(4);

        // 将某个集合中的元素添加到给定位置
        listA.addAll(1, listB);
        System.out.println(listA);

        // 获取指定下标的元素
        Integer integer = listA.get(0);
        System.out.println(integer);

        // 用新元素取代给定位置的元素,并返回原来那个元素
        Integer set = listA.set(0, -1);
        System.out.println(set);

        // 在给定位置添加一个元素
        listA.add(5, 6);
        System.out.println(listA);

        // 删除给定位置的元素并返回这个元素
        Integer remove = listA.remove(5);
        System.out.println(listA + " : " + remove);

        listA.add(6);
        listA.add(6);

        // 返回与指定元素相等的元素在列表中第一次出现的位置,如果没有这样的元素将返回 -1
        System.out.println(listA.indexOf(6));

        // 返回与指定元素相等的元素在列表中最后一次出现的位置,如果没有这样的元素将返回 -1
        System.out.println(listA.lastIndexOf(6));


        // 对这个列表的所有元素应用这个操作   jdk8
        listA.replaceAll(e -> e * 10);
        System.out.println(listA);

        // 使用给定比较器对列表排序
        listA.sort(Comparator.reverseOrder());
        System.out.println(listA);
    }
}
