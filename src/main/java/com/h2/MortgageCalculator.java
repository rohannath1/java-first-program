package com.h2;

import java.text.DecimalFormat;

public class MortgageCalculator {
    
    private long loanAmount;
    private int termInYears;
    private float annualRate;
    private double monthlyPayment;

    public MortgageCalculator(long loanAmount, int termInYears, float annualRate){
        this.termInYears = termInYears;
        this.loanAmount = loanAmount;
        this.annualRate = annualRate;
    }

    private int getNumberOfPayments(){
        return 12*termInYears;
    }

    private float getMonthlyInterestRate(){
        float interestRate = annualRate / 100;
        interestRate = interestRate / 12;
        return interestRate;
    }

    public void calculateMonthlyPayment(){
        long p = loanAmount;
        float r = getMonthlyInterestRate();
        int n = getNumberOfPayments();

        double m = p * (((r * Math.pow(1 + r, n))) / ((Math.pow((1 + r), n)) - 1));
        this.monthlyPayment = m;
    }

    public String toString(){
        DecimalFormat df = new DecimalFormat("####0.00");
        return "monthlyPayment: "+df.format(monthlyPayment);
    }

    public static void main(String[] args) {
        long loanAmount = Utilities.getLongValue(args[0]);
        int termInYears = Utilities.getIntValue(args[1]);
        float annualRate = Utilities.getFloatValue(args[2]);

        MortgageCalculator calculator = new MortgageCalculator(loanAmount, termInYears, annualRate);
        calculator.calculateMonthlyPayment();
        System.out.println(calculator.toString());
    }
}
