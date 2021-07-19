package cn.haohaoli.concurrency.stop;

/**
 * 正确的InterruptedException异常
 *
 * @author lwh
 */
public class HandleInterruptedException {

    public static void main(String[] args) throws InterruptedException {

        // 错误的方式: 压制异常
        suppress();

        // 正确的方式: 恢复中断
        recover();

        // 正确的方式: 传递异常
        transferEx();

    }

    /**
     * 压制异常
     */
    private static void suppress() throws InterruptedException {

        Thread thread = new Thread(() -> {
            Task task = new Task();
            int num = 0;

            // 由于异常被压制,sleep清除中断中断导致`isInterrupted`方法无效
            while (!Thread.currentThread().isInterrupted() && num <= 100) {
                // 压制异常
                task.suppress();
                System.out.println("num: " + num);
                num++;
            }
        });

        thread.start();

        Thread.sleep(1000);

        thread.interrupt();
    }

    /**
     * 恢复中断
     *
     * @throws InterruptedException
     */
    private static void recover() throws InterruptedException {

        Thread thread = new Thread(() -> {
            Task task = new Task();
            int num = 0;

            // 将中断恢复`isInterrupted`方法可以正常的检测到中断
            while (!Thread.currentThread().isInterrupted() && num <= 100) {
                // 恢复中断
                task.recover();
                System.out.println("num: " + num);
                num++;
            }
        });

        thread.start();

        Thread.sleep(1000);

        thread.interrupt();
    }

    /**
     * 传递中断
     *
     * @throws InterruptedException
     */
    private static void transferEx() throws InterruptedException {

        Thread thread = new Thread(() -> {

            Task task = new Task();
            int num = 0;

            // 调用时必须捕获异常
            try {
                while (num <= 100) {

                    // 传递中断
                    task.transferEx();

                    System.out.println("num: " + num);
                    num++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        Thread.sleep(1000);

        thread.interrupt();

    }

    static class Task {

        /**
         * 压制
         */
        void suppress() {
            try {
                // 当前休眠10毫秒
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * 恢复
         */
        void recover() {
            try {
                // 当前休眠10毫秒
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        /**
         * 传递异常
         *
         * @throws InterruptedException
         */
        void transferEx() throws InterruptedException {
            // 当前休眠10毫秒
            Thread.sleep(10);
        }
    }

}
