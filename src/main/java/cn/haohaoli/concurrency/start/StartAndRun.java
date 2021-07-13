package cn.haohaoli.concurrency.start;

/**
 * 调用start方法与run的区别
 * <p>
 * start方法会启动新的线程
 * run方法只是在当前线程中执行
 *
 * @author lwh
 */
public class StartAndRun {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        Thread   thread   = new Thread(runnable);

        // 调用run方法
        thread.run();   // main
        runnable.run(); // main

        // 调用start方法
        thread.start(); // Thread-0
    }
}
