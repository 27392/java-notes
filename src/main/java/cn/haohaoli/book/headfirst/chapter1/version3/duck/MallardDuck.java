package cn.haohaoli.book.headfirst.chapter1.version3.duck;

import cn.haohaoli.book.headfirst.chapter1.version3.FlyWithWings;
import cn.haohaoli.book.headfirst.chapter1.version3.Quack;

/**
 * @author liWenHao
 * @date 2019/1/8 20:14
 */
public class MallardDuck extends Duck {

    MallardDuck(){
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("外观是绿头");
    }
}
