package cn.haohaoli.book.headfirst.demo.factory;

import cn.haohaoli.book.headfirst.demo.*;
import cn.haohaoli.book.headfirst.demo.decorator.QuackCounter;

/**
 * 鸭子工厂(带叫声统计)
 *
 * @author lwh
 */
public class CounterDuckFactory extends AbstractDuckFactory {

    @Override
    public Quackable createMallardDuck() {
        return new QuackCounter(new MallardDuck());
    }

    @Override
    public Quackable createRedheadDuck() {
        return new QuackCounter(new RedheadDuck());
    }

    @Override
    public Quackable createDuckCall() {
        return new QuackCounter(new DuckCall());
    }

    @Override
    public Quackable createRubberDuck() {
        return new QuackCounter(new RubberDuck());
    }
}
