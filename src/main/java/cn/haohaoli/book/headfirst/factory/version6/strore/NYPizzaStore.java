package cn.haohaoli.book.headfirst.factory.version6.strore;

import cn.haohaoli.book.headfirst.factory.version6.NYPizzaIngredientFactory;
import cn.haohaoli.book.headfirst.factory.version6.PizzaIngredientFactory;
import cn.haohaoli.book.headfirst.factory.version6.PizzaStore;
import cn.haohaoli.book.headfirst.factory.version6.pizza.impl.CheesePizza;
import cn.haohaoli.book.headfirst.factory.version6.pizza.impl.ClamPizza;
import cn.haohaoli.book.headfirst.factory.version6.pizza.Pizza;

/**
 * @author LiWenHao
 * @date 2019-04-28 21:33
 */
public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
        Pizza pizza;
        switch (type){
            case "cheese":
                pizza = new CheesePizza(ingredientFactory);
                pizza.setName("纽约风格奶酪披萨");
                break;
            case "clam":
                pizza = new ClamPizza(ingredientFactory);
                pizza.setName("纽约风格哈利披萨");
                break;
            default:
                throw new RuntimeException("类型错误！");

        }
        return pizza;
    }
}
