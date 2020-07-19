package cn.haohaoli.book.java8.chapter2.homework.impl;

import cn.haohaoli.book.java8.chapter2.Apple;
import cn.haohaoli.book.java8.chapter2.homework.AppleFormatter;

/**
 * @author LiWenHao
 */
public class AppleFancyFormatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "重" : "轻";
        return apple.getColor() + " 苹果是" + characteristic + "的";
    }
}

