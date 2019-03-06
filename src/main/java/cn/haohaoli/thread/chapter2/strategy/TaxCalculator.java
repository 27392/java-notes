package cn.haohaoli.thread.chapter2.strategy;

/**
 * 税率计算
 */
public class TaxCalculator {

    private final double salary;
    private final double bonus;
    private final CalculatorStrategy calculatorStrategy;


    public TaxCalculator(double salary, double bonus, CalculatorStrategy calculatorStrategy) {
        this.salary = salary;
        this.bonus = bonus;
        this.calculatorStrategy = calculatorStrategy;
    }

    protected double calcTax() {
        return calculatorStrategy.calculator(salary, bonus);
    }

    public double calculator() {
        return calcTax();
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

}
