package cn.haohaoli.book.headfirst.decorator.version2;

import cn.haohaoli.book.headfirst.decorator.PropertiesHelper;

/**
 * 浓咖啡
 * @author LiWenHao
 * @date 2019-03-11 20:10
 */
public class Espresso extends Beverage {

    public Espresso() {
        super("浓咖啡");
    }

    @Override
    public double cost() {
        return super.cost() + PropertiesHelper.getPropertyForDouble("Espresso");
    }
}
