package cn.haohaoli.book.headfirst.decorator.version2;

/**
 * @author LiWenHao
 * @date 2019-03-25 20:57
 */
public class Test {

    public static void main(String[] args) {
        Beverage beverage = new DarkRoast();
        beverage.setMilk(true);
        beverage.setMocha(true);
        double cost = beverage.cost();
        System.out.println(beverage.getDescription() + " : " + cost);
    }
}
