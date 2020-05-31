package com.functioninterface;

/**
 * @author LiXingyu
 * @date 2020-04-21 4:25 下午
 */
public class CalculatorTax {
    double salary;
    double bonus;
    CalculatorStrategy calculatorStrategy;

    public void setCalculatorStrategy(CalculatorStrategy calculatorStrategy) {
        this.calculatorStrategy = calculatorStrategy;
    }

    public CalculatorTax(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    public double calculateTax()
    {
        return calculate();
    }

    protected double calculate() {
        return calculatorStrategy.calculate(this.salary,this.bonus);
    }

    public static void main(String[] args) {
        CalculatorTax calculatorTax = new CalculatorTax(10000.00, 2000.00);
        //下面两步如果是Spring依赖注入那么如果算法变了只需要替换实现类就行，代码不需要改变动过大
        CalculatorStrategy calculatorStrategySimple = new CalculatorStrategySimple();
        calculatorTax.setCalculatorStrategy(calculatorStrategySimple);
        System.out.println(calculatorTax.calculateTax());

    }
}
