package cn.haohaoli.thread.chapter2.strategy;

/**
 * 计算策略接口
 */
@FunctionalInterface
public interface CalculatorStrategy {

    double calculator(double salary, double bonus);
}
