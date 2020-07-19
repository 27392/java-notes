package cn.haohaoli.book.java8.chapter2.strategy.impl;

import cn.haohaoli.book.java8.chapter2.Apple;
import cn.haohaoli.book.java8.chapter2.strategy.ApplePredicate;

/**
 * @author LiWenHao
 */
public class AppleGreenColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}