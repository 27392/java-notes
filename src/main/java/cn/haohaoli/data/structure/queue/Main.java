package cn.haohaoli.data.structure.queue;

/**
 * @author LiWenHao
 * @date 2019-06-25 21:09
 */
public class Main {

    private static final int COUNT = 10000;

    public static void main(String[] args) {
        new Thread(() -> test(new ArrayQueue<>()),"ArrayQueue").start();
        new Thread(() -> test(new LoopQueue<>()),"LoopQueue").start();
    }

    public static void test(Queue<Integer> queue){
        long l = System.nanoTime();
        for (int i = 0; i < COUNT; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < COUNT; i++) {
            queue.dequeue();
        }
        System.out.printf("%s\t: [%d]次, 耗时[%f]\n", Thread.currentThread().getName(), COUNT, (System.nanoTime() - l) / 1000000000.0);
    }

}
