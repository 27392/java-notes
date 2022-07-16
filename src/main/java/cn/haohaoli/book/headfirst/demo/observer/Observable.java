package cn.haohaoli.book.headfirst.demo.observer;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 每一种鸭子都需要管理观察者,所以将通用的部分提取出.
 * <p>
 * 同时保持单一职责,因为鸭子并不需要负责观察相关的职责
 *
 * @author lwh
 */
@RequiredArgsConstructor
public class Observable implements QuackObservable {

    private final List<Observer> observers = new ArrayList<>();

    private final QuackObservable duck;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(duck);
        }
    }
}
