package cn.haohaoli.book.headfirst.decorator.version1.combination;

import cn.haohaoli.book.headfirst.decorator.PropertiesHelper;
import cn.haohaoli.book.headfirst.decorator.version1.Beverage;

/**
 * @author LiWenHao
 * @date 2019-04-08 15:55
 */
public class DecafWithSteamedMilkAndSoy extends Beverage {

    public DecafWithSteamedMilkAndSoy() {
        super("低咖啡因加牛奶加奶泡");
    }

    @Override
    public double cost() {
        return PropertiesHelper.getPropertyForDouble("Decaf")
                + PropertiesHelper.getPropertyForDouble("Milk")
                + PropertiesHelper.getPropertyForDouble("Soy");
    }
}
