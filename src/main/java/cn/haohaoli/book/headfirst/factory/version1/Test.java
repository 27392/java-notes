package cn.haohaoli.book.headfirst.factory.version1;

/**
 * @author LiWenHao
 * @date 2019-03-27 20:14
 */
public class Test {

    public static void main(String[] args) {
        Pizza pizza = orderPizza("cheese");
    }

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
            case "greek":
                pizza = new GreekPizza();
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
