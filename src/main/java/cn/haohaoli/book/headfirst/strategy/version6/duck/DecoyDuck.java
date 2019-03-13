package cn.haohaoli.book.headfirst.strategy.version6.duck;

import cn.haohaoli.book.headfirst.strategy.version6.Duck;

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
