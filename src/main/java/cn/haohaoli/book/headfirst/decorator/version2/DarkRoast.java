package cn.haohaoli.book.headfirst.decorator.version2;

import cn.haohaoli.book.headfirst.decorator.PropertiesHelper;

/**
 * 深焙
 * @author LiWenHao
 * @date 2019-03-11 20:08
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        super("深焙");
    }

    @Override
    public double cost() {
        return super.cost() + PropertiesHelper.getPropertyForDouble("DarkRoast");
    }
}
