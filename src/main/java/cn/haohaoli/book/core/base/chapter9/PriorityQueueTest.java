package cn.haohaoli.book.core.base.chapter9;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 优先级队列
 * @author LiWenHao
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        Queue<LocalDate> pq = new PriorityQueue<>(Comparator.comparing(LocalDate::getYear));
        pq.add(LocalDate.of(1906, 12, 9));
        pq.add(LocalDate.of(1815, 12, 10));
        pq.add(LocalDate.of(1903, 12, 3));
        pq.add(LocalDate.of(1910, 6, 22));

        pq.iterator().forEachRemaining(System.out::println);

        System.out.println("队头元素: " + pq.peek());

        System.out.println("取出元素");
        while (!pq.isEmpty()){
            System.out.println(pq.remove());
        }
    }
}
