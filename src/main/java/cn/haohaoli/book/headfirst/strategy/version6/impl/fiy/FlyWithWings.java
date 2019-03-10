package cn.haohaoli.book.headfirst.chapter1.strategy.version6.impl.fiy;

import cn.haohaoli.book.headfirst.chapter1.strategy.version6.FlyBehavior;

/**
 * @author liWenHao
 * @date 2019/1/8 20:45
 */
public class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("飞");
    }
}