package cn.haohaoli.book.headfirst.factory.version6;

import cn.haohaoli.book.headfirst.factory.version6.ingredent.*;

/**
 * 原料工厂
 * @author LiWenHao
 * @date 2019-04-28 20:59
 */
public interface PizzaIngredientFactory {

    // 生产面团
    Dough createDough();

    // 生产酱汁
    Sauce createSauce();

    // 生产奶酪
    Cheese createCheese();

    // 生产蔬菜
    Veggies[] createVeggies();

    // 生产腊肠
    Pepperoni createPepperoni();

    // 生产蛤蜊
    Clams createClams();
}
