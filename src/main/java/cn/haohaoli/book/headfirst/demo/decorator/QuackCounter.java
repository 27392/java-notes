package cn.haohaoli.book.headfirst.demo.decorator;

import cn.haohaoli.book.headfirst.demo.AbsteractQuackable;
import cn.haohaoli.book.headfirst.demo.Quackable;
import lombok.RequiredArgsConstructor;

/**
 * 叫声数量装饰器
 *
 * @author lwh
 */
@RequiredArgsConstructor
public class QuackCounter extends AbsteractQuackable {

    private final Quackable quackable;

    private static int numberOfQuacks;

    @Override
    public void doQuack() {
        quackable.quack();
        numberOfQuacks++;
    }

    public static int getNumberOfQuacks() {
        return numberOfQuacks;
    }

    public Quackable getQuackable() {
        return quackable;
    }
}
