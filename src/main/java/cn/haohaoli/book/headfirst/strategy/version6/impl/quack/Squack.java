package cn.haohaoli.book.headfirst.strategy.version6.impl.quack;

import cn.haohaoli.book.headfirst.strategy.version6.QuackBehavior;

/**
 * @author liWenHao
 * @date 2019/1/8 20:47
 */
public class Squack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("吱吱吱");
    }
}
