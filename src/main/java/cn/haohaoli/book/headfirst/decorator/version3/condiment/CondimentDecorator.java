package cn.haohaoli.book.headfirst.decorator.version3.condiment;

import cn.haohaoli.book.headfirst.decorator.version3.Beverage;

/**
 * 调料装饰类
 * @author LiWenHao
 * @date 2019-03-12 21:23
 */
public abstract class CondimentDecorator extends Beverage {

    protected Beverage beverage;

    CondimentDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return this.beverage.getDescription();
    }

    @Override
    public double cost() {
        return this.beverage.cost();
    }
}
