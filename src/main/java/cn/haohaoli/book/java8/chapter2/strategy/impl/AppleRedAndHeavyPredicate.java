package cn.haohaoli.book.java8.chapter2.strategy.impl;

import cn.haohaoli.book.java8.chapter2.Apple;
import cn.haohaoli.book.java8.chapter2.strategy.ApplePredicate;

/**
 * @author LiWenHao
 */
public class AppleRedAndHeavyPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return "red".equals(apple.getColor())
                && apple.getWeight() > 150;
    }
}