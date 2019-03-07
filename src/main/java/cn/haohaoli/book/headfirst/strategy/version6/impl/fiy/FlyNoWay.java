package cn.haohaoli.book.headfirst.chapter1.strategy.version6.impl.fiy;

import cn.haohaoli.book.headfirst.chapter1.strategy.version6.FlyBehavior;

/**
 * @author liWenHao
 * @date 2019/1/8 20:45
 */
public class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        //什么都不会做 不会飞
        System.out.println("什么都不会做 不会飞");
    }
}
