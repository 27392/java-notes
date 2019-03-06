package cn.haohaoli.book.headfirst.chapter1.version2;

/**
 * @author liWenHao
 * @date 2019/1/8 20:14
 */
public class MallardDuck extends Duck implements Quackable,Flyable {

    @Override
    public void display() {
        System.out.println("外观是绿头");
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
