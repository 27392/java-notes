package cn.haohaoli.book.headfirst.factory.version4.pizza.impl;

import cn.haohaoli.book.headfirst.factory.version4.pizza.Pizza;

/**
 * 素食披萨
 * @author LiWenHao
 * @date 2019-03-27 20:40
 */
public class CaliforniaStyleVeggiePizza extends Pizza {

    public CaliforniaStyleVeggiePizza() {
        name = "加州风格-素食披萨";
        dough = "面团";
        sauce = "番茄汁";
        toppings.add("青椒");
        toppings.add("蘑菇");
    }

}
