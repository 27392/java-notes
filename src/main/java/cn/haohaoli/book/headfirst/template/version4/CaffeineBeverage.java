package cn.haohaoli.book.headfirst.template.version4;

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
        if (customerWantsCondiments()) {
            addCondiments();
        }
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
     * 酿造
     */
    abstract void brew();

    /**
     * 客户是否需要调料 (钩子方法)
     * @return
     */
    boolean customerWantsCondiments(){
        return true;
    }

    /**
     * 添加调料
     */
    abstract void addCondiments();

}
