package cn.haohaoli.book.headfirst.factory.version1;

/**
 * @author LiWenHao
 * @date 2019-03-27 20:14
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(PizzaStore.orderPizza("cheese"));
        System.out.println(PizzaStore.orderPizza("greek"));
        System.out.println(PizzaStore.orderPizza("pepperoni"));
    }
}
