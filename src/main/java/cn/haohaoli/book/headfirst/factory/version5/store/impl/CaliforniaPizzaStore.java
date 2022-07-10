package cn.haohaoli.book.headfirst.factory.version5.store.impl;

import cn.haohaoli.book.headfirst.factory.version5.pizza.*;
import cn.haohaoli.book.headfirst.factory.version5.pizza.impl.CaliforniaStyleCheesePizza;
import cn.haohaoli.book.headfirst.factory.version5.pizza.impl.CaliforniaStyleGreekPizza;
import cn.haohaoli.book.headfirst.factory.version5.pizza.impl.CaliforniaStyleVeggiePizza;
import cn.haohaoli.book.headfirst.factory.version5.store.PizzaStore;

/**
 * 加州披萨店
 * @author LiWenHao
 * @date 2019-03-27 20:30
 */
public class CaliforniaPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza;
        switch (type) {
            case "cheese":
                pizza = new CaliforniaStyleCheesePizza();
                break;
            case "greek":
                pizza = new CaliforniaStyleGreekPizza();
                break;
            case "veggie":
                pizza = new CaliforniaStyleVeggiePizza();
                break;
            default:
                throw new RuntimeException("类型错误！");
        }
        return pizza;
    }
}
