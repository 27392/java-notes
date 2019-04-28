package cn.haohaoli.book.headfirst.factory.version4.pizza;

import cn.haohaoli.book.headfirst.factory.version4.Pizza;

/**
 * 希腊披萨
 * @author LiWenHao
 * @date 2019-03-27 20:12
 */
public class ChicagoStyleGreekPizza extends Pizza {

    public ChicagoStyleGreekPizza() {
        name = "芝加哥风格-希腊披萨";
        sauce = "番茄酱";
        dough = "发酵的圆面饼";
        toppings.add("胡萝卜");
    }

}
