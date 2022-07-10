package cn.haohaoli.book.headfirst.factory.version6.strore;

import cn.haohaoli.book.headfirst.factory.version6.ChicagoPizzaIngredientFactory;
import cn.haohaoli.book.headfirst.factory.version6.PizzaIngredientFactory;
import cn.haohaoli.book.headfirst.factory.version6.PizzaStore;
import cn.haohaoli.book.headfirst.factory.version6.pizza.Pizza;
import cn.haohaoli.book.headfirst.factory.version6.pizza.impl.CheesePizza;
import cn.haohaoli.book.headfirst.factory.version6.pizza.impl.ClamPizza;

/**
 * @author LiWenHao
 * @date 2019-04-28 21:33
 */
public class ChicagoPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        PizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory();
        Pizza pizza;
        switch (type){
            case "cheese":
                pizza = new CheesePizza(ingredientFactory);
                pizza.setName("芝加哥风格奶酪披萨");
                break;
            case "clam":
                pizza = new ClamPizza(ingredientFactory);
                pizza.setName("芝加哥风格哈利披萨");
                break;
            default:
                throw new RuntimeException("类型错误！");

        }
        return pizza;
    }
}
