package cn.haohaoli.book.headfirst.chapter1.version5.duck;

import cn.haohaoli.book.headfirst.chapter1.version5.FlyBehavior;
import cn.haohaoli.book.headfirst.chapter1.version5.QuackBehavior;

/**
 * @author liWenHao
 * @date 2019/1/8 20:14
 */
public abstract class Duck {

    QuackBehavior quackBehavior;
    FlyBehavior flyBehavior;

    Duck(){
    }

    public void setQuackBehavior(QuackBehavior behavior) {
        this.quackBehavior = behavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
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
