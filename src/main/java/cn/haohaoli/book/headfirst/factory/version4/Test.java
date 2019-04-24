package cn.haohaoli.book.headfirst.factory.version4;

/**
 * @author LiWenHao
 * @date 2019-04-24 22:47
 */
public class Test {

    public static void main(String[] args) {
        PizzaStore pizzaStore = new NYPizzaStore();
        Pizza pizza = pizzaStore.orderPizza("cheese");
    }
}
