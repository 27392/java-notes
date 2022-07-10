package cn.haohaoli.book.headfirst.factory.version6.pizza.impl;

import cn.haohaoli.book.headfirst.factory.version6.PizzaIngredientFactory;
import cn.haohaoli.book.headfirst.factory.version6.pizza.Pizza;

/**
 * @author LiWenHao
 * @date 2019-04-28 21:32
 */
public class ClamPizza extends Pizza {

    private final PizzaIngredientFactory ingredientFactory;

    public ClamPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        this.dough = ingredientFactory.createDough();
        this.sauce = ingredientFactory.createSauce();
        this.cheese = ingredientFactory.createCheese();
        this.clams = ingredientFactory.createClams();
        this.veggies = ingredientFactory.createVeggies();
        this.pepperoni = ingredientFactory.createPepperoni();
    }
}
