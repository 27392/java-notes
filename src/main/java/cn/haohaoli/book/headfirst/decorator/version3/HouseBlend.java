package cn.haohaoli.book.headfirst.decorator.version3;

/**
 * 黑咖啡
 * @author LiWenHao
 * @date 2019-03-11 20:01
 */
public class HouseBlend extends Beverage {

    @Override
    public String getDescription() {
        return "黑咖啡";
    }

    @Override
    public double cost() {
        return 5.5;
    }
}
