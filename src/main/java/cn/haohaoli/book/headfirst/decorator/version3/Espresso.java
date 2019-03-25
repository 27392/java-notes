package cn.haohaoli.book.headfirst.decorator.version3;

/**
 * 浓咖啡
 * @author LiWenHao
 * @date 2019-03-11 20:10
 */
public class Espresso extends Beverage {

    @Override
    public String getDescription() {
        return "浓咖啡";
    }

    @Override
    public double cost() {
        return 4.5;
    }
}
