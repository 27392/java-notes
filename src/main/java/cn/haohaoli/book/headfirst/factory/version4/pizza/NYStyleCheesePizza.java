package cn.haohaoli.book.headfirst.factory.version4.pizza;

import cn.haohaoli.book.headfirst.factory.version4.Pizza;

/**
 * 芝士披萨
 * @author LiWenHao
 * @date 2019-03-27 20:11
 */
public class NYStyleCheesePizza extends Pizza {

    public NYStyleCheesePizza() {
        name = "纽约风格-芝士披萨";
        dough = "薄饼";
        sauce = "大蒜番茄酱";
        toppings.add("Reggiano 高级奶酪");
    }

}
