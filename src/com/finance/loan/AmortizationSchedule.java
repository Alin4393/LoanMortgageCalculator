
package com.finance.loan;

import java.util.ArrayList;
import java.util.List;

public class AmortizationSchedule {

    public static class PaymentEntry {

        private final int month;
        private final double payment;
        private final double principal;
        private final double interest;
        private final double remainingBalance;

        public PaymentEntry(
                int month,
                double payment,
                double principal,
                double interest,
                double remainingBalance
        ) {

            this.month = month;
            this.payment = payment;
            this.principal = principal;
            this.interest = interest;
            this.remainingBalance = remainingBalance;
        }

        public int getMonth() {
            return month;
        }

        public double getPayment() {
            return payment;
        }

        public double getPrincipal() {
            return principal;
        }

        public double getInterest() {
            return interest;
        }

        public double getRemainingBalance() {
            return remainingBalance;
        }
    }

    public static List<PaymentEntry> generateSchedule(Loan loan) {

        List<PaymentEntry> schedule = new ArrayList<>();

        double balance = loan.getPrincipal();

        double monthlyRate = loan.getMonthlyInterestRate();

        double monthlyPayment =
                LoanCalculator.calculateMonthlyPayment(loan);

        for (int month = 1;
             month <= loan.getTermMonths();
             month++) {

            double interest = balance * monthlyRate;

            double principalPayment = monthlyPayment - interest;

            double payment = monthlyPayment;

            if (principalPayment > balance) {

                principalPayment = balance;

                payment = principalPayment + interest;
            }

            balance -= principalPayment;

            if (balance < 0.000001) {
                balance = 0;
            }

            PaymentEntry entry = new PaymentEntry(
                    month,
                    payment,
                    principalPayment,
                    interest,
                    balance
            );

            schedule.add(entry);

            if (balance == 0) {
                break;
            }
        }

        return schedule;
    }

    public static void printSchedule(Loan loan) {

        List<PaymentEntry> schedule = generateSchedule(loan);

        System.out.println();

        System.out.println(
                "MONTH | PAYMENT | PRINCIPAL | INTEREST | BALANCE"
        );

        System.out.println(
                "-------------------------------------------------------"
        );

        for (PaymentEntry entry : schedule) {

            System.out.printf(
                    "%5d | %7.2f | %9.2f | %8.2f | %10.2f%n",
                    entry.getMonth(),
                    entry.getPayment(),
                    entry.getPrincipal(),
                    entry.getInterest(),
                    entry.getRemainingBalance()
            );
        }

        System.out.println();

        System.out.println(
                "Total months: " + schedule.size()
        );
    }
}
