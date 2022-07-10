package cn.haohaoli.book.headfirst.template.version3;

/**
 * @author LiWenHao
 * @date 2019-05-05 20:37
 */
public abstract class CaffeineBeverage {

    /**
     * 准备
     */
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

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

    /**
     * 冲泡
     */
    abstract void brew();

    /**
     * 添加调料
     */
    abstract void addCondiments();

}
