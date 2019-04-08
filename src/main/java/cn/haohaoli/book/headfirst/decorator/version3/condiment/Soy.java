package cn.haohaoli.book.headfirst.decorator.version3.condiment;

import cn.haohaoli.book.headfirst.decorator.PropertiesHelper;
import cn.haohaoli.book.headfirst.decorator.version3.Beverage;

/**
 * @author LiWenHao
 * @date 2019-03-12 21:26
 */
public class Soy extends CondimentDecorator {

    public Soy(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "-加豆浆" + PropertiesHelper.getPropertyForDouble("Soy");
    }

    @Override
    public double cost() {
        return super.cost() + PropertiesHelper.getPropertyForDouble("Soy");
    }
}
