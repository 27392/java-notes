package cn.haohaoli.book.headfirst.strategy.version5.duck;

import cn.haohaoli.book.headfirst.strategy.version5.Duck;
import cn.haohaoli.book.headfirst.strategy.version5.impl.fly.FlyNoWay;
import cn.haohaoli.book.headfirst.strategy.version5.impl.quack.MuteQuack;

/**
 * @author liWenHao
 * @date 2019/1/8 20:23
 */
public class DecoyDuck extends Duck {

    public DecoyDuck() {
        quackBehavior = new MuteQuack();
        flyBehavior = new FlyNoWay();
    }
    @Override
    public void display() {
        System.out.println("诱饵鸭");
    }

}
