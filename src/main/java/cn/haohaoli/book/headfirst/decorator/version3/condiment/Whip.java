package cn.haohaoli.book.headfirst.decorator.version3.condiment;

import cn.haohaoli.book.headfirst.decorator.PropertiesHelper;
import cn.haohaoli.book.headfirst.decorator.version3.Beverage;

/**
 * @author LiWenHao
 * @date 2019-03-12 21:26
 */
public class Whip extends CondimentDecorator {

    public Whip(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "-加奶泡:" + PropertiesHelper.getPropertyForDouble("Whip");
    }

    @Override
    public double cost() {
        return super.cost() + PropertiesHelper.getPropertyForDouble("Whip");
    }
}
