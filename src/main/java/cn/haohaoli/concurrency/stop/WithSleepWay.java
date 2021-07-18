package cn.haohaoli.concurrency.stop;

/**
 * 休眠的情况
 *
 * @author lwh
 */
public class WithSleepWay {

    public static void main(String[] args) throws InterruptedException {

        // 休眠
//        sleep();

        // 循环休眠(错误方式)
//        loopSleepErrorWay();

        // 循环休眠(正确方式)
//        loopSleepRightWay();

        // 中断后休眠
        interruptedAfterSleep();
    }

    /**
     * 休眠
     *
     * @throws InterruptedException
     */
    static void sleep() throws InterruptedException {
        Thread thread = new Thread(() -> {
            long start = System.currentTimeMillis();

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

            try {
                // 当前线程休眠1秒(1000毫秒)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务完成, 时间: " + (System.currentTimeMillis() - start) + "毫秒");
        });

        thread.start();

        // 主线程休眠500毫秒
        Thread.sleep(500);

        // 中断线程
        thread.interrupt();
    }

    /**
     * 每次循环都休眠,错误方式
     *
     * @throws InterruptedException
     */
    static void loopSleepErrorWay() throws InterruptedException {
        Thread thread = new Thread(() -> {
            long start = System.currentTimeMillis();
            int num = 0;

            // !Thread.currentThread().isInterrupted() 逻辑不生效,sleep方法会重置中断状态
            while (!Thread.currentThread().isInterrupted() && num <= 1000) {
                if (num % 100 == 0) {
                    System.out.println(num);
                }
                num++;

                // 在循环内try
                // sleep方法会重置中断状态
                try {
                    // 当前休眠10毫秒
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }
            System.out.println("任务完成, 时间: " + (System.currentTimeMillis() - start) + "毫秒");
        });

        thread.start();

        // 主线程休眠3秒
        Thread.sleep(3000);

        // 中断线程
        thread.interrupt();
    }

    /**
     * 每次循环都休眠,正确方式
     *
     * @throws InterruptedException
     */
    static void loopSleepRightWay() throws InterruptedException {
        Thread thread = new Thread(() -> {
            long start = System.currentTimeMillis();
            int num = 0;

            try {
                // !Thread.currentThread().isInterrupted() 不需要检查,如果已经被中断`sleep`方法会抛出异常
                // while (!Thread.currentThread().isInterrupted() && num <= 1000)
                while (num <= 1000) {
                    if (num % 100 == 0) {
                        System.out.println(num);
                    }
                    num++;

                    // 当前休眠10毫秒
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务完成, 时间: " + (System.currentTimeMillis() - start) + "毫秒");
        });

        thread.start();

        // 主线程休眠3秒
        Thread.sleep(3000);

        // 中断线程
        thread.interrupt();
    }


    /**
     * 中断后休眠
     *
     * @throws InterruptedException
     */
    static void interruptedAfterSleep() throws InterruptedException {
        Thread thread = new Thread(() -> {
            long start = System.currentTimeMillis();

            // 自己将自己中断
            Thread.currentThread().interrupt();

            try {
                // 在中断状态为`true`时调用`sleep`方法,它不会休眠.它将清除中断状态并抛出`InterruptedException`异常
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务完成, 时间: " + (System.currentTimeMillis() - start) + "毫秒");
        });

        thread.start();

        // 主线程休眠500毫秒
        Thread.sleep(500);

        // 中断线程
        thread.interrupt();
    }
}
