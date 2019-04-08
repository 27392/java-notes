package cn.haohaoli.book.headfirst.decorator.version3;

import cn.haohaoli.book.headfirst.decorator.PropertiesHelper;

/**
 * 黑咖啡
 * @author LiWenHao
 * @date 2019-03-11 20:01
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        super("黑咖啡:" + PropertiesHelper.getPropertyForDouble("HouseBlend"));
    }

    @Override
    public double cost() {
        return PropertiesHelper.getPropertyForDouble("HouseBlend");
    }
}
