package cn.haohaoli.book.headfirst.decorator.version2;

/**
 * 低咖啡因
 * @author LiWenHao
 * @date 2019-03-11 20:10
 */
public class Decaf extends Beverage {

    public Decaf() {
        description = "低咖啡因";
    }

    @Override
    public double cost() {
        return super.cost() + 6;
    }
}
