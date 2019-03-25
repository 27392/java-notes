package cn.haohaoli.book.headfirst.decorator.version2;

/**
 * 深焙
 * @author LiWenHao
 * @date 2019-03-11 20:08
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "深焙";
    }

    @Override
    public double cost() {
        return super.cost() + 5;
    }
}
