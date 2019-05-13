package cn.haohaoli.book.headfirst.template.version2;

/**
 * @author LiWenHao
 * @date 2019-05-05 20:32
 */
public class Tea extends CaffeineBeverage {

    /**
     * 泡茶
     */
    public void prepareRecipe(){
        boilWater();
        steepTeaBag();
        pourInCup();
        addLemon();
    }

    /**
     * 用沸水浸泡茶叶
     */
    public void steepTeaBag(){
        System.out.println("用沸水浸泡茶叶");
    }

    /**
     * 加柠檬
     */
    public void addLemon(){
        System.out.println("加柠檬");
    }

}
