package cn.haohaoli.book.headfirst.demo;

import cn.haohaoli.book.headfirst.demo.observer.QuackObservable;

/**
 * 叫声接口
 *
 * @author lwh
 */
public interface Quackable extends QuackObservable {

    /**
     * 叫
     */
    void quack();
}
