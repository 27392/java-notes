package cn.haohaoli.book.headfirst.factory.version4.pizza;

import cn.haohaoli.book.headfirst.factory.version4.Pizza;

/**
 * 芝士披萨
 * @author LiWenHao
 * @date 2019-03-27 20:11
 */
public class CaliforniaStyleCheesePizza extends Pizza {

    public CaliforniaStyleCheesePizza() {
        name = "加州风格-芝士披萨";
        sauce = "沙拉酱";
        dough = "面团";
        toppings.add("芝士");
    }

}
