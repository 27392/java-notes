package cn.haohaoli.book.headfirst.chapter1.version3.duck;

import cn.haohaoli.book.headfirst.chapter1.version3.FlyNoWay;
import cn.haohaoli.book.headfirst.chapter1.version3.Squack;

/**
 * @author liWenHao
 * @date 2019/1/8 20:20
 */
public class RubberDuck extends Duck {

    RubberDuck(){
        quackBehavior = new Squack();
        flyBehavior = new FlyNoWay();
    }

    @Override
    public void display() {
        System.out.println("外观是橡皮鸭");
    }

}
