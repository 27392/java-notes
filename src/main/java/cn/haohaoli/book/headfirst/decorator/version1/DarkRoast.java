package cn.haohaoli.book.headfirst.decorator.version1;

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
        return 5;
    }
}
