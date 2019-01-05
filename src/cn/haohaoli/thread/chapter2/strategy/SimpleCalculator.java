package cn.haohaoli.thread.chapter2.strategy;

public class SimpleCalculator implements CalculatorStrategy {

    private final static double SALARY_RATE = 0.15;
    private final static double BONUS_RATE = 0.1;

    @Override
    public double calculator(double salary, double bonus) {
        return SALARY_RATE * salary + BONUS_RATE * bonus;
    }
}
