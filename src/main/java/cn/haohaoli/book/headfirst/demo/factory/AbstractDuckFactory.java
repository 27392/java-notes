package cn.haohaoli.book.headfirst.demo.factory;

import cn.haohaoli.book.headfirst.demo.Quackable;

/**
 * 抽象工厂
 *
 * @author lwh
 */
public abstract class AbstractDuckFactory {

    public abstract Quackable createMallardDuck();

    public abstract Quackable createRedheadDuck();

    public abstract Quackable createDuckCall();

    public abstract Quackable createRubberDuck();
}
