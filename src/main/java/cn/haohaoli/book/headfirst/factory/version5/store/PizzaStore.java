package cn.haohaoli.book.headfirst.factory.version5.store;

import cn.haohaoli.book.headfirst.factory.version5.pizza.Pizza;

/**
 * 创建者
 * <p>
 * 定义了一个抽象方法,让子类实现此方法制作产品
 *
 * @author LiWenHao
 * @date 2019-03-27 21:05
 */
public abstract class PizzaStore {

    /**
     * 定披萨
     *
     * @param type 披萨的类型
     * @return 披萨
     */
    public final Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    /**
     * 工厂方法,用来制造产品
     *
     * @param type
     * @return
     */
    protected abstract Pizza createPizza(String type);
}
