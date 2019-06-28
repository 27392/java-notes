package cn.haohaoli.data.structure.queue;

import java.util.function.*;

/**
 * @author LiWenHao
 * @date 2019-06-25 21:09
 */
public class Main {

    private static final int COUNT = 5;

    public static void main(String[] args) {
        test(LoopQueue::new,"LoopQueue");
        test(LinkedListQueue::new, "LinkedListQueue");
        test(ArrayQueue::new, "ArrayQueue");
    }

    public static void test(Supplier<? extends Queue<Integer>> supplier, String name) {
        Queue<Integer> queue = supplier.get();
        long l = System.nanoTime();
        for (int i = 0; i < COUNT; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }
        for (int i = 0; i < COUNT; i++) {
            queue.dequeue();
            System.out.println(queue);
        }
        System.out.printf("%s: [%d]次, 耗时[%f]\n", name, COUNT, (System.nanoTime() - l) / 1000000000.0);
    }

}
