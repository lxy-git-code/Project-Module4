package com.functioninterface;

/**
 * @author LiXingyu
 * @date 2020-04-21 4:38 下午
 */
public class CalculatorStrategySimple implements CalculatorStrategy {

    @Override
    public double calculate(double salary, double bonus) {
        return  salary*0.1+bonus*0.15;
    }
}
