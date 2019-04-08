package cn.haohaoli.book.headfirst.decorator.version3.condiment;

import cn.haohaoli.book.headfirst.decorator.PropertiesHelper;
import cn.haohaoli.book.headfirst.decorator.version3.Beverage;

/**
 * @author LiWenHao
 * @date 2019-03-12 21:25
 */
public class Mocha extends CondimentDecorator {

    public Mocha(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "-加摩卡:" + PropertiesHelper.getPropertyForDouble("Mocha");
    }

    @Override
    public double cost() {
        return super.cost() + PropertiesHelper.getPropertyForDouble("Mocha");
    }
}
