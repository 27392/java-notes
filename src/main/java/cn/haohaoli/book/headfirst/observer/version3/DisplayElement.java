package cn.haohaoli.book.headfirst.observer.version3;

/**
 * @author LiWenHao
 * @date 2019-03-10 18:48
 */
public abstract class DisplayElement {

    protected float temperature;
    protected float humidity;
    protected float pressure;

    public abstract void display();
}
