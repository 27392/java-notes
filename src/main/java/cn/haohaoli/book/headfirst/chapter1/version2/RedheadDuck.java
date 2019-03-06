package cn.haohaoli.book.headfirst.chapter1.version2;

/**
 * @author liWenHao
 * @date 2019/1/8 20:16
 */
public class RedheadDuck extends Duck implements Flyable,Quackable {

    @Override
    public void display() {
        System.out.println("外观是红头");
    }

    @Override
    public void fly() {
        System.out.println("飞");
    }

    @Override
    public void quack() {
        System.out.println("呱呱呱");
    }
}
