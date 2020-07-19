package cn.haohaoli.book.java8.chapter2.homework;

import cn.haohaoli.book.java8.chapter2.Apple;
import cn.haohaoli.book.java8.chapter2.homework.impl.AppleFancyFormatter;
import cn.haohaoli.book.java8.chapter2.homework.impl.AppleSimpleFormatter;

import java.util.Arrays;
import java.util.List;

/**
 * @author LiWenHao
 */
public class Test {

    /*
        编写一个 prettyPrintApple 方法,它接受一个 Apple的List ,并可以对它参数化,以
        多种方式根据苹果生成一个String输出（有点儿像多个可定制的toString方法
        例如,你可以告诉 prettyPrintApple 方法, 只打印每个苹果的重量.
        此外,你可以让 prettyPrintApple 方法分别打印每个苹果,然后说明它是重的还是轻的
        解决方案和我们前面讨论的筛选的例子类似
     */
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(
                new Apple("red", 200),
                new Apple("yellow", 100),
                new Apple("blue", 250),
                new Apple("green", 155)
        );

        prettyPrintApple(apples, new AppleFancyFormatter());
        prettyPrintApple(apples, new AppleSimpleFormatter());

        // lambda
        prettyPrintApple(apples, (Apple apple) -> apple.getColor() + "::" + apple.getWeight());
        prettyPrintApple(apples, (Apple apple) -> String.format("%s \t apple is [%.2fkg]", apple.getColor(), apple.getWeight() / 1000d));

    }

    private static void prettyPrintApple(List<Apple> inventory, AppleFormatter appleFormatter) {
        for (Apple apple : inventory) {
            String output = appleFormatter.accept(apple);
            System.out.println(output);
        }
    }
}
