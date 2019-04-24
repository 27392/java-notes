package cn.haohaoli.book.headfirst.factory.version4;

import cn.haohaoli.book.headfirst.factory.version4.pizza.*;

/**
 * 芝加哥披萨店
 * @author LiWenHao
 * @date 2019-03-27 20:30
 */
public class ChicagoPizzaStore extends PizzaStore{

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
