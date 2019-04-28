package cn.haohaoli.book.headfirst.factory.version3.factory;


import cn.haohaoli.book.headfirst.factory.version3.Pizza;
import cn.haohaoli.book.headfirst.factory.version3.pizza.*;

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
