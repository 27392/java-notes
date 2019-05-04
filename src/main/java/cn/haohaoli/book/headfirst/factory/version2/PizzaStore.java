package cn.haohaoli.book.headfirst.factory.version2;

/**
 * @author LiWenHao
 * @date 2019-05-04 23:20
 */
public class PizzaStore {

    /**
     * 定披萨
     * @param type      披萨的类型
     * @return          披萨
     */
    public static Pizza orderPizza(String type) {
        Pizza pizza;
        switch (type) {
            case "cheese":
                pizza = new CheesePizza();
                break;
            case "pepperoni":
                pizza = new PepperoniPizza();
                break;
            case "clam":
                pizza = new ClamPizza();
                break;
            case "veggie":
                pizza = new VeggiePizza();
                break;
            default:
                throw new RuntimeException("类型错误！");
        }

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
