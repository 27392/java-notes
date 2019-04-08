package cn.haohaoli.book.headfirst.decorator.version2;


import cn.haohaoli.book.headfirst.decorator.PropertiesHelper;

/**
 * 黑咖啡
 * @author LiWenHao
 * @date 2019-03-11 20:01
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        super("黑咖啡");
    }

    @Override
    public double cost() {
        return super.cost() + PropertiesHelper.getPropertyForDouble("HouseBlend");
    }
}
