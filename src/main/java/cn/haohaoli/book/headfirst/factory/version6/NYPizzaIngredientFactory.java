package cn.haohaoli.book.headfirst.factory.version6;

import cn.haohaoli.book.headfirst.factory.version6.ingredent.*;
import cn.haohaoli.book.headfirst.factory.version6.ingredent.impl.*;

/**
 * 纽约披萨原料工厂
 * @author LiWenHao
 * @date 2019-04-28 21:08
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[]{new Garlic()};
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClams() {
        return new FreshClams();
    }
}
