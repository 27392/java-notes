package cn.haohaoli.thread.chapter2;

public class BankVersion2 {

    private final static int MAX = 50;

    public static void main(String[] args) {
        final TicketWindowRunnable ticketWindow1 = new TicketWindowRunnable();

        final Runnable runnable = () -> {
            int index = 1;
            while (index <= MAX) {
                System.out.println(Thread.currentThread() + "-当前的号码是：" + index++);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread1 = new Thread(ticketWindow1, "一号窗口");
        thread1.start();
        Thread thread2 = new Thread(ticketWindow1, "二号窗口");
        thread2.start();
        Thread thread3 = new Thread(ticketWindow1, "三号窗口");
        thread3.start();
    }
}
