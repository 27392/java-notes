package cn.haohaoli.book.headfirst.observer.version2;

/**
 * 主题接口
 * @author LiWenHao
 * @date 2019-03-10 18:41
 */
public interface Subject {

    void registerObserver(Observer observer);

    void removerObserver(Observer observer);

    void notifyObservers();
}
