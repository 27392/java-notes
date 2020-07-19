package cn.haohaoli.book.java8.chapter2.homework.impl;

import cn.haohaoli.book.java8.chapter2.Apple;
import cn.haohaoli.book.java8.chapter2.homework.AppleFormatter;

/**
 * @author LiWenHao
 */
public class AppleSimpleFormatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        return apple.getWeight() + "g";
    }
}