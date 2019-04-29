package cn.haohaoli.book.headfirst.factory.version5;


import cn.haohaoli.book.headfirst.factory.version5.pizza.Pizza;

/**
 * @author LiWenHao
 * @date 2019-03-27 21:05
 */
public abstract class PizzaStore {

    /**
     * 定披萨
     * @param type      披萨的类型
     * @return          披萨
     */
    public final Pizza orderPizza(String type){
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    };

    protected abstract Pizza createPizza(String type);
}
