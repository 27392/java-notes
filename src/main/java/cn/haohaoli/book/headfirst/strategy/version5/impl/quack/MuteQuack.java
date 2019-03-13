package cn.haohaoli.book.headfirst.strategy.version5.impl.quack;

import cn.haohaoli.book.headfirst.strategy.version5.QuackBehavior;

/**
 * @author liWenHao
 * @date 2019/1/8 20:48
 */
public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        //什么都不做 不会叫
    }
}
