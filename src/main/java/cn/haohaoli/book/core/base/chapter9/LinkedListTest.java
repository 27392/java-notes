package cn.haohaoli.book.core.base.chapter9;

import java.util.*;

/**
 * @author LiWenHao
 * @date 2019-05-26 16:19
 */
public class LinkedListTest {

    public static void main(String[] args) {

        List<String> a = new LinkedList<>();
        a.add("Amy");
        a.add("Carl");
        a.add("Erica");

        System.out.println("a : " + a);

        List<String> b = new LinkedList<>();
        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");
        System.out.println("b : " + b);

        ListIterator<String> aIter = a.listIterator();

        Iterator<String> bIter = b.iterator();

        //合并
        while (bIter.hasNext()){
            if (aIter.hasNext()) {
                aIter.next();
            }
            aIter.add(bIter.next());
        }
        System.out.println("合并之后 a : " + a);

        bIter = b.iterator();
        //删除偶数的单词
        while (bIter.hasNext()){
            bIter.next();
            if(bIter.hasNext()){
                bIter.next();
                bIter.remove();
            }
        }
        System.out.println("删除偶数的单词之后 b : " + b);

        /**
         * {@link java.util.AbstractList#removeAll(Collection)}
         */
        a.removeAll(b);
        System.out.println("a");

    }
}
