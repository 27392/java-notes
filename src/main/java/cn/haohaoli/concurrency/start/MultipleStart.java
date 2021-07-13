package cn.haohaoli.concurrency.start;

/**
 * 多次调用start方法
 *
 * @author lwh
 */
public class MultipleStart {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName()));    //RUNNABLE

        thread.start();
        thread.start(); // 多次启动同一个线程会抛出: IllegalThreadStateException 异常
    }
}
