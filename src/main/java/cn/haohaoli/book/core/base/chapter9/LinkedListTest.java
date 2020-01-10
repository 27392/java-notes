package cn.haohaoli.book.core.base.chapter9;

import java.util.*;

/**
 * 链表操作
 * @author LiWenHao
 * @date 2019-05-26 16:19
 */
public class LinkedListTest {

    public static void main(String[] args) {

        LinkedList<String> listA = new LinkedList<>();

        // LinkedList add 方法默认是添加到列表的尾部 等价于 addLast
        listA.add("Carl");

        // 将元素添加到列表的尾部
        listA.addLast("Erica");

        // 将元素添加到列表的头部
        listA.addFirst("Amy");

        System.out.println("listA  : " + listA);

        System.out.println("getFirst :  " + listA.getFirst());
        System.out.println("getLast  :  " + listA.getLast());

        LinkedList<String> listB = new LinkedList<>();
        listB.add("Bob");
        listB.add("Doug");
        listB.add("Frances");
        listB.add("Gloria");
        System.out.println("listB  : " + listB);

        //====================

        ListIterator<String> listIteratorB = listB.listIterator();
        ListIterator<String> listIteratorA = listA.listIterator();

        // 合并两个 List 例子
        while (listIteratorB.hasNext() && listIteratorA.hasNext()){
            listIteratorA.next();
            listIteratorA.add(listIteratorB.next());
        }
        System.out.println("listA  : " + listA);

        // 删除下标是偶数的元素
        listIteratorA = listA.listIterator();
        while (listIteratorA.hasNext()){
            listIteratorA.next();
            if (listIteratorA.hasNext()) {
                listIteratorA.next();
                listIteratorA.remove();
            }
        }

        System.out.println("listA  : " + listA);
    }
}
