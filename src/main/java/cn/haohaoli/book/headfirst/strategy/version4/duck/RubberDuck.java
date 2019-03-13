package cn.haohaoli.book.headfirst.strategy.version4.duck;

import cn.haohaoli.book.headfirst.strategy.version4.Duck;
import cn.haohaoli.book.headfirst.strategy.version4.Quackable;

/**
 * @author liWenHao
 * @date 2019/1/8 20:20
 */
public class RubberDuck extends Duck implements Quackable {

    @Override
    public void quack() {
        System.out.println("吱吱吱");
    }

    @Override
    public void display() {
        System.out.println("外观是橡皮鸭");
    }

}
