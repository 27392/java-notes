package cn.haohaoli.book.headfirst.observer.version2;

/**
 * 主题接口
 * @author LiWenHao
 * @date 2019-03-10 18:41
 */
public interface Subject {

    /**
     * 注册观察者
     * @param observer 观察者
     */
    void registerObserver(Observer observer);

    /**
     * 删除观察者
     * @param observer 观察者
     */
    void removerObserver(Observer observer);

    /**
     * 通知观察者
     */
    void notifyObservers();
}
