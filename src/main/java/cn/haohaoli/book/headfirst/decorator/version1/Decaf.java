package cn.haohaoli.book.headfirst.decorator.version1;

/**
 * 低咖啡因
 * @author LiWenHao
 * @date 2019-03-11 20:10
 */
public class Decaf extends Beverage {

    public Decaf() {
        super("低咖啡因");
    }

    @Override
    public double cost() {
        return 6;
    }
}
