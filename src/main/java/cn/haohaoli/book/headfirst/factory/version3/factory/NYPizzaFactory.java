package cn.haohaoli.book.headfirst.factory.version3.factory;

import cn.haohaoli.book.headfirst.factory.version3.Pizza;
import cn.haohaoli.book.headfirst.factory.version3.pizza.*;

/**
 * 纽约披萨工厂
 * @author LiWenHao
 * @date 2019-03-27 20:48
 */
public class NYPizzaFactory {

    public Pizza createPizza(String type) {
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
