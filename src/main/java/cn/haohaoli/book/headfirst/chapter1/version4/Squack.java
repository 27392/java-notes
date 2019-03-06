package cn.haohaoli.book.headfirst.chapter1.version4;

/**
 * @author liWenHao
 * @date 2019/1/8 20:47
 */
public class Squack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("吱吱吱");
    }
}
