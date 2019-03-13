package cn.haohaoli.book.headfirst.strategy.version3;

/**
 * @author liWenHao
 * @date 2019/1/8 20:23
 */
public class DecoyDuck extends Duck {

    @Override
    public void quack() {
        //诱饵鸭是木头假鸭 不会叫 覆盖叫的方法
    }

    @Override
    public void display() {
        System.out.println("诱饵鸭");
    }

    @Override
    public void fly() {
        //诱饵鸭也不会飞 覆盖方法什么都不做
    }
}
