package cn.haohaoli.book.headfirst.demo.observer;

/**
 * 观察者
 *
 * @author lwh
 */
public interface Observer {

    /**
     * 数据更新
     *
     * @param observable
     */
    void update(QuackObservable observable);
}
