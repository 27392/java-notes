package cn.haohaoli.book.headfirst.demo.composite;

import cn.haohaoli.book.headfirst.demo.AbsteractQuackable;
import cn.haohaoli.book.headfirst.demo.Quackable;
import cn.haohaoli.book.headfirst.demo.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 群
 *
 * @author lwh
 */
public class Flock extends AbsteractQuackable {

    private final List<Quackable> quackables = new ArrayList<>();

    /**
     * 添加鸭子(安全性)
     *
     * @param quackable
     */
    public void add(Quackable quackable) {
        quackables.add(quackable);
    }

    @Override
    public void doQuack() {
        for (Quackable quackable : quackables) {
            quackable.quack();
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        for (Quackable quackable : quackables) {
            quackable.registerObserver(observer);
        }
    }
}
