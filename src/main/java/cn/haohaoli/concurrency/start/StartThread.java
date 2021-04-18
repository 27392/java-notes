package cn.haohaoli.concurrency.start;

/**
 * 启动线程
 *
 * @author lwh
 */
public class StartThread {

    public static void main(String[] args) {

        // 调用start方法与run的区别
        startAndRun();

        // 启动顺序(执行start方法的顺序不代表线程启动的顺序)
        startOrder();

        // 多次调用start方法
        multipleStart();

    }

    /**
     * 多次调用start方法
     */
    public static void multipleStart() {

        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };

        thread.start();
        thread.start(); // 多次启动同一个线程会抛出: IllegalThreadStateException 异常
    }

    /**
     * 调用start方法与run的区别
     * <p>
     * start方法会启动新的线程
     * run方法只是在当前线程中执行
     */
    public static void startAndRun() {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        Thread thread = new Thread(runnable);

        // 调用run方法
        thread.run();   // main
        runnable.run(); // main

        // 调用start方法
        thread.start(); // Thread-0
    }

    /**
     * 启动顺序(执行start方法的顺序不代表线程启动的顺序)
     * <p>
     * 多次执行结果顺序会不一样
     * <p>
     * Thread-1
     * Thread-3
     * Thread-2
     * Thread-0
     * Thread-4
     * </p>
     */
    public static void startOrder() {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
