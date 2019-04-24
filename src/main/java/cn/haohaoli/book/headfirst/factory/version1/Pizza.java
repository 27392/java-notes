package cn.haohaoli.book.headfirst.factory.version1;

/**
 * @author LiWenHao
 * @date 2019-03-27 20:11
 */
public abstract class Pizza {

    public final String name;

    public Pizza(String name) {
        this.name = name;
    }

    //准备
    void prepare(){
        System.out.println("准备" + name + "所使用的材料");
    }

    //烘焙
    void bake(){
        System.out.println(name + "开始烘焙");
    }

    //切片
    void cut(){
        System.out.println(name + "切片");
    }

    //装盒
    void box(){
        System.out.println(name + "装盒");
    }

}
