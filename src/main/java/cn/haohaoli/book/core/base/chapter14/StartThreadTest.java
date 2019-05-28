package cn.haohaoli.book.core.base.chapter14;

/**
 * @author LiWenHao
 * @date 2019/5/28 10:19
 */
public class StartThreadTest {

    public static void main(String[] args) {

        T t = new T();

        //调用run方法只会执行同一个线程中的任务，并不会启动新线程
        t.run();
        t.start();

        new Thread(new R()).start();

        new Thread(()-> {
            try {
                //休眠当前线程
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Lambda " + Thread.currentThread().getName());
        }).start();

        System.out.println(Thread.currentThread().getName());

    }

    /**
     * 通过继承Thread定义线程
     */
    static class T extends Thread{

        @Override
        public void run() {
            System.out.println("T " + Thread.currentThread().getName());
        }
    }

    /**
     * 通过实现Runnable接口定义线程
     */
    static class R implements Runnable{

        @Override
        public void run() {
            System.out.println("R " + Thread.currentThread().getName());
        }
    }
}
