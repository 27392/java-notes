package cn.haohaoli.book.headfirst.chapter1.version4.duck;

import cn.haohaoli.book.headfirst.chapter1.version4.FlyWithWings;
import cn.haohaoli.book.headfirst.chapter1.version4.Quack;

/**
 * @author liWenHao
 * @date 2019/1/8 20:14
 */
public class MallardDuck extends Duck {

    public MallardDuck(){
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("外观是绿头");
    }

}
