package cn.haohaoli.book.headfirst.factory.version4.pizza;

import cn.haohaoli.book.headfirst.factory.version4.Pizza;

/**
 * 希腊披萨
 * @author LiWenHao
 * @date 2019-03-27 20:12
 */
public class CaliforniaStyleGreekPizza extends Pizza {

    public CaliforniaStyleGreekPizza() {
        name = "加州风格-希腊披萨";
        sauce = "番茄酱";
        dough = "发酵的圆面饼";
        toppings.add("马铃薯");
    }

}
