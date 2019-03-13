package cn.haohaoli.book.headfirst.strategy.version5;

import cn.haohaoli.book.headfirst.strategy.version5.FlyBehavior;
import cn.haohaoli.book.headfirst.strategy.version5.QuackBehavior;

/**
 * @author liWenHao
 * @date 2019/1/8 20:14
 */
public abstract class Duck {

    protected QuackBehavior quackBehavior;
    protected FlyBehavior flyBehavior;

    void performQuack(){
        quackBehavior.quack();
    }

    public void swim(){
        System.out.println("游泳");
    }

    public abstract void display();

    void performFly(){
        flyBehavior.fly();
    }

}
