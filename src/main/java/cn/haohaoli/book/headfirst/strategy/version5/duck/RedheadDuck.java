package cn.haohaoli.book.headfirst.strategy.version5.duck;

import cn.haohaoli.book.headfirst.strategy.version5.Duck;
import cn.haohaoli.book.headfirst.strategy.version5.impl.fly.FlyWithWings;
import cn.haohaoli.book.headfirst.strategy.version5.impl.quack.Quack;

/**
 * @author liWenHao
 * @date 2019/1/8 20:16
 */
public class RedheadDuck extends Duck {

    public RedheadDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("外观是红头");
    }
}
