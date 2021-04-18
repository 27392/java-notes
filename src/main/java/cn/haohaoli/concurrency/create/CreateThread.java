package cn.haohaoli.concurrency.create;

/**
 * 创建线程
 *
 * @author lwh
 */
public class CreateThread {

    /*
        使用多线程的正确方法只有2种
         1. 继承`Thread`类.重写Thread类的`run`方法.然后可以分配并启动子类的实例
         2. 实现`Runnable`接口的类.然后,该类实现run方法.然后可以分配该类的实例,在创建Thread时将其作为参数传递并启动

        两种实现方式本质对比
          继承`Thread`类方式: 重写`run`方法
          实现`Runnable`接口的类: 最终调用`target.run()`

        两种实现方法的对比
          继承`Thread`类方式: 业务(run方法中的逻辑)与线程类耦合,应将具体的业务与线程本身的创建运行等进行剥离(使用Runnable).这样更好的符合面向对象的单一职责
          继承`Thread`类方式: 每次我们想新建一个任务都需要去创建一个新的线程,而新建线程的开销(创建、执行、销毁等)是比较大的.使用Runnable则可以使用线程池等工具来避免此类的开销
          继承`Thread`类方式: Java不支持多继承,使用继承的方式就不可以在继承其他类,限制了拓展性(应使用实现Runnable接口的方式)

        总结
         1. 创建线程的方式只有2种,Thread类的源码中有说明,准确的讲.创建线程只有一种方式那就是构造`Thread`类,而实现线程的执行单元有两种方式
           方法1: 继承Thread类并重写Thread类的`run`方法
           方法2: 实现Runnable接口的`run`方法,并将Runnable实例传给Thread类
         2. 本质上没有什么区别.都是执行`run`方法,只是`run`方法的来源不同
         3. 创建线程优先考虑的是实现Runnable接口.而不是使用继承Thread类的方式

     */
    public static void main(String[] args) {

        // 继承Thread类
        ExtendThreadWay extendThreadWay = new ExtendThreadWay();
        extendThreadWay.start();

        // 实现Runnable接口
        Thread implementRunnableWay = new Thread(new ImplementRunnableWay());
        implementRunnableWay.start();
    }

    /**
     * 继承Thread类方式
     */
    public static class ExtendThreadWay extends Thread {

        @Override
        public void run() {
            System.out.println("使用Thread方式实现线程");
        }
    }

    /**
     * 实现Runnable接口方式
     */
    public static class ImplementRunnableWay implements Runnable {

        @Override
        public void run() {
            System.out.println("使用Runnable方式实现线程");
        }
    }
}
