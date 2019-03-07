package cn.haohaoli.book.headfirst.chapter1.strategy.version6;

/**
 * @author liWenHao
 * @date 2019/1/8 20:14
 */
public abstract class Duck {

    protected QuackBehavior quackBehavior;
    protected FlyBehavior flyBehavior;

    public void setQuackBehavior(QuackBehavior behavior) {
        this.quackBehavior = behavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void performQuack(){
        quackBehavior.quack();
    }

    public void swim(){
        System.out.println("游泳");
    }

    public abstract void display();

    public void performFly(){
        flyBehavior.fly();
    }

}
