package cn.haohaoli.book.headfirst.factory.version3;

/**
 * @author LiWenHao
 * @date 2019-03-27 20:43
 */
public class Test {

    public static void main(String[] args) {
        PizzaStore pizzaStore = new PizzaStore(new SimplePizzaFactory());
        System.out.println(pizzaStore.orderPizza("clam"));
    }
}
