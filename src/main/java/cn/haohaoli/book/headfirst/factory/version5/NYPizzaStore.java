package cn.haohaoli.book.headfirst.factory.version5;

import cn.haohaoli.book.headfirst.factory.version5.pizza.*;
import cn.haohaoli.book.headfirst.factory.version5.pizza.impl.NYStyleCheesePizza;
import cn.haohaoli.book.headfirst.factory.version5.pizza.impl.NYStyleGreekPizza;
import cn.haohaoli.book.headfirst.factory.version5.pizza.impl.NYStyleVeggiePizza;

/**
 * 纽约披萨店
 * @author LiWenHao
 * @date 2019-03-27 20:30
 */
public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza;
        switch (type) {
            case "cheese":
                pizza = new NYStyleCheesePizza();
                break;
            case "greek":
                pizza = new NYStyleGreekPizza();
                break;
            case "veggie":
                pizza = new NYStyleVeggiePizza();
                break;
            default:
                throw new RuntimeException("类型错误！");
        }
        return pizza;
    }
}
