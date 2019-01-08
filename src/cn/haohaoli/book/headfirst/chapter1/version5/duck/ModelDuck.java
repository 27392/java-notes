package cn.haohaoli.book.headfirst.chapter1.version5.duck;

import cn.haohaoli.book.headfirst.chapter1.version5.FlyNoWay;
import cn.haohaoli.book.headfirst.chapter1.version5.Quack;

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
