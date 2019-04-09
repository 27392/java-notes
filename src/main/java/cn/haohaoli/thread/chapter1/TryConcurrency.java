package cn.haohaoli.thread.chapter1;

/**
 * 尝试并发
 * @author LiWenHao
 * @date 2019/4/2 10:19
 */
public class TryConcurrency {

    public static void main(String[] args) {

        /**
         * TODO 实例化一个Thread不算一个线程
         *  必须调用start()方法才算一个线程
         */
        Thread thread = new Thread("READ-Thread") {
            @Override
            public void run() {
                println(Thread.currentThread().getName());
                readFormFDateBase();
            }
        };
        thread.start();

        new Thread("WRITE-Thread") {
            @Override
            public void run() {
                writeDataToFile();
            }
        }.start();
    }

    /**
     * 默认数据读取
     */
    private static void readFormFDateBase() {
        try {
            println("开始从数据库中读取数据.");
            Thread.sleep(1000 * 10L);
            println("数据读取完成，开始处理");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("数据处理完成");
    }

    /**
     * 默认数据写入
     */
    private static void writeDataToFile() {
        try {
            println("开始写入数据到文件.");
            Thread.sleep(1000 * 10L);
            println("数据写入完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("数据处理完成");
    }

    private static void println(String message) {
        System.out.println(message);
    }
}
