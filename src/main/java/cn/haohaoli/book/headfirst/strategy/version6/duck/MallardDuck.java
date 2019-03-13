package cn.haohaoli.book.headfirst.strategy.version6.duck;

import cn.haohaoli.book.headfirst.strategy.version6.Duck;
import cn.haohaoli.book.headfirst.strategy.version6.impl.fiy.FlyWithWings;
import cn.haohaoli.book.headfirst.strategy.version6.impl.quack.Quack;

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
