package cn.haohaoli.book.headfirst.strategy.version5.impl.fly;

import cn.haohaoli.book.headfirst.strategy.version5.FlyBehavior;

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
