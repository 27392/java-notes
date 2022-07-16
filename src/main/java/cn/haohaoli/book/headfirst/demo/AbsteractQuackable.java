package cn.haohaoli.book.headfirst.demo;

import cn.haohaoli.book.headfirst.demo.observer.Observable;
import cn.haohaoli.book.headfirst.demo.observer.Observer;

/**
 * 抽象叫声类
 *
 * 在接口与实现类之间增加一层抽象类, 为了避免接口后期变化影响到所有的子类
 *
 * @author lwh
 */
public abstract class AbsteractQuackable implements Quackable {

    private final Observable observable;

    public AbsteractQuackable(){
        this.observable = new Observable(this);
    }

    @Override
    public void quack() {
        this.doQuack();
        this.notifyObservers();
    }

    protected abstract void doQuack();

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }
}
