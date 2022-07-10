package cn.haohaoli.book.headfirst.factory.version5.store.impl;

import cn.haohaoli.book.headfirst.factory.version5.pizza.*;
import cn.haohaoli.book.headfirst.factory.version5.pizza.impl.ChicagoStyleCheesePizza;
import cn.haohaoli.book.headfirst.factory.version5.pizza.impl.ChicagoStyleGreekPizza;
import cn.haohaoli.book.headfirst.factory.version5.pizza.impl.ChicagoStyleVeggiePizza;
import cn.haohaoli.book.headfirst.factory.version5.store.PizzaStore;

/**
 * 芝加哥披萨店
 * @author LiWenHao
 * @date 2019-03-27 20:30
 */
public class ChicagoPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza;
        switch (type) {
            case "cheese":
                pizza = new ChicagoStyleCheesePizza();
                break;
            case "greek":
                pizza = new ChicagoStyleGreekPizza();
                break;
            case "veggie":
                pizza = new ChicagoStyleVeggiePizza();
                break;
            default:
                throw new RuntimeException("类型错误！");
        }
        return pizza;
    }
}
