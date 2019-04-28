package cn.haohaoli.book.headfirst.factory.version3.pizza;

import cn.haohaoli.book.headfirst.factory.version3.Pizza;

/**
 * 芝士披萨
 * @author LiWenHao
 * @date 2019-03-27 20:11
 */
public class ChicagoStyleCheesePizza extends Pizza {

    public ChicagoStyleCheesePizza() {
        name = "芝加哥风格-芝士披萨";
        dough = "厚饼";
        sauce = "小番茄";
        toppings.add("意大利白干酪");
    }

    @Override
    protected void cut() {
        System.out.println("切成正方形");
    }
}
