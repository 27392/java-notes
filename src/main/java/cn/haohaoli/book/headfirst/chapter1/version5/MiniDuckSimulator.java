package cn.haohaoli.book.headfirst.chapter1.version5;

import cn.haohaoli.book.headfirst.chapter1.version5.duck.Duck;
import cn.haohaoli.book.headfirst.chapter1.version5.duck.MallardDuck;
import cn.haohaoli.book.headfirst.chapter1.version5.duck.ModelDuck;

/**
 * @author liWenHao
 * @date 2019/1/8 21:13
 */
public class MiniDuckSimulator {

    public static void main(String[] args) {

        Duck mallardDuck = new MallardDuck();
        mallardDuck.performFly();
        mallardDuck.performQuack();

        Duck model = new ModelDuck();
        model.performQuack();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
