package cn.haohaoli.book.core.base.chapter9;

import java.util.*;

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
        System.out.println("iterator");
        Iterator<Integer> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println(next);
        }

        System.out.println("forEach");
        for (Integer integer : collection) {
            System.out.println(integer);
        }

        System.out.println("forEachRemaining");
        // jdk8
        collection.iterator().forEachRemaining(System.out::println);

        // 删除操作
        Iterator<Integer> secondIterator = collection.iterator();
        while (secondIterator.hasNext()) {
            secondIterator.next();
            secondIterator.remove();    // 如果在调用remove之前没有调用next则会抛出异常,且不能多次删除
        }

        // ListIterator 操作
        System.out.println("============ListIterator============");
        listIteratorOperation();
    }

    /**
     * ListIterator 是专门操作 List的,是List接口添加的方法
     */
    private static void listIteratorOperation() {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        ListIterator<Integer> iterator = list.listIterator();

        System.out.println("hasNext");

        // 正向遍历
        while (iterator.hasNext()){
            Integer next = iterator.next();
            System.out.println(next);
        }

        System.out.println("hasPrevious");

        // 反向遍历
        while (iterator.hasPrevious()){
            Integer previous = iterator.previous();
            System.out.println(previous);
        }

        System.out.println("listIterator");
        // 指定索引
        ListIterator<Integer> indexIterator = list.listIterator(2);

        while (indexIterator.hasPrevious()){
            Integer previous = indexIterator.previous();
            if (previous.equals(1)) {
                indexIterator.add(99);
            }
            if (previous.equals(2)) {
                indexIterator.set(22);
            }
            System.out.println(previous);
        }
        System.out.println(list);
    }
}
