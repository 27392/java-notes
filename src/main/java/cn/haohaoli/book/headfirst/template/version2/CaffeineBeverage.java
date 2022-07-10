package cn.haohaoli.book.headfirst.template.version2;

/**
 * 咖啡因饮料
 * @author LiWenHao
 * @date 2019-05-05 20:37
 */
public abstract class CaffeineBeverage {

    /**
     * 准备
     */
    public abstract void prepareRecipe();

    /**
     * 把水煮沸
     */
    public void boilWater() {
        System.out.println("把水煮沸");
    }

    /**
     * 倒入杯子
     */
    public void pourInCup(){
        System.out.println("倒入杯子");
    }

}
