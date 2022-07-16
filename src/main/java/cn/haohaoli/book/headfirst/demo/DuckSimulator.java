package cn.haohaoli.book.headfirst.demo;

import cn.haohaoli.book.headfirst.demo.adapter.GooseAdapter;
import cn.haohaoli.book.headfirst.demo.composite.Flock;
import cn.haohaoli.book.headfirst.demo.decorator.QuackCounter;
import cn.haohaoli.book.headfirst.demo.factory.AbstractDuckFactory;
import cn.haohaoli.book.headfirst.demo.observer.Quackologist;

/**
 * @author lwh
 */
public class DuckSimulator {

    void simulate(AbstractDuckFactory duckFactory) {
        // 叫声专家
        Quackologist quackologist = new Quackologist();

        Quackable redheadDuck = duckFactory.createRedheadDuck();
        Quackable duckCall    = duckFactory.createDuckCall();
        Quackable rubberDuck  = duckFactory.createRubberDuck();
        Quackable gooseDuck  = new QuackCounter(new GooseAdapter(new Goose()));

        // 观察鹅
        gooseDuck.registerObserver(quackologist);

        // 鸭子群
        Flock flockOfDucks = new Flock();
        flockOfDucks.add(redheadDuck);
        flockOfDucks.add(duckCall);
        flockOfDucks.add(rubberDuck);

        // 野鸭群
        Flock flockOfMallards = new Flock();
        Quackable mallardDuck1 = duckFactory.createMallardDuck();
        Quackable mallardDuck2 = duckFactory.createMallardDuck();
        Quackable mallardDuck3 = duckFactory.createMallardDuck();
        Quackable mallardDuck4 = duckFactory.createMallardDuck();
        flockOfMallards.add(mallardDuck1);
        flockOfMallards.add(mallardDuck2);
        flockOfMallards.add(mallardDuck3);
        flockOfMallards.add(mallardDuck4);

        // 观察野鸭群
        flockOfMallards.registerObserver(quackologist);

        simulate(flockOfDucks);
        simulate(flockOfMallards);
        simulate(gooseDuck);

        System.out.println("叫声总数: " + QuackCounter.getNumberOfQuacks());
    }

    void simulate(Quackable quackable) {
        quackable.quack();
    }
}
