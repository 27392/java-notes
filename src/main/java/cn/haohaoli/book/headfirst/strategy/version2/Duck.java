package cn.haohaoli.book.headfirst.strategy.version2;

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

    public void fly(){
        System.out.println("飞");
    }
}
