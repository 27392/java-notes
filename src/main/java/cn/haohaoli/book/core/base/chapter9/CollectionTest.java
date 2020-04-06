package cn.haohaoli.book.core.base.chapter9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author LiWenHao
 */
public class CollectionTest {

    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>();

        System.out.println("========修改操作=========");
        System.out.println("add         : " + collection.add(1));
        System.out.println("add         : " + collection.add(2));
        System.out.println("add         : " + collection.add(3));
        System.out.println("remove      : " + collection.remove(1));

        System.out.println("========查询操作=========");
        System.out.println("size        : " + collection.size());
        System.out.println("isEmpty     : " + collection.isEmpty());
        System.out.println("contains    : " + collection.contains(1));
        System.out.println("contains    : " + collection.contains(1));

        System.out.println("========批量操作=========");
        batch();
    }

    /**
     * 批量操作
     */
    private static void batch () {

        // 将从list1中删除list2中出现的所有元素 - 叉集
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(3, 4, 5));
        list1.removeAll(list2);
        System.out.println(list1);

        // 将从list1中保留list2中出现的所有元素 - 交集
        list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        list2 = new ArrayList<>(Arrays.asList(3, 4, 5, 6));
        list1.retainAll(list2);
        System.out.println(list1);

        // 将指定集合中的所有元素添加到此集合
        list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        list2 = new ArrayList<>(Arrays.asList(3, 4, 5, 6));
        list1.addAll(list2);
        System.out.println(list1);

        // 此集合是否包含指定集合中的所有元素
        boolean result = list1.containsAll(list2);
        System.out.println(result);

        // 从此集合中删除所有元素
        list1.clear();
        System.out.println("list1 clear after : " + list1);

        // jdk8
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        boolean bol = collection.removeIf((e) -> e.equals(3));

        System.out.println("removeIf    : " + bol);
    }
}
