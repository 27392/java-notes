package cn.haohaoli.book.headfirst.decorator.version3;

/**
 * 深焙
 * @author LiWenHao
 * @date 2019-03-11 20:08
 */
public class DarkRoast extends Beverage {

    @Override
    public String getDescription() {
        return "深焙";
    }

    @Override
    public double cost() {
        return 5;
    }
}
