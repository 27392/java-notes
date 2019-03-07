package cn.haohaoli.book.headfirst.chapter1.strategy.version3;

/**
 * @author liWenHao
 * @date 2019/1/8 20:20
 */
public class RubberDuck extends Duck {

    @Override
    public void quack() {
        System.out.println("吱吱吱");
    }

    @Override
    public void display() {
        System.out.println("外观是橡皮鸭");
    }

    @Override
    public void fly() {
        //橡皮鸭 不会飞 覆盖方法什么都不做
    }
}
