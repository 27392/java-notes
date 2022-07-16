package cn.haohaoli.book.headfirst.demo.observer;

/**
 * 被观察者
 *
 * @author lwh
 */
public interface QuackObservable {

    /**
     * 注册观察者
     *
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 通知观察者
     */
    void notifyObservers();
}
