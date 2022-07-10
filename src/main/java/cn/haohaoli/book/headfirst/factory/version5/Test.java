package cn.haohaoli.book.headfirst.factory.version5;

import cn.haohaoli.book.headfirst.factory.version5.pizza.Pizza;
import cn.haohaoli.book.headfirst.factory.version5.store.PizzaStore;
import cn.haohaoli.book.headfirst.factory.version5.store.impl.CaliforniaPizzaStore;
import cn.haohaoli.book.headfirst.factory.version5.store.impl.ChicagoPizzaStore;
import cn.haohaoli.book.headfirst.factory.version5.store.impl.NYPizzaStore;

/**
 * @author LiWenHao
 * @date 2019-04-24 22:47
 */
public class Test {

    public static void main(String[] args) {
        PizzaStore pizzaStore = new NYPizzaStore();
        pizzaStore.orderPizza("cheese");

        PizzaStore pizzaStore1 = new CaliforniaPizzaStore();
        pizzaStore1.orderPizza("veggie");

        PizzaStore pizzaStore2 = new ChicagoPizzaStore();
        pizzaStore2.orderPizza("cheese");

    }
}
