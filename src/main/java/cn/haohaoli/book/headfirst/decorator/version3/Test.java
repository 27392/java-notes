package cn.haohaoli.book.headfirst.decorator.version3;

import cn.haohaoli.book.headfirst.decorator.version3.condiment.Milk;
import cn.haohaoli.book.headfirst.decorator.version3.condiment.Mocha;
import cn.haohaoli.book.headfirst.decorator.version3.condiment.Soy;
import cn.haohaoli.book.headfirst.decorator.version3.condiment.Whip;

/**
 * @author LiWenHao
 * @date 2019-03-12 21:27
 */
public class Test {

    public static void main(String[] args) {

        Beverage beverage = new Decaf();
        Milk milk = new Milk(new Milk(new Mocha(new Whip(new Soy(beverage)))));
        final double cost = milk.cost();
        System.out.println("描述： " + milk.getDescription());
        System.out.println("金额： " + cost);
    }
}
