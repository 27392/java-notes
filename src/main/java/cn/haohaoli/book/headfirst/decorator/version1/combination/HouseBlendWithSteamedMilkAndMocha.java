package cn.haohaoli.book.headfirst.decorator.version1.combination;

import cn.haohaoli.book.headfirst.decorator.PropertiesHelper;
import cn.haohaoli.book.headfirst.decorator.version1.Beverage;

/**
 * @author LiWenHao
 * @date 2019-04-08 15:55
 */
public class HouseBlendWithSteamedMilkAndMocha extends Beverage {

    public HouseBlendWithSteamedMilkAndMocha(String description) {
        super("黑咖啡加牛奶加摩卡");
    }

    @Override
    public double cost() {
        return PropertiesHelper.getPropertyForDouble("HouseBlend")
                + PropertiesHelper.getPropertyForDouble("Milk")
                + PropertiesHelper.getPropertyForDouble("Mocha");
    }
}
