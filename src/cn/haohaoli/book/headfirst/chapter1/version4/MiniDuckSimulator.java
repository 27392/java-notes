package cn.haohaoli.book.headfirst.chapter1.version4;

import cn.haohaoli.book.headfirst.chapter1.version4.duck.Duck;
import cn.haohaoli.book.headfirst.chapter1.version4.duck.MallardDuck;

/**
 * @author liWenHao
 * @date 2019/1/8 21:13
 */
public class MiniDuckSimulator {

    public static void main(String[] args) {

        Duck mallardDuck = new MallardDuck();
        mallardDuck.performFly();
        mallardDuck.performQuack();
    }
}
