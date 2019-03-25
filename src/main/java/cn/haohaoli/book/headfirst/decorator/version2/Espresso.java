package cn.haohaoli.book.headfirst.decorator.version2;

/**
 * 浓咖啡
 * @author LiWenHao
 * @date 2019-03-11 20:10
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "浓咖啡";
    }

    @Override
    public double cost() {
        return super.cost() + 4.5;
    }
}
