package cn.haohaoli.book.headfirst.factory.version6.pizza.impl;

import cn.haohaoli.book.headfirst.factory.version6.PizzaIngredientFactory;
import cn.haohaoli.book.headfirst.factory.version6.pizza.Pizza;

/**
 * @author LiWenHao
 * @date 2019-04-28 21:32
 */
public class ClamPizza extends Pizza {

    private final PizzaIngredientFactory pizzaIngredientFactory;

    public ClamPizza(PizzaIngredientFactory pizzaIngredientFactory) {
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    @Override
    public void prepare() {
        this.dough = pizzaIngredientFactory.createDough();
        this.sauce = pizzaIngredientFactory.createSauce();
        this.cheese = pizzaIngredientFactory.createCheese();
        this.clams = pizzaIngredientFactory.createClams();
        this.veggies = pizzaIngredientFactory.createVeggies();
        this.pepperoni = pizzaIngredientFactory.createPepperoni();
    }
}
