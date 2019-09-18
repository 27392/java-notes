package cn.haohaoli.book.core.multithreading.chapter1;

/**
 * 创建线程
 * @author LiWenHao
 * @date 2019/9/18 22:12
 */
public class CreateThread {

    /**
     * TODO 创建线程
     *  创建线程的方式主要有两种
     *   1. 继承Thread类
     *   2. 实现Runnable接口
     *  这两种方式其实并没有什么却别,只是在使用继承来创建线程时会有局限性,java只支持单继承.而接口可以实现多个
     *  不管是实现Runnable接口还是继承Thread类都要重写run方法,而启动线程而需要调用Thread类的start方法
     *  这里我们就可以想到调用start方法后它内部肯定是去调用了run方法.
     *    但是需要注意,调用run方法并不能启动线程,它只会单纯执行run方法内容而已
     *  既然启动线程是Thread的方法,那么实现Runnable接口不能说是创建了一个线程.那么实现Runnable接口怎么创建线程?
     *  通过Thread的构造方法我们可以将Runnable接口的实例传递给它.然后在启动线程的时候调用Runnable中的run方法
     *      new Thread(Runnable target);
     *  通过查看Thread.run()方法的源码可以知道,如果我们传入Runnable接口的实例,那么调用的时候就会调用Runnable的run方法
     *     if (target != null) {
     *        target.run();
     *     }
     *  此外Thread同样实现了Runnable接口.当我们继承Thread重写run方法时候其实就是Runnable的run方法
     *  因此使用Runnable接口告诉线程该做什么更为合理,这样更灵活
     *  通过实现Runnable接口,并将该实例传入Thread中.这样避免重写Thread.run方法,单纯使用接口来定义线程,这也是最常用的做法
     */
    public static void main(String[] args) {

        // 一个进程正在运行时至少会有一个线程正在运行
        System.out.println(Thread.currentThread().getName());

        // 继承Thread类
        MyThread myThread = new MyThread();
        myThread.start();

        // 实现Runnable接口
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();

        // jdk1.8一下可以使用匿名内部类快速创建线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类");
            }
        }).start();

        // jdk8 可以使用lambda创建
        new Thread(() -> {
            System.out.println("lambda");
        }).start();
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Thread");
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Runnable");
        }
    }
}
