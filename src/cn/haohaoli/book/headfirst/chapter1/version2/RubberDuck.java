package cn.haohaoli.book.headfirst.chapter1.version2;

/**
 * @author liWenHao
 * @date 2019/1/8 20:20
 */
public class RubberDuck extends Duck implements Quackable {

    @Override
    public void quack() {
        System.out.println("吱吱吱");
    }

    @Override
    public void display() {
        System.out.println("外观是橡皮鸭");
    }

}
