package cn.haohaoli.book.headfirst.factory.version5;

import cn.haohaoli.book.headfirst.factory.version5.ingredent.*;

/**
 * @author LiWenHao
 * @date 2019-04-28 20:59
 */
public interface PizzaIngredientFactory {

    //创建面团
    Dough createDough();

    //创建酱汁
    Sauce createSauce();

    //创建奶酪
    Cheese createCheese();

    //创建蔬菜
    Veggies[] createVeggies();

    //创建腊肠
    Pepperoni createPepperoni();

    //创建蛤蜊
    Clams createClams();
}
