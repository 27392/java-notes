package cn.haohaoli.book.headfirst.factory.version5.pizza.impl;

import cn.haohaoli.book.headfirst.factory.version5.pizza.Pizza;

/**
 * 素食披萨
 * @author LiWenHao
 * @date 2019-03-27 20:40
 */
public class ChicagoStyleVeggiePizza extends Pizza {

    public ChicagoStyleVeggiePizza() {
        name = "芝加哥风格-素食披萨";
        dough = "面团";
        sauce = "番茄汁";
        toppings.add("蘑菇");
    }

}
