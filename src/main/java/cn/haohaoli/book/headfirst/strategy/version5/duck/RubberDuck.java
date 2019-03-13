package cn.haohaoli.book.headfirst.strategy.version5.duck;

import cn.haohaoli.book.headfirst.strategy.version5.Duck;
import cn.haohaoli.book.headfirst.strategy.version5.impl.fly.FlyNoWay;
import cn.haohaoli.book.headfirst.strategy.version5.impl.quack.Squack;

/**
 * @author liWenHao
 * @date 2019/1/8 20:20
 */
public class RubberDuck extends Duck {

    public RubberDuck(){
        quackBehavior = new Squack();
        flyBehavior = new FlyNoWay();
    }

    @Override
    public void display() {
        System.out.println("外观是橡皮鸭");
    }

}
