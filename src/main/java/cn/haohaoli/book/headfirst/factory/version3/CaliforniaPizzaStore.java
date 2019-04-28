package cn.haohaoli.book.headfirst.factory.version3;

import cn.haohaoli.book.headfirst.factory.version3.factory.CaliforniaPizzaFactory;

/**
 * 加州披萨店
 * @author LiWenHao
 * @date 2019-03-27 20:30
 */
public class CaliforniaPizzaStore {

    private CaliforniaPizzaFactory pizzaFactory;

    public CaliforniaPizzaStore(CaliforniaPizzaFactory pizzaFactory) {
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
