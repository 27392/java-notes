package cn.haohaoli.book.headfirst.decorator.version3;

/**
 * 低咖啡因
 * @author LiWenHao
 * @date 2019-03-11 20:10
 */
public class Decaf extends Beverage {

    @Override
    public String getDescription() {
        return "低咖啡因";
    }

    @Override
    public double cost() {
        return 6;
    }
}
