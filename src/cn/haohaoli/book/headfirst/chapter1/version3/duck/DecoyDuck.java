package cn.haohaoli.book.headfirst.chapter1.version3.duck;

import cn.haohaoli.book.headfirst.chapter1.version3.FlyNoWay;
import cn.haohaoli.book.headfirst.chapter1.version3.MuteQuack;

/**
 * @author liWenHao
 * @date 2019/1/8 20:23
 */
public class DecoyDuck extends Duck {

    DecoyDuck() {
        quackBehavior = new MuteQuack();
        flyBehavior = new FlyNoWay();
    }
    @Override
    public void display() {
        System.out.println("诱饵鸭");
    }

}
