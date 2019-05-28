package cn.haohaoli.book.core.base.chapter14;

/**
 * 中断线程
 * @author LiWenHao
 * @date 2019/5/28 10:39
 */
public class InterruptThreadTest {

    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
    }

    public static void version2 () {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                System.out.println(i);
            }
        });
        thread.start();
        try {
            Thread.sleep(10L);
            //调用 interrupt 并没有停止线程
            thread.interrupt();
            System.out.println("是否停止?" + thread.isInterrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void version1 () {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                System.out.println(i);
            }
        });
        thread.start();
        try {
            Thread.sleep(10L);
            //调用 interrupt 并没有停止线程
            thread.interrupt();
            System.out.println("停止");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
