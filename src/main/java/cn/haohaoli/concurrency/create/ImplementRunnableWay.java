package cn.haohaoli.concurrency.create;

/**
 * 实现Runnable接口方式
 *
 * @author lwh
 */
public class ImplementRunnableWay implements Runnable {

    @Override
    public void run() {
        System.out.println("使用Runnable方式实现线程");
    }

    public static void main(String[] args) {
        Runnable runnable             = new ImplementRunnableWay();
        Thread   implementRunnableWay = new Thread(runnable);
        implementRunnableWay.start();
    }
}