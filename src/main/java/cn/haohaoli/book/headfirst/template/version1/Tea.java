package cn.haohaoli.book.headfirst.template.version1;

/**
 * @author LiWenHao
 * @date 2019-05-05 20:32
 */
public class Tea {

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
     * 把水煮沸
     */
    public void boilWater(){
        System.out.println("把水煮沸");
    }

    /**
     * 用沸水浸泡茶叶
     */
    public void steepTeaBag(){
        System.out.println("用沸水浸泡茶叶");
    }

    /**
     * 把茶倒进杯子
     */
    public void pourInCup(){
        System.out.println("把茶倒进杯子");
    }

    /**
     * 加柠檬
     */
    public void addLemon(){
        System.out.println("加柠檬");
    }

}
