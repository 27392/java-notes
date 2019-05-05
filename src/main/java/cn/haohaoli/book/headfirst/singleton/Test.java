package cn.haohaoli.book.headfirst.singleton;

import cn.haohaoli.book.headfirst.singleton.type.LazyDoubleCheckSingleton;

/**
 * @author LiWenHao
 * @date 2019-05-05 16:05
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            System.out.println(LazyDoubleCheckSingleton.getInstance());
        }).start();

        new Thread(() -> {
            System.out.println(LazyDoubleCheckSingleton.getInstance());
        }).start();
    }
}
