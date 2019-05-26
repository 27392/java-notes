package cn.haohaoli.book.core.base.chapter9;

import java.time.LocalDate;
import java.util.PriorityQueue;

/**
 * 优先级队列
 * @author LiWenHao
 * @date 2019-05-26 18:17
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<LocalDate> pq = new PriorityQueue<>();
        pq.add(LocalDate.of(1906, 12, 9));
        pq.add(LocalDate.of(1815, 12, 10));
        pq.add(LocalDate.of(1903, 12, 3));
        pq.add(LocalDate.of(1910, 6, 22));

        for (LocalDate localDate : pq) {
            System.out.println(localDate);
        }

        System.out.println("取出元素");
        while (!pq.isEmpty()){
            System.out.println(pq.remove());
        }
    }
}
