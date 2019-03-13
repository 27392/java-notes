package cn.haohaoli.book.headfirst.strategy.version6;

import cn.haohaoli.book.headfirst.strategy.version6.duck.ModelDuck;
import cn.haohaoli.book.headfirst.strategy.version6.impl.fiy.FlyRocketPowered;

/**
 * @author liWenHao
 * @date 2019/1/8 21:13
 */
public class MiniDuckSimulator {

    public static void main(String[] args) {

        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
