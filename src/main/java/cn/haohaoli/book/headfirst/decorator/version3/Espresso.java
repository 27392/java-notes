package cn.haohaoli.book.headfirst.decorator.version3;

import cn.haohaoli.book.headfirst.decorator.PropertiesHelper;

/**
 * 浓咖啡
 * @author LiWenHao
 * @date 2019-03-11 20:10
 */
public class Espresso extends Beverage {

    public Espresso() {
        super("浓咖啡:" + PropertiesHelper.getPropertyForDouble("Espresso"));
    }

    @Override
    public double cost() {
        return PropertiesHelper.getPropertyForDouble("Espresso");
    }
}
