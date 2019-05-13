package cn.haohaoli.book.headfirst.factory.version4.factory;

import cn.haohaoli.book.headfirst.factory.version4.pizza.Pizza;
import cn.haohaoli.book.headfirst.factory.version4.pizza.impl.ChicagoStyleCheesePizza;
import cn.haohaoli.book.headfirst.factory.version4.pizza.impl.ChicagoStyleGreekPizza;
import cn.haohaoli.book.headfirst.factory.version4.pizza.impl.ChicagoStyleVeggiePizza;

/**
 * 芝加哥比萨工厂
 * @author LiWenHao
 * @date 2019-03-27 20:49
 */
public class ChicagoPizzaFactory {

    public Pizza createPizza(String type) {
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
