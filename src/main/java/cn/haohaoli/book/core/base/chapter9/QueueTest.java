package cn.haohaoli.book.core.base.chapter9;

import java.util.*;

/**
 * @author LiWenHao
 */
public class QueueTest {

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>(Arrays.asList(1,3,2,4,9));

        while (!queue.isEmpty()){
            System.out.println(queue.remove());
        }
        System.out.println();

        // 双端队列
        Deque<Integer> arrayDeque = new ArrayDeque<>(Arrays.asList(5,3,2,4,9));
        arrayDeque.addFirst(10);

        while (!arrayDeque.isEmpty()){
            System.out.println(arrayDeque.removeFirst());
        }
    }
}
