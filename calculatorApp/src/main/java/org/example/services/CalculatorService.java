package org.example.services;

public class CalculatorService implements ICalculatorService {

    @Override
    public double add(double a, double b) {
        return Double.sum(a, b);
    }

    @Override
    public double subtract(double a, double b) {
        return a-b;
    }

    @Override
    public double multiply(double a, double b) {
        return a*b;
    }

    @Override
    public double divide(double a, double b) {
        if(b==0) throw new ArithmeticException("Cannot be divided by 0.");
        return a/b;
    }

    @Override
    public double squareRoot(double a) {
        return Math.sqrt(a);
    }

    @Override
    public double squared(double a) {
        return Math.pow(a,2);
    }

    @Override
    public double cubed(double a) {
        return a*a*a;
    }
}