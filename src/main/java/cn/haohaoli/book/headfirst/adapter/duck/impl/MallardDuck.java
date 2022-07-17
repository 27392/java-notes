package cn.haohaoli.book.headfirst.adapter.duck.impl;

import cn.haohaoli.book.headfirst.adapter.duck.Duck;

/**
 * 野鸭
 * @author LiWenHao
 * @date 2019-05-27 21:58
 */
public class MallardDuck implements Duck {

    @Override
    public void quack() {
        System.out.println("嘎嘎叫");
    }

    @Override
    public void fly() {
        System.out.println("飞");
    }
}
