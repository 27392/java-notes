package cn.haohaoli.book.headfirst.factory.version1;

import lombok.ToString;

/**
 * 披萨抽象类
 * @author LiWenHao
 * @date 2019-03-27 20:11
 */
@ToString
public abstract class Pizza {

    private final String name;

    protected  Pizza(String name) {
        this.name = name;
    }

    // 准备
    protected void prepare(){
        System.out.println("准备" + name + "所使用的材料");
    }

    // 烘焙
    protected void bake(){
        System.out.println(name + "开始烘焙");
    }

    // 切片
    protected void cut(){
        System.out.println(name + "切片");
    }

    // 装盒
    protected void box(){
        System.out.println(name + "装盒");
    }

}
