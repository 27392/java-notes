package cn.haohaoli.book.headfirst.demo.observer;

import cn.haohaoli.book.headfirst.demo.adapter.GooseAdapter;
import cn.haohaoli.book.headfirst.demo.decorator.QuackCounter;

/**
 * 叫声专家(观察者)
 *
 * @author lwh
 */
public class Quackologist implements Observer {

    @Override
    public void update(QuackObservable observable) {
        if (observable instanceof QuackCounter) {
            System.out.println("观察到: [" + ((QuackCounter) observable).getQuackable().getClass().getSimpleName() + "]发出叫声");
        } else if (observable instanceof GooseAdapter) {
            System.out.println("观察到: [" + ((GooseAdapter) observable).getGoose().getClass().getSimpleName() + "]发出叫声");
        } else {
            System.out.println("观察到: [" + observable.getClass().getSimpleName() + "]发出叫声");
        }
    }
}
