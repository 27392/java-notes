package cn.haohaoli.concurrency.stop;

/**
 * 未睡眠的情况
 *
 * @author lwh
 */
public class WithOutSleepWay {

    public static void main(String[] args) throws InterruptedException {

        // 未处理中断请求
//        notHandleInterruptRequest();

        // 处理中断请求
        handleInterruptRequest();
    }


    /**
     * 未处理中断请求
     *
     * @throws InterruptedException
     */
    static void notHandleInterruptRequest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            int num = 0;
            int limit = Integer.MAX_VALUE / 2;
            while (num <= limit) {
                if (num % 100000 == 0) {
                    System.out.println(num);
                }
                num++;
            }
            System.out.println("任务完成");
        });

        thread.start();

        // 主线程休眠50毫秒
        Thread.sleep(50);

        // 中断线程
        thread.interrupt();
    }

    /**
     * 处理中断请求
     *
     * @throws InterruptedException
     */
    static void handleInterruptRequest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            int num = 0;
            int limit = Integer.MAX_VALUE / 2;

            // Thread.interrupted() 获取当前线程是否被中断,同时会清除中断状态
            // Thread.currentThread().isInterrupted() 获取当前线程是否被中断

            // while (!Thread.interrupted() && num <= limit) {
            while (!Thread.currentThread().isInterrupted() && num <= limit) {
                if (num % 100000 == 0) {
                    System.out.println(num);
                }
                num++;
            }
            System.out.println("任务完成, 线程中断状态: " + Thread.currentThread().isInterrupted());
        });

        thread.start();

        // 主线程休眠50毫秒
        Thread.sleep(50);

        // 中断线程
        thread.interrupt();
    }
}
