import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Vector;
import java.util.function.Supplier;

/**
 * synchronized 关键字
 * @author lwh
 */
class SynchronizedTest {

    ///////////////////////////////////////////////////////////////////////////
    // synchronized 方法
    ///////////////////////////////////////////////////////////////////////////

    @DisplayName("synchronized方法,同一时间只能被一个线程执行")
    @Test
    void synchronizedMethod() {

        Runnable r = new Runnable() {

            @Override
            public synchronized void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " -> enter");
                    Thread.sleep(3_000);
                    System.out.println(Thread.currentThread().getName() + " -> exit");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        start(t1, t2);
    }

    @DisplayName("方法内部的变量永远都是线程安全的(因为方法内部的变量是私有的)")
    @Test
    void methodInnerVariableAlwaysThreadSafe() {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                try {
                    String name  = Thread.currentThread().getName();
                    int    count = 0;
                    for (int i = 0; i < 100; i++) {
                        count += i;
                    }
                    if (Objects.equals(name, "t1")) {
                        count = 100;
                        Thread.sleep(200);
                    } else {
                        count = 200;
                    }
                    System.out.println(name + " -> " + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");

        start(t1, t2);
    }

    @DisplayName("共享变量(实例变量)是非线程安全的")
    @Test
    void sharedVariableIsNotThreadSafe() {
        Runnable r = new Runnable() {

            // 共享变量
            int count = 0;

            @Override
//            public synchronized void run() {
            public void run() {
                try {
                    String name = Thread.currentThread().getName();
                    if (Objects.equals(name, "t1")) {
                        count = 100;
                        Thread.sleep(200);
                    } else {
                        count = 200;
                    }
                    System.out.println(name + " -> " + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // 多个线程访问一个对象中的实例变量,是非线程安全的.
        // 当`t1`线程休眠时,其他线程完成了工作后将变量修改.当`t1`休眠完成时读取到的值是错误的,是其他线程修改后的值!
        // 在`run`方法上加上`synchronized`关键字即可解决线程安全问题
        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");

        start(t1, t2);
    }

    @DisplayName("多个对象是多个锁.synchronized获取的是`对象锁`(在本例中)而不是将一段代码或者方法作为锁")
    @Test()
    void multipleObjectsMultipleBlocks() {
        Supplier<Runnable> runnableSupplier = () -> new Runnable() {

            @Override
            public synchronized void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " -> syncMethod enter");

                    Thread.sleep(1_000);

                    System.out.println(Thread.currentThread().getName() + " -> syncMethod exit");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // 获取的是不同的锁,不会产生同步。 需要同步则需要访问同一对象
        Thread t1 = new Thread(runnableSupplier.get());
        Thread t2 = new Thread(runnableSupplier.get());

        /*Runnable runnable = runnableSupplier.get();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);*/

        start(t1, t2);
    }

    @DisplayName("同一个对象,访问synchronized方法需要同步,访问非synchronized方法则不需要")
    @Test
    void synchronizedMethodAndNotSynchronizedMethod() {

        class Task {

            synchronized void syncMethod() {
                try {
                    System.out.println(Thread.currentThread().getName() + " -> syncMethod enter");

                    Thread.sleep(3_000);

                    System.out.println(Thread.currentThread().getName() + " -> syncMethod exit");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized void syncMethod2() {
                System.out.println(Thread.currentThread().getName() + " -> syncMethod2 enter");
                System.out.println(Thread.currentThread().getName() + " -> syncMethod2 exit");
            }

            void notSyncMethod() {
                try {
                    System.out.println(Thread.currentThread().getName() + " -> notSyncMethod enter");

                    Thread.sleep(3_000);

                    System.out.println(Thread.currentThread().getName() + " -> notSyncMethod exit");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        Task task = new Task();

        // 线程 t1,t2,t3 串行执行 -> (访问同一个对象的synchronized方法是同步的.假设`t1`线程获取到锁,t2、t3线程只能等待`t1`释放锁后在此去尝试获取锁)
        // 线程 t4,t5    异步执行 -> (访问同一个对象的非synchronized方法是异步的. 因为无需获取锁)
        Thread t1 = new Thread(task::syncMethod, "t1");
        Thread t2 = new Thread(task::syncMethod, "t2");
        Thread t3 = new Thread(task::syncMethod2, "t3");
        Thread t4 = new Thread(task::notSyncMethod, "t4");
        Thread t5 = new Thread(task::notSyncMethod, "t5");

        start(t1, t2, t3, t4, t5);
    }

    @DisplayName("synchronized锁重入")
    @Test
    void reentrant() {

        // 当一个线程获得对象锁后,再次请求此对象锁时是可以再次得到对象的锁的,
        // 一个synchronized方法或块的内部调用本来的其他synchronized方法或块时,是永远可以得到锁的

        // 锁重入的概念是: 自己可以再次获取自己的内部锁

        class Task {

            private synchronized void syncMethod1() {
                System.out.println(Thread.currentThread().getName() + " -> syncMethod1 enter");
                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                syncMethod2();
                System.out.println(Thread.currentThread().getName() + " -> syncMethod1 exit");
            }

            private synchronized void syncMethod2() {
                System.out.println(Thread.currentThread().getName() + " -> syncMethod2 enter");
                syncMethod3();
                System.out.println(Thread.currentThread().getName() + " -> syncMethod2 exit");
            }

            private synchronized void syncMethod3() {
                System.out.println(Thread.currentThread().getName() + " -> syncMethod3 enter");
                System.out.println(Thread.currentThread().getName() + " -> syncMethod3 exit");
            }
        }

        Task task = new Task();

        // t1线程一旦获取到对象锁,那么对于同样是使用该对象锁的方法或者块都可以再次得到.这就是锁重入
        Thread t1 = new Thread(task::syncMethod1, "t1");
        Thread t2 = new Thread(task::syncMethod3, "t2");

        start(t1, t2);
    }

    @DisplayName("synchronized锁重入(继承关系)")
    @Test
    void reentrantInherit() {
        class Parent {
            synchronized void syncMethod() {
                System.out.println(Thread.currentThread().getName() + " -> syncMethod enter");
                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " -> syncMethod exit");
            }
        }

        class ChildA extends Parent {

            synchronized void childSyncMethod() {
                System.out.println(Thread.currentThread().getName() + " -> childSyncMethod enter");

                // 调用父类方法
                super.syncMethod();
                System.out.println(Thread.currentThread().getName() + " -> childSyncMethod exit");
            }
        }

        // 重入锁对父子类继承也是适用的
        ChildA childA = new ChildA();

        Thread t1 = new Thread(childA::childSyncMethod, "t1");
        Thread t2 = new Thread(childA::childSyncMethod, "t2");

        start(t1, t2);

    }

    @DisplayName("synchronized异常自动释放锁")
    @Test
    void exceptionAutoReleaseLock() {

        Runnable r = new Runnable() {
            @Override
            public synchronized void run() {
                String name = Thread.currentThread().getName();
                System.out.println(name + " -> enter");

                for (int i = 0; i < 100; i++) {
                    if (i == 30 && Objects.equals(name, "t1")) {
                        System.out.println(name + " -> error");
                        throw new RuntimeException();
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name + " -> exit");
            }
        };
        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");

        start(t1, t2);

    }

    ///////////////////////////////////////////////////////////////////////////
    // synchronized 代码块
    ///////////////////////////////////////////////////////////////////////////

    @DisplayName("synchronized代码块.同一时间只能被一个线程执行")
    @Test
    void synchronizedBlock() {

        Runnable r = () -> {

            // synchronized代码块方式,与synchronized方法类似.同样是同一时间只能被一个线程执行,以保证线程安全
            synchronized (this) {
                try {
                    System.out.println(Thread.currentThread().getName() + " -> syncMethod enter");

                    Thread.sleep(2_000);

                    System.out.println(Thread.currentThread().getName() + " -> syncMethod exit");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");

        start(t1, t2);
    }

    @DisplayName("synchronized(this)与synchronized方法锁都是当前对象")
    @Test
    void synchronizedBlockAndSynchronizedMethodLockSame() {
        Runnable r = new Runnable() {

            public synchronized void syncMethod() {
                System.out.println(Thread.currentThread().getName() + " -> syncMethod enter");
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " -> syncMethod exit");
            }

            @Override
            public void run() {
                synchronized (this) {
                    System.out.println(Thread.currentThread().getName() + " -> synchronizedBlock enter");
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 在同步代码块中调用同步(synchronized)方法
                    syncMethod();
                    System.out.println(Thread.currentThread().getName() + " -> synchronizedBlock exit");
                }
            }
        };

        // 多个线程调用同一个对象中的不同名称的`synchronized方法`或`synchronized(this)`同步代码块时,调用的效果就是按顺序执行,也就是同步的、阻塞的
        // 因为他们使用的锁对象都是`当前对象`

        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");

        start(t1, t2);
    }

    @DisplayName("synchronized代码块自定义锁对象")
    @Test
    void synchronizedBlockCustomMonitor() {

        // 使用自定义锁的优化: 如果一个类中有很多同步方法,这时虽然能实现同步,但会收到阻塞影响运行效率
        // 但是使用自定义锁则同步代码块与同步方法的锁不一致,可以不予其他锁`this`的同步方法争抢`this`锁,则可以大大提高运行效率

        Runnable r = new Runnable() {

            // 锁必须保持共享,不可变
            final Object lock = new Object();

            @Override
            public void run() {

                // 保持锁的唯一才能达到同步,如果放在方法中,每次都会产生新的锁,这样是错误的
                // Object lock = new Object();

                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " -> enter");
                    try {
                        Thread.sleep(2_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " -> exit");
                }
            }
        };

        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");

        start(t1, t2);
    }

    @DisplayName("synchronized代码块自定义锁对象与synchronized方法")
    @Test
    void synchronizedBlockCustomMonitorAndSynchronizedMethod() {

        // 自定义锁与同步方法,他两不是不是同步的,双方用的锁是不一样的.所以运行的效果是异步的

        class Task {

            private final Object lock = new Object();

            public void syncBlock() {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " -> enter");
                    try {
                        Thread.sleep(2_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " -> exit");
                }
            }

            public synchronized void syncMethod() {
                System.out.println(Thread.currentThread().getName() + " -> syncMethod enter");
                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " -> syncMethod exit");
            }
        }

        Task task = new Task();

        Thread t1 = new Thread(task::syncBlock, "t1");
        Thread t2 = new Thread(task::syncMethod, "t2");

        start(t1, t2);
    }

    @DisplayName("synchronized代码块自定义锁对象对自定义锁对象中方法的影响")
    @Test
    void synchronizedBlockCustomMonitorAffect() {

        // 总结: 使用自定义对象做为锁时,自定义对象内的`synchronized(this)`或synchronized方法都是`同步`的.因为锁都是一个

        class Task {
            synchronized void syncMethod() {
                System.out.println(Thread.currentThread().getName() + " -> syncMethod enter");
                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " -> syncMethod exit");
            }

            public void syncBlock() {
                synchronized (this) {
                    System.out.println(Thread.currentThread().getName() + " -> Task syncBlock enter");
                    try {
                        Thread.sleep(2_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " -> Task syncBlock exit");
                }
            }
        }

        @RequiredArgsConstructor
        class Executor {
            private final Task task;

            void syncBlock() {
                synchronized (task) {
                    System.out.println(Thread.currentThread().getName() + " -> syncBlock enter");
                    try {
                        Thread.sleep(2_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " -> syncBlock exit");
                }
            }
        }

        Task     task     = new Task();
        Executor executor = new Executor(task);

        // `t1`将`task`对象做为锁,此时调用`task`本身的`synchronized(this)`或synchronized方法都是`同步`的.因为锁都是一个
        Thread t1 = new Thread(task::syncMethod, "t1");
        Thread t2 = new Thread(task::syncBlock, "t2");
        Thread t3 = new Thread(executor::syncBlock, "t3");

        start(t1, t2, t3);
    }

    @DisplayName("synchronized代码块自定义锁对象对自定义锁对象中方法的影响(Vector举例)")
    @Test
    void synchronizedBlockCustomMonitorAffectVector() {

        class CustomVector extends Vector<String> {

            @Override
            public synchronized boolean add(String s) {
                System.out.println(Thread.currentThread().getName() + " -> add enter");
                boolean add = super.add(s);
                System.out.println(Thread.currentThread().getName() + " -> add exit");
                return add;
            }

            @Override
            public synchronized int size() {
                System.out.println(Thread.currentThread().getName() + " -> size enter");
                int size = super.size();
                System.out.println(Thread.currentThread().getName() + " -> size exit");
                return size;
            }

            public void noSyncMethod() {
                System.out.println(Thread.currentThread().getName() + " -> noSyncMethod enter");
                System.out.println(Thread.currentThread().getName() + " -> noSyncMethod exit");
            }
        }
        CustomVector vector = new CustomVector();

        Runnable r = () -> {
            synchronized (vector) {
                System.out.println(Thread.currentThread().getName() + " -> enter");
                try {
                    // 长时间持有`vector`,会导致`vector`类中的同步方法或`synchronized(this)`阻塞
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " -> exit");
            }
        };

        //t1持有`vector`锁,t2,t3都是同步方法所以会受到影响.反观t4它则不是同步方法,并不会阻塞
        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(() -> vector.add("1"), "t2");
        Thread t3 = new Thread(vector::size, "t3");
        Thread t4 = new Thread(vector::noSyncMethod, "t4");     // 非同步方法不会阻塞

        start(t1, t2, t3,t4);
    }

    public static void start(Thread... threads) {

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
