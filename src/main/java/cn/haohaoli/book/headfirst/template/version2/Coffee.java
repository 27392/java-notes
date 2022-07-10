package cn.haohaoli.book.headfirst.template.version2;

/**
 * @author LiWenHao
 * @date 2019-05-05 20:26
 */
public class Coffee extends CaffeineBeverage {

    /**
     * 泡咖啡
     */
    public void prepareRecipe() {
        boilWater();
        brewCoffeeGrinds();
        pourInCup();
        addSugarAbdMilk();
    }

    /**
     * 用沸水冲泡咖啡
     */
    public void brewCoffeeGrinds() {
        System.out.println("用沸水冲泡咖啡");
    }

    /**
     * 加糖和牛奶
     */
    public void addSugarAbdMilk() {
        System.out.println("加糖和牛奶");
    }
}
