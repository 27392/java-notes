package cn.haohaoli.thread.chapter2.strategy;

public class TaxCalculatorMain {

    public static void main(String[] args) {
       /* TaxCalculator taxCalculator = new TaxCalculator(4000d, 200d){
            @Override
            public double calcTax() {
                return getSalary() * 0.1 + getBonus() * 0.1;
            }
        };
        double tax = taxCalculator.calculator();
        System.out.println(tax);*/

        /*CalculatorStrategy calculatorStrategy = new SimpleCalculator();
        TaxCalculator taxCalculator = new TaxCalculator(4000d, 200d, calculatorStrategy);
        double tax = taxCalculator.calculator();
        System.out.println(tax);*/

        TaxCalculator taxCalculator = new TaxCalculator(4000d, 200d, (s, b) -> s * 0.2 + b * 0.15);
        double tax = taxCalculator.calculator();
        System.out.println(tax);
    }
}
