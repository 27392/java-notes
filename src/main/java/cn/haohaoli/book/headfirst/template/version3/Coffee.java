package cn.haohaoli.book.headfirst.template.version3;

/**
 * @author LiWenHao
 * @date 2019-05-05 21:18
 */
public class Coffee extends CaffeineBeverage {

    @Override
    void brew() {
        System.out.println("用沸水冲泡咖啡");
    }

    @Override
    void addCondiments() {
        System.out.println("加糖和牛奶");
    }
}
