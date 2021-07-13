package cn.haohaoli.concurrency.create;

/**
 * 两种方式结合
 *
 * @author lwh
 */
public class BothWayCombine extends Thread {

    public BothWayCombine(Runnable target) {
        super(target);
    }

    @Override
    public void run() {
        System.out.println("thread");
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }
        };

        Thread combine = new BothWayCombine(runnable);

        // 调用start方法会运行新的线程
        combine.start();
    }
}
