package cn.haohaoli.book.headfirst.chapter1.version3.duck;

import cn.haohaoli.book.headfirst.chapter1.version3.FlyBehavior;
import cn.haohaoli.book.headfirst.chapter1.version3.QuackBehavior;

/**
 * @author liWenHao
 * @date 2019/1/8 20:14
 */
public class Duck {

    QuackBehavior quackBehavior;
    FlyBehavior flyBehavior;

    void performQuack(){
        quackBehavior.quack();
    }

    public void swim(){
        System.out.println("游泳");
    }

    public void display(){
    }

    void performFly(){
        flyBehavior.fly();
    }

}
