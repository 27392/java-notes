package cn.haohaoli.concurrency.start;

/**
 * 启动顺序(执行start方法的顺序不代表线程启动的顺序)
 * <p>
 * 多次执行结果顺序会不一样
 *
 * @author lwh
 */
public class StartOrder {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());

        new Thread(runnable, "t1").start();
        new Thread(runnable, "t2").start();
        new Thread(runnable, "t3").start();
        new Thread(runnable, "t4").start();
        new Thread(runnable, "t5").start();
    }
}
