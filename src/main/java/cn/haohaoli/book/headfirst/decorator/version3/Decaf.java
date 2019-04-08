package cn.haohaoli.book.headfirst.decorator.version3;

import cn.haohaoli.book.headfirst.decorator.PropertiesHelper;

/**
 * 低咖啡因
 * @author LiWenHao
 * @date 2019-03-11 20:10
 */
public class Decaf extends Beverage {
    public Decaf() {
        super("低咖啡因:" + PropertiesHelper.getPropertyForDouble("Decaf"));
    }

    @Override
    public double cost() {
        return PropertiesHelper.getPropertyForDouble("Decaf");
    }
}
