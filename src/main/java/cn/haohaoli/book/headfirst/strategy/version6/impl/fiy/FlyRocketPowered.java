package cn.haohaoli.book.headfirst.chapter1.strategy.version6.impl.fiy;

import cn.haohaoli.book.headfirst.chapter1.strategy.version6.FlyBehavior;

/**
 * @author liWenHao
 * @date 2019/1/8 21:34
 */
public class FlyRocketPowered implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("火箭飞行");
    }
}
