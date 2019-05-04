package cn.haohaoli.book.headfirst.factory.version4.pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiWenHao
 * @date 2019-03-27 20:11
 */
public abstract class Pizza {

    //名称
    protected String name;
    //面团
    protected String dough;
    //酱汁
    protected String sauce;
    //配料
    protected List<String> toppings = new ArrayList<>();

    //准备
    public void prepare(){
        System.out.println("准备" + name + "所使用的材料");
        System.out.println("搅拌" + dough);
        System.out.println("添加" + sauce);
        System.out.println("添加配料：");
        for (String topping : toppings) {
            System.out.println("  " + topping);
        }
    }

    //烘焙
    public void bake(){
        System.out.println(name + "开始烘焙");
    }

    //切片
    public void cut(){
        System.out.println(name + "切片");
    }

    //装盒
    public void box(){
        System.out.println(name + "装盒");
    }
}
