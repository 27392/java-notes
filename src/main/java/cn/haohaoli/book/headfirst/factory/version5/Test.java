package cn.haohaoli.book.headfirst.factory.version5;

import cn.haohaoli.book.headfirst.factory.version5.pizza.Pizza;

/**
 * @author LiWenHao
 * @date 2019-04-24 22:47
 */
public class Test {

    public static void main(String[] args) {
        PizzaStore pizzaStore = new NYPizzaStore();
        Pizza pizza = pizzaStore.orderPizza("cheese");

        PizzaStore pizzaStore1 = new CaliforniaPizzaStore();
        Pizza pizza1 = pizzaStore1.orderPizza("veggie");

    }
}
