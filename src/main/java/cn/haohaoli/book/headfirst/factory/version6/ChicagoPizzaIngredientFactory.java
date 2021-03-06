package cn.haohaoli.book.headfirst.factory.version6;

import cn.haohaoli.book.headfirst.factory.version6.ingredent.*;
import cn.haohaoli.book.headfirst.factory.version6.ingredent.impl.*;

/**
 * 芝加哥披萨原料厂
 * @author LiWenHao
 * @date 2019-04-28 23:23
 */
public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new MozzarellaCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[]{new Onion(), new Garlic()};
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClams() {
        return new FrozenClams();
    }
}
