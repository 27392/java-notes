package cn.haohaoli.book.headfirst.template.version3;

/**
 * @author LiWenHao
 * @date 2019-05-05 21:20
 */
public class Tea extends CaffeineBeverage {

    @Override
    void brew() {
        System.out.println("用沸水浸泡茶叶");
    }

    @Override
    void addCondiments() {
        System.out.println("加柠檬");
    }
}
