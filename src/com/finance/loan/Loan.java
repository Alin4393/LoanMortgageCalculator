
package com.finance.loan;

public class Loan {

    private final double principal;
    private final double annualInterestRate;
    private final int termMonths;

    public Loan(double principal, double annualInterestRate, int termMonths) {

        if (principal <= 0) {
            throw new IllegalArgumentException(
                    "Loan amount must be greater than zero."
            );
        }

        if (annualInterestRate < 0) {
            throw new IllegalArgumentException(
                    "Interest rate cannot be negative."
            );
        }

        if (termMonths <= 0) {
            throw new IllegalArgumentException(
                    "Loan term must be greater than zero."
            );
        }

        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.termMonths = termMonths;
    }

    public double getPrincipal() {
        return principal;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public double getMonthlyInterestRate() {
        return annualInterestRate / 100.0 / 12.0;
    }

    @Override
    public String toString() {

        return String.format(
                "Loan amount: %.2f RON | Interest: %.2f%% | Term: %d months",
                principal,
                annualInterestRate,
                termMonths
        );
    }
}
