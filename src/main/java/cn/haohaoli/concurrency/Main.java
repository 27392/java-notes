package cn.haohaoli.concurrency;

import java.util.Random;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LiWenHao
 * @date 2019/9/17 19:24
 */
public class Main {

    public static void main(String[] args) {
        Object         o     = new Object();
        Stack<Integer> stack = new Stack<>();

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition     produce       = reentrantLock.newCondition();
        Condition     consumer      = reentrantLock.newCondition();



        new Thread(() -> {
            while (true) {
                reentrantLock.lock();
                try {
                    if (stack.isEmpty()) {
                        consumer.await();
                    } else {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(String.format("%s\t:\t%d", Thread.currentThread().getName(), stack.pop()));
                        produce.signalAll();
                        consumer.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }, "consumer").start();

        new Thread(() -> {
            while (true) {
                reentrantLock.lock();
                try {
                    TimeUnit.SECONDS.sleep(1);
                    int i = new Random().nextInt();
                    System.out.println(String.format("%s\t:\t%d", Thread.currentThread().getName(), i));
                    stack.push(i);
                    consumer.signalAll();
                    produce.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }, "produces").start();


        /*
        new Thread(() -> {
            while (true) {
                synchronized (o) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        int i = new Random().nextInt();
                        System.out.println(Thread.currentThread().getName() + " : " + i);
                        stack.push(i);
                        o.notifyAll();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "produce").start();

        new Thread(() -> {
            while (true) {
                synchronized (o) {
                    try {
                        if (stack.isEmpty()) {
                            o.wait();
                        } else {
                            TimeUnit.SECONDS.sleep(1);
                            System.out.println(Thread.currentThread().getName() + " : " + stack.pop());
                            o.notifyAll();
                            o.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "consumer").start();*/
    }
}
