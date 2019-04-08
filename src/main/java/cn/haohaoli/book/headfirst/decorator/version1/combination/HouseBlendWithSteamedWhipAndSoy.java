package cn.haohaoli.book.headfirst.decorator.version1.combination;

import cn.haohaoli.book.headfirst.decorator.PropertiesHelper;
import cn.haohaoli.book.headfirst.decorator.version1.Beverage;

/**
 * @author LiWenHao
 * @date 2019-04-08 15:57
 */
public class HouseBlendWithSteamedWhipAndSoy extends Beverage {

    protected HouseBlendWithSteamedWhipAndSoy() {
        super("黑咖啡加豆浆加奶泡");
    }

    @Override
    public double cost() {
        return PropertiesHelper.getPropertyForDouble("HouseBlend")
                + PropertiesHelper.getPropertyForDouble("Whip")
                + PropertiesHelper.getPropertyForDouble("Soy");
    }
}
