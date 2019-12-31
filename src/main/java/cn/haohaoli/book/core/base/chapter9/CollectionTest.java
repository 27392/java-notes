package cn.haohaoli.book.core.base.chapter9;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author LiWenHao
 */
public class CollectionTest {

    public static void main(String[] args) {

        Collection<Integer> collection = new ArrayList<>();

        System.out.println("size        : " + collection.size());
        System.out.println("isEmpty     : " + collection.isEmpty());
        // 添加元素
        System.out.println("add         : " + collection.add(1));
        // 返回此集合中的元素数
        System.out.println("size        : " + collection.size());
        // 此集合是否为空
        System.out.println("isEmpty     : " + collection.isEmpty());
        // 此集合是否包含指定的元素
        System.out.println("contains    : " + collection.contains(1));
        System.out.println("contains    : " + collection.contains(2));
        System.out.println("toString    : " + collection);

        Collection<Integer> newCollection = new ArrayList<>();
        newCollection.add(2);
        newCollection.add(3);

        // 将指定集合中的所有元素添加到此集合
        System.out.println("addAll      : " + collection.addAll(newCollection));
        System.out.println("toString    : " + collection);
        // 此集合是否包含指定集合中的所有元素
        System.out.println("containsAll : " + collection.containsAll(newCollection));

        System.out.println("remove      : " + collection.remove(1));
        System.out.println("toString    : " + collection);
        // 删除指定集合中包含的所有此集合的元素
        System.out.println("removeAll   : " + collection.removeAll(newCollection));
        System.out.println("toString    : " + collection);

        collection.add(1);
        collection.addAll(newCollection);

        // 仅保留此集合中包含在指定集合中的元素
        System.out.println("retainAll    : " + collection.retainAll(newCollection));
        System.out.println("toString     : " + collection);

        System.out.println("toArray      : " + Arrays.toString(collection.toArray()));

        // 从此集合中删除所有元素
        collection.clear();
        System.out.println("clear after  : " + collection);
    }
}
