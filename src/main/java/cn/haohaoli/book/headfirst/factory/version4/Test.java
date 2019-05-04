package cn.haohaoli.book.headfirst.factory.version4;

import cn.haohaoli.book.headfirst.factory.version4.factory.CaliforniaPizzaFactory;
import cn.haohaoli.book.headfirst.factory.version4.factory.ChicagoPizzaFactory;
import cn.haohaoli.book.headfirst.factory.version4.factory.NYPizzaFactory;
import cn.haohaoli.book.headfirst.factory.version4.pizza.Pizza;
import cn.haohaoli.book.headfirst.factory.version4.store.CaliforniaPizzaStore;
import cn.haohaoli.book.headfirst.factory.version4.store.ChicagoPizzaStore;
import cn.haohaoli.book.headfirst.factory.version4.store.ChicagoStylePizzaStore;

/**
 * @author LiWenHao
 * @date 2019-03-27 20:52
 */
public class Test {

    public static void main(String[] args) {

        /**
         * 多个简单工厂
         */

        //纽约风味披萨
        ChicagoStylePizzaStore chicagoStylePizzaStore = new ChicagoStylePizzaStore(new NYPizzaFactory());
        Pizza nyPizza = chicagoStylePizzaStore.orderPizza("cheese");

        //加州风味披萨
        CaliforniaPizzaStore californiaPizzaStore = new CaliforniaPizzaStore(new CaliforniaPizzaFactory());
        Pizza californiaPizza = californiaPizzaStore.orderPizza("greek");

        //芝加哥风味披萨
        ChicagoPizzaStore chicagoPizzaStore = new ChicagoPizzaStore(new ChicagoPizzaFactory());
        Pizza chicagoPizza = chicagoPizzaStore.orderPizza("veggie");
    }
}
