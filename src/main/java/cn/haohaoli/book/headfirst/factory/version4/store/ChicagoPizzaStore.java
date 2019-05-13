package cn.haohaoli.book.headfirst.factory.version4.store;

import cn.haohaoli.book.headfirst.factory.version4.pizza.Pizza;
import cn.haohaoli.book.headfirst.factory.version4.factory.ChicagoPizzaFactory;

/**
 * 芝加哥披萨店
 * @author LiWenHao
 * @date 2019-03-27 20:30
 */
public class ChicagoPizzaStore {

    private ChicagoPizzaFactory pizzaFactory;

    public ChicagoPizzaStore(ChicagoPizzaFactory pizzaFactory) {
        this.pizzaFactory = pizzaFactory;
    }

    /**
     * 定披萨
     * @param type      披萨的类型
     * @return          披萨
     */
    public Pizza orderPizza(String type) {
        Pizza pizza = pizzaFactory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
