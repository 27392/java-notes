package cn.haohaoli.book.headfirst.factory.version6;

import cn.haohaoli.book.headfirst.factory.version6.pizza.Pizza;
import cn.haohaoli.book.headfirst.factory.version6.strore.ChicagoPizzaStore;
import cn.haohaoli.book.headfirst.factory.version6.strore.NYPizzaStore;

/**
 * @author LiWenHao
 * @date 2019-04-28 22:58
 */
public class Test {

    public static void main(String[] args) {
        PizzaStore nyPizzaStore = new NYPizzaStore();
        Pizza cheesePizza = nyPizzaStore.orderPizza("cheese");
        System.out.println(cheesePizza);

        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
        Pizza clamPizza = chicagoPizzaStore.orderPizza("clam");
        System.out.println(clamPizza);
    }
}
