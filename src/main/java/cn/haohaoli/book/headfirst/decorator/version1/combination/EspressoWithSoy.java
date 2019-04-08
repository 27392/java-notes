package cn.haohaoli.book.headfirst.decorator.version1.combination;

import cn.haohaoli.book.headfirst.decorator.PropertiesHelper;
import cn.haohaoli.book.headfirst.decorator.version1.Beverage;

/**
 * @author LiWenHao
 * @date 2019-04-08 15:55
 */
public class EspressoWithSoy extends Beverage {

    public EspressoWithSoy(String description) {
        super("浓咖啡加奶泡");
    }

    @Override
    public double cost() {
        return PropertiesHelper.getPropertyForDouble("Espresso")
                + PropertiesHelper.getPropertyForDouble("Soy");
    }
}
