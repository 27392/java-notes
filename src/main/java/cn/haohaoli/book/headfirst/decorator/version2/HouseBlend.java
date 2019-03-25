package cn.haohaoli.book.headfirst.decorator.version2;


/**
 * 黑咖啡
 * @author LiWenHao
 * @date 2019-03-11 20:01
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "黑咖啡";
    }

    @Override
    public double cost() {
        return super.cost() + 5.5;
    }
}
