package cn.haohaoli.book.core.base.chapter9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 迭代器
 * @author LiWenHao
 */
public class IteratorTest {

    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(2);
        collection.add(3);

        // 遍历操作
        Iterator<Integer> iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        for (Integer integer : collection) {
            System.out.println(integer);
        }
        // jdk8
        collection.iterator().forEachRemaining(System.out::println);

        // 删除操作
        Iterator<Integer> secondIterator = collection.iterator();
        while (secondIterator.hasNext()) {
            secondIterator.next();
            secondIterator.remove();    // 如果在调用remove之前没有调用next则会抛出异常,且不能多次删除
        }
    }
}
