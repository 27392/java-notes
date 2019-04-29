package cn.haohaoli.book.headfirst.factory.version3.factory;

import cn.haohaoli.book.headfirst.factory.version3.Pizza;
import cn.haohaoli.book.headfirst.factory.version3.pizza.*;

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
