package cn.haohaoli.book.headfirst.chapter1.strategy.version1;

/**
 * @author liWenHao
 * @date 2019/1/8 20:14
 */
public abstract class Duck {

    public void quack(){
        System.out.println("呱呱呱");
    }

    public void swim(){
        System.out.println("游泳");
    }

    public abstract void display();
}