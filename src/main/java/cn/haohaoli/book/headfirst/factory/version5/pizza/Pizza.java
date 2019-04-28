package cn.haohaoli.book.headfirst.factory.version5.pizza;

import cn.haohaoli.book.headfirst.factory.version5.ingredent.*;

import java.util.Arrays;

/**
 * @author LiWenHao
 * @date 2019-04-28 20:57
 */
public abstract class Pizza {

    private String name;

    //面团
    protected Dough dough;

    //酱汁
    protected Sauce sauce;

    //奶酪
    protected Cheese cheese;

    //蔬菜
    protected Veggies[] veggies;

    //腊肠
    protected Pepperoni pepperoni;

    //蛤蜊
    protected Clams clams;

    //准备
    public abstract void prepare();

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", dough=" + dough +
                ", sauce=" + sauce +
                ", cheese=" + cheese +
                ", veggies=" + Arrays.toString(veggies) +
                ", pepperoni=" + pepperoni +
                ", clams=" + clams +
                '}';
    }
}
