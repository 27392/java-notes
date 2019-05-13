package cn.haohaoli.book.headfirst.template.version2;

/**
 * @author LiWenHao
 * @date 2019-05-05 20:37
 */
public abstract class CaffeineBeverage {

    /**
     * 准备
     * 声明为final 不希望子类覆盖整个方法
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
