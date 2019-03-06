package cn.haohaoli.book.headfirst.chapter1.version4.duck;

import cn.haohaoli.book.headfirst.chapter1.version4.FlyBehavior;
import cn.haohaoli.book.headfirst.chapter1.version4.QuackBehavior;

/**
 * @author liWenHao
 * @date 2019/1/8 20:14
 */
public abstract class Duck {

    QuackBehavior quackBehavior;
    FlyBehavior flyBehavior;

    Duck(){
    }

    public void performQuack(){
        quackBehavior.quack();
    }

    public void swim(){
        System.out.println("游泳");
    }

    public abstract void display();

    public void performFly(){
        flyBehavior.fly();
    }

}
