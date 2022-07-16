package cn.haohaoli.book.headfirst.demo;

import cn.haohaoli.book.headfirst.demo.factory.CounterDuckFactory;

/**
 * @author lwh
 */
public class Test {

    public static void main(String[] args) {
        DuckSimulator duckSimulator = new DuckSimulator();
        duckSimulator.simulate(new CounterDuckFactory());
    }
}
