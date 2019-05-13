package cn.haohaoli.book.headfirst.template.version1;

/**
 * @author LiWenHao
 * @date 2019-05-05 20:26
 */
public class Coffee {

    /**
     * 泡咖啡
     */
    public void prepareRecipe(){
        boilWater();
        brewCoffeeGrinds();
        pourInCup();
        addSugarAbdMilk();
    }

    /**
     * 把水煮沸
     */
    public void boilWater(){
        System.out.println("把水煮沸");
    }

    /**
     * 用沸水冲泡咖啡
     */
    public void brewCoffeeGrinds(){
        System.out.println("用沸水冲泡咖啡");
    }

    /**
     * 把咖啡倒进杯子
     */
    public void pourInCup(){
        System.out.println("把咖啡倒进杯子");
    }

    /**
     * 加糖和牛奶
     */
    public void addSugarAbdMilk(){
        System.out.println("加糖和牛奶");
    }
}
