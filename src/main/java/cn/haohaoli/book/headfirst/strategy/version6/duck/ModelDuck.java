package cn.haohaoli.book.headfirst.chapter1.strategy.version6.duck;

import cn.haohaoli.book.headfirst.chapter1.strategy.version6.Duck;
import cn.haohaoli.book.headfirst.chapter1.strategy.version6.impl.fiy.FlyNoWay;
import cn.haohaoli.book.headfirst.chapter1.strategy.version6.impl.quack.Quack;

/**
 * @author liWenHao
 * @date 2019/1/8 21:31
 */
public class ModelDuck extends Duck {

    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("火箭鸭");
    }
}
