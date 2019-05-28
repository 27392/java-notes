package cn.haohaoli.book.core.base.chapter14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LiWenHao
 * @date 2019/5/28 16:58
 */
public class ReentrantLookTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
    }
}
