package cn.haohaoli.book.headfirst.chapter1.strategy.version4.duck;

import cn.haohaoli.book.headfirst.chapter1.strategy.version4.Duck;

/**
 * @author liWenHao
 * @date 2019/1/8 20:23
 */
public class DecoyDuck extends Duck {

    @Override
    public void display() {
        System.out.println("诱饵鸭");
    }

}
