
package com.finance.loan;

public class LoanCalculator {

    public static double calculateMonthlyPayment(Loan loan) {

        double principal = loan.getPrincipal();

        double monthlyRate = loan.getMonthlyInterestRate();

        int months = loan.getTermMonths();

        if (monthlyRate == 0) {
            return principal / months;
        }

        double factor = Math.pow(1 + monthlyRate, months);

        return principal * monthlyRate * factor / (factor - 1);
    }

    public static double calculateTotalPayment(Loan loan) {

        double monthlyPayment = calculateMonthlyPayment(loan);

        return monthlyPayment * loan.getTermMonths();
    }

    public static double calculateTotalInterest(Loan loan) {

        return calculateTotalPayment(loan) - loan.getPrincipal();
    }

    public static void printLoanSummary(Loan loan) {

        double monthlyPayment = calculateMonthlyPayment(loan);

        double totalPayment = calculateTotalPayment(loan);

        double totalInterest = calculateTotalInterest(loan);

        System.out.println();
        System.out.println("========== LOAN SUMMARY ==========");

        System.out.println(loan);

        System.out.printf(
                "Monthly payment: %.2f RON%n",
                monthlyPayment
        );

        System.out.printf(
                "Total repayment: %.2f RON%n",
                totalPayment
        );

        System.out.printf(
                "Total interest: %.2f RON%n",
                totalInterest
        );

        System.out.println("================================");
    }
}
