package com.h2;

import java.text.DecimalFormat;

public class MortgageCalculator {
    
    private long loanAmount;
    private int termInyears;
    private float annualRate;
    private double monthlyPayment;

    public MortgageCalculator(long loanAmount, int termInyears, float annualRate){
        this.termInyears = termInyears;
        this.loanAmount = loanAmount;
        this.annualRate = annualRate;
    }

    private int getNumberOfPayment(){
        return 12*termInyears;
    }

    private float getMonthlyInterestRate(){
        float interestRate = annualRate / 100;
        interestRate = interestRate / 12;
        return interestRate;
    }

    public void calculateMonthlyPayment(){
        long p = loanAmount;
        float r = getMonthlyInterestRate();
        int n = getNumberOfPayment();

        double m = p * (((r * Math.pow(1 + r, n))) / ((Math.pow((1 + r), n)) - 1));
        this.monthlyPayment = m;
    }

    public String toString(){
        DecimalFormat df = new DecimalFormat("####0.00");
        return "monthlyPayment: "+df.format(monthlyPayment);
    }

    public static void main(String[] args) {
        long loanAmount = Long.parseLong(args[0]);
        int termInyears = Integer.parseInt(args[1]);
        float annualRate = Float.parseFloat(args[2]);

        MortgageCalculator calculator = new MortgageCalculator(loanAmount, termInyears, annualRate);
        calculator.calculateMonthlyPayment();
        System.out.println(calculator.toString());
    }
}
