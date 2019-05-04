package cn.haohaoli.book.headfirst.factory.version4.pizza.impl;

import cn.haohaoli.book.headfirst.factory.version4.pizza.Pizza;

/**
 * 希腊披萨
 * @author LiWenHao
 * @date 2019-03-27 20:12
 */
public class NYStyleGreekPizza extends Pizza {

    public NYStyleGreekPizza() {
        name  = "纽约风格-希腊披萨";
        dough = "圆面饼";
        sauce = "番茄酱";
        toppings.add("西蓝花");
    }

}
