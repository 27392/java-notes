package cn.haohaoli.concurrency.create;

/**
 * 继承Thread类方式
 *
 * @author lwh
 */
public class ExtendThreadWay extends Thread {

    @Override
    public void run() {
        System.out.println("使用Thread方式实现线程");
    }

    public static void main(String[] args) {

        ExtendThreadWay extendThreadWay = new ExtendThreadWay();
        extendThreadWay.start();
    }
}
