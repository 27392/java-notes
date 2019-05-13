package cn.haohaoli.book.headfirst.factory.version4.factory;


import cn.haohaoli.book.headfirst.factory.version4.pizza.Pizza;
import cn.haohaoli.book.headfirst.factory.version4.pizza.impl.CaliforniaStyleCheesePizza;
import cn.haohaoli.book.headfirst.factory.version4.pizza.impl.CaliforniaStyleGreekPizza;
import cn.haohaoli.book.headfirst.factory.version4.pizza.impl.CaliforniaStyleVeggiePizza;

/**
 * 加州比萨工厂
 * @author LiWenHao
 * @date 2019-03-27 20:50
 */
public class CaliforniaPizzaFactory {

    public Pizza createPizza(String type) {
        Pizza pizza;
        switch (type) {
            case "cheese":
                pizza = new CaliforniaStyleCheesePizza();
                break;
            case "greek":
                pizza = new CaliforniaStyleGreekPizza();
                break;
            case "veggie":
                pizza = new CaliforniaStyleVeggiePizza();
                break;
            default:
                throw new RuntimeException("类型错误！");
        }
        return pizza;
    }
}
