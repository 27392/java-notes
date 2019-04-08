package cn.haohaoli.book.headfirst.decorator.version3.condiment;

import cn.haohaoli.book.headfirst.decorator.PropertiesHelper;
import cn.haohaoli.book.headfirst.decorator.version3.Beverage;

/**
 * @author LiWenHao
 * @date 2019-03-12 21:26
 */
public class Milk extends CondimentDecorator {

    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "-加牛奶:" + PropertiesHelper.getPropertyForDouble("Milk");
    }

    @Override
    public double cost() {
        return super.cost() + PropertiesHelper.getPropertyForDouble("Milk");
    }
}
