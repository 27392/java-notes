package cn.haohaoli.book.headfirst.factory.version5;

import cn.haohaoli.book.headfirst.factory.version5.pizza.impl.CheesePizza;
import cn.haohaoli.book.headfirst.factory.version5.pizza.impl.ClamPizza;
import cn.haohaoli.book.headfirst.factory.version5.pizza.Pizza;

/**
 * @author LiWenHao
 * @date 2019-04-28 21:33
 */
public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        PizzaIngredientFactory pizzaIngredientFactory = new NYPizzaIngredientFactory();
        Pizza pizza;
        switch (type){
            case "cheese":
                pizza = new CheesePizza(pizzaIngredientFactory);
                pizza.setName("纽约风格奶酪披萨");
                break;
            case "clam":
                pizza = new ClamPizza(pizzaIngredientFactory);
                pizza.setName("纽约风格哈利披萨");
                break;
            default:
                throw new RuntimeException("类型错误！");

        }
        return pizza;
    }
}
