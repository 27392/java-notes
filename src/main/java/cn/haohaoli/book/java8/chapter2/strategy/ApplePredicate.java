package cn.haohaoli.book.java8.chapter2.strategy;

import cn.haohaoli.book.java8.chapter2.Apple;

/**
 * @author LiWenHao
 */
public interface ApplePredicate {
    boolean test(Apple apple);
}