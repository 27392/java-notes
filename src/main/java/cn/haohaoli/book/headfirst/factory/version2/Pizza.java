package cn.haohaoli.book.headfirst.factory.version2;

/**
 * @author LiWenHao
 * @date 2019-03-27 20:11
 */
public abstract class Pizza {

    public String name;

    //准备
    public void prepare(){
        System.out.println("准备" + name + "所使用的材料");
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
